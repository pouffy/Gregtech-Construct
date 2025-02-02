package com.pouffydev.gtconstruct.registry;

import com.pouffydev.gtconstruct.GTCModule;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

public class GTCToolParts extends GTCModule {

    public static final ItemObject<ToolPartItem> sawBlade = ITEMS.register("saw_blade", () -> new ToolPartItem(ITEM_PROPS, HeadMaterialStats.ID));
    public static final ItemObject<ToolPartItem> screwdriverTip = ITEMS.register("screwdriver_tip", () -> new ToolPartItem(ITEM_PROPS, HeadMaterialStats.ID));
    public static final ItemObject<ToolPartItem> fileHead = ITEMS.register("file_head", () -> new ToolPartItem(ITEM_PROPS, HeadMaterialStats.ID));
    public static final ItemObject<ToolPartItem> wirecutterClaws = ITEMS.register("wirecutter_claws", () -> new ToolPartItem(ITEM_PROPS, HeadMaterialStats.ID));
    public static final ItemObject<ToolPartItem> wrenchHandle = ITEMS.register("wrench_handle", () -> new ToolPartItem(ITEM_PROPS, HandleMaterialStats.ID));
    //public static final ItemObject<ToolPartItem> plungerHead = ITEMS.register("plunger_head", () -> new ToolPartItem(ITEM_PROPS, HeadMaterialStats.ID));

}
