package com.vagdedes.spartan.abstraction.check;

import ai.idealistic.spartan.abstraction.check.implementation.misc.ImpossibleInventory;
import ai.idealistic.spartan.abstraction.check.implementation.misc.InventoryClicks;
import ai.idealistic.spartan.abstraction.check.implementation.movement.GravitySimulation;
import ai.idealistic.spartan.abstraction.check.implementation.movement.SpeedSimulation;
import ai.idealistic.spartan.abstraction.check.implementation.movement.exploits.Exploits;
import com.vagdedes.spartan.utils.minecraft.inventory.MaterialUtils;
import lombok.Getter;
import org.bukkit.Material;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CheckEnums {

    public enum HackType {
        NAME(
                HackCategoryType.COMBAT,
                null, // class
                Material.ARROW
        );

        @Getter
        private Check check;
        public final HackCategoryType category;
        public final Class<?> executor;
        private final Map<String, Long> detections;
        public final Material material;

        HackType(HackCategoryType category, Class<?> executor, Material material) {
            this.category = category;
            this.executor = executor;
            this.detections = new ConcurrentHashMap<>();
            this.check = new Check(this);
            this.material = material;
        }

        public void resetCheck() {
            if (this.check != null) {
                this.check = new Check(this);
            }
        }

        public void addDetection(String detection, long averageTime) {
            this.detections.put(detection, averageTime);
        }

        public void removeDetection(String detection) {
            this.detections.remove(detection);
        }

        public long getDefaultAverageTime(String detection) {
            return this.detections.get(detection);
        }

        public Collection<String> getDetections() {
            return this.detections.keySet();
        }

        @Override
        public String toString() {
            return super.toString().toLowerCase().replace("_", "-");
        }
    }

    public enum HackCategoryType {
        COMBAT(Material.IRON_SWORD),
        MOVEMENT(Material.FEATHER),
        WORLD(Material.DIAMOND_PICKAXE),
        MISCELLANEOUS(MaterialUtils.get("crafting_table"));

        public final Material material;

        HackCategoryType(Material material) {
            this.material = material;
        }

        @Override
        public String toString() {
            return super.toString().toLowerCase().replace("_", "-");
        }

    }

}
