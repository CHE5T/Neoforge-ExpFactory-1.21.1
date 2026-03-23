package com.lazl0.expfactory.registry.energy;

import net.minecraft.util.Mth;
import net.neoforged.neoforge.energy.EnergyStorage;

public class EnergyStorageExtra extends EnergyStorage {
    public EnergyStorageExtra(int capacity) {
        super(capacity);
    }

    public EnergyStorageExtra(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    public EnergyStorageExtra(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    public EnergyStorageExtra(int capacity, int maxReceive, int maxExtract, int energy) {
        super(capacity, maxReceive, maxExtract, energy);
    }

    public void setCapacity(int capacity){
        super.capacity = capacity;
    }

    public void setEnergyStored(int energy){
        super.energy = energy;
    }

    public void setMaxTransfer(int maxTransfer){
        super.maxReceive = maxTransfer;
        super.maxExtract = maxTransfer;
    }

    public void setMaxReceive(int maxReceive){
        super.maxReceive = maxReceive;
    }

    public void setMaxExtract(int maxExtract){
        super.maxExtract = maxExtract;
    }

    //For blocks that can't recieve energy from other sources
    public int generateEnergy(int toReceive, boolean simulate) {
        if (toReceive > 0) {
            //Uses maxExtract for IO instead of maxReceive
            int energyReceived = Mth.clamp(this.capacity - this.energy, 0, Math.min(this.maxExtract, toReceive));
            if (!simulate) {
                this.energy += energyReceived;
            }

            return energyReceived;
        } else {
            return 0;
        }
    }

    //For blocks that can't recieve energy from other sources
    public int consumeEnergy(int toExtract, boolean simulate) {
        if (toExtract > 0) {
            //Uses maxReceive for IO instead of maxExtract
            int energyExtracted = Math.min(this.energy, Math.min(this.maxReceive, toExtract));
            if (!simulate) {
                this.energy -= energyExtracted;
            }

            return energyExtracted;
        } else {
            return 0;
        }
    }
}
