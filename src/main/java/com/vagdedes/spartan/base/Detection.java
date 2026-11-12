package com.vagdedes.spartan.base;

import com.vagdedes.spartan.Register;
import com.vagdedes.spartan.files.ConfigManagement;
import com.vagdedes.spartan.math.AlgebraUtils;
import com.vagdedes.spartan.files.ConfigSyntax;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Detection {

    private static boolean panic = false;
    public static final int maxCommands = 10;
    private static final File
            file = new File(
            Register.plugin.getDataFolder() + "/detections.yml"
    );

    private static final List<String> defaultPunishments = new ArrayList<>(Detection.maxCommands);

    public static void setPanic(boolean panic) {
        Detection.panic = panic;
        ConfigManagement.create();
    }

    public static boolean getPanic() {
        return Detection.panic;
    }

    static {
        defaultPunishments.add(Register.command + " kick {player} {detection:name}");

        for (int position = (defaultPunishments.size() + 1); position <= Detection.maxCommands; position++) {
            defaultPunishments.add("");
        }
    }

    // Object

    public final DetectionEnums.DetectionType hackType;
    private String name;
    private final Map<String, Object> options;
    public final boolean handleCancelledEvents;
    private final boolean enabled, silent, punish, detectionDetails;
    private final Set<String>
            disabledWorlds,
            silentWorlds;
    private Collection<String> punishments;

    // Config values may be booleans, numbers or text, and numbers must not be cast directly as the exact type is unknown
    private static boolean toBoolean(Object value) {
        if (value instanceof Boolean) {
            return (Boolean) value;
        } else if (value instanceof Number) {
            return ((Number) value).doubleValue() > 0.0;
        } else {
            return Boolean.parseBoolean(value.toString().toLowerCase());
        }
    }

    // Object Methods

    public Detection(String name) {
        this.options = new ConcurrentHashMap<>();
        this.hackType = hackType;

        // Separator

        String name = getOption("name", this.hackType.toString(), false).toString(),
                worlds_config = getOption("disabled_worlds", "exampleDisabledWorld1, exampleDisabledWorld2", false).toString(),
                silents_config = getOption("silent_worlds", "exampleSilentWorld1, exampleSilentWorld2", false).toString();

        // Separator
        this.enabled = new boolean[DataType.values().length];
        this.silent = new boolean[DataType.values().length];
        this.punish = new boolean[DataType.values().length];
        this.detectionDetails = new boolean[DataType.values().length];

        for (Detection.DataType dataType : DataType.values()) {
            Object optionValue = getOption(
                    "enabled." + dataType.toString().toLowerCase(),
                    true,
                    false
            );
            boolean enabled = toBoolean(optionValue);
            this.enabled[dataType.ordinal()] = enabled;
            optionValue = getOption(
                    "silent." + dataType.toString().toLowerCase(),
                    false,
                    false
            );
            this.silent[dataType.ordinal()] = toBoolean(optionValue);
            optionValue = getOption(
                    "punishments.enabled." + dataType.toString().toLowerCase(),
                    true,
                    false
            );
            this.punish[dataType.ordinal()] = toBoolean(optionValue);

            optionValue = getOption(
                    "detection_details." + dataType.toString().toLowerCase(),
                    true,
                    false
            );
            this.detectionDetails[dataType.ordinal()] = toBoolean(optionValue);
        }

        // Separator

        if (name != null) {
            if (name.length() > 32) {
                name = name.substring(0, 32);
            }
            this.name = name;
        } else {
            this.name = hackType.toString();
        }

        // Separator

        this.handleCancelledEvents = handleCancelledEvents != null
                && toBoolean(handleCancelledEvents);

        // Separator

        if (worlds_config != null) {
            String[] worldsSplit = worlds_config.split(",");
            int size = worldsSplit.length;

            if (size > 0) {
                Set<String> set = new HashSet<>(size);

                for (String world : worldsSplit) {
                    world = world.replace(" ", "");

                    if (!world.isEmpty()) {
                        set.add(world.toLowerCase());
                    }
                }
                if (!set.isEmpty()) {
                    this.disabledWorlds = set;
                } else {
                    this.disabledWorlds = new HashSet<>(0);
                }
            } else {
                this.disabledWorlds = new HashSet<>(0);
            }
        } else {
            this.disabledWorlds = new HashSet<>(0);
        }

        // Separator

        if (silents_config != null) {
            String[] worldsSplit = silents_config.split(",");
            int size = worldsSplit.length;

            if (size > 0) {
                Set<String> set = new HashSet<>(size);

                for (String world : worldsSplit) {
                    world = world.replace(" ", "");

                    if (!world.isEmpty()) {
                        set.add(world.toLowerCase());
                    }
                }
                if (!set.isEmpty()) {
                    this.silentWorlds = set;
                } else {
                    this.silentWorlds = new HashSet<>(0);
                }
            } else {
                this.silentWorlds = new HashSet<>(0);
            }
        } else {
            this.silentWorlds = new HashSet<>(0);
        }

        // Separator

        calculatePunishmentCommands();
    }

    // Separator

    public boolean isEnabled(Detection.DataType dataType, String world) {
        if (dataType == null) {
            boolean enabled = false;

            for (Detection.DataType type : DataType.values()) {
                if (this.enabled[type.ordinal()]) {
                    enabled = true;
                    break;
                }
            }
            if (!enabled) {
                return false;
            }
        } else if (!this.enabled[dataType.ordinal()]) {
            return false;
        }
        return world == null
                || !disabledWorlds.contains(world.toLowerCase());
    }

    public void setEnabled(Detection.DataType dataType, boolean b) {
        Detection.DataType[] dataTypes;

        if (dataType == null) {
            dataTypes = DataType.values();
        } else {
            dataTypes = null;

            for (Detection.DataType type : DataType.values()) {
                if (type == dataType) {
                    dataTypes = new Detection.DataType[]{dataType};
                    break;
                }
            }

            if (dataTypes == null) {
                return;
            }
        }
        for (Detection.DataType type : dataTypes) {
            CheckToggleEvent event;

            if (ConfigManagement.settings.getBoolean("Important.enable_developer_api")) {
                event = new CheckToggleEvent(this.hackType, b ? ToggleAction.ENABLE : ToggleAction.DISABLE);
                Bukkit.getPluginManager().callEvent(event);
            } else {
                event = null;
            }

            if (event == null || !event.isCancelled()) {
                this.enabled[type.ordinal()] = b;
                ResearchEngine.queueToCache(this.hackType, type);
                setOption("enabled." + type.toString().toLowerCase(), b);
                options.clear();
            }
        }
    }

    // Separator

    public void setName(String name) {
        if (name.length() > 32) {
            name = name.substring(0, 32);
        }
        for (DetectionEnums.DetectionType hackType : DetectionEnums.DetectionType.values()) {
            if (!hackType.equals(this.hackType)
                    && (name.equalsIgnoreCase(hackType.toString()))
                    || name.equalsIgnoreCase(hackType.getCheck().getName())) {
                return;
            }
        }
        this.name = name;
        setOption("name", name);
    }

    // Separator

    private void setOption(String option, Object value) {
        try {
            String key = this.hackType + "." + option;
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            configuration.set(key, value);

            try {
                configuration.save(file);
                options.remove(key); // Remove instead of modifying to be on demand and have the chance to catch changes by the user
            } catch (Exception ex) {
                AwarenessNotifications.forcefullySend("Failed to store '" + key + "' option in '" + file.getName() + "' file.");
                ex.printStackTrace();
            }
            return;
        } catch (Exception ex) {
            AwarenessNotifications.forcefullySend("Failed to find/create the '" + file.getName() + "' file.");
            ex.printStackTrace();
        }
        AwarenessNotifications.forcefullySend("Failed to find/create the '" + file.getName() + "' file.");
    }

    private Object getOption(String option, Object def, boolean cache) {
        if (cache) {
            Object cached = options.get(option);

            if (cached != null) {
                return cached;
            }
        }
        try {
            String key = this.hackType + "." + option;
            boolean isDefaultNull = def == null;
            File file = cache ? advancedFile : Detection.file;
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

            if (cache) {
                if (configuration.contains(key)) {
                    Object value = configuration.get(key, def);

                    if (!isDefaultNull) {
                        options.put(option, value);
                    }
                    return value;
                }
                if (!isDefaultNull) {
                    configuration.set(key, def);

                    try {
                        configuration.save(file);
                        options.put(option, def);
                    } catch (Exception ex) {
                        AwarenessNotifications.forcefullySend("Failed to store '" + key + "' option in '" + file.getName() + "' file.");
                        ex.printStackTrace();
                    }
                }
            } else {
                if (configuration.contains(key)) {
                    return configuration.get(key, def);
                } else if (!isDefaultNull) {
                    configuration.set(key, def);

                    try {
                        configuration.save(file);
                    } catch (Exception ex) {
                        AwarenessNotifications.forcefullySend("Failed to store '" + key + "' option in '" + file.getName() + "' file.");
                        ex.printStackTrace();
                    }
                }
            }
        } catch (Exception ex) {
            AwarenessNotifications.forcefullySend("Failed to find/create the '" + file.getName() + "' file.");
            ex.printStackTrace();
        }
        return def;
    }

    public boolean getBooleanOption(String option, Boolean def) {
        Object object = getOption(option, def, true);
        return object instanceof Boolean ? (boolean) object :
                object instanceof String ? Boolean.parseBoolean(object.toString().toLowerCase()) :
                        object instanceof Number ? ((Number) object).doubleValue() > 0.0 :
                                def != null && def;
    }

    public int getNumericalOption(String option, int def) {
        Object object = getOption(option, def, true);

        if (object instanceof Integer || object instanceof Short) {
            return ((Number) object).intValue();
        } else if (object instanceof String || object instanceof Long) {
            try {
                return Integer.parseInt(object.toString());
            } catch (Exception ex) {
                return def;
            }
        } else if (object instanceof Double || object instanceof Float) {
            try {
                return AlgebraUtils.integerRound(Double.parseDouble(object.toString()));
            } catch (Exception ex) {
                return def;
            }
        } else {
            return def;
        }
    }

    public double getDecimalOption(String option, double def) {
        Object object = getOption(option, def, true);

        if (object instanceof Double || object instanceof Float) {
            return ((Number) object).doubleValue();
        } else if (object instanceof Long) {
            return ((Long) object).doubleValue();
        } else if (object instanceof Integer) {
            return ((Integer) object).doubleValue();
        } else if (object instanceof Short) {
            return ((Short) object).doubleValue();
        } else if (object instanceof String) {
            try {
                return Double.parseDouble(object.toString());
            } catch (Exception ex) {
                return def;
            }
        } else {
            return def;
        }
    }

    // Separator

    public boolean isDetectionDetails(Detection.DataType dataType) {
        if (dataType == null) {
            for (Detection.DataType type : DataType.values()) {
                if (this.detectionDetails[type.ordinal()]) {
                    return true;
                }
            }
            return false;
        } else {
            return this.detectionDetails[dataType.ordinal()];
        }
    }

    public void setDetectionDetails(Detection.DataType dataType, boolean b) {
        Detection.DataType[] dataTypes;

        if (dataType == null) {
            dataTypes = DataType.values();
        } else {
            dataTypes = new Detection.DataType[]{dataType};
        }
        for (Detection.DataType type : dataTypes) {
            this.detectionDetails[type.ordinal()] = b;
            setOption("detection_details." + type.toString().toLowerCase(), b);
            options.clear();
        }
    }

    // Separator

    public boolean canPunish(Detection.DataType dataType) {
        if (panic) {
            return false;
        }
        if (dataType == null) {
            for (Detection.DataType type : DataType.values()) {
                if (this.punish[type.ordinal()]) {
                    return true;
                }
            }
            return false;
        } else {
            return this.punish[dataType.ordinal()];
        }
    }

    // Separator

    public boolean isSilent(Detection.DataType dataType, String world) {
        if (panic
                || world != null && silentWorlds.contains(world.toLowerCase())) {
            return true;
        }
        if (dataType == null) {
            for (Detection.DataType type : DataType.values()) {
                if (this.silent[type.ordinal()]) {
                    return true;
                }
            }
            return false;
        } else return this.silent[dataType.ordinal()];
    }

    public void setSilent(Detection.DataType dataType, boolean b) {
        Detection.DataType[] dataTypes;

        if (dataType == null) {
            dataTypes = DataType.values();
        } else {
            dataTypes = null;

            for (Detection.DataType type : DataType.values()) {
                if (type == dataType) {
                    dataTypes = new Detection.DataType[]{dataType};
                    break;
                }
            }

            if (dataTypes == null) {
                return;
            }
        }
        for (Detection.DataType type : dataTypes) {
            CheckSilentToggleEvent event;

            if (ConfigManagement.settings.getBoolean("Important.enable_developer_api")) {
                event = new CheckSilentToggleEvent(this.hackType, b ? ToggleAction.ENABLE : ToggleAction.DISABLE);
                Bukkit.getPluginManager().callEvent(event);
            } else {
                event = null;
            }

            if (event == null || !event.isCancelled()) {
                this.silent[type.ordinal()] = b;
                setOption("silent." + type.toString().toLowerCase(), b);
                options.clear();
            }
        }
    }

    public List<String> getPunishmentCommands() {
        return new ArrayList<>(this.punishments);
    }

    private void calculatePunishmentCommands() {
        String sectionString = hackType + ".punishments.commands";
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        if (!configuration.contains(sectionString)) {
            for (int position = 0; position < defaultPunishments.size(); position++) {
                ConfigManagement.add(file, sectionString + "." + (position + 1), defaultPunishments.get(position));
            }
        }
        ConfigurationSection section = configuration.getConfigurationSection(sectionString);

        if (section != null) {
            Collection<String> list = new ArrayList<>();
            Set<String> keys = section.getKeys(true);

            if (!keys.isEmpty()) {
                sectionString += ".";

                for (String key : keys) {
                    if (AlgebraUtils.validInteger(key)) {
                        int number = Integer.parseInt(key);

                        if (number >= 1 && number <= Detection.maxCommands) {
                            String command = configuration.getString(sectionString + number);

                            if (command != null && !command.isEmpty()) {
                                list.add(command);
                            }
                        }
                    }
                }
            }
            this.punishments = list;
        } else {
            this.punishments = new ArrayList<>(0);
        }
    }

    public void setPunishmentCommand(int position, String command) {
        if (position < 1 || position > Detection.maxCommands) {
            return;
        }
        if (command == null) {
            command = "";
        }
        String sectionString = this.hackType + ".punishments.commands." + position;
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.set(sectionString, command);

        try {
            configuration.save(file);
            calculatePunishmentCommands();
        } catch (Exception ex) {
            AwarenessNotifications.forcefullySend("Failed to store '" + sectionString + "' option in '" + file.getName() + "' file.");
            ex.printStackTrace();
        }
    }

    public void setPunish(Detection.DataType dataType, boolean b) {
        Detection.DataType[] dataTypes;

        if (dataType == null) {
            dataTypes = DataType.values();
        } else {
            dataTypes = new Detection.DataType[]{dataType};
        }
        for (Detection.DataType type : dataTypes) {
            CheckPunishmentToggleEvent event;

            if (ConfigManagement.settings.getBoolean("Important.enable_developer_api")) {
                event = new CheckPunishmentToggleEvent(this.hackType, b ? ToggleAction.ENABLE : ToggleAction.DISABLE);
                Bukkit.getPluginManager().callEvent(event);
            } else {
                event = null;
            }

            if (event == null || !event.isCancelled()) {
                this.punish[type.ordinal()] = b;
                setOption("punishments.enabled." + type.toString().toLowerCase(), b);
                options.clear();
            }
        }
    }

}
