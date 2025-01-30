package com.pouffydev.gtconstruct.datagen;

import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import com.pouffydev.gtconstruct.GTConstruct;
import com.pouffydev.gtconstruct.registry.GTCModifiers;
import com.pouffydev.gtconstruct.registry.GTCToolDefinitions;
import com.pouffydev.gtconstruct.registry.GTCToolParts;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.tinkering.AbstractToolDefinitionDataProvider;
import slimeknights.tconstruct.library.materials.RandomMaterial;
import slimeknights.tconstruct.library.tools.definition.module.aoe.BoxAOEIterator;
import slimeknights.tconstruct.library.tools.definition.module.aoe.IBoxExpansion;
import slimeknights.tconstruct.library.tools.definition.module.build.SetStatsModule;
import slimeknights.tconstruct.library.tools.definition.module.build.ToolActionsModule;
import slimeknights.tconstruct.library.tools.definition.module.build.ToolTraitsModule;
import slimeknights.tconstruct.library.tools.definition.module.material.DefaultMaterialsModule;
import slimeknights.tconstruct.library.tools.definition.module.material.PartStatsModule;
import slimeknights.tconstruct.library.tools.definition.module.mining.IsEffectiveModule;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.TinkerToolParts;
import slimeknights.tconstruct.tools.data.ModifierIds;

public class GTCToolDefinitionProv extends AbstractToolDefinitionDataProvider {
    public GTCToolDefinitionProv(PackOutput packOutput) {
        super(packOutput, GTConstruct.MOD_ID);
    }

    @Override
    protected void addToolDefinitions() {
        RandomMaterial tier1Material = RandomMaterial.random().tier(1).build();
        RandomMaterial randomMaterial = RandomMaterial.random().build();
        DefaultMaterialsModule defaultTwoParts = DefaultMaterialsModule.builder().material(tier1Material, tier1Material).build();
        DefaultMaterialsModule defaultThreeParts = DefaultMaterialsModule.builder().material(tier1Material, tier1Material, tier1Material).build();
        DefaultMaterialsModule defaultFourParts = DefaultMaterialsModule.builder().material(tier1Material, tier1Material, tier1Material, tier1Material).build();
        DefaultMaterialsModule ancientTwoParts = DefaultMaterialsModule.builder().material(randomMaterial, randomMaterial).build();
        DefaultMaterialsModule ancientThreeParts = DefaultMaterialsModule.builder().material(randomMaterial, randomMaterial, randomMaterial).build();

        define(GTCToolDefinitions.SAW)
                .module(PartStatsModule.parts()
                        .part(GTCToolParts.sawBlade)
                        .part(TinkerToolParts.toolHandle)
                        .part(TinkerToolParts.toolBinding).build())
                .module(defaultThreeParts)
                .module(new SetStatsModule(StatsNBT.builder()
                        .set(ToolStats.ATTACK_DAMAGE, -1.0F)
                        .set(ToolStats.ATTACK_SPEED, -2.6F).build()))
                .smallToolStartingSlots()
                .module(ToolTraitsModule.builder().trait(GTCModifiers.iceCutter).build())
                .module(IsEffectiveModule.tag(TagUtil.createBlockTag("mineable/saw", false)))
                .build();

        define(GTCToolDefinitions.SCREWDRIVER)
                .module(PartStatsModule.parts()
                        .part(GTCToolParts.screwdriverHead)
                        .part(TinkerToolParts.toolHandle).build())
                .module(defaultTwoParts)
                .module(new SetStatsModule(StatsNBT.builder()
                        .set(ToolStats.ATTACK_DAMAGE, -1.0F)
                        .set(ToolStats.ATTACK_SPEED, 3.0F).build()))
                .smallToolStartingSlots()
                .module(ToolTraitsModule.builder().trait(ModifierIds.baneOfSssss, 1).build())
                .build();
    }

    @Override
    public String getName() {
        return "GregTech Construct Tool Definitions";
    }
}
