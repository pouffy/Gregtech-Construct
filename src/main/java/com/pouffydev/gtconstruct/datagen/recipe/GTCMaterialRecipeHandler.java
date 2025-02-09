package com.pouffydev.gtconstruct.datagen.recipe;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.IngotProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.pouffydev.gtconstruct.common.manager.LinkedMaterialManager;
import com.pouffydev.gtconstruct.common.stats.PlungerHeadMaterialStats;
import com.pouffydev.gtconstruct.common.stats.SoftMalletHeadMaterialStats;
import com.pouffydev.gtconstruct.registry.GTCCommons;
import com.pouffydev.gtconstruct.registry.GTCMaterialRegistry;
import com.pouffydev.gtconstruct.registry.GTCToolParts;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.tools.nbt.MaterialIdNBT;
import slimeknights.tconstruct.tools.TinkerToolParts;
import slimeknights.tconstruct.tools.stats.StatlessMaterialStats;

import java.util.Collections;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.NO_SMASHING;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.EXTRUDER_RECIPES;

public class GTCMaterialRecipeHandler {

    public static void init(Consumer<FinishedRecipe> provider) {
        ingot.executeHandler(provider, PropertyKey.INGOT, GTCMaterialRecipeHandler::processIngot);
    }

    public static void processIngot(TagPrefix ingotPrefix, Material material, IngotProperty property, Consumer<FinishedRecipe> provider) {
        LinkedMaterialManager linkedMaterialManager = GTCMaterialRegistry.getInstance().linkedMaterialManager;
        if (!linkedMaterialManager.isMaterialLinked(material)) {
            return;
        }
        MaterialId tinkerMaterial = linkedMaterialManager.getTinkerMaterial(material);
        MaterialIdNBT nbt = new MaterialIdNBT(Collections.singletonList(tinkerMaterial));

        int voltageMultiplier = getVoltageMultiplier(material);
        if (linkedMaterialManager.hasStat(tinkerMaterial, PlungerHeadMaterialStats.ID)) {
            EXTRUDER_RECIPES.recipeBuilder("extrude_" + material.getName() + "_to_plunger_head")
                    .inputItems(ingotPrefix, material, 2)
                    .notConsumable(GTCCommons.ShapeExtruderPlungerHead)
                    .outputItems(nbt.updateStack(new ItemStack(GTCToolParts.plungerHead.get())))
                    .duration((int) material.getMass())
                    .EUt(8L * voltageMultiplier)
                    .save(provider);

            if (material.hasFlag(NO_SMASHING)) {
                EXTRUDER_RECIPES.recipeBuilder("extrude_" + material.getName() + "_dust_to_plunger_head")
                        .inputItems(dust, material, 2)
                        .notConsumable(GTCCommons.ShapeExtruderPlungerHead)
                        .outputItems(nbt.updateStack(new ItemStack(GTCToolParts.plungerHead.get())))
                        .duration((int) material.getMass())
                        .EUt(8L * voltageMultiplier)
                        .save(provider);
            }
        }
        if (linkedMaterialManager.hasStat(tinkerMaterial, SoftMalletHeadMaterialStats.ID)) {
            EXTRUDER_RECIPES.recipeBuilder("extrude_" + material.getName() + "_to_soft_mallet_head")
                    .inputItems(ingotPrefix, material, 3)
                    .notConsumable(GTCCommons.ShapeExtruderSoftMalletHead)
                    .outputItems(nbt.updateStack(new ItemStack(GTCToolParts.softMalletHead.get())))
                    .duration((int) material.getMass())
                    .EUt(8L * voltageMultiplier)
                    .save(provider);

            if (material.hasFlag(NO_SMASHING)) {
                EXTRUDER_RECIPES.recipeBuilder("extrude_" + material.getName() + "_dust_to_soft_mallet_head")
                        .inputItems(dust, material, 3)
                        .notConsumable(GTCCommons.ShapeExtruderSoftMalletHead)
                        .outputItems(nbt.updateStack(new ItemStack(GTCToolParts.softMalletHead.get())))
                        .duration((int) material.getMass())
                        .EUt(8L * voltageMultiplier)
                        .save(provider);
            }
        }
        if (linkedMaterialManager.hasStat(tinkerMaterial, StatlessMaterialStats.BINDING.getIdentifier())) {
            EXTRUDER_RECIPES.recipeBuilder("extrude_" + material.getName() + "_to_tool_binding")
                    .inputItems(ingotPrefix, material, 1)
                    .notConsumable(GTCCommons.ShapeExtruderToolBinding)
                    .outputItems(nbt.updateStack(new ItemStack(TinkerToolParts.toolBinding.get())))
                    .duration((int) material.getMass())
                    .EUt(8L * voltageMultiplier)
                    .save(provider);
            EXTRUDER_RECIPES.recipeBuilder("extrude_" + material.getName() + "_to_tough_binding")
                    .inputItems(ingotPrefix, material, 3)
                    .notConsumable(GTCCommons.ShapeExtruderToughCollar)
                    .outputItems(nbt.updateStack(new ItemStack(TinkerToolParts.toughBinding.get())))
                    .duration((int) material.getMass())
                    .EUt(8L * voltageMultiplier)
                    .save(provider);

            if (material.hasFlag(NO_SMASHING)) {
                EXTRUDER_RECIPES.recipeBuilder("extrude_" + material.getName() + "_dust_to_tool_binding")
                        .inputItems(dust, material, 1)
                        .notConsumable(GTCCommons.ShapeExtruderToolBinding)
                        .outputItems(nbt.updateStack(new ItemStack(TinkerToolParts.toolBinding.get())))
                        .duration((int) material.getMass())
                        .EUt(8L * voltageMultiplier)
                        .save(provider);
                EXTRUDER_RECIPES.recipeBuilder("extrude_" + material.getName() + "_dust_to_tough_binding")
                        .inputItems(dust, material, 3)
                        .notConsumable(GTCCommons.ShapeExtruderToughCollar)
                        .outputItems(nbt.updateStack(new ItemStack(TinkerToolParts.toughBinding.get())))
                        .duration((int) material.getMass())
                        .EUt(8L * voltageMultiplier)
                        .save(provider);
            }
        }
    }

    private static int getVoltageMultiplier(Material material) {
        return material.getBlastTemperature() >= 2800 ? VA[LV] : VA[ULV];
    }
}
