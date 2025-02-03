package com.pouffydev.gtconstruct.registry;

import com.pouffydev.gtconstruct.GTCModule;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.tools.TinkerToolParts;

public class GTCSmeltery extends GTCModule {

    public static final RegistryObject<CreativeModeTab> tabSmeltery = CREATIVE_TABS.register(
            "smeltery", () -> CreativeModeTab.builder().title(makeTranslation("itemGroup", "smeltery"))
                    .icon(() -> new ItemStack(GTCSmeltery.screwdriverTipCast))
                    .displayItems(GTCSmeltery::addTabItems)
                    .withTabsBefore(GTCToolParts.tabToolParts.getId())
                    .build());

    public static final CastItemObject sawBladeCast = ITEMS.registerCast(GTCToolParts.sawBlade, ITEM_PROPS);
    public static final CastItemObject fileHeadCast = ITEMS.registerCast(GTCToolParts.fileHead, ITEM_PROPS);
    public static final CastItemObject screwdriverTipCast = ITEMS.registerCast(GTCToolParts.screwdriverTip, ITEM_PROPS);
    public static final CastItemObject wirecutterClawsCast = ITEMS.registerCast(GTCToolParts.wirecutterClaws, ITEM_PROPS);
    public static final CastItemObject wrenchHandleCast = ITEMS.registerCast(GTCToolParts.wrenchHandle, ITEM_PROPS);

    private static void addTabItems(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {
        output.accept(fileHeadCast);
        output.accept(sawBladeCast);
        output.accept(screwdriverTipCast);
        output.accept(wirecutterClawsCast);
        output.accept(wrenchHandleCast);
    }
}
