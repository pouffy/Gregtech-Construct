package com.pouffydev.gtconstruct;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.pouffydev.gtconstruct.api.GTConstructAPI;
import com.pouffydev.gtconstruct.common.CommonProxy;
import com.pouffydev.gtconstruct.common.material.MaterialLinkRegistryManager;
import com.pouffydev.gtconstruct.common.material.event.MaterialLinkEvent;
import com.pouffydev.gtconstruct.common.material.event.MaterialLinkRegistryEvent;
import com.pouffydev.gtconstruct.common.material.event.PostMaterialLinkEvent;
import com.pouffydev.gtconstruct.datagen.recipe.GTCMachineRecipeLoader;
import com.pouffydev.gtconstruct.datagen.recipe.GTCMaterialRecipeHandler;
import com.pouffydev.gtconstruct.registry.GTCMaterialLinks;
import com.pouffydev.gtconstruct.registry.registrate.GTCItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraftforge.fml.ModLoader;

import java.util.function.Consumer;

import static com.pouffydev.gtconstruct.registry.GTCRegistration.REGISTRATE;

@GTAddon
public class GTConstructAddon implements IGTAddon {
    @Override
    public GTRegistrate getRegistrate() {
        return REGISTRATE;
    }

    @Override
    public void initializeAddon() {
        initMaterialLinks();
        GTCItems.init();
    }

    @Override
    public String addonModId() {
        return GTConstruct.MOD_ID;
    }

    public void addRecipes(Consumer<FinishedRecipe> provider) {
        GTCMaterialRecipeHandler.init(provider);
        GTCMachineRecipeLoader.init(provider);
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
            CommonProxy.KJSEventWrapper.materialRegistry();
        }

        managerInternal.closeRegistries();
        ModLoader.get().postEvent(new PostMaterialLinkEvent());
        if (GTCEu.Mods.isKubeJSLoaded()) {
            CommonProxy.KJSEventWrapper.materialModification();
        }

        // Freeze Material Registry before processing Items, Blocks, and Fluids
        managerInternal.freezeRegistries();
    }
}
