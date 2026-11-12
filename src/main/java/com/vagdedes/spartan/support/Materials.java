package com.vagdedes.spartan.support;

import org.bukkit.Material;

import java.util.*;

public class Materials {

    private static final Map<String, Material> alternative = new LinkedHashMap<>(2);

    static {
        if (Versions.isOrGreater(Versions.MCVersion.V1_13)) {
            alternative.put("water", Material.WATER);
            alternative.put("lava", Material.LAVA);
            alternative.put("web", Material.COBWEB);
            alternative.put("gold_axe", Material.GOLDEN_AXE);
            alternative.put("wood_axe", Material.WOODEN_AXE);
            alternative.put("gold_pickaxe", Material.GOLDEN_PICKAXE);
            alternative.put("wood_pickaxe", Material.WOODEN_PICKAXE);
            alternative.put("gold_sword", Material.GOLDEN_SWORD);
            alternative.put("wood_sword", Material.WOODEN_SWORD);
            alternative.put("watch", Material.CLOCK);
            alternative.put("exp_bottle", Material.EXPERIENCE_BOTTLE);
            alternative.put("redstone_comparator", Material.COMPARATOR);
            alternative.put("cake", Material.CAKE);
            alternative.put("diamond_spade", Material.DIAMOND_HOE);
            alternative.put("iron_spade", Material.IRON_HOE);
            alternative.put("gold_spade", Material.GOLDEN_HOE);
            alternative.put("stone_spade", Material.STONE_HOE);
            alternative.put("wood_spade", Material.WOODEN_HOE);
            alternative.put("beetroot_block", Material.BEETROOT);
            alternative.put("magma", Material.MAGMA_BLOCK);
            alternative.put("firework", Material.FIREWORK_ROCKET);
            alternative.put("nether_portal", Material.NETHER_PORTAL);
            alternative.put("cobblestone_wall", Material.COBBLESTONE_WALL);
            alternative.put("end_portal_frame", Material.END_PORTAL_FRAME);
            alternative.put("iron_bars", Material.IRON_BARS);
            alternative.put("enchanting_table", Material.ENCHANTING_TABLE);
            alternative.put("piston_extension", Material.PISTON_HEAD);
            alternative.put("piston_moving", Material.MOVING_PISTON);
            alternative.put("piston", Material.PISTON);
            alternative.put("sticky_piston", Material.STICKY_PISTON);
            alternative.put("lily_pad", Material.LILY_PAD);
            alternative.put("repeater_on", Material.REPEATER);
            alternative.put("repeater_off", Material.REPEATER);
            alternative.put("comparator_on", Material.COMPARATOR);
            alternative.put("comparator_off", Material.COMPARATOR);
            alternative.put("soil", Material.FARMLAND);
            alternative.put("gold_boots", Material.GOLDEN_BOOTS);
            alternative.put("grass_block", Material.GRASS_BLOCK);
            alternative.put("diamond_shovel", Material.DIAMOND_SHOVEL);
            alternative.put("gold_shovel", Material.GOLDEN_SHOVEL);
            alternative.put("iron_shovel", Material.IRON_SHOVEL);
            alternative.put("stone_shovel", Material.STONE_SHOVEL);
            alternative.put("wood_shovel", Material.WOODEN_SHOVEL);
            alternative.put("redstone_torch", Material.REDSTONE_TORCH);
            alternative.put("daylight_detector_1", Material.DAYLIGHT_DETECTOR);
            alternative.put("daylight_detector_2", Material.DAYLIGHT_DETECTOR);
            alternative.put("crafting_table", Material.CRAFTING_TABLE);
            alternative.put("furnace", Material.FURNACE);
            alternative.put("snowball", Material.SNOWBALL);
            alternative.put("fireball", Material.FIRE_CHARGE);
            alternative.put("lead", Material.LEAD);
            alternative.put("carrot_on_a_stick", Material.CARROT_ON_A_STICK);
        } else {
            alternative.put("water", Material.getMaterial("STATIONARY_WATER"));
            alternative.put("lava", Material.getMaterial("STATIONARY_LAVA"));
            alternative.put("web", Material.getMaterial("WEB"));
            alternative.put("gold_axe", Material.getMaterial("GOLD_AXE"));
            alternative.put("wood_axe", Material.getMaterial("WOOD_AXE"));
            alternative.put("gold_pickaxe", Material.getMaterial("GOLD_PICKAXE"));
            alternative.put("wood_pickaxe", Material.getMaterial("WOOD_PICKAXE"));
            alternative.put("gold_sword", Material.getMaterial("GOLD_SWORD"));
            alternative.put("wood_sword", Material.getMaterial("WOOD_SWORD"));
            alternative.put("watch", Material.getMaterial("WATCH"));
            alternative.put("exp_bottle", Material.getMaterial("EXP_BOTTLE"));
            alternative.put("redstone_comparator", Material.getMaterial("REDSTONE_COMPARATOR"));
            alternative.put("cake", Material.getMaterial("CAKE_BLOCK"));
            alternative.put("diamond_spade", Material.getMaterial("DIAMOND_SPADE"));
            alternative.put("iron_spade", Material.getMaterial("IRON_SPADE"));
            alternative.put("gold_spade", Material.getMaterial("GOLD_SPADE"));
            alternative.put("stone_spade", Material.getMaterial("STONE_SPADE"));
            alternative.put("wood_spade", Material.getMaterial("WOOD_SPADE"));
            alternative.put("beetroot_block", Material.getMaterial("BEETROOT_BLOCK"));
            alternative.put("magma", Material.getMaterial("MAGMA"));
            alternative.put("firework", Material.getMaterial("FIREWORK"));
            alternative.put("nether_portal", Material.getMaterial("PORTAL"));
            alternative.put("cobblestone_wall", Material.getMaterial("COBBLE_WALL"));
            alternative.put("end_portal_frame", Material.getMaterial("ENDER_PORTAL_FRAME"));
            alternative.put("iron_bars", Material.getMaterial("IRON_FENCE"));
            alternative.put("enchanting_table", Material.getMaterial("ENCHANTMENT_TABLE"));
            alternative.put("piston_extension", Material.getMaterial("PISTON_EXTENSION"));
            alternative.put("piston_moving", Material.getMaterial("PISTON_MOVING_PIECE"));
            alternative.put("piston", Material.getMaterial("PISTON_BASE"));
            alternative.put("sticky_piston", Material.getMaterial("PISTON_STICKY_BASE"));
            alternative.put("lily_pad", Material.getMaterial("WATER_LILY"));
            alternative.put("repeater_on", Material.getMaterial("DIODE_BLOCK_ON"));
            alternative.put("repeater_off", Material.getMaterial("DIODE_BLOCK_OFF"));
            alternative.put("comparator_on", Material.getMaterial("REDSTONE_COMPARATOR_ON"));
            alternative.put("comparator_off", Material.getMaterial("REDSTONE_COMPARATOR_OFF"));
            alternative.put("soil", Material.getMaterial("SOIL"));
            alternative.put("gold_boots", Material.getMaterial("GOLD_BOOTS"));
            alternative.put("grass_block", Material.getMaterial("GRASS"));
            alternative.put("diamond_shovel", Material.getMaterial("DIAMOND_SPADE"));
            alternative.put("gold_shovel", Material.getMaterial("GOLD_SPADE"));
            alternative.put("iron_shovel", Material.getMaterial("IRON_SPADE"));
            alternative.put("stone_shovel", Material.getMaterial("STONE_SPADE"));
            alternative.put("wood_shovel", Material.getMaterial("WOOD_SPADE"));
            alternative.put("redstone_torch", Material.getMaterial("REDSTONE_TORCH_ON"));
            alternative.put("daylight_detector_1", Material.getMaterial("DAYLIGHT_DETECTOR"));
            alternative.put("daylight_detector_2", Material.getMaterial("DAYLIGHT_DETECTOR_INVERTED"));
            alternative.put("crafting_table", Material.getMaterial("WORKBENCH"));
            alternative.put("furnace", Material.getMaterial("BURNING_FURNACE"));
            alternative.put("snowball", Material.getMaterial("SNOW_BALL"));
            alternative.put("fireball", Material.getMaterial("FIREBALL"));
            alternative.put("lead", Material.getMaterial("LEASH"));
            alternative.put("carrot_on_a_stick", Material.getMaterial("CARROT_STICK"));
        }
        for (String s : new String[]{
                "WOODEN_SPEAR",
                "STONE_SPEAR",
                "IRON_SPEAR",
                "DIAMOND_SPEAR",
                "GOLDEN_SPEAR",
                "NETHERITE_SPEAR"
        }) {
            Material m = Materials.findMaterial(s);

            if (m != null) {
                alternative.put(s.toLowerCase(), m);
            }
        }
    }

    public static Material get(String s) {
        return alternative.get(s.toLowerCase());
    }

    public static Material findMaterial(String string) {
        for (Material material : Material.values()) {
            if (material.toString().equals(string)) {
                return material;
            }
        }
        return null;
    }

}
