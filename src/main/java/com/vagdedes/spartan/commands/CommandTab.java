package com.vagdedes.spartan.commands;

import com.vagdedes.spartan.abstraction.protocol.PlayerProtocol;
import com.vagdedes.spartan.api.Permission;
import com.vagdedes.spartan.support.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;

public class CommandTab implements TabCompleter {

    private static final Map<String, String> commands = new LinkedHashMap<>(18); // Attention

    static {
        commands.put("command", "perm"); // todo
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String arg, String[] args) {
        List<String> list = new ArrayList<>();
        int length = args.length;

        if (length == 1) {
            boolean isPlayer = sender instanceof Player;
            Player p = isPlayer ? (Player) sender : null;
            String argAbstract = args[0].toLowerCase();

            for (Map.Entry<String, String> entry : commands.entrySet()) {
                boolean add;

                if (!isPlayer) {
                    add = true;
                } else {
                    add = false;

                    if (entry.getValue().length > 0) {
                        for (Permission permission : entry.getValue()) {
                            if (Permissions.has(p, permission)) {
                                add = true;
                                break;
                            }
                        }
                    } else {
                        add = Permissions.has(p, Permission.ADMIN);
                    }
                }

                if (add) {
                    String key = entry.getKey();

                    if (key.contains(argAbstract)) {
                        list.add(key);
                    }
                }
            }
        } else if (length > 1) {
            Collection<PlayerProtocol> protocols = PluginBase.getProtocols();

            if (!protocols.isEmpty()) {
                String argAbstract = args[length - 1].toLowerCase();
                boolean player = sender instanceof Player;
                PlayerProtocol p = player ? PluginBase.getProtocol((Player) sender) : null;
                player &= p != null;

                for (PlayerProtocol protocol : protocols) {
                    if (!player || p.bukkit().canSee(protocol.bukkit())) {
                        String name = protocol.bukkit().getName();

                        if (name.toLowerCase().contains(argAbstract)) {
                            list.add(name);
                        }
                    }
                }
            }
        }
        return list;
    }
}
