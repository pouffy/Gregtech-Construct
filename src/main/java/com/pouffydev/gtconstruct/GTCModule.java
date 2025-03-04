package com.pouffydev.gtconstruct;

import com.mojang.serialization.Codec;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctionType;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import slimeknights.mantle.item.BlockTooltipItem;
import slimeknights.mantle.item.TooltipItem;
import slimeknights.mantle.registration.deferred.*;
import slimeknights.mantle.registration.object.BuildingBlockObject;
import slimeknights.mantle.registration.object.EnumObject;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.common.registration.BlockDeferredRegisterExtension;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.recipe.TinkerRecipeTypes;
import slimeknights.tconstruct.library.utils.Util;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class GTCModule {
    protected GTCModule() {
        GTConstruct.sealGTCClass(this, "GTCModule", "This is a bug with the mod containing that class, they should create their own deferred registers.");
    }

    // deferred register instances
    // gameplay singleton
    protected static final BlockDeferredRegisterExtension BLOCKS = new BlockDeferredRegisterExtension(GTConstruct.MOD_ID);
    protected static final ItemDeferredRegisterExtension ITEMS = new ItemDeferredRegisterExtension(GTConstruct.MOD_ID);
    protected static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(GTConstruct.MOD_ID);
    protected static final SynchronizedDeferredRegister<MobEffect> MOB_EFFECTS = SynchronizedDeferredRegister.create(ForgeRegistries.MOB_EFFECTS, GTConstruct.MOD_ID);
    protected static final SynchronizedDeferredRegister<ParticleType<?>> PARTICLE_TYPES = SynchronizedDeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, GTConstruct.MOD_ID);
    protected static final SynchronizedDeferredRegister<EntityDataSerializer<?>> DATA_SERIALIZERS = SynchronizedDeferredRegister.create(ForgeRegistries.Keys.ENTITY_DATA_SERIALIZERS, GTConstruct.MOD_ID);
    protected static final SynchronizedDeferredRegister<CreativeModeTab> CREATIVE_TABS = SynchronizedDeferredRegister.create(Registries.CREATIVE_MODE_TAB, GTConstruct.MOD_ID);
    // gameplay instances
    protected static final BlockEntityTypeDeferredRegister BLOCK_ENTITIES = new BlockEntityTypeDeferredRegister(GTConstruct.MOD_ID);
    protected static final EntityTypeDeferredRegister ENTITIES = new EntityTypeDeferredRegister(GTConstruct.MOD_ID);
    protected static final MenuTypeDeferredRegister MENUS = new MenuTypeDeferredRegister(GTConstruct.MOD_ID);
    // datapacks
    protected static final SynchronizedDeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = SynchronizedDeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, GTConstruct.MOD_ID);
    protected static final SynchronizedDeferredRegister<Codec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIERS = SynchronizedDeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, GTConstruct.MOD_ID);
    protected static final SynchronizedDeferredRegister<LootItemConditionType> LOOT_CONDITIONS = SynchronizedDeferredRegister.create(Registries.LOOT_CONDITION_TYPE, GTConstruct.MOD_ID);
    protected static final SynchronizedDeferredRegister<LootItemFunctionType> LOOT_FUNCTIONS = SynchronizedDeferredRegister.create(Registries.LOOT_FUNCTION_TYPE, GTConstruct.MOD_ID);
    protected static final SynchronizedDeferredRegister<LootPoolEntryType> LOOT_ENTRIES = SynchronizedDeferredRegister.create(Registries.LOOT_POOL_ENTRY_TYPE, GTConstruct.MOD_ID);

    protected static final Item.Properties ITEM_PROPS = new Item.Properties();
    protected static final Item.Properties UNSTACKABLE_PROPS = new Item.Properties().stacksTo(1);
    protected static final Function<Block,? extends BlockItem> BLOCK_ITEM = (b) -> new BlockItem(b, ITEM_PROPS);
    protected static final Function<Block,? extends BlockItem> TOOLTIP_BLOCK_ITEM = (b) -> new BlockTooltipItem(b, ITEM_PROPS);
    protected static final Function<Block,? extends BlockItem> UNSTACKABLE_BLOCK_ITEM = (b) -> new BlockTooltipItem(b, UNSTACKABLE_PROPS);
    protected static final Supplier<Item> TOOLTIP_ITEM = () -> new TooltipItem(ITEM_PROPS);

    public static void initRegisters() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        // gameplay singleton
        BLOCKS.register(bus);
        ITEMS.register(bus);
        FLUIDS.register(bus);
        MOB_EFFECTS.register(bus);
        PARTICLE_TYPES.register(bus);
        DATA_SERIALIZERS.register(bus);
        CREATIVE_TABS.register(bus);
        // gameplay instance
        BLOCK_ENTITIES.register(bus);
        ENTITIES.register(bus);
        MENUS.register(bus);
        // datapacks
        RECIPE_SERIALIZERS.register(bus);
        GLOBAL_LOOT_MODIFIERS.register(bus);
        LOOT_CONDITIONS.register(bus);
        LOOT_FUNCTIONS.register(bus);
        LOOT_ENTRIES.register(bus);
        TinkerRecipeTypes.init(bus);
    }

    protected static <T> ResourceKey<T> key(ResourceKey<? extends Registry<T>> registry, String name) {
        return ResourceKey.create(registry, GTConstruct.id(name));
    }

    protected static void accept(CreativeModeTab.Output output, EnumObject<?,? extends ItemLike> items, CreativeModeTab.TabVisibility visibility) {
        items.forEach(item -> output.accept(item, visibility));
    }

    /** Adds an enum object to the given tab with default visbility */
    protected static void accept(CreativeModeTab.Output output, EnumObject<?,? extends ItemLike> items) {
        accept(output, items, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    /** Adds an building block object to the given tab with default visbility */
    protected static void accept(CreativeModeTab.Output output, BuildingBlockObject object, CreativeModeTab.TabVisibility visibility) {
        object.forEach(item -> output.accept(item, visibility));
    }

    /** Adds an building block object to the given tab with default visbility */
    protected static void accept(CreativeModeTab.Output output, BuildingBlockObject object) {
        accept(output, object, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    /** Accepts the given item if the passed tag has items */
    protected static boolean acceptIfTag(CreativeModeTab.Output output, ItemLike item, CreativeModeTab.TabVisibility visibility, TagKey<Item> tagCondition) {
        Optional<HolderSet.Named<Item>> tag = BuiltInRegistries.ITEM.getTag(tagCondition);
        if (tag.isPresent() && tag.get().size() > 0) {
            output.accept(item, visibility);
            return true;
        }
        return false;
    }

    /** Accepts the given item if the passed tag has items */
    protected static boolean acceptIfTag(CreativeModeTab.Output output, ItemLike item, TagKey<Item> tagCondition) {
        return acceptIfTag(output, item, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS, tagCondition);
    }

    public static String makeTranslationKey(String base, String name) {
        return Util.makeTranslationKey(base, GTConstruct.id(name));
    }

    public static MutableComponent makeTranslation(String base, String name) {
        return Component.translatable(makeTranslationKey(base, name));
    }
}
