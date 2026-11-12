package me.vagdedes.spartan.system;

import com.vagdedes.spartan.abstraction.check.CheckEnums;
import lombok.Getter;

public class Enums {

    // API Use
    // Should have used capital letters but won't change them now so to not break the dozen APIs who use these enums
    public enum HackType {
        XRay(CheckEnums.HackType.X_RAY),
        NoSwing(CheckEnums.HackType.NO_SWING),
        IrregularMovements(CheckEnums.HackType.IRREGULAR_MOVEMENTS),
        ImpossibleActions(CheckEnums.HackType.IMPOSSIBLE_ACTIONS),
        InventoryClicks(CheckEnums.HackType.INVENTORY_CLICKS),
        Criticals(CheckEnums.HackType.CRITICALS),
        SpeedSimulation(CheckEnums.HackType.SPEED_SIMULATION),
        Exploits(CheckEnums.HackType.EXPLOITS),
        GravitySimulation(CheckEnums.HackType.GRAVITY_SIMULATION),
        BlockReach(CheckEnums.HackType.BLOCK_REACH),
        FastClicks(CheckEnums.HackType.FAST_CLICKS),
        FastHeal(CheckEnums.HackType.FAST_HEAL),
        ImpossibleInventory(CheckEnums.HackType.IMPOSSIBLE_INVENTORY),
        HitReach(CheckEnums.HackType.HIT_REACH),
        FastBreak(CheckEnums.HackType.FAST_BREAK),
        FastPlace(CheckEnums.HackType.FAST_PLACE),
        MorePackets(CheckEnums.HackType.MORE_PACKETS),
        FastEat(CheckEnums.HackType.FAST_EAT),
        Velocity(CheckEnums.HackType.VELOCITY),
        KillAura(CheckEnums.HackType.KILL_AURA);

        @Getter
        private final CheckEnums.HackType hackType;

        HackType(CheckEnums.HackType hackType) {
            this.hackType = hackType;
        }
    }

    // API Use
    public enum Permission {
        CONDITION(com.vagdedes.spartan.api.Permission.CONDITION),
        WAVE(com.vagdedes.spartan.api.Permission.WAVE),
        ADMIN(com.vagdedes.spartan.api.Permission.ADMIN),
        RELOAD(com.vagdedes.spartan.api.Permission.RELOAD),
        KICK(com.vagdedes.spartan.api.Permission.KICK),
        BYPASS(com.vagdedes.spartan.api.Permission.BYPASS),
        MANAGE(com.vagdedes.spartan.api.Permission.MANAGE),
        INFO(com.vagdedes.spartan.api.Permission.INFO),
        WARN(com.vagdedes.spartan.api.Permission.WARN),
        USE_BYPASS(com.vagdedes.spartan.api.Permission.USE_BYPASS),
        NOTIFICATIONS(com.vagdedes.spartan.api.Permission.NOTIFICATIONS),
        BEDROCK(com.vagdedes.spartan.api.Permission.BEDROCK);

        @Getter
        private final com.vagdedes.spartan.api.Permission permission;

        Permission(com.vagdedes.spartan.api.Permission permission) {
            this.permission = permission;
        }
    }

    // API Use
    public enum ToggleAction {
        ENABLE(com.vagdedes.spartan.api.ToggleAction.ENABLE),
        DISABLE(com.vagdedes.spartan.api.ToggleAction.DISABLE);

        @Getter
        private final com.vagdedes.spartan.api.ToggleAction toggleAction;

        ToggleAction(com.vagdedes.spartan.api.ToggleAction toggleAction) {
            this.toggleAction = toggleAction;
        }
    }

}
