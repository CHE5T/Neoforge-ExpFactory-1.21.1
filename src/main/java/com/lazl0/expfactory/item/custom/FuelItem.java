package com.lazl0.expfactory.item.custom;

import com.lazl0.expfactory.item.TooltipItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class FuelItem extends TooltipItem {
    private int burnTime = 0;

    public FuelItem(Item.Properties properties, String key, String key2, int burnTime) {
        super(properties, key, key2);
        this.burnTime = burnTime;
    }

    @Override
    public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return this.burnTime;
    }
}
