package com.pouffydev.gtconstruct.integration.kubejs;

import com.pouffydev.gtconstruct.common.material.MaterialLinkRegistryManager;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;

public class GTConstructKubeJSPlugin extends KubeJSPlugin {

    @Override
    public void initStartup() {
        super.initStartup();
    }

    @Override
    public void init() {
        super.init();

    }

    @Override
    public void registerBindings(BindingsEvent event) {
        super.registerBindings(event);

        event.add("GTCMaterialLinkRegistry", MaterialLinkRegistryManager.getInstance());
    }
}
