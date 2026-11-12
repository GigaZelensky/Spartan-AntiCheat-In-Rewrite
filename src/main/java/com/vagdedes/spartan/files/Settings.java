package com.vagdedes.spartan.files;

import com.vagdedes.spartan.functionality.DetectionNotifications;

import java.util.Collection;

public class Settings extends ConfigBuilder {

    public Settings() {
        super("settings");
    }

    @Override
    public void create() {
        addOption("Notifications.individual_only_notifications", false);

        addOption("Important.op_bypass_checks", false);
        addOption("Important.bedrock_player_prefix", ".");

        addOption("Discord.server_name", "");
        addOption("Discord.webhook_hex_color", "4caf50");
        addOption("Discord.checks_webhook_url", "");
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
