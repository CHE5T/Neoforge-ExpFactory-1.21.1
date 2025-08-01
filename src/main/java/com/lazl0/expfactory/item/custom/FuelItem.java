package com.lazl0.expfactory.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class FuelItem extends Item{
    private int burnTime = 0;

    public FuelItem(Item.Properties properties, int burnTime) {
        super(properties);
        this.burnTime = burnTime;
    }

    @Override
    public int getBurnTime(@NotNull ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return this.burnTime;
    }

    //@Override
    //Website for what to put in the en_us.json for formatting: https://minecraft.wiki/w/Formatting_codes
    //public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
    //    tooltipComponents.add(Component.translatable("tooltip.exponential_factory.fuelitem.tooltip"));
    //    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    //}
}
