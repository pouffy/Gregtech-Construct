package com.pouffydev.gtconstruct;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import slimeknights.mantle.item.BlockTooltipItem;
import slimeknights.mantle.item.TooltipItem;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.deferred.SynchronizedDeferredRegister;
import slimeknights.tconstruct.common.registration.BlockDeferredRegisterExtension;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;

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

    protected static final Item.Properties ITEM_PROPS = new Item.Properties();
    protected static final Item.Properties UNSTACKABLE_PROPS = new Item.Properties().stacksTo(1);
    protected static final Function<Block,? extends BlockItem> BLOCK_ITEM = (b) -> new BlockItem(b, ITEM_PROPS);
    protected static final Function<Block,? extends BlockItem> TOOLTIP_BLOCK_ITEM = (b) -> new BlockTooltipItem(b, ITEM_PROPS);
    protected static final Function<Block,? extends BlockItem> UNSTACKABLE_BLOCK_ITEM = (b) -> new BlockTooltipItem(b, UNSTACKABLE_PROPS);
    protected static final Supplier<Item> TOOLTIP_ITEM = () -> new TooltipItem(ITEM_PROPS);
}
