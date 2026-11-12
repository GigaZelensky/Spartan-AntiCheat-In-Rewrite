package com.vagdedes.spartan.files;

import com.vagdedes.spartan.Register;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigManagement {

    public static final Settings settings = new Settings();
    public static final Messages messages = new Messages();

    public static final ConfigBuilder[] configurations = {
            settings,
            messages
    };

    public static void create() {
        boolean enabledPlugin = Register.isPluginEnabled();

        // todo reset checks
        if (enabledPlugin) {
            for (ConfigBuilder configuration : configurations) {
                configuration.create();
            }
        } else {
            for (ConfigBuilder configuration : configurations) {
                configuration.clear();
            }
        }
    }

    static void set(File file, String path, Object value) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception ignored) {
            }
        }
        final YamlConfiguration filea = YamlConfiguration.loadConfiguration(file);
        filea.set(path, value);

        try {
            filea.save(file);
        } catch (Exception ignored) {
        }
    }

    public static void add(File file, String path, Object value) {
        YamlConfiguration filea = YamlConfiguration.loadConfiguration(file);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception ignored) {
            }
        }
        if (!filea.contains(path)) {
            set(file, path, value);
        }
    }

}
