package com.pouffydev.gtconstruct.datagen;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import com.google.gson.JsonObject;
import com.pouffydev.gtconstruct.GTConstruct;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.*;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.recipe.data.IRecipeHelper;
import slimeknights.tconstruct.TConstruct;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public abstract class GTCRecipeProvider extends RecipeProvider implements IConditionBuilder, IRecipeHelper {
    public GTCRecipeProvider(PackOutput generator) {
        super(generator);
        GTConstruct.sealGTCClass(this, "GTCRecipeProvider", "GTCRecipeProvider is trivial to recreate and directly extending can lead to addon recipes polluting our namespace.");
    }

    @Override
    protected abstract void buildRecipes(Consumer<FinishedRecipe> consumer);

    @Override
    public abstract @NotNull String getName();

    @Override
    public String getModId() {
        return GTConstruct.MOD_ID;
    }
}
