package com.pouffydev.gtconstruct.datagen;

import com.pouffydev.gtconstruct.GTConstruct;
import com.pouffydev.gtconstruct.common.stats.PlungerHeadMaterialStats;
import slimeknights.tconstruct.library.client.data.material.AbstractPartSpriteProvider;

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
        addHead("screwdriver_tip");
        addHead("wirecutter_claws");
        addHead("saw_blade");
        addHandle("wrench_handle");
        addHead("file_head");
        addPart("plunger_head", PlungerHeadMaterialStats.ID);

        buildTool("saw").addBreakableHead("head").addHandle("handle").addBinding("binding");
        buildTool("screwdriver").addBreakableHead("head").addHandle("handle").addBinding("binding");
        buildTool("file").addBreakableHead("head").addHandle("handle").addBinding("binding");
        buildTool("wire_cutters").addBreakableHead("head").addHandle("right_handle").addHandle("left_handle").addBinding("binding");
        buildTool("plunger").addBreakablePart("head", PlungerHeadMaterialStats.ID).addHandle("handle").addBinding("binding");
    }
}
