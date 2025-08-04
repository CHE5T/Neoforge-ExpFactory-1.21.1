package com.lazl0.expfactory.datagen;

import com.lazl0.expfactory.block.ModBlocks;
import com.lazl0.expfactory.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> TINIUM_SMELTABLES = List.of(ModItems.RAW_TINIUM,
                ModBlocks.TINIUM_ORE, ModBlocks.DEEPSLATE_TINIUM_ORE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TINIUM_BLOCK.get())
                .pattern("TTT")
                .pattern("TTT")
                .pattern("TTT")
                .define('T', ModItems.TINIUM_INGOT.get())
                .unlockedBy("has_tinium", has(ModItems.TINIUM_INGOT)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TINIUM_INGOT.get(), 9)
                .requires(ModBlocks.TINIUM_BLOCK)
                .unlockedBy("has tinium_block", has(ModBlocks.TINIUM_BLOCK))
                .save(recipeOutput, "exponential_factory:tinium_ingot_from_tinium_block");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TUENIUM_INGOT.get(), 9)
                .requires(ModBlocks.TUENIUM_BLOCK)
                .unlockedBy("has tuenium_block", has(ModBlocks.TUENIUM_BLOCK)).
                save(recipeOutput, "exponential_factory:tuenium_ingot_from_tuenium_block");

        oreSmelting(recipeOutput, TINIUM_SMELTABLES, RecipeCategory.MISC, ModItems.TINIUM_INGOT.get(), 0.7f, 200, "tinium");
        oreBlasting(recipeOutput, TINIUM_SMELTABLES, RecipeCategory.MISC, ModItems.TINIUM_INGOT.get(), 0.7f, 100, "tinium");
    }
}
