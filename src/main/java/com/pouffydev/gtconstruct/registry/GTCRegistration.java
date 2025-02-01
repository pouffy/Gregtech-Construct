package com.pouffydev.gtconstruct.registry;

import com.pouffydev.gtconstruct.GTConstruct;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class GTCRegistration {
    public static final GTCRegistrate REGISTRATE = GTCRegistrate.create(GTConstruct.MOD_ID);
    static {
        GTCRegistration.REGISTRATE.defaultCreativeTab((ResourceKey<CreativeModeTab>) null);
    }

    private GTCRegistration() {/**/}
}
