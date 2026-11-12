package com.vagdedes.spartan;

import com.vagdedes.spartan.functionality.CommandExecution;
import com.vagdedes.spartan.functionality.CommandTab;
import com.vagdedes.spartan.functionality.Config;
import com.vagdedes.spartan.functionality.MultiVersion;
import com.vagdedes.spartan.utils.AlgebraUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedHashSet;
import java.util.Set;

public class Register extends JavaPlugin {

    public static Plugin plugin = null;
    public static final String
            pluginName = "Spartan",
            command = "spartan",
            pluginURL = "https://vagdedes.com",
            user = "%%__USER__%%",
            resource = "%%__RESOURCE__%%";

    private static final boolean
            hasResourceId = AlgebraUtils.validInteger(resource),
            builtByBit = "%%__BUILTBYBIT__%%".length() != 18,
            spigot = !builtByBit && hasResourceId;

    private static final Set<Class<?>> listeners = new LinkedHashSet<>(2);

    public static boolean isFreeEdition() {
        return !builtByBit && !spigot;
    }

    public void onEnable() {
        plugin = this;

        if (MultiVersion.serverVersion == MultiVersion.MCVersion.OTHER) {
            Bukkit.getConsoleSender().sendMessage(
                    "[Spartan] The server's version or type is not supported. "
                            + "Please contact the plugin's developer if you think this is an error."
            );
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        Config.create();
        //enable(new EventClass());
        this.getCommand(command).setExecutor(new CommandExecution());
        this.getCommand(command).setTabCompleter(new CommandTab());
    }

    public void onDisable() {
        plugin = this;
        listeners.clear();
    }

    // Utilities

    private static void enable(Listener l) {
        if (isPluginEnabled() && listeners.add(l.getClass())) {
            Bukkit.getPluginManager().registerEvents(l, plugin);
        }
    }

    public static boolean isPluginEnabled() {
        return plugin != null && plugin.isEnabled();
    }

}
