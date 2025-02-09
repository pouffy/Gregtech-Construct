package com.pouffydev.gtconstruct.registry;

import com.pouffydev.gtconstruct.common.manager.LinkedMaterialManager;
import com.pouffydev.gtconstruct.common.stats.PlungerHeadMaterialStats;
import com.pouffydev.gtconstruct.common.stats.SoftMalletHeadMaterialStats;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.OnDatapackSyncEvent;
import slimeknights.tconstruct.library.materials.IMaterialRegistry;
import slimeknights.tconstruct.library.materials.MaterialRegistry;
import slimeknights.tconstruct.library.materials.definition.MaterialManager;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsManager;

import static com.pouffydev.gtconstruct.GTConstruct.id;
import static slimeknights.tconstruct.library.materials.MaterialRegistry.MELEE_HARVEST;

public class GTCMaterialRegistry {

    static GTCMaterialRegistry INSTANCE;

    public final LinkedMaterialManager linkedMaterialManager;

    private static boolean materialsLinked = false;
    private static boolean fullyLoaded = false;

    public static GTCMaterialRegistry getInstance() {
        return INSTANCE;
    }

    public static void init() {
        INSTANCE = new GTCMaterialRegistry();
        MinecraftForge.EVENT_BUS.addListener(INSTANCE::addDataPackListeners);
        MinecraftForge.EVENT_BUS.addListener(INSTANCE::onDatapackSync);
    }

    public GTCMaterialRegistry() {
        linkedMaterialManager = new LinkedMaterialManager(() -> {
            materialsLinked = true;
            checkAllLoaded();
        });
    }

    private static void checkAllLoaded() {
        if (materialsLinked) {
            materialsLinked = false;
            fullyLoaded = true;
        } else {
            fullyLoaded = false;
        }
    }

    public static void setup() {
        IMaterialRegistry registry = MaterialRegistry.getInstance();
        registry.registerStatType(PlungerHeadMaterialStats.TYPE, MELEE_HARVEST);
        registry.registerStatType(SoftMalletHeadMaterialStats.TYPE, MELEE_HARVEST);
    }

    private void addDataPackListeners(final AddReloadListenerEvent event) {
        event.addListener(linkedMaterialManager);
    }

    private void onDatapackSync(OnDatapackSyncEvent event) {

    }
}
