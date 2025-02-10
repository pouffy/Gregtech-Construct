package com.pouffydev.gtconstruct.registry;

import com.pouffydev.gtconstruct.common.stats.PlungerHeadMaterialStats;
import com.pouffydev.gtconstruct.common.stats.SoftMalletHeadMaterialStats;
import slimeknights.tconstruct.library.materials.IMaterialRegistry;
import slimeknights.tconstruct.library.materials.MaterialRegistry;

import static slimeknights.tconstruct.library.materials.MaterialRegistry.MELEE_HARVEST;

public class GTCMaterialRegistry {

    public static void setup() {
        IMaterialRegistry registry = MaterialRegistry.getInstance();
        registry.registerStatType(PlungerHeadMaterialStats.TYPE, MELEE_HARVEST);
        registry.registerStatType(SoftMalletHeadMaterialStats.TYPE, MELEE_HARVEST);
    }
}
