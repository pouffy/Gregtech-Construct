package com.pouffydev.gtconstruct.registry;

import com.pouffydev.gtconstruct.GTCModule;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.tools.TinkerToolParts;

public class GTCSmeltery extends GTCModule {

    public static final CastItemObject sawBladeCast = ITEMS.registerCast(GTCToolParts.sawBlade, ITEM_PROPS);
    public static final CastItemObject fileHeadCast = ITEMS.registerCast(GTCToolParts.fileHead, ITEM_PROPS);
    public static final CastItemObject screwdriverHeadCast = ITEMS.registerCast(GTCToolParts.screwdriverHead, ITEM_PROPS);
    public static final CastItemObject wirecutterClawsCast = ITEMS.registerCast(GTCToolParts.wirecutterClaws, ITEM_PROPS);
    public static final CastItemObject wrenchHandleCast = ITEMS.registerCast(GTCToolParts.wrenchHandle, ITEM_PROPS);
}
