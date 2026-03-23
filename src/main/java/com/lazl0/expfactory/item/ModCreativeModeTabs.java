package com.lazl0.expfactory.item;

import com.lazl0.expfactory.ExpFactory;
import com.lazl0.expfactory.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExpFactory.MODID);

    public static final Supplier<CreativeModeTab> TINIUM_ITEMS_TAB = CREATIVE_MODE_TAB.register("tinium_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TUENIUM_INGOT.get()))
                    .title(Component.translatable("creativetab.exponential_factory.tinium_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        //Items Start

                        //Crafting Ingredients Start
                        output.accept(ModItems.RAW_TINIUM);
                        output.accept(ModItems.TINIUM_INGOT);
                        output.accept(ModItems.SYNTHETIC_TINIUM);
                        output.accept(ModItems.TUENIUM_INGOT);
                        output.accept(ModItems.SYNTHETIC_TUENIUM);
                        output.accept(ModItems.TRINIUM_INGOT);

                        output.accept(ModItems.REDSTONE_BATTERY);
                        //Crafting Ingredients End

                        //Tools Start
                        output.accept(ModItems.TUENIUM_SWORD);
                        output.accept(ModItems.TUENIUM_AXE);
                        output.accept(ModItems.TUENIUM_PICKAXE);
                        output.accept(ModItems.TUENIUM_SHOVEL);
                        output.accept(ModItems.TUENIUM_HOE);
                        //Tools End

                        //Armors Start
                        output.accept(ModItems.TUENIUM_HELMET);
                        output.accept(ModItems.TUENIUM_CHESTPLATE);
                        output.accept(ModItems.TUENIUM_LEGGINGS);
                        output.accept(ModItems.TUENIUM_BOOTS);
                        //Armors End

                        //Items End



                        //Blocks Start

                        //Raw Ore Blocks Start
                        output.accept(ModBlocks.RAW_TINIUM_BLOCK);
                        //Raw Ore Blocks End

                        //Ingredient Blocks Start
                        output.accept(ModBlocks.TINIUM_BLOCK);
                        output.accept(ModBlocks.TUENIUM_BLOCK);
                        output.accept(ModBlocks.TRINIUM_BLOCK);
                        //Ingredient Blocks End

                        //Ore Blocks Start
                        output.accept(ModBlocks.TINIUM_ORE);
                        output.accept(ModBlocks.DEEPSLATE_TINIUM_ORE);
                        output.accept(ModBlocks.DEEPSLATE_TUENIUM_ORE);
                        output.accept(ModBlocks.NETHERRACK_TRINIUM_ORE);
                        //Ore Blocks End

                        //Custom Blocks Start
                        output.accept(ModBlocks.SOLID_WATER);
                        output.accept(ModBlocks.SOLID_WATER_ADVANCED);
                        output.accept(ModBlocks.SOLID_LAVA);
                        output.accept(ModBlocks.SOLID_GLOWSTONE);
                        output.accept(ModBlocks.CAPSULE);
                        //Custom Blocks End

                        //Storage Blocks Start
                        output.accept(ModBlocks.THERMAL_BATTERY);
                        //Storage Blocks End

                        //Crafting Blocks Start
                        output.accept(ModBlocks.SIMPLE_MILL);
                        //Crafting Blocks End

                        //Generator Blocks Start
                        output.accept(ModBlocks.COMBUSTION_GENERATOR);
                        //Generator Blocks End

                        //Blocks End
                    }).build());

    /*public static final Supplier<CreativeModeTab> TINIUM_BLOCK_TAB = CREATIVE_MODE_TAB.register("tinium_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.TINIUM_BLOCK))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(ExpFactory.MODID, "tinium_items_tab"))
                    .title(Component.translatable("creativetab.exponential_factory.tinium_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        //Raw ore blocks
                        output.accept(ModBlocks.RAW_TINIUM_BLOCK);
                        //Blocks made with ingredients
                        output.accept(ModBlocks.TINIUM_BLOCK);
                        output.accept(ModBlocks.TUENIUM_BLOCK);
                        output.accept(ModBlocks.TRINIUM_BLOCK);
                        //Ore Blocks
                        output.accept(ModBlocks.TINIUM_ORE);
                        output.accept(ModBlocks.DEEPSLATE_TINIUM_ORE);
                        output.accept(ModBlocks.DEEPSLATE_TUENIUM_ORE);
                        output.accept(ModBlocks.NETHERRACK_TRINIUM_ORE);
                        //Custom Blocks
                        output.accept(ModBlocks.SOLID_WATER);
                        output.accept(ModBlocks.SOLID_WATER_ADVANCED);
                        output.accept(ModBlocks.SOLID_LAVA);
                        output.accept(ModBlocks.SOLID_GLOWSTONE);
                        output.accept(ModBlocks.CAPSULE);

                        //Storage Blocks
                        output.accept(ModBlocks.THERMAL_BATTERY);
                        //Crafting Blocks
                        output.accept(ModBlocks.SIMPLE_MILL);
                    }).build());*/


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}