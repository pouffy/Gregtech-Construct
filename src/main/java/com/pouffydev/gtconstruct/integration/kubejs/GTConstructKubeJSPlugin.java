package com.pouffydev.gtconstruct.integration.kubejs;

import com.pouffydev.gtconstruct.GTConstruct;
import com.pouffydev.gtconstruct.api.GTConstructAPI;
import com.pouffydev.gtconstruct.common.material.MaterialLink;
import com.pouffydev.gtconstruct.common.material.MaterialLinkRegistryManager;
import com.pouffydev.gtconstruct.common.stats.PlungerHeadMaterialStats;
import com.pouffydev.gtconstruct.common.stats.SoftMalletHeadMaterialStats;
import com.pouffydev.gtconstruct.registry.GTCMaterialIds;
import com.pouffydev.gtconstruct.registry.GTCMaterialLinks;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.kubejs.util.ClassFilter;

public class GTConstructKubeJSPlugin extends KubeJSPlugin {

    @Override
    public void initStartup() {
        super.initStartup();
    }

    @Override
    public void init() {
        super.init();
        GTCRegistryInfo.MATERIAL_LINK.addType("basic", MaterialLink.Builder.class, MaterialLink.Builder::new, true);
    }

    @Override
    public void registerEvents() {
        super.registerEvents();
        GTConstructStartupEvents.GROUP.register();
    }

    @Override
    public void registerClasses(ScriptType type, ClassFilter filter) {
        super.registerClasses(type, filter);
        // allow user to access all gtceu classes by importing them.
        filter.allow("com.pouffydev.gtconstruct");
    }

    @Override
    public void registerBindings(BindingsEvent event) {
        super.registerBindings(event);

        event.add("GTConstruct", GTConstruct.class);
        event.add("GTConstructAPI", GTConstructAPI.class);

        event.add("GTCMaterialLinkRegistry", MaterialLinkRegistryManager.getInstance());
        event.add("GTCMaterialIds", GTCMaterialIds.class);
        event.add("GTCMaterialLinks", GTCMaterialLinks.class);
        event.add("PlungerHeadMaterialStats", PlungerHeadMaterialStats.class);
        event.add("SoftMalletHeadMaterialStats", SoftMalletHeadMaterialStats.class);
    }
}
