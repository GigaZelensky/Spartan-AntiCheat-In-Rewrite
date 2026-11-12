package com.vagdedes.spartan.files;

import com.vagdedes.spartan.Register;
import com.vagdedes.spartan.base.Detection;
import com.vagdedes.spartan.abstraction.protocol.PlayerProtocol;
import com.vagdedes.spartan.api.SpartanAPI;
import com.vagdedes.spartan.support.Floodgate;
import com.vagdedes.spartan.support.Versions;
import com.vagdedes.spartan.math.AlgebraUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class ConfigSyntax {

    public static String replace(String message, String target, String replacement) {
        return message.replace(target, replacement);
    }

    public static String replaceWithSyntax(String message, DetectionType hackType) {
        message = replace(message, "{space}", " ");
        message = replace(message, "{motd}", Bukkit.getMotd());
        message = replace(message, "{server:name}", ConfigManagement.settings.getString("Discord.server_name"));
        message = replace(message, "{plugin:version}", Register.getVersion());
        message = replace(message, "{server:version}", Versions.serverVersion.toString());
        message = replace(message, "{line}", "\n");

        LocalDateTime now = LocalDateTime.now();
        message = replace(message, "{date:time}", DateTimeFormatter.ofPattern("HH:mm:ss").format(now));
        message = replace(message, "{date:d-m-y}", DateTimeFormatter.ofPattern("dd/MM/yyyy").format(now));
        message = replace(message, "{date:m-d-y}", DateTimeFormatter.ofPattern("MM/dd/yyyy").format(now));
        message = replace(message, "{date:y-m-d}", DateTimeFormatter.ofPattern("yyyy/MM/dd").format(now));

        if (hackType != null) {
            Detection check = hackType.getCheck();
            message = replace(message, "{detection}", check.getName());
            message = replace(message, "{detection:real}", hackType.toString());
        }
        return message;
    }

    public static String replaceWithSyntax(Player player, String message, DetectionType hackType) {
        Location loc = player.getLocation();
        String worldName = loc.getWorld().getName();
        message = replace(message, "{player}", player.getName());
        message = replace(message, "{player:type}", Floodgate.isBedrockPlayer(player) ? "bedrock" : "java");
        message = replace(message, "{uuid}", player.getUniqueId().toString());
        message = replace(message, "{ping}", String.valueOf(player.getPing()));
        message = replace(message, "{world}", worldName);
        message = replace(message, "{health}", String.valueOf(player.getHealth()));
        message = replace(message, "{gamemode}", player.getGameMode().toString().toLowerCase());
        message = replace(message, "{x}", String.valueOf(loc.getBlockX()));
        message = replace(message, "{y}", String.valueOf(loc.getBlockY()));
        message = replace(message, "{z}", String.valueOf(loc.getBlockZ()));
        message = replace(message, "{yaw}", String.valueOf(AlgebraUtils.integerRound(loc.getYaw())));
        message = replace(message, "{pitch}", String.valueOf(AlgebraUtils.integerRound(loc.getPitch())));

        if (hackType != null) {
            message = replace(message, "{detection:punish}", String.valueOf(hackType.getCheck().canPunish(player.getDataType())));
        }
        return ChatColor.translateAlternateColorCodes('&', replaceWithSyntax(message, hackType));
    }

}
