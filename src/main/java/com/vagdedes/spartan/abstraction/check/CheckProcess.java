package com.vagdedes.spartan.abstraction.check;

import com.vagdedes.spartan.abstraction.protocol.PlayerProtocol;

public abstract class CheckProcess {

    public final CheckEnums.HackType hackType;
    public final PlayerProtocol protocol;

    protected CheckProcess(CheckEnums.HackType hackType, PlayerProtocol protocol) {
        this.hackType = hackType;
        this.protocol = protocol;
    }

}
