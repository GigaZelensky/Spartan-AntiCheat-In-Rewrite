package com.vagdedes.spartan.configuration;

import com.vagdedes.spartan.utils.ConfigurationBuilder;
import com.vagdedes.spartan.functionality.DetectionNotifications;

import java.util.Collection;

public class Settings extends ConfigurationBuilder {

    public Settings() {
        super("settings");
    }

    @Override
    public void create() {
        addOption("Logs.log_file", true);
        addOption("Logs.log_console", true);

        addOption("Notifications.individual_only_notifications", false);
        addOption("Notifications.enable_notifications_on_login", true);

        addOption("Important.op_bypass", false);
        addOption("Important.bedrock_player_prefix", ".");

        addOption("Discord.server_name", "");
        addOption("Discord.webhook_hex_color", "4caf50");
        addOption("Discord.checks_webhook_url", "");
    }

    public void runOnLogin(PlayerProtocol p) {
        if (getBoolean("Notifications.enable_notifications_on_login")
                && DetectionNotifications.hasPermission(p)
                && !DetectionNotifications.isEnabled(p)) {
            DetectionNotifications.set(p, DetectionNotifications.defaultFrequency);
        }
    }

    public void runOnLogin() {
        Collection<PlayerProtocol> protocols = PluginBase.getProtocols();

        if (!protocols.isEmpty()) {
            for (PlayerProtocol protocol : protocols) {
                runOnLogin(protocol);
            }
        }
    }

    @Override
    public final String getString(String path) {
        return super.getString(path);
    }

    @Override
    public final String getColorfulString(String path) {
        return super.getColorfulString(path);
    }

}
