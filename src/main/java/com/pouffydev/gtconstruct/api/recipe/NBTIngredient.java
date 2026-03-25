package com.pouffydev.gtconstruct.api.recipe;

import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.AbstractIngredient;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientSerializer;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class NBTIngredient extends AbstractIngredient {
    public static final NBTIngredientSerializer SERIALIZER = new NBTIngredientSerializer(NBTIngredient::new, NBTIngredient::new);

    private final Predicate<ItemStack> predicate;

    public NBTIngredient(Predicate<ItemStack> predicate) {
        super(Stream.empty());
        this.predicate = predicate;
    }

    private NBTIngredient(JsonObject json) {
        predicate = stack -> true;
    }

    private NBTIngredient(FriendlyByteBuf buf) {
        predicate = stack -> true;
    }

    @Override
    public boolean isSimple() {
        return false;
    }

    @Override
    public IIngredientSerializer<? extends Ingredient> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public final JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("type", CraftingHelper.getID(getSerializer()).toString());
        toJson(json);
        return json;
    }

    @Override
    public boolean test(ItemStack stack) {
        return predicate.test(stack);
    }

    public void toJson(JsonObject json) {
    }

    public void write(FriendlyByteBuf buf) {
    }

    public record NBTIngredientSerializer(Function<JsonObject, NBTIngredient> fromJson,
                                   Function<FriendlyByteBuf, NBTIngredient> fromNet) implements IIngredientSerializer<NBTIngredient> {
        @Override
        public NBTIngredient parse(JsonObject json) {
            return fromJson.apply(json);
        }

        @Override
        public NBTIngredient parse(FriendlyByteBuf buf) {
            return fromNet.apply(buf);
        }

        @Override
        public void write(FriendlyByteBuf buf, NBTIngredient ingredient) {
            ingredient.write(buf);
        }
    }
}
