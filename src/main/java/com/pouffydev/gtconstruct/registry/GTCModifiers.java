package com.pouffydev.gtconstruct.registry;

import com.pouffydev.gtconstruct.GTCModule;
import com.pouffydev.gtconstruct.GTConstruct;
import com.pouffydev.gtconstruct.common.modifier.IceCutterModifier;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

public class GTCModifiers extends GTCModule {

    public GTCModifiers() {
        MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }


    public static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(GTConstruct.MOD_ID);

    public static final StaticModifier<IceCutterModifier> iceCutter = MODIFIERS.register("ice_cutter", IceCutterModifier::new);
}
