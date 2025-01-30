package com.pouffydev.gtconstruct.registry;

import com.pouffydev.gtconstruct.GTCModule;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.tools.TinkerToolParts;

public class GTCSmeltery extends GTCModule {

    public static final CastItemObject sawBladeCast = ITEMS.registerCast(GTCToolParts.sawBlade, ITEM_PROPS);
    public static final CastItemObject screwdriverHeadCast = ITEMS.registerCast(GTCToolParts.screwdriverHead, ITEM_PROPS);
}
