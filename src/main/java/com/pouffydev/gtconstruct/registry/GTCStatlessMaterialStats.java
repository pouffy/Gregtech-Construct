package com.pouffydev.gtconstruct.registry;

import com.pouffydev.gtconstruct.GTConstruct;
import lombok.Getter;
import net.minecraft.network.chat.Component;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.materials.stats.IMaterialStats;
import slimeknights.tconstruct.library.materials.stats.MaterialStatType;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;

import java.util.List;

public enum GTCStatlessMaterialStats implements IMaterialStats {
    PLUNGER_HEAD("plunger_head"),
    ;

    private static final List<Component> LOCALIZED = List.of(IMaterialStats.makeTooltip(TConstruct.getResource("extra.no_stats")));
    private static final List<Component> DESCRIPTION = List.of(Component.empty());
    @Getter
    private final MaterialStatType<GTCStatlessMaterialStats> type;

    // no stats

    GTCStatlessMaterialStats(String name) {
        this.type = MaterialStatType.singleton(new MaterialStatsId(GTConstruct.id(name)), this);
    }

    @Override
    public List<Component> getLocalizedInfo() {
        return LOCALIZED;
    }

    @Override
    public List<Component> getLocalizedDescriptions() {
        return DESCRIPTION;
    }

    @Override
    public void apply(ModifierStatsBuilder builder, float scale) {}
}
