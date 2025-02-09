package com.pouffydev.gtconstruct.datagen.recipe;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.pouffydev.gtconstruct.registry.GTCMaterialIds;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.materials.stats.MaterialStatsId;

import java.util.*;

public enum MaterialToMaterial {
    siliconeRubber(GTCMaterialIds.siliconeRubber, GTMaterials.SiliconeRubber),
    styreneButadieneRubber(GTCMaterialIds.styreneButadieneRubber, GTMaterials.StyreneButadieneRubber),
    polybenzimidazole(GTCMaterialIds.polybenzimidazole, GTMaterials.Polybenzimidazole),
    polyethylene(GTCMaterialIds.polyethylene, GTMaterials.Polyethylene),
    polytetrafluoroethylene(GTCMaterialIds.polytetrafluoroethylene, GTMaterials.Polytetrafluoroethylene),
    rubber(GTCMaterialIds.rubber, GTMaterials.Rubber);

    public final MaterialId tinkerMaterial;
    public final Material gregtechMaterial;
    private final Map<MaterialId, List<MaterialStatsId>> allStats = new HashMap<>();

    MaterialToMaterial(MaterialId tinkerMaterial, Material gregtechMaterial, MaterialStatsId... stats) {
        this.tinkerMaterial = tinkerMaterial;
        this.gregtechMaterial = gregtechMaterial;
        allStats.computeIfAbsent(tinkerMaterial, materialId -> new ArrayList<>())
                .addAll(Arrays.asList(stats));
    }

    public static Material getGregtechMaterial(MaterialId tinkerMaterial) {
        for (MaterialToMaterial materialToMaterial : values()) {
            if (materialToMaterial.tinkerMaterial.equals(tinkerMaterial)) {
                return materialToMaterial.gregtechMaterial;
            }
        }
        return null;
    }

    public static MaterialId getTinkerMaterial(Material gregtechMaterial) {
        for (MaterialToMaterial materialToMaterial : values()) {
            if (materialToMaterial.gregtechMaterial.equals(gregtechMaterial)) {
                return materialToMaterial.tinkerMaterial;
            }
        }
        return null;
    }

    public static boolean contains(MaterialId tinkerMaterial) {
        for (MaterialToMaterial materialToMaterial : values()) {
            if (materialToMaterial.tinkerMaterial.equals(tinkerMaterial)) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(Material gregtechMaterial) {
        for (MaterialToMaterial materialToMaterial : values()) {
            if (materialToMaterial.gregtechMaterial.equals(gregtechMaterial)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasStat(MaterialId tinkerMaterial, MaterialStatsId stat) {
        for (MaterialToMaterial materialToMaterial : values()) {
            if (materialToMaterial.tinkerMaterial.equals(tinkerMaterial)) {
                return materialToMaterial.allStats.get(tinkerMaterial).contains(stat);
            }
        }
        return false;
    }

    public static boolean hasStat(Material gregtechMaterial, MaterialStatsId stat) {
        for (MaterialToMaterial materialToMaterial : values()) {
            if (materialToMaterial.gregtechMaterial.equals(gregtechMaterial)) {
                return materialToMaterial.allStats.get(materialToMaterial.tinkerMaterial).contains(stat);
            }
        }
        return false;
    }
}
