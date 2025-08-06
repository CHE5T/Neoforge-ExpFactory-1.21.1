package com.lazl0.expfactory.datagen;

import com.lazl0.expfactory.block.ModBlocks;
import com.lazl0.expfactory.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
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

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_TINIUM_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.RAW_TINIUM.get())
                .unlockedBy("has_raw_tinium", has(ModItems.RAW_TINIUM)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TINIUM_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.TINIUM_INGOT.get())
                .unlockedBy("has_tinium", has(ModItems.TINIUM_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TUENIUM_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.TUENIUM_INGOT.get())
                .unlockedBy("has_tuenium", has(ModItems.TUENIUM_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SOLID_WATER.get())
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.STONE)
                .define('C', Items.WATER_BUCKET)
                .unlockedBy("has_water", has(Items.WATER_BUCKET)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_TINIUM.get(), 9)
                .requires(ModBlocks.RAW_TINIUM_BLOCK)
                .unlockedBy("has has_raw_tinium", has(ModBlocks.RAW_TINIUM_BLOCK))
                .save(recipeOutput, "exponential_factory:raw_tinium_from_raw_tinium_block");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TINIUM_INGOT.get(), 9)
                .requires(ModBlocks.TINIUM_BLOCK)
                .unlockedBy("has tinium_block", has(ModBlocks.TINIUM_BLOCK))
                .save(recipeOutput, "exponential_factory:tinium_ingot_from_tinium_block");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TUENIUM_INGOT.get(), 9)
                .requires(ModBlocks.TUENIUM_BLOCK)
                .unlockedBy("has tuenium_block", has(ModBlocks.TUENIUM_BLOCK)).
                save(recipeOutput, "exponential_factory:tuenium_ingot_from_tuenium_block");

        List<ItemLike> TINIUM_INGOT_OUTPUT = List.of(ModItems.RAW_TINIUM, ModBlocks.TINIUM_ORE, ModBlocks.DEEPSLATE_TINIUM_ORE);
        oreSmelting(recipeOutput, TINIUM_INGOT_OUTPUT, RecipeCategory.MISC, ModItems.TINIUM_INGOT.get(), 0.7f, 200, "tinium");
        oreBlasting(recipeOutput, TINIUM_INGOT_OUTPUT, RecipeCategory.MISC, ModItems.TINIUM_INGOT.get(), 0.7f, 100, "tinium");

        List<ItemLike> IRON_INPUT = List.of(Items.IRON_INGOT);
        oreSmelting(recipeOutput, IRON_INPUT, RecipeCategory.MISC, ModItems.SYNTHETIC_TINIUM.get(), 0.7f, 200, "synthetic_tinium");
        oreBlasting(recipeOutput, IRON_INPUT, RecipeCategory.MISC, ModItems.SYNTHETIC_TINIUM.get(), 0.7f, 100, "synthetic_tinium");
    }
}
