package com.pouffydev.gtconstruct.datagen;

import com.pouffydev.gtconstruct.GTConstruct;
import com.pouffydev.gtconstruct.common.stats.GTStatlessMaterialStats;
import com.pouffydev.gtconstruct.common.stats.PlungerHeadMaterialStats;
import com.pouffydev.gtconstruct.common.stats.SoftMalletHeadMaterialStats;
import slimeknights.tconstruct.library.client.data.material.AbstractPartSpriteProvider;
import slimeknights.tconstruct.tools.stats.StatlessMaterialStats;

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
        uniquePart("plunger_head");
        uniquePart("soft_mallet_head");
        uniquePart("mortar_bowl");

        buildTool("saw").addBreakableHead("head").addHandle("handle").addBinding("binding");
        buildTool("screwdriver").addBreakableHead("head").addHandle("handle").addBinding("binding");
        buildTool("file").addBreakableHead("head").addHandle("handle").addBinding("binding");
        buildTool("wire_cutters").addBreakableHead("head").addHandle("right_handle").addHandle("left_handle").addBinding("binding");
        buildTool("plunger").addBreakablePart("head", StatlessMaterialStats.REPAIR_KIT.getIdentifier()).addHandle("handle").addBinding("binding");
        buildTool("soft_mallet").addBreakablePart("head", StatlessMaterialStats.REPAIR_KIT.getIdentifier()).addHandle("handle").addBinding("binding");
        buildTool("mortar").addBreakableHead("head").addPart("mortar_bowl", StatlessMaterialStats.REPAIR_KIT.getIdentifier());
    }

    //Just to ensure sprite creation for the custom part types
    void uniquePart(String partName) {
        addPart(partName, StatlessMaterialStats.REPAIR_KIT.getIdentifier());
    }
}
