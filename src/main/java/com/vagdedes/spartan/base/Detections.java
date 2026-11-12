package com.vagdedes.spartan.base;

public class Detections {

    public static final Detection
            MOVEMENT_HORIZONTAL = new Detection("MOVEMENT_HORIZONTAL"),
            MOVEMENT_VERTICAL = new Detection("MOVEMENT_HORIZONTAL"),
            MOVEMENT_COMBINED = new Detection("MOVEMENT_HORIZONTAL");
    public static final Detection[] LIST = new Detection[]{
            MOVEMENT_HORIZONTAL,
            MOVEMENT_VERTICAL,
            MOVEMENT_COMBINED
    };
}
