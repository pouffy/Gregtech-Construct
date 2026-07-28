package com.pouffydev.gtconstruct.datagen;

import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import com.pouffydev.gtconstruct.GTConstruct;
import com.pouffydev.gtconstruct.datagen.backing.GTCTagsProvider;
import com.pouffydev.gtconstruct.registry.GTCRegistration;
import com.pouffydev.gtconstruct.registry.GTCSmeltery;
import com.pouffydev.gtconstruct.registry.GTCToolParts;
import com.pouffydev.gtconstruct.registry.GTCTools;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.common.TinkerTags;

import static slimeknights.tconstruct.common.TinkerTags.Items.SMALL_TOOLS;

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
        prov.tag(TagUtil.createItemTag("tools/files", false)).add(GTCTools.file.asItem());
        prov.tag(TagUtil.createItemTag("tools/wire_cutters", false)).add(GTCTools.wirecutter.asItem());
        prov.tag(TagUtil.createItemTag("tools/plungers", false)).add(GTCTools.plunger.asItem());
        prov.tag(TagUtil.createItemTag("tools/mallets", false)).add(GTCTools.softMallet.asItem());
        prov.tag(TagUtil.createItemTag("tools/mortars", false)).add(GTCTools.mortar.asItem());

        prov.tag(ItemTags.create(GTConstruct.appendId("gtceu:tools/crafting_saws"))).add(GTCTools.saw.asItem());
        prov.tag(ItemTags.create(GTConstruct.appendId("gtceu:tools/crafting_screwdrivers"))).add(GTCTools.screwdriver.asItem());
        prov.tag(ItemTags.create(GTConstruct.appendId("gtceu:tools/crafting_files"))).add(GTCTools.file.asItem());
        prov.tag(ItemTags.create(GTConstruct.appendId("gtceu:tools/crafting_wire_cutters"))).add(GTCTools.wirecutter.asItem());
        prov.tag(ItemTags.create(GTConstruct.appendId("gtceu:tools/crafting_mortars"))).add(GTCTools.mortar.asItem());

        prov.tag(TinkerTags.Items.TOOL_PARTS)
                .add(
                        GTCToolParts.sawBlade.asItem(),
                        GTCToolParts.screwdriverTip.asItem(),
                        GTCToolParts.fileHead.asItem(),
                        GTCToolParts.wirecutterClaws.asItem(),
                        GTCToolParts.wrenchHandle.asItem(),
                        GTCToolParts.plungerHead.asItem(),
                        GTCToolParts.softMalletHead.asItem(),
                        GTCToolParts.mortarBowl.asItem()
                );

        prov.tag(TagUtil.createItemTag("tools", false))
                .add(
                        GTCTools.saw.asItem(),
                        GTCTools.screwdriver.asItem(),
                        GTCTools.file.asItem(),
                        GTCTools.wirecutter.asItem(),
                        GTCTools.plunger.asItem(),
                        GTCTools.softMallet.asItem(),
                        GTCTools.mortar.asItem()
                );

        prov.tag(TagUtil.createItemTag("tools", true))
                .add(
                        GTCTools.saw.asItem(),
                        GTCTools.screwdriver.asItem(),
                        GTCTools.file.asItem(),
                        GTCTools.wirecutter.asItem(),
                        GTCTools.plunger.asItem(),
                        GTCTools.softMallet.asItem(),
                        GTCTools.mortar.asItem()
                );

        prov.tag(TinkerTags.Items.INTERACTABLE)
                .add(
                        GTCTools.saw.asItem(),
                        GTCTools.screwdriver.asItem(),
                        GTCTools.file.asItem(),
                        GTCTools.wirecutter.asItem(),
                        GTCTools.plunger.asItem(),
                        GTCTools.softMallet.asItem()
                );

        prov.tag(TinkerTags.Items.MODIFIABLE).add(
                GTCTools.saw.asItem(),
                GTCTools.screwdriver.asItem(),
                GTCTools.file.asItem(),
                GTCTools.wirecutter.asItem(),
                GTCTools.plunger.asItem(),
                GTCTools.softMallet.asItem()
        );
        prov.tag(TinkerTags.Items.BROAD_TOOLS).add(
                GTCTools.wirecutter.asItem()
        );
        prov.tag(SMALL_TOOLS).add(
                GTCTools.saw.asItem(),
                GTCTools.screwdriver.asItem(),
                GTCTools.file.asItem(),
                GTCTools.plunger.asItem(),
                GTCTools.softMallet.asItem()
        );
        prov.tag(TinkerTags.Items.HELD).add(
                GTCTools.saw.asItem(),
                GTCTools.screwdriver.asItem(),
                GTCTools.file.asItem(),
                GTCTools.wirecutter.asItem(),
                GTCTools.plunger.asItem(),
                GTCTools.softMallet.asItem()
        );
        prov.tag(TinkerTags.Items.MELEE).add(
                GTCTools.saw.asItem(),
                GTCTools.screwdriver.asItem(),
                GTCTools.file.asItem(),
                GTCTools.wirecutter.asItem(),
                GTCTools.plunger.asItem(),
                GTCTools.softMallet.asItem()
        );
        prov.tag(TinkerTags.Items.HARVEST).add(
                GTCTools.saw.asItem(),
                GTCTools.file.asItem(),
                GTCTools.wirecutter.asItem(),
                GTCTools.softMallet.asItem()
        );
        prov.tag(TinkerTags.Items.DURABILITY).add(
                GTCTools.saw.asItem(),
                GTCTools.screwdriver.asItem(),
                GTCTools.file.asItem(),
                GTCTools.wirecutter.asItem(),
                GTCTools.plunger.asItem(),
                GTCTools.softMallet.asItem()
        );
        prov.tag(TinkerTags.Items.BALLISTA_AMMO).add(
                GTCTools.screwdriver.asItem(),
                GTCTools.file.asItem(),
                GTCTools.plunger.asItem(),
                GTCTools.softMallet.asItem()
        );
        prov.tag(TinkerTags.Items.MULTIPART_TOOL).add(
                GTCTools.saw.asItem(),
                GTCTools.screwdriver.asItem(),
                GTCTools.file.asItem(),
                GTCTools.wirecutter.asItem(),
                GTCTools.plunger.asItem(),
                GTCTools.softMallet.asItem()
        );

        prov.tag(GTCSmeltery.sawBladeCast.getMultiUseTag()).add(GTCSmeltery.sawBladeCast.get());
        prov.tag(GTCSmeltery.fileHeadCast.getMultiUseTag()).add(GTCSmeltery.fileHeadCast.get());
        prov.tag(GTCSmeltery.screwdriverTipCast.getMultiUseTag()).add(GTCSmeltery.screwdriverTipCast.get());
        prov.tag(GTCSmeltery.wirecutterClawsCast.getMultiUseTag()).add(GTCSmeltery.wirecutterClawsCast.get());
        prov.tag(GTCSmeltery.wrenchHandleCast.getMultiUseTag()).add(GTCSmeltery.wrenchHandleCast.get());

        prov.tag(GTCSmeltery.sawBladeCast.getSingleUseTag()).add(GTCSmeltery.sawBladeCast.getSand(), GTCSmeltery.sawBladeCast.getRedSand());
        prov.tag(GTCSmeltery.fileHeadCast.getSingleUseTag()).add(GTCSmeltery.fileHeadCast.getSand(), GTCSmeltery.fileHeadCast.getRedSand());
        prov.tag(GTCSmeltery.screwdriverTipCast.getSingleUseTag()).add(GTCSmeltery.screwdriverTipCast.getSand(), GTCSmeltery.screwdriverTipCast.getRedSand());
        prov.tag(GTCSmeltery.wirecutterClawsCast.getSingleUseTag()).add(GTCSmeltery.wirecutterClawsCast.getSand(), GTCSmeltery.wirecutterClawsCast.getRedSand());
        prov.tag(GTCSmeltery.wrenchHandleCast.getSingleUseTag()).add(GTCSmeltery.wrenchHandleCast.getSand(), GTCSmeltery.wrenchHandleCast.getRedSand());
    }

    private static void genFluidTags(RegistrateTagsProvider<?> provIn) {

    }

    private static void genEntityTags(RegistrateTagsProvider<?> provIn) {

    }

    public static <T> TagKey<T> optionalTag(Registry<T> registry, ResourceLocation id) {
        return TagKey.create(registry.key(), id);
    }

    public static <T> TagKey<T> optionalTag(ResourceKey<? extends Registry<T>> registryKey, ResourceLocation id) {
        return TagKey.create(registryKey, id);
    }

    public static <T> TagKey<T> createModTag(Registry<T> registry, String path) {
        return optionalTag(registry, GTConstruct.id(path));
    }

    public static <T> TagKey<T> createModTag(ResourceKey<? extends Registry<T>> registryKey, String path) {
        return TagKey.create(registryKey, GTConstruct.id(path));
    }

    public static TagKey<Item> createModItemTag(String path) {
        return createModTag(BuiltInRegistries.ITEM, path);
    }
}
