package com.lazl0.expfactory.item;

import com.lazl0.expfactory.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier TUENIUM_TIER = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_TUENIUM_TOOL,
            350, 8, 4, 22, () ->{
        return Ingredient.of(ModTags.Items.TIER_TWO_INGOT);
    });
}
