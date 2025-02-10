package com.pouffydev.gtconstruct.registry;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.pouffydev.gtconstruct.common.material.MaterialLink;
import com.pouffydev.gtconstruct.common.stats.PlungerHeadMaterialStats;
import com.pouffydev.gtconstruct.common.stats.SoftMalletHeadMaterialStats;
import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.tools.stats.StatlessMaterialStats;

public class GTCMaterialLinks {

    public static void init() {
        Links.register();
    }

    public static MaterialLink SiliconeRubber;
    public static MaterialLink StyreneButadieneRubber;
    public static MaterialLink Polybenzimidazole;
    public static MaterialLink Polyethylene;
    public static MaterialLink Polytetrafluoroethylene;
    public static MaterialLink Rubber;

    public static class Links {
        public static void register() {
            SiliconeRubber = link(GTCMaterialIds.siliconeRubber, GTMaterials.SiliconeRubber)
                    .withStats(StatlessMaterialStats.BINDING.getIdentifier(), PlungerHeadMaterialStats.ID, SoftMalletHeadMaterialStats.ID)
                    .buildAndRegister();
            StyreneButadieneRubber = link(GTCMaterialIds.styreneButadieneRubber, GTMaterials.StyreneButadieneRubber)
                    .withStats(StatlessMaterialStats.BINDING.getIdentifier(), PlungerHeadMaterialStats.ID, SoftMalletHeadMaterialStats.ID)
                    .buildAndRegister();
            Polybenzimidazole = link(GTCMaterialIds.polybenzimidazole, GTMaterials.Polybenzimidazole)
                    .withStats(StatlessMaterialStats.BINDING.getIdentifier(), PlungerHeadMaterialStats.ID, SoftMalletHeadMaterialStats.ID)
                    .buildAndRegister();
            Polyethylene = link(GTCMaterialIds.polyethylene, GTMaterials.Polyethylene)
                    .withStats(StatlessMaterialStats.BINDING.getIdentifier(), PlungerHeadMaterialStats.ID, SoftMalletHeadMaterialStats.ID)
                    .buildAndRegister();
            Polytetrafluoroethylene = link(GTCMaterialIds.polytetrafluoroethylene, GTMaterials.Polytetrafluoroethylene)
                    .withStats(StatlessMaterialStats.BINDING.getIdentifier(), PlungerHeadMaterialStats.ID, SoftMalletHeadMaterialStats.ID)
                    .buildAndRegister();
            Rubber = link(GTCMaterialIds.rubber, GTMaterials.Rubber)
                    .withStats(StatlessMaterialStats.BINDING.getIdentifier(), PlungerHeadMaterialStats.ID, SoftMalletHeadMaterialStats.ID)
                    .buildAndRegister();
        }

        private static MaterialLink.Builder link(MaterialId id, Material material) {
            ResourceLocation name = id.getId();
            return new MaterialLink.Builder(name).setTinkerMaterial(id).setGregTechMaterial(material);
        }
    }


}
