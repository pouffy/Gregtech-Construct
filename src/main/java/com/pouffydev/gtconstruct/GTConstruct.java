package com.pouffydev.gtconstruct;

import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.pouffydev.gtconstruct.registry.GTCModifiers;
import com.pouffydev.gtconstruct.registry.GTCSmeltery;
import com.pouffydev.gtconstruct.registry.GTCToolParts;
import com.pouffydev.gtconstruct.registry.GTCTools;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(GTConstruct.MOD_ID)
public class GTConstruct
{
    public static final String MOD_ID = "gtconstruct";
    public static final String NAME = "GregTech Construct";
    public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

    public GTConstruct()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.register(new GTCTools());

        GTCToolParts.ITEMS.register(modEventBus);
        GTCTools.ITEMS.register(modEventBus);
        GTCSmeltery.ITEMS.register(modEventBus);
        GTCModifiers.MODIFIERS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, FormattingUtil.toLowerCaseUnder(path));
    }

    public static String appendIdString(String id) {
        return id.indexOf(':') == -1 ? (MOD_ID + ":" + id) : id;
    }

    public static ResourceLocation appendId(String id) {
        String[] strings = new String[] { MOD_ID, id };
        int i = id.indexOf(':');
        if (i >= 0) {
            strings[1] = id.substring(i + 1);
            if (i >= 1) {
                strings[0] = id.substring(0, i);
            }
        }
        return new ResourceLocation(strings[0], strings[1]);
    }

    public static boolean isDataGen() {
        return FMLLoader.getLaunchHandler().isData();
    }

    public static void sealGTCClass(Object self, String base, String solution) {
        // note for future maintainers: this does not use Java 9's sealed classes as unless you use modules those are restricted to the same package.
        // Dumb restriction but not like we can change it.
        String name = self.getClass().getName();
        if (!name.startsWith("com.pouffydev.gtconstruct.")) {
            throw new IllegalStateException(base + " being extended from invalid package " + name + ". " + solution);
        }
    }
}
