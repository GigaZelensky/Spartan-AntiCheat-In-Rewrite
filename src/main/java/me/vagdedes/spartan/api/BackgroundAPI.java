package me.vagdedes.spartan.api;

import me.vagdedes.spartan.system.Enums.HackType;
import me.vagdedes.spartan.system.Enums.Permission;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BackgroundAPI {

    static String licenseID() {
        return com.vagdedes.spartan.api.BackgroundAPI.licenseID();
    }

    static String getVersion() {
        return com.vagdedes.spartan.api.BackgroundAPI.getVersion();
    }

    static String getMessage(String path) {
        return com.vagdedes.spartan.api.BackgroundAPI.getMessage(path);
    }

    static boolean getSetting(String path) {
        return com.vagdedes.spartan.api.BackgroundAPI.getSetting(path);
    }

    @Deprecated
    static String getCategory(Player p, HackType hackType) {
        return com.vagdedes.spartan.api.BackgroundAPI.getCategory(p, hackType.getHackType());
    }

    static boolean hasVerboseEnabled(Player p) {
        return com.vagdedes.spartan.api.BackgroundAPI.hasVerboseEnabled(p);
    }

    static boolean hasNotificationsEnabled(Player p) {
        return com.vagdedes.spartan.api.BackgroundAPI.hasNotificationsEnabled(p);
    }

    @Deprecated
    static int getViolationResetTime() {
        return com.vagdedes.spartan.api.BackgroundAPI.getViolationResetTime();
    }

    static void setVerbose(Player p, boolean value) {
        com.vagdedes.spartan.api.BackgroundAPI.setVerbose(p, value);
    }

    static void setNotifications(Player p, boolean value) {
        com.vagdedes.spartan.api.BackgroundAPI.setNotifications(p, value);
    }

    @Deprecated
    static void setVerbose(Player p, boolean value, int frequency) {
        com.vagdedes.spartan.api.BackgroundAPI.setVerbose(p, value, frequency);
    }

    static void setNotifications(Player p, int frequency) {
        com.vagdedes.spartan.api.BackgroundAPI.setNotifications(p, frequency);
    }

    static int getPing(Player p) {
        return com.vagdedes.spartan.api.BackgroundAPI.getPing(p);
    }

    @Deprecated
    static double getTPS() {
        return com.vagdedes.spartan.api.BackgroundAPI.getTPS();
    }

    static boolean hasPermission(Player p, Permission Permission) {
        return com.vagdedes.spartan.api.BackgroundAPI.hasPermission(p, Permission.getPermission());
    }

    static boolean isEnabled(HackType HackType) {
        return com.vagdedes.spartan.api.BackgroundAPI.isEnabled(HackType.getHackType());
    }

    static boolean isSilent(HackType HackType) {
        return com.vagdedes.spartan.api.BackgroundAPI.isSilent(HackType.getHackType());
    }

    static int getVL(Player p, HackType hackType) {
        return com.vagdedes.spartan.api.BackgroundAPI.getVL(p, hackType.getHackType());
    }

    static double getCertainty(Player p, HackType hackType) {
        return com.vagdedes.spartan.api.BackgroundAPI.getCertainty(p, hackType.getHackType());
    }

    static double getDecimalVL(Player p, HackType hackType) {
        return com.vagdedes.spartan.api.BackgroundAPI.getDecimalVL(p, hackType.getHackType());
    }

    static int getVL(Player p) {
        return com.vagdedes.spartan.api.BackgroundAPI.getVL(p);
    }

    static double getDecimalVL(Player p) {
        return com.vagdedes.spartan.api.BackgroundAPI.getDecimalVL(p);
    }

    @Deprecated
    static void setVL(Player p, HackType HackType, int amount) {
        com.vagdedes.spartan.api.BackgroundAPI.setVL(p, HackType.getHackType(), amount);
    }

    @Deprecated
    static int getCancelViolation(HackType hackType, String worldName) {
        return com.vagdedes.spartan.api.BackgroundAPI.getCancelViolation(hackType.getHackType(), worldName);
    }

    @Deprecated
    static int getCancelViolation(HackType hackType) {
        return com.vagdedes.spartan.api.BackgroundAPI.getCancelViolation(hackType.getHackType());
    }

    @Deprecated
    static int getViolationDivisor(Player p, HackType hackType) {
        return com.vagdedes.spartan.api.BackgroundAPI.getViolationDivisor(p, hackType.getHackType());
    }

    static void reloadConfig() {
        com.vagdedes.spartan.api.BackgroundAPI.reloadConfig();
    }

    @Deprecated
    static void reloadPermissions() {
        com.vagdedes.spartan.api.BackgroundAPI.reloadPermissions();
    }

    @Deprecated
    static void reloadPermissions(Player p) {
        com.vagdedes.spartan.api.BackgroundAPI.reloadPermissions(p);
    }

    static void enableCheck(HackType HackType) {
        com.vagdedes.spartan.api.BackgroundAPI.enableCheck(HackType.getHackType());
    }

    static void disableCheck(HackType HackType) {
        com.vagdedes.spartan.api.BackgroundAPI.disableCheck(HackType.getHackType());
    }

    static void cancelCheck(Player p, HackType hackType, int ticks) {
        com.vagdedes.spartan.api.BackgroundAPI.cancelCheck(p, hackType.getHackType(), ticks);
    }

    static void cancelCheckPerVerbose(Player p, String string, int ticks) {
        com.vagdedes.spartan.api.BackgroundAPI.cancelCheckPerVerbose(p, string, ticks);
    }

    static void enableSilentChecking(HackType HackType) {
        com.vagdedes.spartan.api.BackgroundAPI.enableSilentChecking(HackType.getHackType());
    }

    static void disableSilentChecking(HackType HackType) {
        com.vagdedes.spartan.api.BackgroundAPI.disableSilentChecking(HackType.getHackType());
    }

    static void enableSilentChecking(Player p, HackType hackType) {
        com.vagdedes.spartan.api.BackgroundAPI.enableSilentChecking(p, hackType.getHackType());
    }

    static void disableSilentChecking(Player p, HackType hackType) {
        com.vagdedes.spartan.api.BackgroundAPI.disableSilentChecking(p, hackType.getHackType());
    }

    static void startCheck(Player p, HackType hackType) {
        com.vagdedes.spartan.api.BackgroundAPI.startCheck(p, hackType.getHackType());
    }

    static void stopCheck(Player p, HackType hackType) {
        com.vagdedes.spartan.api.BackgroundAPI.stopCheck(p, hackType.getHackType());
    }

    static void resetVL() {
        com.vagdedes.spartan.api.BackgroundAPI.resetVL();
    }

    static void resetVL(Player p) {
        com.vagdedes.spartan.api.BackgroundAPI.resetVL(p);
    }

    static boolean isBypassing(Player p) {
        return com.vagdedes.spartan.api.BackgroundAPI.isBypassing(p);
    }

    static boolean isBypassing(Player p, HackType HackType) {
        return com.vagdedes.spartan.api.BackgroundAPI.isBypassing(p, HackType.getHackType());
    }

    @Deprecated
    static void banPlayer(UUID uuid, String reason) {
        com.vagdedes.spartan.api.BackgroundAPI.banPlayer(uuid, reason);
    }

    @Deprecated
    static boolean isBanned(UUID uuid) {
        return com.vagdedes.spartan.api.BackgroundAPI.isBanned(uuid);
    }

    @Deprecated
    static void unbanPlayer(UUID uuid) {
        com.vagdedes.spartan.api.BackgroundAPI.unbanPlayer(uuid);
    }

    @Deprecated
    static String getBanReason(UUID uuid) {
        return com.vagdedes.spartan.api.BackgroundAPI.getBanReason(uuid);
    }

    @Deprecated
    static String getBanPunisher(UUID uuid) {
        return com.vagdedes.spartan.api.BackgroundAPI.getBanPunisher(uuid);
    }

    @Deprecated
    static boolean isHacker(Player p) {
        return com.vagdedes.spartan.api.BackgroundAPI.isHacker(p);
    }

    @Deprecated
    static boolean isLegitimate(Player p) {
        return com.vagdedes.spartan.api.BackgroundAPI.isLegitimate(p);
    }

    @Deprecated
    static boolean hasMiningNotificationsEnabled(Player p) {
        return com.vagdedes.spartan.api.BackgroundAPI.hasMiningNotificationsEnabled(p);
    }

    @Deprecated
    static void setMiningNotifications(Player p, boolean value) {
        com.vagdedes.spartan.api.BackgroundAPI.setMiningNotifications(p, value);
    }

    @Deprecated
    static int getCPS(Player p) {
        return com.vagdedes.spartan.api.BackgroundAPI.getCPS(p);
    }

    @Deprecated
    static UUID[] getBanList() {
        return com.vagdedes.spartan.api.BackgroundAPI.getBanList();
    }

    static boolean addToWave(UUID uuid, String command) {
        return com.vagdedes.spartan.api.BackgroundAPI.addToWave(uuid, command);
    }

    static void removeFromWave(UUID uuid) {
        com.vagdedes.spartan.api.BackgroundAPI.removeFromWave(uuid);
    }

    static void clearWave() {
        com.vagdedes.spartan.api.BackgroundAPI.clearWave();
    }

    static void runWave() {
        com.vagdedes.spartan.api.BackgroundAPI.runWave();
    }

    static UUID[] getWaveList() {
        return com.vagdedes.spartan.api.BackgroundAPI.getWaveList();
    }

    static int getWaveSize() {
        return com.vagdedes.spartan.api.BackgroundAPI.getWaveSize();
    }

    static boolean isAddedToTheWave(UUID uuid) {
        return com.vagdedes.spartan.api.BackgroundAPI.isAddedToTheWave(uuid);
    }

    static void warnPlayer(Player p, String reason) {
        com.vagdedes.spartan.api.BackgroundAPI.warnPlayer(p, reason);
    }

    @Deprecated
    static void addPermission(Player p, Permission permission) {
        com.vagdedes.spartan.api.BackgroundAPI.addPermission(p, permission.getPermission());
    }

    @Deprecated
    static void sendClientSidedBlock(Player p, Location loc, Material m, byte b) {
        com.vagdedes.spartan.api.BackgroundAPI.sendClientSidedBlock(p, loc, m, b);
    }

    @Deprecated
    static void destroyClientSidedBlock(Player p, Location loc) {
        com.vagdedes.spartan.api.BackgroundAPI.destroyClientSidedBlock(p, loc);
    }

    @Deprecated
    static void removeClientSidedBlocks(Player p) {
        com.vagdedes.spartan.api.BackgroundAPI.removeClientSidedBlocks(p);
    }

    @Deprecated
    static boolean containsClientSidedBlock(Player p, Location loc) {
        return com.vagdedes.spartan.api.BackgroundAPI.containsClientSidedBlock(p, loc);
    }

    @Deprecated
    static Material getClientSidedBlockMaterial(Player p, Location loc) {
        return com.vagdedes.spartan.api.BackgroundAPI.getClientSidedBlockMaterial(p, loc);
    }

    @Deprecated
    static byte getClientSidedBlockData(Player p, Location loc) {
        return com.vagdedes.spartan.api.BackgroundAPI.getClientSidedBlockData(p, loc);
    }

    static String getConfiguredCheckName(HackType hackType) {
        return com.vagdedes.spartan.api.BackgroundAPI.getConfiguredCheckName(hackType.getHackType());
    }

    static void setConfiguredCheckName(HackType hackType, String name) {
        com.vagdedes.spartan.api.BackgroundAPI.setConfiguredCheckName(hackType.getHackType(), name);
    }

    @Deprecated
    static void disableVelocityProtection(Player p, int ticks) {
        com.vagdedes.spartan.api.BackgroundAPI.disableVelocityProtection(p, ticks);
    }

    @Deprecated
    static void setOnGround(Player p, int ticks) {
        com.vagdedes.spartan.api.BackgroundAPI.setOnGround(p, ticks);
    }

    @Deprecated
    static int getMaxPunishmentViolation(HackType hackType) {
        return com.vagdedes.spartan.api.BackgroundAPI.getMaxPunishmentViolation(hackType.getHackType());
    }

    @Deprecated
    static int getMinPunishmentViolation(HackType hackType) {
        return com.vagdedes.spartan.api.BackgroundAPI.getMinPunishmentViolation(hackType.getHackType());
    }

    @Deprecated
    static boolean mayPunishPlayer(Player p, HackType hackType) {
        return com.vagdedes.spartan.api.BackgroundAPI.mayPunishPlayer(p, hackType.getHackType());
    }

}
