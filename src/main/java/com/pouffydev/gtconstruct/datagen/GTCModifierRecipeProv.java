package com.pouffydev.gtconstruct.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class GTCModifierRecipeProv extends GTCRecipeProvider {
    public GTCModifierRecipeProv(PackOutput output) {
        super(output);
    }

    @Override
    public @NotNull String getName() {
        return "GregTech Construct Modifier Recipes";
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

    }
}
