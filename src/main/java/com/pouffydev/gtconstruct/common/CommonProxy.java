package com.pouffydev.gtconstruct.common;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.IMaterialRegistryManager;
import com.gregtechceu.gtceu.api.data.chemical.material.event.PostMaterialEvent;
import com.gregtechceu.gtceu.common.registry.GTRegistration;
import com.gregtechceu.gtceu.common.unification.material.MaterialRegistryManager;
import com.pouffydev.gtconstruct.GTConstruct;
import com.pouffydev.gtconstruct.api.GTConstructAPI;
import com.pouffydev.gtconstruct.common.material.event.MaterialLinkEvent;
import com.pouffydev.gtconstruct.common.material.event.MaterialLinkRegistryEvent;
import com.pouffydev.gtconstruct.common.material.MaterialLinkRegistryManager;
import com.pouffydev.gtconstruct.common.material.event.PostMaterialLinkEvent;
import com.pouffydev.gtconstruct.integration.kubejs.GTCRegistryInfo;
import com.pouffydev.gtconstruct.registry.GTCMaterialLinks;
import com.pouffydev.gtconstruct.registry.GTCRegistration;
import com.pouffydev.gtconstruct.registry.registrate.GTCItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoader;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class CommonProxy {

    public CommonProxy() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.register(this);

        GTConstructAPI.materialLinkManager = MaterialLinkRegistryManager.getInstance();
    }

    public static void init() {
        GTConstruct.LOGGER.info("GTConstruct common proxy init!");
        initMaterialLinks();

        MaterialRegistryManager managerInternal = (MaterialRegistryManager) GTCEuAPI.materialManager;
        if (managerInternal.getPhase() == IMaterialRegistryManager.Phase.FROZEN)
            GTCItems.init();

        GTCRegistration.REGISTRATE.registerRegistrate();
    }

    private static void initMaterialLinks() {
        MaterialLinkRegistryManager managerInternal = (MaterialLinkRegistryManager) GTConstructAPI.materialLinkManager;

        GTConstruct.LOGGER.info("Registering material link registries");
        ModLoader.get().postEvent(new MaterialLinkRegistryEvent());

        managerInternal.unfreezeRegistries();
        GTConstruct.LOGGER.info("Registering GTConstruct Material Links");
        GTCMaterialLinks.init();

        GTCEu.LOGGER.info("Registering addon Material Links");
        MaterialLinkEvent materialLinkEvent = new MaterialLinkEvent();
        ModLoader.get().postEvent(materialLinkEvent);
        if (GTCEu.Mods.isKubeJSLoaded()) {
            KJSEventWrapper.materialRegistry();
        }

        managerInternal.freezeRegistries();
        ModLoader.get().postEvent(new PostMaterialLinkEvent());
    }

    @SubscribeEvent
    public void modConstruct(FMLConstructModEvent event) {
        // this is done to delay initialization of content to be after KJS has set up.
        event.enqueueWork(CommonProxy::init);
    }

    public static final class KJSEventWrapper {

        public static void materialRegistry() {
            GTCRegistryInfo.registerFor(GTConstructAPI.materialLinkManager.getRegistry(GTConstruct.MOD_ID).getRegistryName());
        }

    }
}
