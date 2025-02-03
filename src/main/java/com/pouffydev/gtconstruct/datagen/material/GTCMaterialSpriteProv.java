package com.pouffydev.gtconstruct.datagen.material;

import com.pouffydev.gtconstruct.common.stats.PlungerHeadMaterialStats;
import com.pouffydev.gtconstruct.registry.GTCMaterialIds;
import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToColorMapping;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToSpriteTransformer;
import slimeknights.tconstruct.library.client.data.spritetransformer.ISpriteTransformer;
import slimeknights.tconstruct.library.materials.definition.MaterialVariantId;
import slimeknights.tconstruct.tools.data.material.MaterialIds;
import slimeknights.tconstruct.tools.stats.StatlessMaterialStats;

import static slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider.WOOD;

public class GTCMaterialSpriteProv extends AbstractMaterialSpriteProvider {
    @Override
    public String getName() {
        return "GregTech Construct Material Sprites";
    }

    @Override
    protected void addAllMaterials() {
        buildMaterial(GTCMaterialIds.bismuth)
                .meleeHarvest()
                .fallbacks("metal").ranged()
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF24383e).addARGB(102, 0xFF274449).addARGB(140, 0xFF315e63).addARGB(178, 0xFF3d7d81).addARGB(216, 0xFF489fa2).addARGB(255, 0xFF55c5c5).build());

        buildMaterial(GTCMaterialIds.sterlingSilver)
                .meleeHarvest()
                .fallbacks("metal").ranged()
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF24221a).addARGB(102, 0xFF464338).addARGB(140, 0xFF716e60).addARGB(178, 0xFFa5a18e).addARGB(216, 0xFFe2dcc7).addARGB(255, 0xFFf9f4e4).build());
        buildMaterial(GTCMaterialIds.blackBronze)
                .meleeHarvest()
                .fallbacks("metal").ranged()
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF241e19).addARGB(102, 0xFF2c2520).addARGB(140, 0xFF3c342d).addARGB(178, 0xFF50463e).addARGB(216, 0xFF655950).addARGB(255, 0xFF7c6f64).build());
        buildMaterial(GTCMaterialIds.cupronickel)
                .meleeHarvest()
                .fallbacks("metal").ranged()
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF5c1c08).addARGB(102, 0xFF652e15).addARGB(140, 0xFF814927).addARGB(178, 0xFFa16c3e).addARGB(216, 0xFFc1945a).addARGB(255, 0xFFe3c27b).build());
        buildMaterial(GTCMaterialIds.bismuthBronze)
                .meleeHarvest()
                .fallbacks("metal").ranged()
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF42301e).addARGB(102, 0xFF503c23).addARGB(140, 0xFF6e5530).addARGB(178, 0xFF917440).addARGB(216, 0xFFb99551).addARGB(255, 0xFFe3bb63).build());

        buildMaterial(GTCMaterialIds.blackSteel)
                .meleeHarvest().armor()
                .fallbacks("metal").ranged()
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF100c0b).addARGB(102, 0xFF171513).addARGB(140, 0xFF242120).addARGB(178, 0xFF343231).addARGB(216, 0xFF464545).addARGB(255, 0xFF5b5b5b).build());
        buildMaterial(GTCMaterialIds.blueSteel)
                .meleeHarvest().armor()
                .fallbacks("metal").ranged()
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF101227).addARGB(102, 0xFF191f34).addARGB(140, 0xFF28324b).addARGB(178, 0xFF3b4b69).addARGB(216, 0xFF51688b).addARGB(255, 0xFF6a89b0).build());
        buildMaterial(GTCMaterialIds.redSteel)
                .meleeHarvest().armor()
                .fallbacks("metal").ranged()
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF270a0a).addARGB(102, 0xFF301717).addARGB(140, 0xFF432929).addARGB(178, 0xFF5a4242).addARGB(216, 0xFF735f5f).addARGB(255, 0xFF8f8181).build());

        buildMaterial(GTCMaterialIds.siliconeRubber)
                .fallbacks("cloth")
                .statType(StatlessMaterialStats.BINDING.getIdentifier(), PlungerHeadMaterialStats.ID).repairKit()
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF686865).addARGB(102, 0xFF81817e).addARGB(140, 0xFF979795).addARGB(178, 0xFFadadac).addARGB(216, 0xFFc2c2c2).addARGB(255, 0xFFd8d8d8).build());
        buildMaterial(GTCMaterialIds.styreneButadieneRubber)
                .fallbacks("cloth")
                .statType(StatlessMaterialStats.BINDING.getIdentifier(), PlungerHeadMaterialStats.ID).repairKit()
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF0a0706).addARGB(102, 0xFF12100e).addARGB(140, 0xFF1a1714).addARGB(178, 0xFF221f1b).addARGB(216, 0xFF2a2823).addARGB(255, 0xFF5b5955).build());
        buildMaterial(GTCMaterialIds.polybenzimidazole)
                .fallbacks("cloth")
                .statType(StatlessMaterialStats.BINDING.getIdentifier(), PlungerHeadMaterialStats.ID).repairKit()
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF1a160e).addARGB(102, 0xFF221f19).addARGB(140, 0xFF292621).addARGB(178, 0xFF312e2b).addARGB(216, 0xFF393735).addARGB(255, 0xFF676664).build());
        buildMaterial(GTCMaterialIds.polyethylene)
                .fallbacks("cloth")
                .statType(StatlessMaterialStats.BINDING.getIdentifier(), PlungerHeadMaterialStats.ID).repairKit()
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF595959).addARGB(102, 0xFF6d6d6d).addARGB(140, 0xFF7f7f7f).addARGB(178, 0xFF919191).addARGB(216, 0xFFa2a2a2).addARGB(255, 0xFFbdbdbd).build());
        buildMaterial(GTCMaterialIds.polytetrachloroethylene)
                .fallbacks("cloth")
                .statType(StatlessMaterialStats.BINDING.getIdentifier(), PlungerHeadMaterialStats.ID).repairKit()
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF131313).addARGB(102, 0xFF262626).addARGB(140, 0xFF353535).addARGB(178, 0xFF474747).addARGB(216, 0xFF595959).addARGB(255, 0xFF828282).build());
        buildMaterial(GTCMaterialIds.rubber)
                .fallbacks("cloth")
                .statType(StatlessMaterialStats.BINDING.getIdentifier(), PlungerHeadMaterialStats.ID).repairKit()
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF070706).addARGB(102, 0xFF10100d).addARGB(140, 0xFF181813).addARGB(178, 0xFF21211a).addARGB(216, 0xFF2b2b21).addARGB(255, 0xFF5c5c54).build());
    }

    public static ISpriteTransformer transformerFromSprite(ResourceLocation texture, int frames, int highlightColor) {
        GreyToSpriteTransformer.Builder builder = GreyToSpriteTransformer.builderFromBlack();
        builder.addTexture( 63, texture, 0xFF404040)
                .addTexture(102, texture, 0xFF808080)
                .addTexture(140, texture);
        if (highlightColor != 0) {
            builder.addTexture(216, texture).addARGB(255, highlightColor);
        }
        if (frames > 1) {
            return builder.animated(texture, frames);
        }
        return builder.build();
    }

    /** Adds a plank type as a wood variant */
    private MaterialSpriteInfoBuilder buildPlanks(MaterialVariantId material) {
        return buildMaterial(material)
                .variant().meleeHarvest().ranged().shieldCore().statType(WOOD)
                .fallbacks("wood", "stick", "primitive");
    }
}
