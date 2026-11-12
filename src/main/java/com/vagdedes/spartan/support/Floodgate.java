package com.vagdedes.spartan.support;

import com.vagdedes.spartan.files.ConfigManagement;
import com.vagdedes.spartan.math.java.ReflectionUtils;
import org.bukkit.entity.Player;
import org.geysermc.floodgate.api.FloodgateApi;

public class Floodgate {

    private static boolean classExists = false;

    static {
        reload();
    }

    public static void reload() {
        classExists = ReflectionUtils.classExists(
                "org.geysermc.floodgate.api.FloodgateApi"
        );
    }

    public static boolean isBedrockPlayer(Player player) {
        return classExists
                && FloodgateApi.getInstance().isFloodgatePlayer(player.getUniqueId())

                || isBedrockPlayer(player.getName());
    }

    private static boolean isBedrockPlayer(String name) {
        String prefix = ConfigManagement.settings.getString("Important.bedrock_player_prefix");
        return !prefix.isEmpty() && name.startsWith(prefix);
    }
}
