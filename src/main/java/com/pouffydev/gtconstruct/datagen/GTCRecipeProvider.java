package com.pouffydev.gtconstruct.datagen;

import com.pouffydev.gtconstruct.GTConstruct;
import net.minecraft.data.*;
import net.minecraft.data.recipes.*;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.recipe.data.IRecipeHelper;

import java.util.function.Consumer;

public abstract class GTCRecipeProvider extends RecipeProvider implements IConditionBuilder, IRecipeHelper {
    public GTCRecipeProvider(PackOutput generator) {
        super(generator);
        GTConstruct.sealGTCClass(this, "GTCRecipeProvider", "GTCRecipeProvider is trivial to recreate and directly extending can lead to addon recipes polluting our namespace.");
    }

    @Override
    protected abstract void buildRecipes(Consumer<FinishedRecipe> consumer);

    @Override
    public abstract @NotNull String getName();

    @Override
    public String getModId() {
        return GTConstruct.MOD_ID;
    }
}
