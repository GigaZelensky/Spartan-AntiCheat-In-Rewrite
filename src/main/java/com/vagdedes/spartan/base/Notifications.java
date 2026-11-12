package com.vagdedes.spartan.base;

import com.vagdedes.spartan.files.ConfigManagement;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class Notifications {

    private static final Set<UUID>
            verbose = new HashSet<>(),
            notifications = new HashSet<>();

    public static Collection<Player> getNotificationPlayers() {
        if (!notifications.isEmpty()) {
            Collection<Player> list = new HashSet<>(notifications.size());

            for (UUID uuid : notifications) {
                Player player = Bukkit.getPlayer(uuid);

                if (player != null
                        && player.isOnline()) {
                    list.add(player);
                }
            }
            return list;
        }
        return new ArrayList<>(0);
    }

    public static Collection<Player> getVerbosePlayers() {
        if (!verbose.isEmpty()) {
            Collection<Player> list = new HashSet<>(verbose.size());

            for (UUID uuid : verbose) {
                Player player = Bukkit.getPlayer(uuid);

                if (player != null
                        && player.isOnline()) {
                    list.add(player);
                }
            }
            return list;
        }
        return new ArrayList<>(0);
    }

    public static boolean isEnabled(Player p) {
        return notifications.contains(p.getUniqueId());
    }

    public static boolean isVerboseEnabled(Player p) {
        return verbose.contains(p.getUniqueId());
    }

    public static void setVerbose(Player p, boolean b) {
        UUID uuid = p.getUniqueId();

        if (b) {
            if (verbose.add(uuid)) {
                p.sendMessage(ConfigManagement.messages.getColorfulString("verbose_enable"));
            }
        } else if (verbose.remove(uuid)) {
            p.sendMessage(ConfigManagement.messages.getColorfulString("verbose_disable"));
        }
    }

    public static void setNotifications(Player p, boolean b) {
        UUID uuid = p.getUniqueId();

        if (b) {
            if (notifications.add(uuid)) {
                p.sendMessage(ConfigManagement.messages.getColorfulString("notification_enable"));
            }
        } else if (notifications.remove(uuid)) {
            p.sendMessage(ConfigManagement.messages.getColorfulString("notification_disable"));
        }
    }

}
