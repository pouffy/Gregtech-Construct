package com.pouffydev.gtconstruct.registry;

import com.pouffydev.gtconstruct.common.stats.PlungerHeadMaterialStats;
import slimeknights.tconstruct.library.materials.IMaterialRegistry;
import slimeknights.tconstruct.library.materials.MaterialRegistry;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;

import static com.pouffydev.gtconstruct.GTConstruct.id;
import static slimeknights.tconstruct.library.materials.MaterialRegistry.MELEE_HARVEST;

public class GTCMaterialRegistry {

    public static final MaterialStatsId PLUNGER_HEAD = new MaterialStatsId(id("plunger_head"));

    public static void setup() {
        IMaterialRegistry registry = MaterialRegistry.getInstance();
        registry.registerStatType(PlungerHeadMaterialStats.TYPE, MELEE_HARVEST);
    }
}
