package com.lazl0.expfactory.item.custom;

import com.lazl0.expfactory.item.TooltipItem;
import com.lazl0.expfactory.registry.ModDataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.capabilities.Capabilities;

import java.util.List;

public class RedstoneBatteryItem extends TooltipItem {
    public RedstoneBatteryItem(Properties properties, String key, String key2) {
        super(properties.component(ModDataComponents.ENERGY.get(), 0), key, key2);
    }

    private boolean hasEnergyCapability(ItemStack stack) {
        return stack.getCapability(Capabilities.EnergyStorage.ITEM) != null;
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return true;
    }
    @Override
    public int getBarWidth(ItemStack stack) {
        if(hasEnergyCapability(stack)) {
            float stored = stack.getOrDefault(ModDataComponents.ENERGY.get(), 0)*stack.getCount();
            float capacity = stack.getCapability(Capabilities.EnergyStorage.ITEM).getMaxEnergyStored();
            return Math.round(13.0F * (stored/capacity));
        }
        //Fallback if the energy hasn't loaded
        return 0;
    }
    @Override
    public int getBarColor(ItemStack stack) {
        //Checks if it hasEnergyCapability so the "capacity" line won't crash if it doesn't load
        if(hasEnergyCapability(stack)) {
            //Returns a "gradient" from red to green based on the item's current energy stored
            float stored = stack.getOrDefault(ModDataComponents.ENERGY.get(), 0)*stack.getCount();
            float capacity = stack.getCapability(Capabilities.EnergyStorage.ITEM).getMaxEnergyStored();
            float ratio = Math.max(0.0F, Math.min(stored / capacity, 1.0F));
            int red = (int) ((1.0F - ratio) * 255);
            int green = 0;
            int blue = (int) (ratio * 255);
            return (red << 16) | (green << 8) | blue;
        }
        //Default, just returns a red of my choosing, now a fallback option
        return 0xFF6C7C;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(hasEnergyCapability(stack)) {
            int stored = stack.getOrDefault(ModDataComponents.ENERGY.get(), 0)*stack.getCount();
            int capacity = stack.getCapability(Capabilities.EnergyStorage.ITEM).getMaxEnergyStored();
            tooltipComponents.add(Component.literal("§9"+stored + " FE/" + capacity + " FE§r"));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
