package com.pouffydev.gtconstruct.registry;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.pouffydev.gtconstruct.common.material.MaterialLink;
import com.pouffydev.gtconstruct.common.stats.PlungerHeadMaterialStats;
import com.pouffydev.gtconstruct.common.stats.SoftMalletHeadMaterialStats;
import net.minecraft.resources.ResourceLocation;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.tools.data.material.MaterialIds;
import slimeknights.tconstruct.tools.stats.*;

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

    public static MaterialLink Bismuth;

    public static MaterialLink SterlingSilver;
    public static MaterialLink BlackBronze;
    public static MaterialLink BismuthBronze;
    public static MaterialLink Cupronickel;
    public static MaterialLink BlackSteel;
    public static MaterialLink BlueSteel;
    public static MaterialLink RedSteel;

    public static MaterialLink Duranium;
    public static MaterialLink Neutronium;

    //tinker stuff
    public static MaterialLink Steel;
    public static MaterialLink Bronze;
    public static MaterialLink Silver;
    public static MaterialLink Invar;
    public static MaterialLink Cobalt;
    public static MaterialLink Osmium;
    public static MaterialLink Lead;
    public static MaterialLink RoseGold;
    public static MaterialLink Electrum;

    //vanilla stuff
    public static MaterialLink Iron;
    public static MaterialLink Gold;
    public static MaterialLink Copper;

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

            Bismuth = link(GTCMaterialIds.bismuth, GTMaterials.Bismuth)
                    .withStats(HeadMaterialStats.ID, HandleMaterialStats.ID, StatlessMaterialStats.BINDING.getIdentifier(), LimbMaterialStats.ID, GripMaterialStats.ID)
                    .buildAndRegister();

            SterlingSilver = link(GTCMaterialIds.sterlingSilver, GTMaterials.SterlingSilver)
                    .withStats(HeadMaterialStats.ID, HandleMaterialStats.ID, StatlessMaterialStats.BINDING.getIdentifier(), LimbMaterialStats.ID, GripMaterialStats.ID)
                    .buildAndRegister();
            BlackBronze = link(GTCMaterialIds.blackBronze, GTMaterials.BlackBronze)
                    .withStats(HeadMaterialStats.ID, HandleMaterialStats.ID, StatlessMaterialStats.BINDING.getIdentifier(), LimbMaterialStats.ID, GripMaterialStats.ID)
                    .buildAndRegister();
            BismuthBronze = link(GTCMaterialIds.bismuthBronze, GTMaterials.BismuthBronze)
                    .withStats(HeadMaterialStats.ID, HandleMaterialStats.ID, StatlessMaterialStats.BINDING.getIdentifier(), LimbMaterialStats.ID, GripMaterialStats.ID)
                    .buildAndRegister();
            Cupronickel = link(GTCMaterialIds.cupronickel, GTMaterials.Cupronickel)
                    .withStats(HeadMaterialStats.ID, HandleMaterialStats.ID, StatlessMaterialStats.BINDING.getIdentifier(), LimbMaterialStats.ID, GripMaterialStats.ID)
                    .buildAndRegister();
            BlackSteel = link(GTCMaterialIds.blackSteel, GTMaterials.BlackSteel)
                    .withStats(HeadMaterialStats.ID, HandleMaterialStats.ID, StatlessMaterialStats.BINDING.getIdentifier(), LimbMaterialStats.ID, GripMaterialStats.ID,
                            PlatingMaterialStats.BOOTS.getId(), PlatingMaterialStats.LEGGINGS.getId(), PlatingMaterialStats.CHESTPLATE.getId(), PlatingMaterialStats.HELMET.getId(), StatlessMaterialStats.MAILLE.getIdentifier())
                    .buildAndRegister();
            BlueSteel = link(GTCMaterialIds.blueSteel, GTMaterials.BlueSteel)
                    .withStats(HeadMaterialStats.ID, HandleMaterialStats.ID, StatlessMaterialStats.BINDING.getIdentifier(), LimbMaterialStats.ID, GripMaterialStats.ID,
                            PlatingMaterialStats.BOOTS.getId(), PlatingMaterialStats.LEGGINGS.getId(), PlatingMaterialStats.CHESTPLATE.getId(), PlatingMaterialStats.HELMET.getId(), StatlessMaterialStats.MAILLE.getIdentifier())
                    .buildAndRegister();
            RedSteel = link(GTCMaterialIds.redSteel, GTMaterials.RedSteel)
                    .withStats(HeadMaterialStats.ID, HandleMaterialStats.ID, StatlessMaterialStats.BINDING.getIdentifier(), LimbMaterialStats.ID, GripMaterialStats.ID,
                            PlatingMaterialStats.BOOTS.getId(), PlatingMaterialStats.LEGGINGS.getId(), PlatingMaterialStats.CHESTPLATE.getId(), PlatingMaterialStats.HELMET.getId(), StatlessMaterialStats.MAILLE.getIdentifier())
                    .buildAndRegister();

            Duranium = link(GTCMaterialIds.duranium, GTMaterials.Duranium)
                    .withStats(HeadMaterialStats.ID, HandleMaterialStats.ID, StatlessMaterialStats.BINDING.getIdentifier(), LimbMaterialStats.ID, GripMaterialStats.ID)
                    .buildAndRegister();
            Neutronium = link(GTCMaterialIds.neutronium, GTMaterials.Neutronium)
                    .withStats(HeadMaterialStats.ID, HandleMaterialStats.ID, StatlessMaterialStats.BINDING.getIdentifier(), LimbMaterialStats.ID, GripMaterialStats.ID)
                    .buildAndRegister();

            Iron = link(MaterialIds.iron, GTMaterials.Iron)
                    .withStats(HeadMaterialStats.ID, HandleMaterialStats.ID, StatlessMaterialStats.BINDING.getIdentifier(), LimbMaterialStats.ID, GripMaterialStats.ID,
                            PlatingMaterialStats.BOOTS.getId(), PlatingMaterialStats.LEGGINGS.getId(), PlatingMaterialStats.CHESTPLATE.getId(), PlatingMaterialStats.HELMET.getId(), StatlessMaterialStats.MAILLE.getIdentifier())
                    .buildAndRegister();
            Gold = link(MaterialIds.gold, GTMaterials.Gold)
                    .withStats(PlatingMaterialStats.BOOTS.getId(), PlatingMaterialStats.LEGGINGS.getId(), PlatingMaterialStats.CHESTPLATE.getId(), PlatingMaterialStats.HELMET.getId(), StatlessMaterialStats.MAILLE.getIdentifier())
                    .buildAndRegister();
            Copper = link(MaterialIds.copper, GTMaterials.Copper)
                    .withStats(HeadMaterialStats.ID, HandleMaterialStats.ID, StatlessMaterialStats.BINDING.getIdentifier(), LimbMaterialStats.ID, GripMaterialStats.ID,
                            PlatingMaterialStats.BOOTS.getId(), PlatingMaterialStats.LEGGINGS.getId(), PlatingMaterialStats.CHESTPLATE.getId(), PlatingMaterialStats.HELMET.getId(), StatlessMaterialStats.MAILLE.getIdentifier())
                    .buildAndRegister();
            Steel = link(MaterialIds.steel, GTMaterials.Steel)
                    .withStats(HeadMaterialStats.ID, HandleMaterialStats.ID, StatlessMaterialStats.BINDING.getIdentifier(), LimbMaterialStats.ID, GripMaterialStats.ID,
                            PlatingMaterialStats.BOOTS.getId(), PlatingMaterialStats.LEGGINGS.getId(), PlatingMaterialStats.CHESTPLATE.getId(), PlatingMaterialStats.HELMET.getId(), StatlessMaterialStats.MAILLE.getIdentifier())
                    .buildAndRegister();
            Bronze = link(MaterialIds.bronze, GTMaterials.Bronze)
                    .withStats(HeadMaterialStats.ID, HandleMaterialStats.ID, StatlessMaterialStats.BINDING.getIdentifier(), LimbMaterialStats.ID, GripMaterialStats.ID,
                            PlatingMaterialStats.BOOTS.getId(), PlatingMaterialStats.LEGGINGS.getId(), PlatingMaterialStats.CHESTPLATE.getId(), PlatingMaterialStats.HELMET.getId(), StatlessMaterialStats.MAILLE.getIdentifier())
                    .buildAndRegister();
            Silver = link(MaterialIds.silver, GTMaterials.Silver)
                    .withStats(HeadMaterialStats.ID, HandleMaterialStats.ID, StatlessMaterialStats.BINDING.getIdentifier(), LimbMaterialStats.ID, GripMaterialStats.ID,
                            PlatingMaterialStats.BOOTS.getId(), PlatingMaterialStats.LEGGINGS.getId(), PlatingMaterialStats.CHESTPLATE.getId(), PlatingMaterialStats.HELMET.getId(), StatlessMaterialStats.MAILLE.getIdentifier())
                    .buildAndRegister();
            Invar = link(MaterialIds.invar, GTMaterials.Invar)
                    .withStats(HeadMaterialStats.ID, HandleMaterialStats.ID, StatlessMaterialStats.BINDING.getIdentifier(), LimbMaterialStats.ID, GripMaterialStats.ID,
                            PlatingMaterialStats.BOOTS.getId(), PlatingMaterialStats.LEGGINGS.getId(), PlatingMaterialStats.CHESTPLATE.getId(), PlatingMaterialStats.HELMET.getId(), StatlessMaterialStats.MAILLE.getIdentifier())
                    .buildAndRegister();
            Cobalt = link(MaterialIds.cobalt, GTMaterials.Cobalt)
                    .withStats(HeadMaterialStats.ID, HandleMaterialStats.ID, StatlessMaterialStats.BINDING.getIdentifier(), LimbMaterialStats.ID, GripMaterialStats.ID,
                            PlatingMaterialStats.BOOTS.getId(), PlatingMaterialStats.LEGGINGS.getId(), PlatingMaterialStats.CHESTPLATE.getId(), PlatingMaterialStats.HELMET.getId(), StatlessMaterialStats.MAILLE.getIdentifier())
                    .buildAndRegister();
            Osmium = link(MaterialIds.osmium, GTMaterials.Osmium)
                    .withStats(HeadMaterialStats.ID, HandleMaterialStats.ID, StatlessMaterialStats.BINDING.getIdentifier(),
                            PlatingMaterialStats.BOOTS.getId(), PlatingMaterialStats.LEGGINGS.getId(), PlatingMaterialStats.CHESTPLATE.getId(), PlatingMaterialStats.HELMET.getId(), StatlessMaterialStats.MAILLE.getIdentifier())
                    .buildAndRegister();
            Lead = link(MaterialIds.lead, GTMaterials.Lead)
                    .withStats(HeadMaterialStats.ID, HandleMaterialStats.ID, StatlessMaterialStats.BINDING.getIdentifier(), LimbMaterialStats.ID, GripMaterialStats.ID,
                            PlatingMaterialStats.BOOTS.getId(), PlatingMaterialStats.LEGGINGS.getId(), PlatingMaterialStats.CHESTPLATE.getId(), PlatingMaterialStats.HELMET.getId(), StatlessMaterialStats.MAILLE.getIdentifier())
                    .buildAndRegister();
            RoseGold = link(MaterialIds.roseGold, GTMaterials.RoseGold)
                    .withStats(HeadMaterialStats.ID, HandleMaterialStats.ID, StatlessMaterialStats.BINDING.getIdentifier(), LimbMaterialStats.ID, GripMaterialStats.ID, StatlessMaterialStats.BOWSTRING.getIdentifier(),
                            PlatingMaterialStats.BOOTS.getId(), PlatingMaterialStats.LEGGINGS.getId(), PlatingMaterialStats.CHESTPLATE.getId(), PlatingMaterialStats.HELMET.getId(), StatlessMaterialStats.MAILLE.getIdentifier())
                    .buildAndRegister();
            Electrum = link(MaterialIds.electrum, GTMaterials.Electrum)
                    .withStats(HeadMaterialStats.ID, HandleMaterialStats.ID, StatlessMaterialStats.BINDING.getIdentifier(), LimbMaterialStats.ID, GripMaterialStats.ID,
                            PlatingMaterialStats.BOOTS.getId(), PlatingMaterialStats.LEGGINGS.getId(), PlatingMaterialStats.CHESTPLATE.getId(), PlatingMaterialStats.HELMET.getId(), StatlessMaterialStats.MAILLE.getIdentifier())
                    .buildAndRegister();
        }

        private static MaterialLink.Builder link(MaterialId id, Material material) {
            ResourceLocation name = id.getId();
            return new MaterialLink.Builder(name).setTinkerMaterial(id).setGregTechMaterial(material);
        }
    }


}
