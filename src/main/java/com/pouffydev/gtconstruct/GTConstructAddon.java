package com.pouffydev.gtconstruct;

import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.pouffydev.gtconstruct.datagen.recipe.GTCMaterialRecipeHandler;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.pouffydev.gtconstruct.registry.GTCRegistration.REGISTRATE;

public class GTConstructAddon implements IGTAddon {
    @Override
    public GTRegistrate getRegistrate() {
        return REGISTRATE;
    }

    @Override
    public void initializeAddon() {

    }

    @Override
    public String addonModId() {
        return GTConstruct.MOD_ID;
    }

    public void addRecipes(Consumer<FinishedRecipe> provider) {
        GTCMaterialRecipeHandler.init(provider);
    }
}
