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
        blockWithItem(ModBlocks.TINIUM_BLOCK);
        blockWithItem(ModBlocks.TUENIUM_BLOCK);

        blockWithItem(ModBlocks.RAW_TINIUM_BLOCK);

        blockWithItem(ModBlocks.TINIUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_TINIUM_ORE);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
