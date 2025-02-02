package com.pouffydev.gtconstruct.datagen.lang;

import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.tterrag.registrate.providers.RegistrateLangProvider;

public class TinkerLang {

    public static void init(RegistrateLangProvider provider) {
        initToolNames(provider);
        initCastsAndPatterns(provider);
        initModifiers(provider);
        initPartNames(provider);
    }

    private static void initToolNames(RegistrateLangProvider provider) {
        provider.add("item.gtconstruct.saw", "Saw");
        provider.add("item.gtconstruct.screwdriver", "Screwdriver");
    }

    private static void initCastsAndPatterns(RegistrateLangProvider provider) {
        createCastsAndPattern(provider, "saw_blade");
        createCastsAndPattern(provider, "screwdriver_tip");
        createCastsAndPattern(provider, "file_head");
        createCastsAndPattern(provider, "wirecutter_claws");
        createCastsAndPattern(provider, "wrench_handle");
    }

    private static void initModifiers(RegistrateLangProvider provider) {
        provider.add("modifier.gtconstruct.ice_cutter", "Ice Cutter");
    }

    private static void initPartNames(RegistrateLangProvider provider) {
        provider.add("item.gtconstruct.saw_blade", "Saw Blade");
        provider.add("item.gtconstruct.screwdriver_tip", "Screwdriver Tip");
        provider.add("item.gtconstruct.file_head", "File Head");
        provider.add("item.gtconstruct.wirecutter_claws", "Wirecutter Claws");
        provider.add("item.gtconstruct.wrench_handle", "Wrench Handle");
    }

    private static void createCastsAndPattern(RegistrateLangProvider provider, String name) {
        provider.add("item.gtconstruct.%s_cast".formatted(name), "%s Gold Cast".formatted(FormattingUtil.toEnglishName(name)));
        provider.add("item.gtconstruct.%s_sand_cast".formatted(name), "%s Sand Cast".formatted(FormattingUtil.toEnglishName(name)));
        provider.add("item.gtconstruct.%s_red_sand_cast".formatted(name), "%s Red Sand Cast".formatted(FormattingUtil.toEnglishName(name)));
        provider.add("pattern.gtconstruct.%s".formatted(name), "%s".formatted(FormattingUtil.toEnglishName(name)));
    }
}
