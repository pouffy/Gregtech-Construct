package com.pouffydev.gtconstruct.datagen;

import com.pouffydev.gtconstruct.GTConstruct;
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
        addHead("screwdriver_head");
        addHead("wirecutter_claws");
        addHead("saw_blade");
        addHandle("wrench_handle");
        addHead("file_head");

        buildTool("saw").addBreakableHead("head").addHandle("handle").addBinding("binding");
        buildTool("screwdriver").addBreakableHead("head").addHandle("handle");
    }
}
