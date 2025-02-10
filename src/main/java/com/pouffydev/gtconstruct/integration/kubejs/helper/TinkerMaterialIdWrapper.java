package com.pouffydev.gtconstruct.integration.kubejs.helper;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

import java.util.Map;
import java.util.WeakHashMap;

public record TinkerMaterialIdWrapper(MaterialId materialId) {

    private static final Map<String, TinkerMaterialIdWrapper> PARSE_CACHE = new WeakHashMap<>();

    public static TinkerMaterialIdWrapper fromString(CharSequence str) {
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
        cached = new TinkerMaterialIdWrapper(new MaterialId(copyFinal));
        PARSE_CACHE.put(trimmed, cached);
        return cached;
    }

    public TinkerMaterialIdWrapper copy() {
        return new TinkerMaterialIdWrapper(materialId);
    }

    public boolean isEmpty() {
        return this.materialId == null;
    }

    public MaterialId toMaterialId() {
        return materialId;
    }
}
