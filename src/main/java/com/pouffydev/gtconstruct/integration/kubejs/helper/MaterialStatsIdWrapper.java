package com.pouffydev.gtconstruct.integration.kubejs.helper;

import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;

import java.util.Map;
import java.util.WeakHashMap;

public record MaterialStatsIdWrapper(MaterialStatsId materialStatsId) {
    private static final Map<String, MaterialStatsIdWrapper> PARSE_CACHE = new WeakHashMap<>();

    public static MaterialStatsIdWrapper fromString(CharSequence str) {
        String trimmed = str.toString().trim();
        String copy = trimmed;

        var cached = PARSE_CACHE.get(trimmed);

        if (cached != null) {
            return cached.isEmpty() ? null : cached.copy();
        }

        var rlIndex = trimmed.indexOf(':');

        if (rlIndex == -1) {
            copy = "tconstruct:" + trimmed;
        }

        final String copyFinal = copy;
        cached = new MaterialStatsIdWrapper(new MaterialStatsId(copyFinal));
        PARSE_CACHE.put(trimmed, cached);
        return cached;
    }

    public MaterialStatsIdWrapper copy() {
        return new MaterialStatsIdWrapper(materialStatsId);
    }

    public boolean isEmpty() {
        return this.materialStatsId == null;
    }

    public MaterialStatsId toMaterialStatsId() {
        return materialStatsId;
    }
}
