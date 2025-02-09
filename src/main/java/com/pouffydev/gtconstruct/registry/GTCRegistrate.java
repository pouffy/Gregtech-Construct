package com.pouffydev.gtconstruct.registry;

import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.Builder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.builders.NoConfigBuilder;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GTCRegistrate extends GTRegistrate {

    private final AtomicBoolean registered = new AtomicBoolean(false);

    protected GTCRegistrate(String modid) {
        super(modid);
    }

    public static GTCRegistrate create(String modid) {
        var ret = new GTCRegistrate(modid);
        ret.registerEventListeners(ret.getModEventBus());
        return ret;
    }

    public void registerRegistrate() {
        registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @Override
    public Registrate registerEventListeners(IEventBus bus) {
        if (!registered.getAndSet(true)) {
            return super.registerEventListeners(bus);
        }
        return this;
    }

    protected <
            P> NoConfigBuilder<CreativeModeTab, CreativeModeTab, P> createCreativeModeTab(P parent, String name,
                                                                                          Consumer<CreativeModeTab.Builder> config) {
        return this.generic(parent, name, Registries.CREATIVE_MODE_TAB, () -> {
            var builder = CreativeModeTab.builder()
                    .icon(() -> getAll(Registries.ITEM).stream().findFirst().map(ItemEntry::cast)
                            .map(ItemEntry::asStack).orElse(new ItemStack(Items.AIR)));
            config.accept(builder);
            return builder.build();
        });
    }


    @Override
    public <T extends Item> @NotNull ItemBuilder<T, Registrate> item(String name,
                                                                     NonNullFunction<Item.Properties, T> factory) {
        return super.item(name, factory).lang(FormattingUtil.toEnglishName(name.replaceAll("\\.", "_")));
    }

    private RegistryEntry<CreativeModeTab> currentTab;
    private static final Map<RegistryEntry<?>, RegistryEntry<CreativeModeTab>> TAB_LOOKUP = new IdentityHashMap<>();

    public void creativeModeTab(Supplier<RegistryEntry<CreativeModeTab>> currentTab) {
        this.currentTab = currentTab.get();
    }

    public void creativeModeTab(RegistryEntry<CreativeModeTab> currentTab) {
        this.currentTab = currentTab;
    }

    public boolean isInCreativeTab(RegistryEntry<?> entry, RegistryEntry<CreativeModeTab> tab) {
        return TAB_LOOKUP.get(entry) == tab;
    }

    public void setCreativeTab(RegistryEntry<?> entry, @Nullable RegistryEntry<CreativeModeTab> tab) {
        TAB_LOOKUP.put(entry, tab);
    }

    protected <R,
            T extends R> RegistryEntry<T> accept(String name, ResourceKey<? extends Registry<R>> type,
                                                 Builder<R, T, ?, ?> builder, NonNullSupplier<? extends T> creator,
                                                 NonNullFunction<RegistryObject<T>, ? extends RegistryEntry<T>> entryFactory) {
        RegistryEntry<T> entry = super.accept(name, type, builder, creator, entryFactory);

        if (this.currentTab != null) {
            TAB_LOOKUP.put(entry, this.currentTab);
        }

        return entry;
    }

    public <P> NoConfigBuilder<CreativeModeTab, CreativeModeTab, P> defaultCreativeTab(P parent, String name,
                                                                                       Consumer<CreativeModeTab.Builder> config) {
        return createCreativeModeTab(parent, name, config);
    }

}
