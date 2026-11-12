package com.vagdedes.spartan.functionality;

import com.vagdedes.spartan.Register;
import com.vagdedes.spartan.base.CheckEnums.CheckType;
import com.vagdedes.spartan.utils.ConfigurationBuilder;
import com.vagdedes.spartan.configuration.Messages;
import com.vagdedes.spartan.configuration.Settings;

public class Config {

    public static final Settings settings = new Settings();
    public static final Messages messages = new Messages();

    public static final ConfigurationBuilder[] configurations = {
            settings,
            messages
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
