package com.pouffydev.gtconstruct.registry;

import com.pouffydev.gtconstruct.GTCModule;
import com.pouffydev.gtconstruct.common.stats.PlungerHeadMaterialStats;
import com.pouffydev.gtconstruct.common.stats.SoftMalletHeadMaterialStats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.materials.MaterialRegistry;
import slimeknights.tconstruct.library.materials.definition.MaterialVariantId;
import slimeknights.tconstruct.library.tools.helper.ToolBuildHandler;
import slimeknights.tconstruct.library.tools.part.IMaterialItem;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.tools.TinkerToolParts;
import slimeknights.tconstruct.tools.TinkerTools;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class GTCToolParts extends GTCModule {

    public static final RegistryObject<CreativeModeTab> tabToolParts = CREATIVE_TABS.register(
            "tool_parts", () -> CreativeModeTab.builder().title(makeTranslation("itemGroup", "tool_parts"))
                    .icon(() -> {
                        MaterialVariantId material;
                        if (MaterialRegistry.isFullyLoaded()) {
                            material = ToolBuildHandler.RANDOM.getMaterial(HeadMaterialStats.ID, RandomSource.create());
                        } else {
                            material = ToolBuildHandler.getRenderMaterial(0);
                        }
                        return GTCToolParts.screwdriverTip.get().withMaterialForDisplay(material);
                    })
                    .displayItems(GTCToolParts::addTabItems)
                    .withTabsBefore(GTCTools.tabTools.getId())
                    .withSearchBar()
                    .build());

    public static final ItemObject<ToolPartItem> sawBlade = ITEMS.register("saw_blade", () -> new ToolPartItem(ITEM_PROPS, HeadMaterialStats.ID));
    public static final ItemObject<ToolPartItem> screwdriverTip = ITEMS.register("screwdriver_tip", () -> new ToolPartItem(ITEM_PROPS, HeadMaterialStats.ID));
    public static final ItemObject<ToolPartItem> fileHead = ITEMS.register("file_head", () -> new ToolPartItem(ITEM_PROPS, HeadMaterialStats.ID));
    public static final ItemObject<ToolPartItem> wirecutterClaws = ITEMS.register("wirecutter_claws", () -> new ToolPartItem(ITEM_PROPS, HeadMaterialStats.ID));
    public static final ItemObject<ToolPartItem> wrenchHandle = ITEMS.register("wrench_handle", () -> new ToolPartItem(ITEM_PROPS, HandleMaterialStats.ID));
    public static final ItemObject<ToolPartItem> plungerHead = ITEMS.register("plunger_head", () -> new ToolPartItem(ITEM_PROPS, PlungerHeadMaterialStats.ID));
    public static final ItemObject<ToolPartItem> softMalletHead = ITEMS.register("soft_mallet_head", () -> new ToolPartItem(ITEM_PROPS, SoftMalletHeadMaterialStats.ID));

    private static void addTabItems(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output tab) {
        Consumer<ItemStack> output = tab::accept;
        accept(output, sawBlade);
        accept(output, screwdriverTip);
        accept(output, fileHead);
        accept(output, wirecutterClaws);
        accept(output, wrenchHandle);
        accept(output, plungerHead);
        accept(output, softMalletHead);
    }

    private static void accept(Consumer<ItemStack> output, Supplier<? extends IMaterialItem> item) {
        item.get().addVariants(output, "");
    }
}
