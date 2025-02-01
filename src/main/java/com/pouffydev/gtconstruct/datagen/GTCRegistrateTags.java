package com.pouffydev.gtconstruct.datagen;

import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import com.pouffydev.gtconstruct.datagen.backing.GTCTagsProvider;
import com.pouffydev.gtconstruct.registry.GTCRegistration;
import com.pouffydev.gtconstruct.registry.GTCTools;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class GTCRegistrateTags {
    public static void addGenerators() {
        GTCRegistration.REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, GTCRegistrateTags::genBlockTags);
        GTCRegistration.REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, GTCRegistrateTags::genItemTags);
        GTCRegistration.REGISTRATE.addDataGenerator(ProviderType.FLUID_TAGS, GTCRegistrateTags::genFluidTags);
        GTCRegistration.REGISTRATE.addDataGenerator(ProviderType.ENTITY_TAGS, GTCRegistrateTags::genEntityTags);
    }

    private static void genBlockTags(RegistrateTagsProvider<Block> provIn) {

    }

    private static void genItemTags(RegistrateTagsProvider<Item> provIn) {
        GTCTagsProvider<Item> prov = new GTCTagsProvider<>(provIn, Item::builtInRegistryHolder);
        prov.tag(TagUtil.createItemTag("tools/saws", false)).add(GTCTools.saw.asItem());
        prov.tag(TagUtil.createItemTag("tools/screwdrivers", false)).add(GTCTools.screwdriver.asItem());
    }

    private static void genFluidTags(RegistrateTagsProvider<?> provIn) {

    }

    private static void genEntityTags(RegistrateTagsProvider<?> provIn) {

    }
}
