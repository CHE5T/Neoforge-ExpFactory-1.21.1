package com.lazl0.expfactory.util;

import com.lazl0.expfactory.ExpFactory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_TUENIUM_TOOL = createTage("needs_tuenium_tool");
        public static final TagKey<Block> INCORRECT_FOR_TUENIUM_TOOL = createTage("incorrect_for_tuenium_tool");

        private static TagKey<Block> createTage(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(ExpFactory.MODID, name));
        }
    }

    public static class Items {

        private static TagKey<Item> createTage(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(ExpFactory.MODID, name));
        }
    }
}
