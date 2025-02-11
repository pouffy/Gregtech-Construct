package com.pouffydev.gtconstruct.datagen.recipe;

import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.IngotProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.recipe.ingredient.NBTIngredient;
import com.pouffydev.gtconstruct.api.GTConstructAPI;
import com.pouffydev.gtconstruct.common.material.IMaterialLinkRegistryManager;
import com.pouffydev.gtconstruct.common.material.MaterialLink;
import com.pouffydev.gtconstruct.common.stats.PlungerHeadMaterialStats;
import com.pouffydev.gtconstruct.common.stats.SoftMalletHeadMaterialStats;
import com.pouffydev.gtconstruct.registry.GTCToolParts;
import com.pouffydev.gtconstruct.registry.registrate.GTCItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.tools.nbt.MaterialIdNBT;
import slimeknights.tconstruct.tools.TinkerToolParts;
import slimeknights.tconstruct.tools.stats.StatlessMaterialStats;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.NO_SMASHING;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.EXTRUDER_RECIPES;
import static slimeknights.tconstruct.library.tools.part.IMaterialItem.MATERIAL_TAG;

public class GTCMaterialRecipeHandler {

    public static void init(Consumer<FinishedRecipe> provider) {
        ingot.executeHandler(provider, PropertyKey.INGOT, GTCMaterialRecipeHandler::processIngot);
    }

    public static void processIngot(TagPrefix ingotPrefix, Material material, IngotProperty property, Consumer<FinishedRecipe> provider) {
        IMaterialLinkRegistryManager registryManager = GTConstructAPI.materialLinkManager;
        Collection<MaterialLink> linkedMaterials = registryManager.getRegisteredMaterialLinks();
        if (!registryManager.isLinked(material)) {
            return;
        }
        MaterialId tinkerMaterial = registryManager.getTinkerMaterial(material);
        MaterialIdNBT nbt = new MaterialIdNBT(Collections.singletonList(tinkerMaterial));

        int voltageMultiplier = getVoltageMultiplier(material);
        if (registryManager.hasStat(tinkerMaterial, PlungerHeadMaterialStats.ID)) {
            ItemStack plungerHead = GTCToolParts.plungerHead.asItem().getDefaultInstance();
            plungerHead.getOrCreateTag().putString(MATERIAL_TAG, tinkerMaterial.toString());
            EXTRUDER_RECIPES.recipeBuilder("extrude_" + material.getName() + "_to_plunger_head")
                    .inputItems(ingotPrefix, material, 2)
                    .notConsumable(GTCItems.ShapeExtruderPlungerHead)
                    .output(ItemRecipeCapability.CAP, NBTIngredient.createNBTIngredient(plungerHead))
                    .duration((int) material.getMass())
                    .EUt(8L * voltageMultiplier)
                    .save(provider);

            if (material.hasFlag(NO_SMASHING)) {
                EXTRUDER_RECIPES.recipeBuilder("extrude_" + material.getName() + "_dust_to_plunger_head")
                        .inputItems(dust, material, 2)
                        .notConsumable(GTCItems.ShapeExtruderPlungerHead)
                        .output(ItemRecipeCapability.CAP, NBTIngredient.createNBTIngredient(plungerHead))
                        .duration((int) material.getMass())
                        .EUt(8L * voltageMultiplier)
                        .save(provider);
            }
        }
        if (registryManager.hasStat(tinkerMaterial, SoftMalletHeadMaterialStats.ID)) {
            ItemStack softMalletHead = GTCToolParts.softMalletHead.asItem().getDefaultInstance();
            softMalletHead.getOrCreateTag().putString(MATERIAL_TAG, tinkerMaterial.toString());
            EXTRUDER_RECIPES.recipeBuilder("extrude_" + material.getName() + "_to_soft_mallet_head")
                    .inputItems(ingotPrefix, material, 3)
                    .notConsumable(GTCItems.ShapeExtruderSoftMalletHead)
                    .output(ItemRecipeCapability.CAP, NBTIngredient.createNBTIngredient(softMalletHead))
                    .duration((int) material.getMass())
                    .EUt(8L * voltageMultiplier)
                    .save(provider);

            if (material.hasFlag(NO_SMASHING)) {
                EXTRUDER_RECIPES.recipeBuilder("extrude_" + material.getName() + "_dust_to_soft_mallet_head")
                        .inputItems(dust, material, 3)
                        .notConsumable(GTCItems.ShapeExtruderSoftMalletHead)
                        .output(ItemRecipeCapability.CAP, NBTIngredient.createNBTIngredient(softMalletHead))
                        .duration((int) material.getMass())
                        .EUt(8L * voltageMultiplier)
                        .save(provider);
            }
        }
        if (registryManager.hasStat(tinkerMaterial, StatlessMaterialStats.BINDING.getIdentifier())) {
            ItemStack toolBinding = TinkerToolParts.toolBinding.asItem().getDefaultInstance();
            toolBinding.getOrCreateTag().putString(MATERIAL_TAG, tinkerMaterial.toString());
            ItemStack toughBinding = TinkerToolParts.toughBinding.asItem().getDefaultInstance();
            toughBinding.getOrCreateTag().putString(MATERIAL_TAG, tinkerMaterial.toString());
            EXTRUDER_RECIPES.recipeBuilder("extrude_" + material.getName() + "_to_tool_binding")
                    .inputItems(ingotPrefix, material, 1)
                    .notConsumable(GTCItems.ShapeExtruderToolBinding)
                    .output(ItemRecipeCapability.CAP, NBTIngredient.createNBTIngredient(toolBinding))
                    .duration((int) material.getMass())
                    .EUt(8L * voltageMultiplier)
                    .save(provider);
            EXTRUDER_RECIPES.recipeBuilder("extrude_" + material.getName() + "_to_tough_binding")
                    .inputItems(ingotPrefix, material, 3)
                    .notConsumable(GTCItems.ShapeExtruderToughCollar)
                    .output(ItemRecipeCapability.CAP, NBTIngredient.createNBTIngredient(toughBinding))
                    .duration((int) material.getMass())
                    .EUt(8L * voltageMultiplier)
                    .save(provider);

            if (material.hasFlag(NO_SMASHING)) {
                EXTRUDER_RECIPES.recipeBuilder("extrude_" + material.getName() + "_dust_to_tool_binding")
                        .inputItems(dust, material, 1)
                        .notConsumable(GTCItems.ShapeExtruderToolBinding)
                        .output(ItemRecipeCapability.CAP, NBTIngredient.createNBTIngredient(toolBinding))
                        .duration((int) material.getMass())
                        .EUt(8L * voltageMultiplier)
                        .save(provider);
                EXTRUDER_RECIPES.recipeBuilder("extrude_" + material.getName() + "_dust_to_tough_binding")
                        .inputItems(dust, material, 3)
                        .notConsumable(GTCItems.ShapeExtruderToughCollar)
                        .output(ItemRecipeCapability.CAP, NBTIngredient.createNBTIngredient(toughBinding))
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
