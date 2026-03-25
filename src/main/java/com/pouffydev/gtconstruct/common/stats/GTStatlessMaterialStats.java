package com.pouffydev.gtconstruct.common.stats;

import com.pouffydev.gtconstruct.GTConstruct;
import lombok.Getter;
import net.minecraft.network.chat.Component;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.materials.stats.IMaterialStats;
import slimeknights.tconstruct.library.materials.stats.MaterialStatType;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;
import slimeknights.tconstruct.library.tools.stat.ModifierStatsBuilder;

import java.util.List;

public enum GTStatlessMaterialStats implements IMaterialStats {
    MORTAR_BOWL("mortar_bowl"),

    ;

    private static final List<Component> LOCALIZED = List.of(IMaterialStats.makeTooltip(TConstruct.getResource("extra.no_stats")));
    private static final List<Component> DESCRIPTION = List.of(Component.empty());
    @Getter
    private final MaterialStatType<GTStatlessMaterialStats> type;

    // no stats

    GTStatlessMaterialStats(String name) {
        this.type = MaterialStatType.singleton(new MaterialStatsId(GTConstruct.appendId(name)), this);
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
