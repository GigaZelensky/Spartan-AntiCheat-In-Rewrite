package com.vagdedes.spartan.functionality.tracking;

import com.vagdedes.spartan.Register;
import com.vagdedes.spartan.abstraction.protocol.PlayerProtocol;
import com.vagdedes.spartan.functionality.moderation.AwarenessNotifications;
import com.vagdedes.spartan.functionality.server.Permissions;
import com.vagdedes.spartan.functionality.server.PluginBase;

public class LimitedEdition {

    private static final int playerLimit = 5;
    public static final String message = "Detections are disabled after "
            + playerLimit + " online players on free/limited edition.";

    public static boolean canRun() {
        return !Register.isFreeEdition()
                || PluginBase.getPlayerCount() <= playerLimit;
    }

    public static void notify(PlayerProtocol protocol) {
        if (!canRun()
                && Permissions.isStaff(protocol.bukkit())) {
            AwarenessNotifications.forcefullySend(
                    protocol,
                    message,
                    true
            );
        }
    }

}
