package com.pouffydev.gtconstruct.registry;

import com.pouffydev.gtconstruct.common.stats.GTStatlessMaterialStats;
import com.pouffydev.gtconstruct.common.stats.PlungerHeadMaterialStats;
import com.pouffydev.gtconstruct.common.stats.SoftMalletHeadMaterialStats;
import slimeknights.tconstruct.library.client.data.material.GeneratorPartTextureJsonGenerator;
import slimeknights.tconstruct.library.materials.IMaterialRegistry;
import slimeknights.tconstruct.library.materials.MaterialRegistry;
import slimeknights.tconstruct.library.materials.definition.MaterialVariantId;
import slimeknights.tconstruct.tools.data.material.MaterialIds;

import java.util.ArrayList;
import java.util.List;

import static slimeknights.tconstruct.library.materials.MaterialRegistry.MELEE_HARVEST;

@SuppressWarnings({"removal", "deprecation"})
public class GTCMaterialRegistry {

    public static void setup() {
        IMaterialRegistry registry = MaterialRegistry.getInstance();
        registry.registerStatType(PlungerHeadMaterialStats.TYPE);
        registry.registerStatType(SoftMalletHeadMaterialStats.TYPE);
        registry.registerStatType(GTStatlessMaterialStats.MORTAR_BOWL.getType());
    }

    public static final List<MaterialVariantId> mortarBowlMaterials = new ArrayList<>();
    public static final List<MaterialVariantId> plungerHeadMaterials = new ArrayList<>();
    public static final List<MaterialVariantId> softMalletHeadMaterials = new ArrayList<>();

    public static GeneratorPartTextureJsonGenerator.StatOverride getStatOverrides() {
        GeneratorPartTextureJsonGenerator.StatOverride.Builder builder = new GeneratorPartTextureJsonGenerator.StatOverride.Builder();
        mortarBowlMaterials.forEach((material) -> builder.addVariant(GTStatlessMaterialStats.MORTAR_BOWL.getIdentifier(), material));
        plungerHeadMaterials.forEach((material) -> builder.addVariant(PlungerHeadMaterialStats.ID, material));
        softMalletHeadMaterials.forEach((material) -> builder.addVariant(SoftMalletHeadMaterialStats.ID, material));
        return builder.build();
    }

    static {
        //Existing Materials
        softMalletHeadMaterials.add(MaterialIds.wood);
        softMalletHeadMaterials.add(MaterialIds.treatedWood);
        softMalletHeadMaterials.add(MaterialIds.slimewood);

        mortarBowlMaterials.add(MaterialIds.rock);
        mortarBowlMaterials.add(MaterialIds.flint);
        mortarBowlMaterials.add(MaterialIds.scorchedStone);
        mortarBowlMaterials.add(MaterialIds.searedStone);
        mortarBowlMaterials.add(MaterialIds.whitestone);

        //Existing variants (will add more as I come across them)

        //Wood
        softMalletHeadMaterials.add(MaterialIds.oak);
        softMalletHeadMaterials.add(MaterialIds.spruce);
        softMalletHeadMaterials.add(MaterialIds.birch);
        softMalletHeadMaterials.add(MaterialIds.jungle);
        softMalletHeadMaterials.add(MaterialIds.acacia);
        softMalletHeadMaterials.add(MaterialIds.darkOak);
        softMalletHeadMaterials.add(MaterialIds.mangrove);
        softMalletHeadMaterials.add(MaterialIds.cherry);
        softMalletHeadMaterials.add(MaterialIds.crimson);
        softMalletHeadMaterials.add(MaterialIds.warped);

        //Slimewood
        softMalletHeadMaterials.add(MaterialIds.slimewoodComposite);
        softMalletHeadMaterials.add(MaterialIds.greenheart);
        softMalletHeadMaterials.add(MaterialIds.skyroot);
        softMalletHeadMaterials.add(MaterialIds.bloodshroom);
        softMalletHeadMaterials.add(MaterialIds.enderbark);

        //Rock
        mortarBowlMaterials.add(MaterialIds.stone);
        mortarBowlMaterials.add(MaterialIds.andesite);
        mortarBowlMaterials.add(MaterialIds.diorite);
        mortarBowlMaterials.add(MaterialIds.granite);
        mortarBowlMaterials.add(MaterialIds.calcite);
        mortarBowlMaterials.add(MaterialIds.blackstone);

        //Flint
        mortarBowlMaterials.add(MaterialIds.basalt);
        mortarBowlMaterials.add(MaterialIds.deepslate);

        //Whitestone
        mortarBowlMaterials.add(MaterialIds.endstone);
        mortarBowlMaterials.add(MaterialIds.whitestoneComposite);
        mortarBowlMaterials.add(MaterialIds.whitestoneAluminum);
        mortarBowlMaterials.add(MaterialIds.whitestoneTin);
        mortarBowlMaterials.add(MaterialIds.whitestoneZinc);
    }
}
