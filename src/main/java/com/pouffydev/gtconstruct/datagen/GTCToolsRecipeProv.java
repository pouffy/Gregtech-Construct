package com.pouffydev.gtconstruct.datagen;

import com.pouffydev.gtconstruct.registry.GTCSmeltery;
import com.pouffydev.gtconstruct.registry.GTCToolParts;
import com.pouffydev.gtconstruct.registry.GTCTools;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IToolRecipeHelper;
import slimeknights.tconstruct.tools.TinkerToolParts;
import slimeknights.tconstruct.tools.stats.PlatingMaterialStats;

import java.util.function.Consumer;

public class GTCToolsRecipeProv extends GTCRecipeProvider implements IMaterialRecipeHelper, IToolRecipeHelper {
    public GTCToolsRecipeProv(PackOutput generator) {
        super(generator);
    }

    @Override
    public @NotNull String getName() {
        return "GTConstruct Tools Recipes";
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        this.addToolBuildingRecipes(consumer);
        this.addPartRecipes(consumer);
    }

    private void addToolBuildingRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "tools/building/";

        toolBuilding(consumer, GTCTools.saw, folder);
        toolBuilding(consumer, GTCTools.screwdriver, folder);
        toolBuilding(consumer, GTCTools.file, folder);

    }

    private void addPartRecipes(Consumer<FinishedRecipe> consumer) {
        String partFolder = "tools/parts/";
        String castFolder = "smeltery/casts/";

        partRecipes(consumer, GTCToolParts.sawBlade,     GTCSmeltery.sawBladeCast,     2, partFolder, castFolder);
        partRecipes(consumer, GTCToolParts.fileHead,     GTCSmeltery.fileHeadCast,     1, partFolder, castFolder);
        partCasting(consumer, GTCToolParts.wirecutterClaws.get(), GTCSmeltery.wirecutterClawsCast, 3, partFolder, castFolder);
        partCasting(consumer, GTCToolParts.screwdriverTip.get(), GTCSmeltery.screwdriverTipCast, 1, partFolder, castFolder);
        partRecipes(consumer, GTCToolParts.wrenchHandle,     GTCSmeltery.wrenchHandleCast,     4, partFolder, castFolder);
    }
}
