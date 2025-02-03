package com.pouffydev.gtconstruct.datagen.backing;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.material.Fluid;
import slimeknights.mantle.recipe.ingredient.FluidIngredient;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.materials.definition.MaterialVariantId;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.recipe.casting.material.MaterialFluidRecipeBuilder;

import java.util.function.Consumer;

import static slimeknights.tconstruct.library.recipe.melting.IMeltingRecipe.getTemperature;

public interface IGTCMaterialRecipeHelper extends IMaterialRecipeHelper {

    default void materialMeltingCasting(Consumer<FinishedRecipe> consumer, MaterialVariantId material, Fluid fluid, int fluidAmount, String folder) {
        MaterialFluidRecipeBuilder.material(material)
                .setFluid(FluidIngredient.of(fluid, fluidAmount))
                .setTemperature(getTemperature(fluid))
                .save(consumer, location(folder + "casting/" + material.getLocation('_').getPath()));
        materialMelting(consumer, material, fluid, fluidAmount, folder);
    }

    /** Adds recipes to melt and cast a compat material of ingot size */
    default void materialMeltingCasting(Consumer<FinishedRecipe> consumer, MaterialVariantId material, Fluid fluid, String folder) {
        materialMeltingCasting(consumer, material, fluid, FluidValues.INGOT, folder);
    }
}
