package com.pouffydev.gtconstruct.registry;

import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.pouffydev.gtconstruct.GTCModule;
import com.pouffydev.gtconstruct.common.item.ModifiableGTToolItem;
import com.pouffydev.gtconstruct.datagen.GTCModifierProv;
import com.pouffydev.gtconstruct.datagen.GTCToolDefinitionProv;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.mantle.registration.object.ItemObject;

public class GTCTools extends GTCModule {

    public static final ItemObject<ModifiableGTToolItem> saw = ITEMS.register("saw", () -> new ModifiableGTToolItem(UNSTACKABLE_PROPS, GTCToolDefinitions.SAW, GTToolType.SAW));
    public static final ItemObject<ModifiableGTToolItem> screwdriver = ITEMS.register("screwdriver", () -> new ModifiableGTToolItem(UNSTACKABLE_PROPS, GTCToolDefinitions.SAW, GTToolType.SCREWDRIVER));

    @SubscribeEvent
    void gatherData(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        PackOutput output = generator.getPackOutput();

        generator.addProvider(event.includeServer(), new GTCToolDefinitionProv(output));
        generator.addProvider(event.includeServer(), new GTCModifierProv(output));
    }
}
