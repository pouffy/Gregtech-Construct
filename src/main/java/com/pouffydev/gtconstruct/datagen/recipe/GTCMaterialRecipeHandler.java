package com.pouffydev.gtconstruct.datagen.recipe;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.IngotProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKey;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.pouffydev.gtconstruct.api.GTConstructAPI;
import com.pouffydev.gtconstruct.api.recipe.NBTIngredient;
import com.pouffydev.gtconstruct.common.material.IMaterialLinkRegistryManager;
import com.pouffydev.gtconstruct.common.stats.PlungerHeadMaterialStats;
import com.pouffydev.gtconstruct.common.stats.SoftMalletHeadMaterialStats;
import com.pouffydev.gtconstruct.registry.GTCToolParts;
import com.pouffydev.gtconstruct.registry.registrate.GTCItems;
import dev.latvian.mods.kubejs.platform.forge.ingredient.CustomIngredient;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.PartialNBTIngredient;
import net.minecraftforge.common.crafting.StrictNBTIngredient;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.tools.TinkerToolParts;
import slimeknights.tconstruct.tools.stats.StatlessMaterialStats;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.NO_SMASHING;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.EXTRUDER_RECIPES;
import static slimeknights.tconstruct.library.tools.part.IMaterialItem.MATERIAL_TAG;

public class GTCMaterialRecipeHandler {

    public static void init(Consumer<FinishedRecipe> provider, Material material) {
        processIngot(material, provider);
        processLiquid(material, provider);
    }

    public static void processIngot(Material material, Consumer<FinishedRecipe> provider) {
        if (!material.shouldGenerateRecipesFor(ingot) || !material.hasProperty(PropertyKey.INGOT)) {
            return;
        }
        IMaterialLinkRegistryManager registryManager = GTConstructAPI.materialLinkManager;
        if (!registryManager.isLinked(material)) {
            return;
        }
        MaterialId tinkerMaterial = registryManager.getTinkerMaterial(material);

        int voltageMultiplier = getVoltageMultiplier(material);
        if (registryManager.hasStat(tinkerMaterial, PlungerHeadMaterialStats.ID)) {
            ItemStack plungerHead = GTCToolParts.plungerHead.asItem().getDefaultInstance();
            plungerHead.getOrCreateTag().putString(MATERIAL_TAG, tinkerMaterial.toString());
            EXTRUDER_RECIPES.recipeBuilder("extrude_" + material.getName() + "_to_plunger_head")
                    .inputItems(ingot, material, 2)
                    .notConsumable(GTCItems.ShapeExtruderPlungerHead)
                    .output(ItemRecipeCapability.CAP, StrictNBTIngredient.of(plungerHead))
                    .duration((int) material.getMass())
                    .EUt(8L * voltageMultiplier)
                    .save(provider);

            if (material.hasFlag(NO_SMASHING)) {
                EXTRUDER_RECIPES.recipeBuilder("extrude_" + material.getName() + "_dust_to_plunger_head")
                        .inputItems(dust, material, 2)
                        .notConsumable(GTCItems.ShapeExtruderPlungerHead)
                        .output(ItemRecipeCapability.CAP, StrictNBTIngredient.of(plungerHead))
                        .duration((int) material.getMass())
                        .EUt(8L * voltageMultiplier)
                        .save(provider);
            }
        }
        if (registryManager.hasStat(tinkerMaterial, SoftMalletHeadMaterialStats.ID)) {
            ItemStack softMalletHead = GTCToolParts.softMalletHead.asItem().getDefaultInstance();
            softMalletHead.getOrCreateTag().putString(MATERIAL_TAG, tinkerMaterial.toString());
            EXTRUDER_RECIPES.recipeBuilder("extrude_" + material.getName() + "_to_soft_mallet_head")
                    .inputItems(ingot, material, 3)
                    .notConsumable(GTCItems.ShapeExtruderSoftMalletHead)
                    .output(ItemRecipeCapability.CAP, StrictNBTIngredient.of(softMalletHead))
                    .duration((int) material.getMass())
                    .EUt(8L * voltageMultiplier)
                    .save(provider);

            if (material.hasFlag(NO_SMASHING)) {
                EXTRUDER_RECIPES.recipeBuilder("extrude_" + material.getName() + "_dust_to_soft_mallet_head")
                        .inputItems(dust, material, 3)
                        .notConsumable(GTCItems.ShapeExtruderSoftMalletHead)
                        .output(ItemRecipeCapability.CAP, StrictNBTIngredient.of(softMalletHead))
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
                    .inputItems(ingot, material, 1)
                    .notConsumable(GTCItems.ShapeExtruderToolBinding)
                    .output(ItemRecipeCapability.CAP, StrictNBTIngredient.of(toolBinding))
                    .duration((int) material.getMass())
                    .EUt(8L * voltageMultiplier)
                    .save(provider);
            EXTRUDER_RECIPES.recipeBuilder("extrude_" + material.getName() + "_to_tough_binding")
                    .inputItems(ingot, material, 3)
                    .notConsumable(GTCItems.ShapeExtruderToughCollar)
                    .output(ItemRecipeCapability.CAP, StrictNBTIngredient.of(toughBinding))
                    .duration((int) material.getMass())
                    .EUt(8L * voltageMultiplier)
                    .save(provider);

            if (material.hasFlag(NO_SMASHING)) {
                EXTRUDER_RECIPES.recipeBuilder("extrude_" + material.getName() + "_dust_to_tool_binding")
                        .inputItems(dust, material, 1)
                        .notConsumable(GTCItems.ShapeExtruderToolBinding)
                        .output(ItemRecipeCapability.CAP, StrictNBTIngredient.of(toolBinding))
                        .duration((int) material.getMass())
                        .EUt(8L * voltageMultiplier)
                        .save(provider);
                EXTRUDER_RECIPES.recipeBuilder("extrude_" + material.getName() + "_dust_to_tough_binding")
                        .inputItems(dust, material, 3)
                        .notConsumable(GTCItems.ShapeExtruderToughCollar)
                        .output(ItemRecipeCapability.CAP, StrictNBTIngredient.of(toughBinding))
                        .duration((int) material.getMass())
                        .EUt(8L * voltageMultiplier)
                        .save(provider);
            }
        }
    }

    public static void processLiquid(Material material, Consumer<FinishedRecipe> provider) {
        if (!(material.hasFlag(MaterialFlags.NO_UNIFICATION) && (material.hasProperty(PropertyKey.FLUID) && material.getFluid(FluidStorageKeys.LIQUID) != null))) {
            return;
        }

    }

    private static int getVoltageMultiplier(Material material) {
        return material.getBlastTemperature() >= 2800 ? VA[LV] : VA[ULV];
    }
}
