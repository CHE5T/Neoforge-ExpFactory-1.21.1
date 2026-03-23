package com.lazl0.expfactory.datagen;

import com.lazl0.expfactory.block.ModBlocks;
import com.lazl0.expfactory.item.ModItems;
import com.lazl0.expfactory.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        //Item to block recipes start
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
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TRINIUM_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.TRINIUM_INGOT.get())
                .unlockedBy("has_trinium", has(ModItems.TRINIUM_INGOT)).save(recipeOutput);
        //Item to block recipes end



        //Block to item recipes start
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
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TRINIUM_INGOT.get(), 9)
                .requires(ModBlocks.TRINIUM_BLOCK)
                .unlockedBy("has trinium_block", has(ModBlocks.TRINIUM_BLOCK)).
                save(recipeOutput, "exponential_factory:trinium_ingot_from_trinium_block");
        //Block to item recipes end



        //Mod tool recipes start

        //Tuenium tool recipes start
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TUENIUM_SWORD.get())
                .pattern("A")
                .pattern("A")
                .pattern("B")
                .define('A', ModTags.Items.TIER_TWO_INGOT)
                .define('B', Items.STICK)
                .unlockedBy("has tier_two_ingot", has(ModTags.Items.TIER_TWO_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TUENIUM_AXE.get())
                .pattern("AA")
                .pattern("AB")
                .pattern(" B")
                .define('A', ModTags.Items.TIER_TWO_INGOT)
                .define('B', Items.STICK)
                .unlockedBy("has tier_two_ingot", has(ModTags.Items.TIER_TWO_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TUENIUM_PICKAXE.get())
                .pattern("AAA")
                .pattern(" B ")
                .pattern(" B ")
                .define('A', ModTags.Items.TIER_TWO_INGOT)
                .define('B', Items.STICK)
                .unlockedBy("has tier_two_ingot", has(ModTags.Items.TIER_TWO_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TUENIUM_SHOVEL.get())
                .pattern("A")
                .pattern("B")
                .pattern("B")
                .define('A', ModTags.Items.TIER_TWO_INGOT)
                .define('B', Items.STICK)
                .unlockedBy("has tier_two_ingot", has(ModTags.Items.TIER_TWO_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TUENIUM_HOE.get())
                .pattern("AA")
                .pattern(" B")
                .pattern(" B")
                .define('A', ModTags.Items.TIER_TWO_INGOT)
                .define('B', Items.STICK)
                .unlockedBy("has tier_two_ingot", has(ModTags.Items.TIER_TWO_INGOT)).save(recipeOutput);
        //Tuenium tool recipes end

        //Mod tool recipes end



        //Mod armor recipes start

        //Tuenium armors start
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TUENIUM_HELMET.get())
                .pattern("AAA")
                .pattern("A A")
                .define('A', ModTags.Items.TIER_TWO_INGOT)
                .unlockedBy("has tier_two_ingot", has(ModTags.Items.TIER_TWO_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TUENIUM_CHESTPLATE.get())
                .pattern("A A")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModTags.Items.TIER_TWO_INGOT)
                .unlockedBy("has tier_two_ingot", has(ModTags.Items.TIER_TWO_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TUENIUM_LEGGINGS.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', ModTags.Items.TIER_TWO_INGOT)
                .unlockedBy("has tier_two_ingot", has(ModTags.Items.TIER_TWO_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TUENIUM_BOOTS.get())
                .pattern("A A")
                .pattern("A A")
                .define('A', ModTags.Items.TIER_TWO_INGOT)
                .unlockedBy("has tier_two_ingot", has(ModTags.Items.TIER_TWO_INGOT)).save(recipeOutput);
        //Tuenium armor recipes end

        //Mod armor recipes end



        //Solid Block Generator recipes start
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SOLID_WATER.get())
                .pattern("BAB")
                .pattern("ACA")
                .pattern("BAB")
                .define('A', ModTags.Items.TIER_ONE_INGOT)
                .define('B', Tags.Items.STONES)
                .define('C', Items.WATER_BUCKET)
                .unlockedBy("has water", has(Items.WATER_BUCKET)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SOLID_WATER_ADVANCED.get())
                .pattern("BAB")
                .pattern("ACA")
                .pattern("BAB")
                .define('A', ModTags.Items.TIER_TWO_INGOT)
                .define('B', Tags.Items.STONES)
                .define('C', Items.WATER_BUCKET)
                .unlockedBy("has tier_two_ingot", has(ModTags.Items.TIER_TWO_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SOLID_LAVA.get())
                .pattern("BAB")
                .pattern("ACA")
                .pattern("BAB")
                .define('A', ModTags.Items.TIER_TWO_INGOT)
                .define('B', Tags.Items.STONES)
                .define('C', Items.LAVA_BUCKET)
                .unlockedBy("has tier_two_ingot", has(ModTags.Items.TIER_TWO_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SOLID_GLOWSTONE.get())
                .pattern("BAB")
                .pattern("ACA")
                .pattern("BAB")
                .define('A', ModTags.Items.TIER_THREE_INGOT)
                .define('B', Tags.Items.STONES)
                .define('C', Items.GLOWSTONE)
                .unlockedBy("has trinium_ingot", has(ModTags.Items.TIER_THREE_INGOT)).save(recipeOutput);
        //Solid Block Generator recipes end

        //Special recipes start
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CAPSULE.get())
                .pattern("B B")
                .pattern(" A ")
                .pattern("B B")
                .define('A', ModTags.Items.TIER_ONE_INGOT)
                .define('B', Items.IRON_NUGGET)
                .unlockedBy("has tier_one_ingot", has(ModTags.Items.TIER_ONE_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.THERMAL_BATTERY.get())
                .pattern("ABA")
                .pattern("BCB")
                .pattern("ABA")
                .define('A', ModTags.Items.TIER_TWO_INGOT)
                .define('B', Items.REDSTONE)
                .define('C', Items.SANDSTONE)
                .unlockedBy("has tier_two_ingot", has(ModTags.Items.TIER_TWO_INGOT)).save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.REDSTONE_BATTERY.get())
                .pattern(" CA")
                .pattern("CBC")
                .pattern("BC ")
                .define('A', Tags.Items.INGOTS_GOLD)
                .define('B', Tags.Items.DUSTS_REDSTONE)
                .define('C', ModTags.Items.TIER_ONE_INGOT)
                .unlockedBy("has tier_one_ingot", has(ModTags.Items.TIER_ONE_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SIMPLE_MILL.get())
                .pattern("BAB")
                .pattern("ACA")
                .pattern("BAB")
                .define('A', ModTags.Items.TIER_TWO_INGOT)
                .define('B', Items.REDSTONE)
                .define('C', Items.PISTON)
                .unlockedBy("has tier_two_ingot", has(ModTags.Items.TIER_TWO_INGOT)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.COMBUSTION_GENERATOR.get())
                .pattern("BAB")
                .pattern("ACA")
                .pattern("BAB")
                .define('A', Items.COPPER_INGOT)
                .define('B', Items.COBBLESTONE)
                .define('C', ModTags.Items.TIER_TWO_INGOT)
                .unlockedBy("has tier_two_ingot", has(ModTags.Items.TIER_TWO_INGOT)).save(recipeOutput);
        //Special recipes end

        /*Component clearing recipes start
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.THERMAL_BATTERY.get()).requires(ModBlocks.THERMAL_BATTERY.get())
                .unlockedBy("has thermal_battery", has(ModBlocks.THERMAL_BATTERY))
                .save(recipeOutput, "exponential_factory:component_reset_thermal_battery");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.REDSTONE_BATTERY.get()).requires(ModItems.REDSTONE_BATTERY.get())
                .unlockedBy("has redstone_battery", has(ModItems.REDSTONE_BATTERY))
                .save(recipeOutput, "exponential_factory:component_reset_redstone_battery");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.SIMPLE_MILL.get()).requires(ModBlocks.SIMPLE_MILL.get())
                .unlockedBy("has simple_mill", has(ModBlocks.SIMPLE_MILL))
                .save(recipeOutput, "exponential_factory:component_reset_simple_mill");
        Component clearing recipes end*/



        //Smelting Recipes start
        List<ItemLike> TINIUM_INGOT_OUTPUT = List.of(ModItems.RAW_TINIUM, ModBlocks.TINIUM_ORE, ModBlocks.DEEPSLATE_TINIUM_ORE);
        oreSmelting(recipeOutput, TINIUM_INGOT_OUTPUT, RecipeCategory.MISC, ModItems.TINIUM_INGOT.get(), 0.7f, 200, "tinium");
        oreBlasting(recipeOutput, TINIUM_INGOT_OUTPUT, RecipeCategory.MISC, ModItems.TINIUM_INGOT.get(), 0.7f, 100, "tinium");

        List<ItemLike> TUENIUM_INGOT_OUTPUT = List.of(ModBlocks.DEEPSLATE_TUENIUM_ORE);
        oreSmelting(recipeOutput, TUENIUM_INGOT_OUTPUT, RecipeCategory.MISC, ModItems.TUENIUM_INGOT.get(), 0.7f, 200, "tuenium");
        oreBlasting(recipeOutput, TUENIUM_INGOT_OUTPUT, RecipeCategory.MISC, ModItems.TUENIUM_INGOT.get(), 0.7f, 100, "tuenium");

        List<ItemLike> IRON_INPUT = List.of(Items.IRON_INGOT);
        oreSmelting(recipeOutput, IRON_INPUT, RecipeCategory.MISC, ModItems.SYNTHETIC_TINIUM.get(), 0.7f, 200, "synthetic_tinium");
        oreBlasting(recipeOutput, IRON_INPUT, RecipeCategory.MISC, ModItems.SYNTHETIC_TINIUM.get(), 0.7f, 100, "synthetic_tinium");

        List<ItemLike> GOLD_INPUT = List.of(Items.GOLD_INGOT);
        oreSmelting(recipeOutput, GOLD_INPUT, RecipeCategory.MISC, ModItems.SYNTHETIC_TUENIUM.get(), 0.9f, 300, "synthetic_tuenium");
        oreBlasting(recipeOutput, GOLD_INPUT, RecipeCategory.MISC, ModItems.SYNTHETIC_TUENIUM.get(), 0.9f, 150, "synthetic_tuenium");
        //Smelting Recipes end
    }
}
