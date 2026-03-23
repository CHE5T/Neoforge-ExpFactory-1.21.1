package com.lazl0.expfactory.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record SimpleMillRecipe(Ingredient inputItem, ItemStack outputItem) implements Recipe<SimpleMillRecipeInput> {
    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem);
        return list;
    }

    @Override
    public boolean matches(SimpleMillRecipeInput input, Level level) {
        //if(level.isClientSide()){
        //    return false;
        //}
        return inputItem.test(input.getItem(0));
    }

    @Override
    public ItemStack assemble(SimpleMillRecipeInput input, HolderLookup.Provider registries) {
        return outputItem.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return outputItem;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.SIMPLE_MILL_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.SIMPLE_MILL_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<SimpleMillRecipe> {
        public static final MapCodec<SimpleMillRecipe> CODEC = RecordCodecBuilder.mapCodec(simpleMillRecipeInstance -> simpleMillRecipeInstance.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient").forGetter(SimpleMillRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(SimpleMillRecipe::outputItem)
        ).apply(simpleMillRecipeInstance, SimpleMillRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, SimpleMillRecipe> STREAM_CODEC =
                StreamCodec.composite(
                        Ingredient.CONTENTS_STREAM_CODEC, SimpleMillRecipe::inputItem,
                        ItemStack.STREAM_CODEC, SimpleMillRecipe::outputItem,
                        SimpleMillRecipe::new);

        @Override
        public MapCodec<SimpleMillRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, SimpleMillRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
