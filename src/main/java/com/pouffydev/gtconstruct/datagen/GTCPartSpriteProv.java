package com.pouffydev.gtconstruct.datagen;

import com.pouffydev.gtconstruct.GTConstruct;
import slimeknights.tconstruct.library.client.data.material.AbstractPartSpriteProvider;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

public class GTCPartSpriteProv extends AbstractPartSpriteProvider {
    public GTCPartSpriteProv() {
        super(GTConstruct.MOD_ID);
    }

    @Override
    public String getName() {
        return "GregTech Construct Parts";
    }

    @Override
    protected void addAllSpites() {
        addTexture(GTConstruct.id("item/screwdriver_head"), HeadMaterialStats.ID);
        addTexture(GTConstruct.id("item/wirecutter_claws"), HeadMaterialStats.ID);
        addTexture(GTConstruct.id("item/saw_blade"), HeadMaterialStats.ID);
        addTexture(GTConstruct.id("item/wrench_handle"), HandleMaterialStats.ID);

        buildTool("saw").addBreakableHead("head").addHandle("handle").addBinding("binding");
        buildTool("screwdriver").addBreakableHead("head").addHandle("handle");
    }
}
