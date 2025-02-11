package com.pouffydev.gtconstruct.datagen.recipe;

import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTItems.SHAPE_EXTRUDER_INGOT;
import static com.gregtechceu.gtceu.common.data.GTItems.SHAPE_EXTRUDER_RING;
import static com.pouffydev.gtconstruct.registry.registrate.GTCItems.ShapeExtruderPlungerHead;

public class GTCCraftingRecipeLoader {

    public static void init(Consumer<FinishedRecipe> provider) {
        VanillaRecipeHelper.addStrictShapedRecipe(provider, "shape_extruder_plunger_head", ShapeExtruderPlungerHead.asStack(),
                "   ", " S ", " x ", 'S', SHAPE_EXTRUDER_RING.asStack());
    }
}
