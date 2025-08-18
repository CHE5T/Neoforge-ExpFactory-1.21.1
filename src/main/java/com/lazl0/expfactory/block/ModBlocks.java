package com.lazl0.expfactory.block;

import com.lazl0.expfactory.ExpFactory;
import com.lazl0.expfactory.block.custom.*;
import com.lazl0.expfactory.item.ModItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(ExpFactory.MODID);

    //Raw ore blocks
    public static final DeferredBlock<Block> RAW_TINIUM_BLOCK = registerBlock("raw_tinium_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4.0f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    //Blocks made from ingredients
    public static final DeferredBlock<Block> TINIUM_BLOCK = registerBlock("tinium_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3.0f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> TUENIUM_BLOCK = registerBlock("tuenium_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4.0f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> TRINIUM_BLOCK = registerBlock("trinium_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(6.5f).requiresCorrectToolForDrops().sound(SoundType.ANCIENT_DEBRIS)));
    //Ore blocks
    public static final DeferredBlock<Block> TINIUM_ORE = registerBlock("tinium_ore",
            () -> new DropExperienceBlock(UniformInt.of(2,4),
                    BlockBehaviour.Properties.of().strength(4.0f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final DeferredBlock<Block> DEEPSLATE_TINIUM_ORE = registerBlock("deepslate_tinium_ore",
            () -> new DropExperienceBlock(UniformInt.of(4,6),
                    BlockBehaviour.Properties.of().strength(5.0f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    public static final DeferredBlock<Block> DEEPSLATE_TUENIUM_ORE = registerBlock("deepslate_tuenium_ore",
            () -> new DropExperienceBlock(UniformInt.of(5,7),
                    BlockBehaviour.Properties.of().strength(6.0f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));
    public static final DeferredBlock<Block> NETHERRACK_TRINIUM_ORE = registerBlock("netherrack_trinium_ore",
            () -> new DropExperienceBlock(UniformInt.of(6,8),
                    BlockBehaviour.Properties.of().strength(7.0f).requiresCorrectToolForDrops().sound(SoundType.NETHERRACK)));
    //Custom Blocks
    public static final DeferredBlock<SolidWater> SOLID_WATER = registerBlock("solid_water",
            () -> new SolidWater(BlockBehaviour.Properties.of()
                    .strength(0.1f, 100f).sound(SoundType.MUD),
                    "tooltip.exponential_factory.solid_water",
                    "tooltip.exponential_factory.solid_water.shift_down"));
    public static final DeferredBlock<SolidWaterAdvanced> SOLID_WATER_ADVANCED = registerBlock("solid_water_advanced",
            () -> new SolidWaterAdvanced(BlockBehaviour.Properties.of()
                    .strength(0.2f, 100f).sound(SoundType.MUD),
                    "tooltip.exponential_factory.solid_water_advanced"));
    public static final DeferredBlock<SolidLava> SOLID_LAVA = registerBlock("solid_lava",
            () -> new SolidLava(BlockBehaviour.Properties.of()
                    .strength(0.4f, 100f).sound(SoundType.WART_BLOCK),
                    "tooltip.exponential_factory.solid_lava"));
    public static final DeferredBlock<SolidGlowstone> SOLID_GLOWSTONE = registerBlock("solid_glowstone",
            () -> new SolidGlowstone(BlockBehaviour.Properties.of()
                    .strength(0.4f, 100f).sound(SoundType.GLASS),
                    "tooltip.exponential_factory.solid_glowstone"));

    public static final DeferredBlock<Block> CAPSULE = registerBlock("capsule",
            () -> new Capsule(BlockBehaviour.Properties.of().noLootTable().noOcclusion()));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
