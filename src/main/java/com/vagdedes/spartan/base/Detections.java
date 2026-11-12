package com.vagdedes.spartan.base;

public class Detections {

    public static final Detection
            MOVEMENT_HORIZONTAL = new Detection("MOVEMENT_HORIZONTAL"),
            MOVEMENT_VERTICAL = new Detection("MOVEMENT_HORIZONTAL"),
            MOVEMENT_EUCLIDEAN = new Detection("MOVEMENT_EUCLIDEAN"),
            MOVEMENT_POSITION = new Detection("MOVEMENT_POSITION"),
            MOVEMENT_ROTATIONS = new Detection("MOVEMENT_ROTATIONS"),
            COMBAT_REACH = new Detection("COMBAT_REACH"),
            COMBAT_DAMAGE = new Detection("COMBAT_DAMAGE"),
            BLOCK_PLACE_SPEED = new Detection("BLOCK_PLACE_SPEED"),
            BLOCK_BREAK_SPEED = new Detection("BLOCK_BREAK_SPEED");

    public static final Detection[] LIST = new Detection[]{
            MOVEMENT_HORIZONTAL,
            MOVEMENT_VERTICAL,
            MOVEMENT_EUCLIDEAN,
            MOVEMENT_POSITION,
            MOVEMENT_ROTATIONS,
            COMBAT_REACH,
            COMBAT_DAMAGE,
            BLOCK_PLACE_SPEED,
            BLOCK_BREAK_SPEED
    };

}
