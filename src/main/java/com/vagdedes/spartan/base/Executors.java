package com.vagdedes.spartan.base;

import com.vagdedes.spartan.Register;
import com.vagdedes.spartan.support.Versions;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

class Executors {

    static void transfer(Runnable runnable) {
        if (Register.isPluginEnabled()) {
            if (!Versions.folia) {
                Bukkit.getScheduler().runTask(Register.plugin, runnable);
            } else {
                Bukkit.getGlobalRegionScheduler().run(Register.plugin, consumer -> runnable.run());
            }
        }
    }

    static void run(Player protocol, Runnable runnable, boolean sync) {
        if (!Versions.folia) {
            if (sync && !Bukkit.isPrimaryThread()) {
                if (Register.isPluginEnabled()) {
                    Bukkit.getScheduler().runTask(Register.plugin, runnable);
                }
            } else {
                runnable.run();
            }
        } else {
            if (protocol != null) {
                if (Register.isPluginEnabled()) {
                    Location location = protocol.getLocation();

                    if (location != null) {
                        Bukkit.getRegionScheduler()
                                .execute(Register.plugin,
                                        location.getWorld(),
                                        location.getBlockX() << 4,
                                        location.getBlockZ() << 4,
                                        runnable);
                    }
                }
            } else {
                if (sync) {
                    Bukkit.getGlobalRegionScheduler().run(Register.plugin, consumer -> runnable.run());
                } else {
                    runnable.run();
                }
            }
        }
    }

    static void run(World world, int x, int z, Runnable runnable, boolean sync) {
        if (!Versions.folia) {
            if (sync && !Bukkit.isPrimaryThread()) {
                if (Register.isPluginEnabled()) {
                    Bukkit.getScheduler().runTask(Register.plugin, runnable);
                }
            } else {
                runnable.run();
            }
        } else if (Register.isPluginEnabled()) {
            Bukkit.getRegionScheduler()
                    .execute(Register.plugin,
                            world, x, z,
                            runnable);
        }
    }

    static Object schedule(Player protocol, Runnable runnable, long start, long repetition) {
        if (Register.isPluginEnabled()) {
            if (!Versions.folia) {
                if (repetition == -1L) {
                    return Bukkit.getScheduler()
                            .scheduleSyncDelayedTask(Register.plugin, runnable, start);
                } else {
                    return Bukkit.getScheduler()
                            .scheduleSyncRepeatingTask(Register.plugin, runnable, start, repetition);
                }
            } else {
                if (repetition == -1L) {
                    if (protocol != null) {
                        Location location = protocol.getLocation();

                        if (location != null) {
                            return Bukkit.getRegionScheduler()
                                    .runDelayed(Register.plugin,
                                            location.getWorld(),
                                            location.getBlockX() << 4,
                                            location.getBlockZ() << 4,
                                            consumer -> runnable.run(), start);
                        } else {
                            return null;
                        }
                    } else {
                        return Bukkit.getGlobalRegionScheduler()
                                .runDelayed(Register.plugin, consumer -> runnable.run(), start);
                    }
                } else {
                    if (protocol != null) {
                        Location location = protocol.getLocation();

                        if (location != null) {
                            return Bukkit.getRegionScheduler()
                                    .runAtFixedRate(Register.plugin,
                                            location.getWorld(),
                                            location.getBlockX() << 4,
                                            location.getBlockZ() << 4,
                                            consumer -> runnable.run(), start, repetition);
                        } else {
                            return null;
                        }
                    } else {
                        return Bukkit.getGlobalRegionScheduler()
                                .runAtFixedRate(Register.plugin, consumer -> runnable.run(), start, repetition);
                    }
                }
            }
        } else {
            return null;
        }
    }

    static void cancel(Object task) {
        if (!Versions.folia) {
            if (task instanceof Integer) {
                Bukkit.getScheduler().cancelTask((int) task);
            }
        } else if (task instanceof ScheduledTask) {
            ((ScheduledTask) task).cancel();
        }
    }
}
