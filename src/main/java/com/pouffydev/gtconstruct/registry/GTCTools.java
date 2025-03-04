package com.pouffydev.gtconstruct.registry;

import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.pouffydev.gtconstruct.GTCModule;
import com.pouffydev.gtconstruct.common.item.ModifiableGTToolItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.registration.object.EnumObject;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.library.tools.helper.ToolBuildHandler;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.tables.TinkerTables;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class GTCTools extends GTCModule {

    public static final RegistryObject<CreativeModeTab> tabTools = CREATIVE_TABS.register(
            "tools", () -> CreativeModeTab.builder().title(makeTranslation("itemGroup", "tools"))
                    .icon(() -> GTCTools.screwdriver.get().getRenderTool())
                    .displayItems(GTCTools::addTabItems)
                    .withTabsBefore(TinkerTables.tabTables.getId())
                    .withSearchBar()
                    .build());


    public static final ItemObject<ModifiableGTToolItem> saw = ITEMS.register("saw", () -> new ModifiableGTToolItem(UNSTACKABLE_PROPS, GTCToolDefinitions.SAW, GTToolType.SAW));

    public static final ItemObject<ModifiableGTToolItem> screwdriver = ITEMS.register("screwdriver", () -> new ModifiableGTToolItem(UNSTACKABLE_PROPS, GTCToolDefinitions.SCREWDRIVER, GTToolType.SCREWDRIVER));

    public static final ItemObject<ModifiableGTToolItem> file = ITEMS.register("file", () -> new ModifiableGTToolItem(UNSTACKABLE_PROPS, GTCToolDefinitions.FILE, GTToolType.FILE));

    public static final ItemObject<ModifiableGTToolItem> wirecutter = ITEMS.register("wire_cutters", () -> new ModifiableGTToolItem(UNSTACKABLE_PROPS, GTCToolDefinitions.WIRECUTTER, GTToolType.WIRE_CUTTER));

    public static final ItemObject<ModifiableGTToolItem> plunger = ITEMS.register("plunger", () -> new ModifiableGTToolItem(UNSTACKABLE_PROPS, GTCToolDefinitions.PLUNGER, GTToolType.PLUNGER));

    public static final ItemObject<ModifiableGTToolItem> softMallet = ITEMS.register("soft_mallet", () -> new ModifiableGTToolItem(UNSTACKABLE_PROPS, GTCToolDefinitions.SOFT_MALLET, GTToolType.SOFT_MALLET));

    //TODO: Wrench, Crafting Hammer, Soft Mallet

    private static void addTabItems(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output tab) {
        Consumer<ItemStack> output = tab::accept;
        acceptTool(output, saw);
        acceptTool(output, screwdriver);
        acceptTool(output, file);
        acceptTool(output, wirecutter);
        acceptTool(output, plunger);
        acceptTool(output, softMallet);
    }


    /** Adds a tool to the tab */
    private static void acceptTool(Consumer<ItemStack> output, Supplier<? extends IModifiable> tool) {
        ToolBuildHandler.addVariants(output, tool.get(), "");
    }

    /** Adds a tool to the tab */
    private static void acceptTools(Consumer<ItemStack> output, EnumObject<?,? extends IModifiable> tools) {
        tools.forEach(tool -> ToolBuildHandler.addVariants(output, tool, ""));
    }
}
