package com.pouffydev.gtconstruct.datagen.material;

import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.pouffydev.gtconstruct.datagen.GTCRecipeProvider;
import com.pouffydev.gtconstruct.datagen.backing.IGTCMaterialRecipeHelper;
import com.pouffydev.gtconstruct.registry.GTCMaterialIds;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;

import java.util.function.Consumer;

public class GTCMaterialRecipeProv extends GTCRecipeProvider implements IGTCMaterialRecipeHelper {
    public GTCMaterialRecipeProv(PackOutput generator) {
        super(generator);
    }

    @Override
    public String getName() {
        return "GregTech Construct Material Recipes";
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        addMaterialItems(consumer);
        addMaterialSmeltery(consumer);
    }

    private void addMaterialItems(Consumer<FinishedRecipe> consumer) {
        String folder = "tools/materials/";

    }

    private void addMaterialSmeltery(Consumer<FinishedRecipe> consumer) {
        String folder = "tools/materials/";

        materialMeltingCasting(consumer, GTCMaterialIds.sterlingSilver, GTMaterials.SterlingSilver.getFluid(),    folder);
        materialMeltingCasting(consumer, GTCMaterialIds.bismuthBronze, GTMaterials.BismuthBronze.getFluid(),    folder);
        materialMeltingCasting(consumer, GTCMaterialIds.cupronickel, GTMaterials.Cupronickel.getFluid(),    folder);
        materialMeltingCasting(consumer, GTCMaterialIds.blackBronze, GTMaterials.BlackBronze.getFluid(),    folder);

        materialMeltingCasting(consumer, GTCMaterialIds.blackSteel, GTMaterials.BlackSteel.getFluid(),    folder);
        materialMeltingCasting(consumer, GTCMaterialIds.blueSteel, GTMaterials.BlueSteel.getFluid(),    folder);
        materialMeltingCasting(consumer, GTCMaterialIds.redSteel, GTMaterials.RedSteel.getFluid(),    folder);

        materialMeltingCasting(consumer, GTCMaterialIds.bismuth, GTMaterials.Bismuth.getFluid(),    folder);

    }
}
