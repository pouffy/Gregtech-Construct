package com.pouffydev.gtconstruct.datagen.lang;

import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.tterrag.registrate.providers.RegistrateLangProvider;

public class TinkerLang {

    public static void init(RegistrateLangProvider provider) {
        initToolNames(provider);
        initCastsAndPatterns(provider);
        initModifiers(provider);
        initPartNames(provider);
        initMaterials(provider);
    }

    private static void initToolNames(RegistrateLangProvider provider) {
        toEng(provider, "item.gtconstruct.saw");
        toEng(provider, "item.gtconstruct.screwdriver");
        toEng(provider, "item.gtconstruct.file");
        toEng(provider, "item.gtconstruct.plunger");
    }

    private static void initCastsAndPatterns(RegistrateLangProvider provider) {
        createCastsAndPattern(provider, "saw_blade");
        createCastsAndPattern(provider, "screwdriver_tip");
        createCastsAndPattern(provider, "file_head");
        createCastsAndPattern(provider, "wirecutter_claws");
        createCastsAndPattern(provider, "wrench_handle");
    }

    private static void initModifiers(RegistrateLangProvider provider) {
        toEng(provider,"modifier.gtconstruct.ice_cutter");
        toEng(provider,"modifier.gtconstruct.blunt");
    }

    private static void initPartNames(RegistrateLangProvider provider) {
        toEng(provider, "item.gtconstruct.saw_blade");
        toEng(provider, "item.gtconstruct.screwdriver_tip");
        toEng(provider, "item.gtconstruct.file_head");
        toEng(provider, "item.gtconstruct.wirecutter_claws");
        toEng(provider, "item.gtconstruct.wrench_handle");
        toEng(provider, "item.gtconstruct.plunger_head");
    }

    private static void initMaterials(RegistrateLangProvider provider) {
        toEng(provider, "material.gtconstruct.sterling_silver");
        toEng(provider, "material.gtconstruct.bismuth_bronze");
        toEng(provider, "material.gtconstruct.cupronickel");
        toEng(provider, "material.gtconstruct.black_bronze");
        toEng(provider, "material.gtconstruct.black_steel");
        toEng(provider, "material.gtconstruct.blue_steel");
        toEng(provider, "material.gtconstruct.red_steel");
        toEng(provider, "material.gtconstruct.bismuth");
        toEng(provider, "material.gtconstruct.silicone_rubber");
        toEng(provider, "material.gtconstruct.styrene_butadiene_rubber");
        toEng(provider, "material.gtconstruct.polybenzimidazole");
        toEng(provider, "material.gtconstruct.polyethylene");
        toEng(provider, "material.gtconstruct.polytetrafluoroethylene");
        toEng(provider, "material.gtconstruct.rubber");
    }

    private static void createCastsAndPattern(RegistrateLangProvider provider, String name) {
        provider.add("item.gtconstruct.%s_cast".formatted(name), "%s Gold Cast".formatted(FormattingUtil.toEnglishName(name)));
        provider.add("item.gtconstruct.%s_sand_cast".formatted(name), "%s Sand Cast".formatted(FormattingUtil.toEnglishName(name)));
        provider.add("item.gtconstruct.%s_red_sand_cast".formatted(name), "%s Red Sand Cast".formatted(FormattingUtil.toEnglishName(name)));
        provider.add("pattern.gtconstruct.%s".formatted(name), "%s".formatted(FormattingUtil.toEnglishName(name)));
    }



    private static void toEng(RegistrateLangProvider provider, String name) {
        // get the last part of the translation key to use as the english name
        String[] parts = name.split("\\.");
        String toTranslate = parts[parts.length - 1];
        provider.add(name, "%s".formatted(FormattingUtil.toEnglishName(toTranslate)));
    }
}
