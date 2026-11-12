package com.vagdedes.spartan.functionality.server;

import com.vagdedes.spartan.Register;
import com.vagdedes.spartan.abstraction.check.CheckEnums.CheckType;
import com.vagdedes.spartan.abstraction.configuration.ConfigurationBuilder;
import com.vagdedes.spartan.abstraction.configuration.implementation.Advanced;
import com.vagdedes.spartan.abstraction.configuration.implementation.Messages;
import com.vagdedes.spartan.abstraction.configuration.implementation.Settings;

public class Config {

    public static final Settings settings = new Settings();
    public static final Messages messages = new Messages();
    public static final Advanced advanced = new Advanced();

    public static final ConfigurationBuilder[] configurations = {
            settings,
            messages,
            advanced
    };

    public static void create() {
        boolean enabledPlugin = Register.isPluginEnabled();

        for (CheckType hackType : CheckType.values()) {
            hackType.resetCheck();
        }
        if (enabledPlugin) {
            for (ConfigurationBuilder configuration : configurations) {
                configuration.create();
            }
        } else {
            for (ConfigurationBuilder configuration : configurations) {
                configuration.clear();
            }
        }
    }

}
