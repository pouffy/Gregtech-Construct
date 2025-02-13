package com.pouffydev.gtconstruct.integration.kubejs;

import com.gregtechceu.gtceu.api.registry.GTRegistry;
import com.gregtechceu.gtceu.api.registry.registrate.BuilderBase;
import com.pouffydev.gtconstruct.GTConstruct;
import com.pouffydev.gtconstruct.common.material.MaterialLink;
import com.pouffydev.gtconstruct.common.material.MaterialLinkRegistryManager;
import com.pouffydev.gtconstruct.integration.kubejs.events.GTCRegistryEventJS;
import dev.latvian.mods.kubejs.DevProperties;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.kubejs.util.UtilsJS;
import net.minecraft.resources.ResourceLocation;

import java.util.*;
import java.util.function.Supplier;

public class GTCRegistryInfo<K, V> {

    @FunctionalInterface
    public interface BuilderFactory<T> {

        BuilderBase<? extends T> createBuilder(ResourceLocation id);
    }

    public record BuilderType<T>(String type, Class<? extends BuilderBase<? extends T>> builderClass,
                                 BuilderFactory<T> factory) {}

    public static final Map<ResourceLocation, GTCRegistryInfo<?, ?>> MAP = new LinkedHashMap<>();

    public static final Set<ResourceLocation> EXTRA_IDS = new HashSet<>();

    public static final Map<ResourceLocation, List<GTCRegistryInfo<?, ?>>> POST_AT = new HashMap<>();
    public static final List<BuilderBase<?>> ALL_BUILDERS = new ArrayList<>();

    public static final GTCRegistryInfo<String, MaterialLink> MATERIAL_LINK = add(
            MaterialLinkRegistryManager.getInstance().getRegistry(GTConstruct.MOD_ID), MaterialLink.class);

    public final ResourceLocation registryKey;
    public final Class<V> objectBaseClass;
    public final Map<String, BuilderType<V>> types;
    public final Map<ResourceLocation, BuilderBase<? extends V>> objects;
    public final Supplier<Map<K, V>> registryValues;
    private BuilderType<V> defaultType;
    public BuilderBase<? extends V> current;

    private GTCRegistryInfo(ResourceLocation key, Supplier<Map<K, V>> registryValues, Class<V> baseClass) {
        registryKey = key;
        objectBaseClass = baseClass;
        types = new LinkedHashMap<>();
        objects = new LinkedHashMap<>();
        this.registryValues = registryValues;
        current = null;
    }

    public static <K, V> GTCRegistryInfo<K, V> add(GTRegistry<K, V> key, Class<?> baseClass) {
        ResourceLocation id = key.getRegistryName();
        var types = new GTCRegistryInfo<>(id, key::registry, UtilsJS.cast(baseClass));

        if (MAP.put(id, types) != null) {
            throw new IllegalStateException("Registry with id '" + id + "' already exists!");
        }

        POST_AT.computeIfAbsent(key.getRegistryName(), (k) -> new LinkedList<>()).add(types);

        return types;
    }

    public static <K, V> GTCRegistryInfo<K, V> add(ResourceLocation id, Supplier<Map<K, V>> registryValues,
                                                  Class<?> baseClass) {
        var types = new GTCRegistryInfo<>(id, registryValues, UtilsJS.cast(baseClass));

        if (MAP.put(id, types) != null || !EXTRA_IDS.add(id)) {
            throw new IllegalStateException("Registry with id '" + id + "' already exists!");
        }

        POST_AT.computeIfAbsent(id, (k) -> new LinkedList<>()).add(types);

        return types;
    }

    public void addType(String type, Class<? extends BuilderBase<? extends V>> builderType, BuilderFactory<V> factory,
                        boolean isDefault) {
        var b = new BuilderType<>(type, builderType, factory);
        types.put(type, b);

        if (isDefault) {
            if (defaultType != null) {
                ConsoleJS.STARTUP.warn("Previous default type '" + defaultType.type + "' for registry '" + registryKey +
                        "' replaced with '" + type + "'!");
            }

            defaultType = b;
        }
    }

    public void addBuilder(BuilderBase<? extends V> builder) {
        if (builder == null) {
            throw new IllegalArgumentException("Can't add null builder in registry '" + registryKey + "'!");
        }

        if (DevProperties.get().debugInfo) {
            ConsoleJS.STARTUP.info("~ " + registryKey + " | " + builder.id);
        }

        if (objects.containsKey(builder.id)) {
            throw new IllegalArgumentException("Duplicate key '" + builder.id + "' in registry '" + registryKey + "'!");
        }

        objects.put(builder.id, builder);
        ALL_BUILDERS.add(builder);
    }

    public BuilderType<V> getDefaultType() {
        if (types.isEmpty()) {
            return null;
        } else if (defaultType == null) {
            defaultType = types.values().iterator().next();
        }

        return defaultType;
    }

    public void postEvent() {
        GTConstructStartupEvents.REGISTRY.post(ScriptType.STARTUP, registryKey, new GTCRegistryEventJS<>(this));
    }

    public static void registerFor(ResourceLocation registry) {
        for (var type : POST_AT.getOrDefault(registry, List.of())) {
            type.postEvent();

            for (var builder : type.objects.values()) {
                if (DevProperties.get().debugInfo) {
                    ConsoleJS.STARTUP.info("+ " + registry + " | " + builder.id);
                }
                builder.register();
            }
        }
    }
}
