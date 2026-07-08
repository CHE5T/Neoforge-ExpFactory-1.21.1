package com.lazl0.expfactory.registry.energy;

import com.lazl0.expfactory.registry.ModDataComponents;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.energy.IEnergyStorage;

//For items from the EnergyStorage class (not IEnergyStorage)
public class EnergyItem implements IEnergyStorage{
    protected final ItemStack stack;
    protected final int capacity;
    protected final int maxReceive;
    protected final int maxExtract;

    public EnergyItem(ItemStack stack, int capacity){
        this(stack, capacity, capacity/20, capacity/20);
    }
    public EnergyItem(ItemStack stack, int capacity, int maxTransfer){
        this(stack, capacity, maxTransfer, maxTransfer);
    }
    public EnergyItem(ItemStack stack, int capacity, int maxReceive, int maxExtract) {
        this.stack = stack;
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
    }

    @Override
    public int receiveEnergy(int toReceive, boolean simulate) {
        if (this.canReceive() && toReceive > 0) {
            int energyReceived = Mth.clamp(getMaxEnergyStored() - getEnergyStored(), 0, Math.min(this.maxReceive*stack.getCount(), toReceive));
            if (!simulate) {
                setEnergy((getEnergyStored() + energyReceived)/stack.getCount());
            }

            return energyReceived;
        } else {
            return 0;
        }
    }

    @Override
    public int extractEnergy(int toExtract, boolean simulate) {
        if (this.canExtract() && toExtract > 0) {
            int energyExtracted = Math.min(getEnergyStored(), Math.min(this.maxExtract*stack.getCount(), toExtract));
            if (!simulate) {
                setEnergy((getEnergyStored()- energyExtracted)/stack.getCount());
            }

            return energyExtracted;
        } else {
            return 0;
        }
    }

    @Override
    public int getEnergyStored() {return stack.getOrDefault(ModDataComponents.ENERGY.get(), 0)*stack.getCount();}

    @Override
    public int getMaxEnergyStored() {
        return this.capacity*stack.getCount();
    }

    @Override
    public boolean canExtract() {
        return this.maxExtract > 0;
    }

    @Override
    public boolean canReceive() {
        return this.maxReceive > 0;
    }

    private void setEnergy(int energy) {
        stack.set(ModDataComponents.ENERGY.get(), energy);
    }
}
