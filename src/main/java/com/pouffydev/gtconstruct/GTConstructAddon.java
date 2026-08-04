package com.pouffydev.gtconstruct;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.pouffydev.gtconstruct.datagen.recipe.GTCCraftingRecipeLoader;
import com.pouffydev.gtconstruct.datagen.recipe.GTCMachineRecipeLoader;
import com.pouffydev.gtconstruct.datagen.recipe.GTCMaterialRecipeHandler;
import com.pouffydev.gtconstruct.registry.registrate.GTCItems;
import net.minecraft.data.recipes.FinishedRecipe;

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
        GTCItems.init();
    }

    @Override
    public String addonModId() {
        return GTConstruct.MOD_ID;
    }

    public void addRecipes(Consumer<FinishedRecipe> provider) {
        for (Material material : GTCEuAPI.materialManager.getRegisteredMaterials()) {
            GTCMaterialRecipeHandler.init(provider, material);

        }
        GTCMachineRecipeLoader.init(provider);
        GTCCraftingRecipeLoader.init(provider);
    }
}
