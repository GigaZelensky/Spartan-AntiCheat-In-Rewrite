package com.vagdedes.spartan.compatibility;

import com.vagdedes.spartan.api.Permission;
import ai.idealistic.spartan.compatibility.necessary.protocollib.ProtocolLib;
import com.vagdedes.spartan.functionality.server.Config;
import com.vagdedes.spartan.functionality.server.Permissions;
import org.bukkit.entity.Player;

public class BedrockCompatibility {

    public static boolean isPlayer(Player p) {
        return ProtocolSupport.isBedrockPlayer(p)

                || Floodgate.isBedrockPlayer(
                ProtocolLib.isTemporary(p) ? null : p.getUniqueId(),
                p.getName()
        )

                || Config.settings.getBoolean("Important.bedrock_client_permission")
                && Permissions.onlyHas(p, Permission.BEDROCK);
    }

}
