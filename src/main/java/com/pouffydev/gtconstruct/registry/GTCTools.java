package com.pouffydev.gtconstruct.registry;

import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.pouffydev.gtconstruct.GTCModule;
import com.pouffydev.gtconstruct.common.item.ModifiableGTToolItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import slimeknights.mantle.registration.object.EnumObject;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.library.tools.helper.ToolBuildHandler;
import slimeknights.tconstruct.library.tools.item.IModifiable;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class GTCTools extends GTCModule {

    public static final ItemObject<ModifiableGTToolItem> saw = ITEMS.register("saw", () -> new ModifiableGTToolItem(UNSTACKABLE_PROPS, GTCToolDefinitions.SAW, GTToolType.SAW));
    public static final ItemObject<ModifiableGTToolItem> screwdriver = ITEMS.register("screwdriver", () -> new ModifiableGTToolItem(UNSTACKABLE_PROPS, GTCToolDefinitions.SAW, GTToolType.SCREWDRIVER));

    private static void addTabItems(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output tab) {
        Consumer<ItemStack> output = tab::accept;
        acceptTool(output, saw);
        acceptTool(output, screwdriver);
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
