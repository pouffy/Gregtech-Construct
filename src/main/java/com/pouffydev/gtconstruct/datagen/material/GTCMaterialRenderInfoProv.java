package com.pouffydev.gtconstruct.datagen.material;

import com.pouffydev.gtconstruct.registry.GTCMaterialIds;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialRenderInfoProvider;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.tools.data.material.MaterialIds;

public class GTCMaterialRenderInfoProv extends AbstractMaterialRenderInfoProvider {
    public GTCMaterialRenderInfoProv(PackOutput packOutput, @Nullable AbstractMaterialSpriteProvider materialSprites, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, materialSprites, existingFileHelper);
    }

    @Override
    protected void addMaterialRenderInfo() {
        buildRenderInfo(GTCMaterialIds.sterlingSilver).fallbacks("metal");
        buildRenderInfo(GTCMaterialIds.bismuthBronze).fallbacks("metal");
        buildRenderInfo(GTCMaterialIds.cupronickel).fallbacks("metal");
        buildRenderInfo(GTCMaterialIds.blackBronze).fallbacks("metal");
        buildRenderInfo(GTCMaterialIds.blackSteel).fallbacks("metal");
        buildRenderInfo(GTCMaterialIds.blueSteel).fallbacks("metal");
        buildRenderInfo(GTCMaterialIds.redSteel).fallbacks("metal");
        buildRenderInfo(GTCMaterialIds.bismuth).fallbacks("metal");

        buildRenderInfo(GTCMaterialIds.siliconeRubber).fallbacks("cloth");
        buildRenderInfo(GTCMaterialIds.styreneButadieneRubber).fallbacks("cloth");
        buildRenderInfo(GTCMaterialIds.polybenzimidazole).fallbacks("cloth");
        buildRenderInfo(GTCMaterialIds.polyethylene).fallbacks("cloth");
        buildRenderInfo(GTCMaterialIds.polytetrachloroethylene).fallbacks("cloth");
        buildRenderInfo(GTCMaterialIds.rubber).fallbacks("cloth");

    }

    @Override
    public String getName() {
        return "GregTech Construct Material Render Info";
    }
}
