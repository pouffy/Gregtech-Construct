package com.pouffydev.gtconstruct.datagen.material;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterialItems;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.pouffydev.gtconstruct.datagen.GTCRecipeProvider;
import com.pouffydev.gtconstruct.datagen.backing.IGTCMaterialRecipeHelper;
import com.pouffydev.gtconstruct.registry.GTCMaterialIds;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.tools.data.material.MaterialIds;

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

        materialRecipe(consumer, GTCMaterialIds.siliconeRubber,      Ingredient.of(GTMaterialItems.MATERIAL_ITEMS.get(TagPrefix.ingot, GTMaterials.SiliconeRubber)),      1, 1, folder + "silicone_rubber/ingot");
        materialRecipe(consumer, GTCMaterialIds.siliconeRubber,      Ingredient.of(GTMaterialItems.MATERIAL_ITEMS.get(TagPrefix.nugget, GTMaterials.SiliconeRubber)),      1, 9, folder + "silicone_rubber/nugget");
        materialRecipe(consumer, GTCMaterialIds.siliconeRubber,      Ingredient.of(ChemicalHelper.getBlock(TagPrefix.block, GTMaterials.SiliconeRubber)),      9, 1, folder + "silicone_rubber/block");

        materialRecipe(consumer, GTCMaterialIds.styreneButadieneRubber,      Ingredient.of(GTMaterialItems.MATERIAL_ITEMS.get(TagPrefix.ingot, GTMaterials.StyreneButadieneRubber)),      1, 1, folder + "styrene_butadiene_rubber/ingot");
        materialRecipe(consumer, GTCMaterialIds.styreneButadieneRubber,      Ingredient.of(GTMaterialItems.MATERIAL_ITEMS.get(TagPrefix.nugget, GTMaterials.StyreneButadieneRubber)),      1, 9, folder + "styrene_butadiene_rubber/nugget");
        materialRecipe(consumer, GTCMaterialIds.styreneButadieneRubber,      Ingredient.of(ChemicalHelper.getBlock(TagPrefix.block, GTMaterials.StyreneButadieneRubber)),      9, 1, folder + "styrene_butadiene_rubber/block");

        materialRecipe(consumer, GTCMaterialIds.polybenzimidazole,      Ingredient.of(GTMaterialItems.MATERIAL_ITEMS.get(TagPrefix.ingot, GTMaterials.Polybenzimidazole)),      1, 1, folder + "polybenzimidazole/ingot");
        materialRecipe(consumer, GTCMaterialIds.polybenzimidazole,      Ingredient.of(GTMaterialItems.MATERIAL_ITEMS.get(TagPrefix.ingot, GTMaterials.Polybenzimidazole)),      1, 9, folder + "polybenzimidazole/nugget");
        materialRecipe(consumer, GTCMaterialIds.polybenzimidazole,      Ingredient.of(ChemicalHelper.getBlock(TagPrefix.block, GTMaterials.Polybenzimidazole)),      9, 1, folder + "polybenzimidazole/block");

        materialRecipe(consumer, GTCMaterialIds.polyethylene,      Ingredient.of(GTMaterialItems.MATERIAL_ITEMS.get(TagPrefix.ingot, GTMaterials.Polyethylene)),      1, 1, folder + "polyethylene/ingot");
        materialRecipe(consumer, GTCMaterialIds.polyethylene,      Ingredient.of(GTMaterialItems.MATERIAL_ITEMS.get(TagPrefix.ingot, GTMaterials.Polyethylene)),      1, 9, folder + "polyethylene/nugget");
        materialRecipe(consumer, GTCMaterialIds.polyethylene,      Ingredient.of(ChemicalHelper.getBlock(TagPrefix.block, GTMaterials.Polyethylene)),      9, 1, folder + "polyethylene/block");

        materialRecipe(consumer, GTCMaterialIds.polytetrafluoroethylene,      Ingredient.of(GTMaterialItems.MATERIAL_ITEMS.get(TagPrefix.ingot, GTMaterials.Polytetrafluoroethylene)),      1, 1, folder + "polytetrafluoroethylene/ingot");
        materialRecipe(consumer, GTCMaterialIds.polytetrafluoroethylene,      Ingredient.of(GTMaterialItems.MATERIAL_ITEMS.get(TagPrefix.ingot, GTMaterials.Polytetrafluoroethylene)),      1, 9, folder + "polytetrafluoroethylene/nugget");
        materialRecipe(consumer, GTCMaterialIds.polytetrafluoroethylene,      Ingredient.of(ChemicalHelper.getBlock(TagPrefix.block, GTMaterials.Polytetrafluoroethylene)),      9, 1, folder + "polytetrafluoroethylene/block");

        materialRecipe(consumer, GTCMaterialIds.rubber,      Ingredient.of(GTMaterialItems.MATERIAL_ITEMS.get(TagPrefix.ingot, GTMaterials.Rubber)),      1, 1, folder + "rubber/ingot");
        materialRecipe(consumer, GTCMaterialIds.rubber,      Ingredient.of(GTMaterialItems.MATERIAL_ITEMS.get(TagPrefix.ingot, GTMaterials.Rubber)),      1, 9, folder + "rubber/nugget");
        materialRecipe(consumer, GTCMaterialIds.rubber,      Ingredient.of(ChemicalHelper.getBlock(TagPrefix.block, GTMaterials.Rubber)),      9, 1, folder + "rubber/block");
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
