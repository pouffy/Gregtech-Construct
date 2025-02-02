package com.pouffydev.gtconstruct.datagen.material;

import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToSpriteTransformer;
import slimeknights.tconstruct.library.client.data.spritetransformer.ISpriteTransformer;
import slimeknights.tconstruct.library.materials.definition.MaterialVariantId;

import static slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider.WOOD;

public class GTCMaterialSpriteProv extends AbstractMaterialSpriteProvider {
    @Override
    public String getName() {
        return "GregTech Construct Material Sprites";
    }

    @Override
    protected void addAllMaterials() {

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
