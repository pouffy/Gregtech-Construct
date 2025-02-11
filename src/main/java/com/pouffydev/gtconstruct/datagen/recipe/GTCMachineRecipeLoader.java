package com.pouffydev.gtconstruct.datagen.recipe;

import com.gregtechceu.gtceu.data.recipe.misc.MachineRecipeLoader;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.common.data.GTItems.SHAPE_EMPTY;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.FORMING_PRESS_RECIPES;
import static com.pouffydev.gtconstruct.registry.registrate.GTCItems.SHAPE_EXTRUDERS;

public class GTCMachineRecipeLoader {

    private GTCMachineRecipeLoader() {}

    public static void init(Consumer<FinishedRecipe> provider) {
        registerBendingCompressingRecipes(provider);
    }

    private static void registerBendingCompressingRecipes(Consumer<FinishedRecipe> provider) {

        for (ItemEntry<Item> shapeExtruder : SHAPE_EXTRUDERS) {
            if (shapeExtruder == null) continue;
            FORMING_PRESS_RECIPES.recipeBuilder("copy_shape_" + shapeExtruder.get())
                    .duration(120).EUt(22)
                    .notConsumable(shapeExtruder)
                    .inputItems(SHAPE_EMPTY)
                    .outputItems(shapeExtruder)
                    .save(provider);
        }
    }
}
