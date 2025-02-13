package com.pouffydev.gtconstruct.common.material;

import com.google.common.collect.ImmutableList;
import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;

import java.util.*;
import java.util.stream.Collectors;

public class ConnectedMaterialStats {
    private final Set<ResourceLocation> stats = new HashSet<>();

    public ConnectedMaterialStats addStats(ResourceLocation... stats) {
        this.stats.addAll(Arrays.asList(stats));
        return this;
    }

    //public ConnectedMaterialStats addStats(ImmutableList<ResourceLocation> stats) {
    //    this.stats.addAll(stats);
    //    return this;
    //}

    public ConnectedMaterialStats addStats(ImmutableList<ResourceLocation> stats) {
        this.stats.addAll(stats);
        return this;
    }

    public void verify(MaterialLink material) {
        stats.addAll(stats.stream()
                .map(f -> MaterialStatsId.tryParse(f.toString()))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet()));
    }

    public boolean hasStat(ResourceLocation stat) {
        return stats.contains(stat);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        stats.forEach(f -> sb.append(f.toString()).append("\n"));
        return sb.toString();
    }
}
