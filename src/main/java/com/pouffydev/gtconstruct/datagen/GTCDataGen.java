package com.pouffydev.gtconstruct.datagen;

import com.pouffydev.gtconstruct.GTConstruct;
import com.pouffydev.gtconstruct.datagen.lang.LangHandler;
import com.pouffydev.gtconstruct.datagen.material.*;
import com.pouffydev.gtconstruct.registry.GTCRegistration;
import com.tterrag.registrate.providers.ProviderType;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.client.data.material.GeneratorPartTextureJsonGenerator;
import slimeknights.tconstruct.library.client.data.material.MaterialPaletteDebugGenerator;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.tools.data.material.MaterialRenderInfoProvider;
import slimeknights.tconstruct.tools.data.sprite.TinkerMaterialSpriteProvider;

import java.util.concurrent.CompletableFuture;

public class GTCDataGen {

    public static void gatherData(GatherDataEvent event) {
        addExtraRegistrateData();

        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        boolean server = event.includeServer();
        boolean client = event.includeClient();

        generator.addProvider(server, new GTCToolsRecipeProv(packOutput));
        generator.addProvider(server, new GTCToolDefinitionProv(packOutput));
        generator.addProvider(server, new GTCStationSlotLayoutProv(packOutput));
        generator.addProvider(client, new GTCToolItemModelProv(packOutput, existingFileHelper));
        GTCPartSpriteProv partSprites = new GTCPartSpriteProv();
        generator.addProvider(client, new GeneratorPartTextureJsonGenerator(packOutput, GTConstruct.MOD_ID, partSprites));
        generator.addProvider(server, new GTCModifierProv(packOutput));
        generator.addProvider(client, new GTCItemModelProv(packOutput, existingFileHelper));

        GTCMaterialDataProv materials = new GTCMaterialDataProv(packOutput);
        GTCMaterialSpriteProv materialSprites = new GTCMaterialSpriteProv();

        generator.addProvider(client, new GTCMaterialRenderInfoProv(packOutput, materialSprites, existingFileHelper));
        generator.addProvider(client, new MaterialPartTextureGenerator(packOutput, existingFileHelper, partSprites, materialSprites));
        generator.addProvider(client, new MaterialPaletteDebugGenerator(packOutput, GTConstruct.MOD_ID, materialSprites));

        generator.addProvider(server, new GTCMaterialRecipeProv(packOutput));
        generator.addProvider(server, materials);
        generator.addProvider(server, new GTCMaterialStatsProv(packOutput, materials));
        generator.addProvider(server, new GTCMaterialTraitsProv(packOutput, materials));
    }
    private static void addExtraRegistrateData() {
        GTCRegistrateTags.addGenerators();

        GTCRegistration.REGISTRATE.addDataGenerator(ProviderType.LANG, LangHandler::init);
    }

}
