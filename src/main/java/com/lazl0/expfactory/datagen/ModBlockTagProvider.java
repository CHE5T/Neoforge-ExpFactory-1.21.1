package com.lazl0.expfactory.datagen;

import com.lazl0.expfactory.ExpFactory;
import com.lazl0.expfactory.block.ModBlocks;
import com.lazl0.expfactory.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ExpFactory.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.TINIUM_BLOCK.get())
                .add(ModBlocks.TUENIUM_BLOCK.get())
                .add(ModBlocks.TRINIUM_BLOCK.get())

                .add(ModBlocks.RAW_TINIUM_BLOCK.get())

                .add(ModBlocks.TINIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_TINIUM_ORE.get());


        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.TINIUM_BLOCK.get())

                .add(ModBlocks.TINIUM_ORE.get())
                .add(ModBlocks.DEEPSLATE_TINIUM_ORE.get())

                .add(ModBlocks.RAW_TINIUM_BLOCK.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.TRINIUM_BLOCK.get());

        tag(ModTags.Blocks.NEEDS_TUENIUM_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_TUENIUM_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .remove(ModTags.Blocks.NEEDS_TUENIUM_TOOL);
    }
}
