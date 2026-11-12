package com.vagdedes.spartan.support;

import com.vagdedes.spartan.Register;
import com.vagdedes.spartan.base.Detection;
import com.vagdedes.spartan.files.ConfigManagement;
import org.bukkit.entity.Player;

public class Permissions {

    public static final String
            KICK = Register.command + ".kick",
            ADMIN = Register.command + ".admin",
            NOTIFICATIONS = Register.command + ".notifications",
            VERBOSE = Register.command + ".verbose",
            BYPASS = Register.command + ".bypass",
            RELOAD = Register.command + ".reload",
            TOGGLE = Register.command + ".toggle",
            ENABLE = Register.command + ".enable",
            DISABLE = Register.command + ".disable",
            PANIC = Register.command + ".panic",
            PUNISH = Register.command + ".punish";

    public static final String[] keys = new String[]{
            KICK,
            ADMIN,
            NOTIFICATIONS,
            VERBOSE,
            BYPASS,
            RELOAD,
            TOGGLE,
            ENABLE,
            DISABLE,
            PANIC,
            PUNISH
    };

    public static boolean hasAny(Player p) {
        for (String key : keys) {
            if (p.hasPermission(key)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isBypassing(Player p, Detection detection) {
        if (p.isOp()) {
            return ConfigManagement.settings.getBoolean("Important.op_bypass");
        } else {
            if (p.hasPermission(BYPASS)) {
                return true;
            } else if (detection != null) {
                return p.hasPermission(BYPASS + "." + detection.realName);
            } else {
                return false;
            }
        }
    }

}
