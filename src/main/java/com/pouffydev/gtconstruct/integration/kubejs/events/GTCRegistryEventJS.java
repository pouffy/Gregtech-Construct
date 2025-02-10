package com.pouffydev.gtconstruct.integration.kubejs.events;

import com.gregtechceu.gtceu.api.registry.registrate.BuilderBase;
import com.pouffydev.gtconstruct.GTConstruct;
import com.pouffydev.gtconstruct.integration.kubejs.GTCRegistryInfo;
import dev.latvian.mods.kubejs.event.StartupEventJS;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.kubejs.util.UtilsJS;

public class GTCRegistryEventJS<K, V> extends StartupEventJS {
    private final GTCRegistryInfo<K, V> registry;

    public GTCRegistryEventJS(GTCRegistryInfo<K, V> r) {
        registry = r;
    }

    public BuilderBase<? extends V> create(String id, String type) {
        var t = registry.types.get(type);

        if (t == null) {
            throw new IllegalArgumentException("Unknown type '" + type + "' for object '" + id + "'!");
        }

        var b = t.factory()
                .createBuilder(UtilsJS.getMCID(ScriptType.STARTUP.manager.get().context, GTConstruct.appendId(id)));

        if (b == null) {
            throw new IllegalArgumentException("Unknown type '" + type + "' for object '" + id + "'!");
        } else {
            registry.addBuilder(b);
        }

        return b;
    }

    public BuilderBase<? extends V> create(String id) {
        var t = registry.getDefaultType();

        if (t == null) {
            throw new IllegalArgumentException(
                    "Registry for type '" + registry.registryKey + "' doesn't have any builders registered!");
        }

        var b = t.factory()
                .createBuilder(UtilsJS.getMCID(ScriptType.STARTUP.manager.get().context, GTConstruct.appendId(id)));

        if (b == null) {
            throw new IllegalArgumentException("Unknown type '" + t.type() + "' for object '" + id + "'!");
        } else {
            registry.addBuilder(b);
        }

        return b;
    }
}
