package com.pouffydev.gtconstruct.integration.kubejs.helper;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.common.data.GTMaterials;

import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Supplier;

public record MaterialWrapper(Supplier<Material> material) {

    private static final Map<String, MaterialWrapper> PARSE_CACHE = new WeakHashMap<>();

    public static MaterialWrapper fromString(CharSequence str) {
        String trimmed = str.toString().trim();

        var cached = PARSE_CACHE.get(trimmed);

        if (cached != null) {
            return cached.isEmpty() ? null : cached.copy();
        }

        final String trimmedFinal = trimmed;
        cached = new MaterialWrapper(() -> GTMaterials.get(trimmedFinal));
        PARSE_CACHE.put(trimmed, cached);
        return cached.copy();
    }

    public MaterialWrapper copy() {
        return new MaterialWrapper(material);
    }

    public boolean isEmpty() {
        return this.material == null;
    }

    public Material toMaterial() {
        return material.get();
    }
}
