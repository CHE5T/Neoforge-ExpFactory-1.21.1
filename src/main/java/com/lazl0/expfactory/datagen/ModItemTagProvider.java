package com.lazl0.expfactory.datagen;

import com.lazl0.expfactory.ExpFactory;
import com.lazl0.expfactory.item.ModItems;
import com.lazl0.expfactory.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, ExpFactory.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        //Mod resources
        tag(ModTags.Items.TIER_ONE_INGOT)
                .add(ModItems.TINIUM_INGOT.get())
                .add(ModItems.SYNTHETIC_TINIUM.get());
        tag(ModTags.Items.TIER_TWO_INGOT)
                .add(ModItems.TUENIUM_INGOT.get())
                .add(ModItems.SYNTHETIC_TUENIUM.get());
        tag(ModTags.Items.TIER_THREE_INGOT)
                .add(ModItems.TRINIUM_INGOT.get());

        //Mod tools
        tag(ItemTags.SWORDS)
                .add(ModItems.TUENIUM_SWORD.get());
        tag(ItemTags.AXES)
                .add(ModItems.TUENIUM_AXE.get());
        tag(ItemTags.PICKAXES)
                .add(ModItems.TUENIUM_PICKAXE.get());
        tag(ItemTags.SHOVELS)
                .add(ModItems.TUENIUM_SHOVEL.get());
        tag(ItemTags.HOES)
                .add(ModItems.TUENIUM_HOE.get());

        //Mod armors
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.TUENIUM_HELMET.get())
                .add(ModItems.TUENIUM_CHESTPLATE.get())
                .add(ModItems.TUENIUM_LEGGINGS.get())
                .add(ModItems.TUENIUM_BOOTS.get());
    }
}
