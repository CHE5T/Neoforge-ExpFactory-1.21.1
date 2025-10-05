package com.lazl0.expfactory.registry;

import net.neoforged.neoforge.energy.EnergyStorage;

public class EnergyStorageMachine extends EnergyStorage {
    //For machines, so you cannot extract energy from them, but they can still spend the energy
    public EnergyStorageMachine(int capacity, int maxReceive) {
        super(capacity, maxReceive);
    }

    //For when machines get upgrades
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
    public void setMaxRecieve(int maxRecieve){
        this.maxReceive = maxRecieve;
    }

    public void setEnergy(int energy){
        this.energy = energy;
    }


    @Override
    public int extractEnergy(int toExtract, boolean simulate) {
        if (toExtract <= 0) {
            return 0;
        }

        int energyExtracted = Math.min(this.energy, toExtract);
        if (!simulate)
            this.energy -= energyExtracted;
        return energyExtracted;
    }
}
