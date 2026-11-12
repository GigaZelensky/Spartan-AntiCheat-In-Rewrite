package com.vagdedes.spartan.abstraction.check;

import org.bukkit.entity.Player;

public abstract class CheckProcess {

    public final CheckEnums.CheckType checkType;
    public final Player player;

    protected CheckProcess(CheckEnums.CheckType checkType, Player player) {
        this.checkType = checkType;
        this.player = player;
    }

}
