package ru.mkdata.energymod.Block;

import cofh.redstoneflux.impl.EnergyStorage;

/**
 * @author mikhail
 * прослойка, которая убирает такое понятие, как максимальный прием и макс передачу
 */
public class EEnergyStorage extends EnergyStorage {

    public EEnergyStorage(int capacity) {
        super(capacity);
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {

        int energyReceived = Math.min(capacity - energy, maxReceive);
        if (!simulate) {
            energy += energyReceived;
        }
        return energyReceived;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {

        int energyExtracted = Math.min(energy, maxExtract);
        if (!simulate) {
            energy -= energyExtracted;
        }
        return energyExtracted;
    }
}
