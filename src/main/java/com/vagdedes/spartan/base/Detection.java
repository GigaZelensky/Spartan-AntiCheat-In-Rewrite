package com.vagdedes.spartan.base;

import com.vagdedes.spartan.Register;
import com.vagdedes.spartan.files.ConfigManagement;
import com.vagdedes.spartan.math.NumberConversions;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Detection {

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

    public final String realName;
    private String name;
    private final Map<String, Object> options;
    public final boolean handleCancelledEvents;
    private boolean enabled, punish;
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
        this.realName = name;
        this.name = getOption("name", name, false).toString();
        this.enabled = getOption(
                "enabled",
                true,
                false
        ) == Boolean.TRUE;
        this.punish = getOption(
                "punish",
                true,
                false
        ) == Boolean.TRUE;

        // Separator

        if (this.name != null) {
            if (this.name.length() > 32) {
                this.name = this.name.substring(0, 32);
            }
        } else {
            this.name = name;
        }
        this.setName(this.name);

        // Separator
        String worlds_config = getOption("disabled_worlds", "exampleDisabledWorld1, exampleDisabledWorld2", false).toString(),
                silents_config = getOption("silent_worlds", "exampleSilentWorld1, exampleSilentWorld2", false).toString();

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

    public String getName() {
        return name;
    }

    // Separator

    public boolean isEnabled(String world) {
        return this.enabled
                && (world == null
                || !disabledWorlds.contains(world.toLowerCase()));
    }

    public boolean setEnabled(boolean b) {
        if (this.enabled != b) {
            this.enabled = b;
            setOption("enabled", b);
            options.clear();
            return true;
        } else {
            return false;
        }
    }

    // Separator

    public boolean setName(String name) {
        if (name.length() > 32) {
            name = name.substring(0, 32);
        }
        for (Detection detection : Detections.LIST) {
            if (detection.getName().equalsIgnoreCase(this.name)
                    || detection.realName.equalsIgnoreCase(this.name)) {
                this.name = realName;
                return false;
            }
        }
        this.name = name;
        setOption("name", name);
        return true;
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

    // Separator

    public boolean canPunish() {
        return !panic && this.punish;
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
        String sectionString = realName + ".punishments.commands";
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
                    if (NumberConversions.validInteger(key)) {
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
