package com.pouffydev.gtconstruct.datagen;

import com.pouffydev.gtconstruct.registry.GTCSmeltery;
import com.pouffydev.gtconstruct.registry.GTCToolParts;
import com.pouffydev.gtconstruct.registry.GTCTools;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import slimeknights.tconstruct.common.data.BaseRecipeProvider;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IToolRecipeHelper;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

import java.util.function.Consumer;

public class GTCToolsRecipeProv extends BaseRecipeProvider implements IMaterialRecipeHelper, IToolRecipeHelper {
    public GTCToolsRecipeProv(PackOutput generator) {
        super(generator);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        this.addToolBuildingRecipes(consumer);
        this.addPartRecipes(consumer);
    }

    @Override
    public String getName() {
        return "";
    }

    private void addToolBuildingRecipes(Consumer<FinishedRecipe> consumer) {
        String folder = "tools/building/";

        toolBuilding(consumer, GTCTools.saw, folder);
        toolBuilding(consumer, GTCTools.screwdriver, folder);

    }

    private void addPartRecipes(Consumer<FinishedRecipe> consumer) {
        String partFolder = "tools/parts/";
        String castFolder = "smeltery/casts/";

        partRecipes(consumer, GTCToolParts.sawBlade,     GTCSmeltery.sawBladeCast,     2, partFolder, castFolder);
        partRecipes(consumer, GTCToolParts.fileHead,     GTCSmeltery.fileHeadCast,     1, partFolder, castFolder);
        partRecipes(consumer, GTCToolParts.screwdriverHead,     GTCSmeltery.screwdriverHeadCast,     1, partFolder, castFolder);
        partRecipes(consumer, GTCToolParts.wirecutterClaws,     GTCSmeltery.wirecutterClawsCast,     3, partFolder, castFolder);
        partRecipes(consumer, GTCToolParts.wrenchHandle,     GTCSmeltery.wrenchHandleCast,     4, partFolder, castFolder);

    }
}
