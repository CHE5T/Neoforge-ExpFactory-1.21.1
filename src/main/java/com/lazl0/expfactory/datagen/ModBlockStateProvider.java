package com.lazl0.expfactory.datagen;

import com.lazl0.expfactory.ExpFactory;
import com.lazl0.expfactory.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider  extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ExpFactory.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //Raw ore blocks
        blockWithItem(ModBlocks.RAW_TINIUM_BLOCK);
        //Blocks made with ingredients
        blockWithItem(ModBlocks.TINIUM_BLOCK);
        blockWithItem(ModBlocks.TUENIUM_BLOCK);
        //Ore blocks
        blockWithItem(ModBlocks.TINIUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_TINIUM_ORE);
        //Custom blocks
        blockWithItem(ModBlocks.SOLID_WATER);
        blockWithItem(ModBlocks.SOLID_WATER_ADVANCED);
        blockWithItem(ModBlocks.SOLID_LAVA);
        blockWithItem(ModBlocks.SOLID_GLOWSTONE);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
