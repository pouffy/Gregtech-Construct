package com.pouffydev.gtconstruct.registry;

import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.pouffydev.gtconstruct.GTCModule;
import com.pouffydev.gtconstruct.GTConstruct;
import com.pouffydev.gtconstruct.common.item.ModifiableGTToolItem;
import com.pouffydev.gtconstruct.datagen.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.library.client.data.material.GeneratorPartTextureJsonGenerator;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.tools.data.sprite.TinkerMaterialSpriteProvider;

public class GTCTools extends GTCModule {

    public static final ItemObject<ModifiableGTToolItem> saw = ITEMS.register("saw", () -> new ModifiableGTToolItem(UNSTACKABLE_PROPS, GTCToolDefinitions.SAW, GTToolType.SAW));
    public static final ItemObject<ModifiableGTToolItem> screwdriver = ITEMS.register("screwdriver", () -> new ModifiableGTToolItem(UNSTACKABLE_PROPS, GTCToolDefinitions.SAW, GTToolType.SCREWDRIVER));

    @SubscribeEvent
    void gatherData(final GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        PackOutput packOutput = generator.getPackOutput();

        boolean server = event.includeServer();
        boolean client = event.includeClient();

        generator.addProvider(server, new GTCToolDefinitionProv(packOutput));
        generator.addProvider(server, new GTCModifierProv(packOutput));
        generator.addProvider(server, new GTCStationSlotLayoutProv(packOutput));
        generator.addProvider(server, new GTCToolsRecipeProv(packOutput));

        generator.addProvider(client, new GTCToolItemModelProv(packOutput, existingFileHelper));
        GTCPartSpriteProv partSprites = new GTCPartSpriteProv();
        TinkerMaterialSpriteProvider materialSprites = new TinkerMaterialSpriteProvider();
        generator.addProvider(client, new GeneratorPartTextureJsonGenerator(packOutput, GTConstruct.MOD_ID, partSprites));
        generator.addProvider(client, new MaterialPartTextureGenerator(packOutput, existingFileHelper, partSprites, materialSprites));
    }
}
