package com.pouffydev.gtconstruct.datagen.material;

import com.pouffydev.gtconstruct.common.stats.PlungerHeadMaterialStats;
import com.pouffydev.gtconstruct.registry.GTCMaterialIds;
import net.minecraft.data.PackOutput;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.tools.stats.*;

import static net.minecraft.world.item.Tiers.*;

public class GTCMaterialStatsProv extends AbstractMaterialStatsDataProvider {
    public GTCMaterialStatsProv(PackOutput packOutput, AbstractMaterialDataProvider materials) {
        super(packOutput, materials);
    }

    @Override
    protected void addMaterialStats() {
        addMeleeHarvest();
        addRanged();
        addArmor();
        addMisc();
    }

    private void addMeleeHarvest() {
        addMaterialStats(GTCMaterialIds.bismuth,
                new HeadMaterialStats(270, 6.5f, IRON, 1.5f),
                HandleMaterialStats.multipliers().durability(1.05f).build(),
                StatlessMaterialStats.BINDING);

        addMaterialStats(GTCMaterialIds.bismuthBronze,
                new HeadMaterialStats(770, 6.5f, IRON, 2.2f),
                HandleMaterialStats.multipliers().durability(1.15f).build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(GTCMaterialIds.cupronickel,
                new HeadMaterialStats(400, 5.2f, IRON, 1.5f),
                HandleMaterialStats.multipliers().durability(1.05f).build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(GTCMaterialIds.sterlingSilver,
                new HeadMaterialStats(330, 5.2f, IRON, 2.5f),
                HandleMaterialStats.multipliers().durability(0.95f).miningSpeed(1.1f).attackSpeed(1.05f).build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(GTCMaterialIds.blackBronze,
                new HeadMaterialStats(830, 6.2f, IRON, 2.0f),
                HandleMaterialStats.multipliers().durability(1.25f).miningSpeed(1.2f).build(),
                StatlessMaterialStats.BINDING);

        addMaterialStats(GTCMaterialIds.blackSteel,
                new HeadMaterialStats(1040, 7.0f, DIAMOND, 3.0f),
                HandleMaterialStats.multipliers().durability(1.15f).miningSpeed(1.2f).attackSpeed(1.1f).build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(GTCMaterialIds.blueSteel,
                new HeadMaterialStats(1140, 7.0f, DIAMOND, 3.5f),
                HandleMaterialStats.multipliers().durability(1.8f).miningSpeed(1.3f).attackSpeed(1.2f).build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(GTCMaterialIds.redSteel,
                new HeadMaterialStats(1140, 7.0f, DIAMOND, 3.5f),
                HandleMaterialStats.multipliers().durability(1.2f).miningSpeed(1.7f).attackSpeed(1.5f).build(),
                StatlessMaterialStats.BINDING);
    }

    private void addRanged() {
        addMaterialStats(GTCMaterialIds.bismuth,
                new LimbMaterialStats(270, -0.05f, 0.15f, 0),
                new GripMaterialStats(1.05f, 0f, 1.5f));

        addMaterialStats(GTCMaterialIds.bismuthBronze,
                new LimbMaterialStats(770, -0.15f, 0.15f, 0.23f),
                new GripMaterialStats(1.15f, 0f, 2.2f));
        addMaterialStats(GTCMaterialIds.cupronickel,
                new LimbMaterialStats(400, -0.25f, 0.15f, 0.14f),
                new GripMaterialStats(1.05f, 0f, 1.5f));
        addMaterialStats(GTCMaterialIds.sterlingSilver,
                new LimbMaterialStats(330, -0.2f, 0.15f, 0.2f),
                new GripMaterialStats(0.95f, 0f, 2.5f));
        addMaterialStats(GTCMaterialIds.blackBronze,
                new LimbMaterialStats(830, -0.1f, 0.15f, 0.18f),
                new GripMaterialStats(1.25f, 0f, 2.0f));

        addMaterialStats(GTCMaterialIds.blackSteel,
                new LimbMaterialStats(1040, -0.05f, 0.15f, 0.3f),
                new GripMaterialStats(1.15f, 0f, 3.0f));
        addMaterialStats(GTCMaterialIds.blueSteel,
                new LimbMaterialStats(1140, -0.05f, 0.15f, 0.35f),
                new GripMaterialStats(1.8f, 0.1f, 3.5f));
        addMaterialStats(GTCMaterialIds.redSteel,
                new LimbMaterialStats(1140, -0.05f, 0.15f, 0.45f),
                new GripMaterialStats(1.4f, 0.25f, 2.2f));
    }

    private void addArmor() {
        addArmorShieldStats(GTCMaterialIds.blackSteel,      PlatingMaterialStats.builder().durabilityFactor(29).armor(2, 5, 7, 2).toughness(2), StatlessMaterialStats.MAILLE);
        addArmorShieldStats(GTCMaterialIds.blueSteel,       PlatingMaterialStats.builder().durabilityFactor(31).armor(3, 6, 8, 2.5f).toughness(3.5f), StatlessMaterialStats.MAILLE);
        addArmorShieldStats(GTCMaterialIds.redSteel,        PlatingMaterialStats.builder().durabilityFactor(31).armor(3, 6, 8, 2.5f).toughness(3.5f), StatlessMaterialStats.MAILLE);
    }

    private void addMisc() {
        addMaterialStats(GTCMaterialIds.siliconeRubber, new PlungerHeadMaterialStats(430, 0.5f, WOOD, 0.5f), StatlessMaterialStats.BINDING);
        addMaterialStats(GTCMaterialIds.styreneButadieneRubber, new PlungerHeadMaterialStats(430, 0.5f, WOOD, 0.5f), StatlessMaterialStats.BINDING);
        addMaterialStats(GTCMaterialIds.polybenzimidazole, new PlungerHeadMaterialStats(985, 0.5f, WOOD, 0.5f), StatlessMaterialStats.BINDING);
        addMaterialStats(GTCMaterialIds.polyethylene, new PlungerHeadMaterialStats(225, 0.5f, WOOD, 0.5f), StatlessMaterialStats.BINDING);
        addMaterialStats(GTCMaterialIds.polytetrafluoroethylene, new PlungerHeadMaterialStats(430, 0.5f, WOOD, 0.5f), StatlessMaterialStats.BINDING);
        addMaterialStats(GTCMaterialIds.rubber, new PlungerHeadMaterialStats(225, 0.5f, WOOD, 0.5f), StatlessMaterialStats.BINDING);
    }

    @Override
    public String getName() {
        return "GregTech Construct Material Stats";
    }
}
