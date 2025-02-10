package com.pouffydev.gtconstruct.integration.kubejs;

import com.gregtechceu.gtceu.api.registry.GTRegistry;
import com.pouffydev.gtconstruct.GTConstruct;
import com.pouffydev.gtconstruct.integration.kubejs.events.GTCRegistryEventJS;
import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import dev.latvian.mods.kubejs.event.Extra;

public interface GTCStartupEvents {

    EventGroup GROUP = EventGroup.of("GTConstructStartupEvents");

    Extra REGISTRY_EXTRA = Extra.REQUIRES_STRING.copy().validator(GTCStartupEvents::validateRegistry);

    private static boolean validateRegistry(Object o) {
        try {
            var id = GTConstruct.appendId(o.toString());
            return GTRegistry.REGISTERED.containsKey(id) || GTCRegistryInfo.EXTRA_IDS.contains(id);
        } catch (Exception ex) {
            return false;
        }
    }

    EventHandler REGISTRY = GROUP.startup("registry", () -> GTCRegistryEventJS.class).extra(REGISTRY_EXTRA);
}
