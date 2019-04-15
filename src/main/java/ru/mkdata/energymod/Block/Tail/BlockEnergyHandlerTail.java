package ru.mkdata.energymod.Block.Tail;

import cofh.redstoneflux.api.IEnergyProvider;
import cofh.redstoneflux.api.IEnergyReceiver;
import cofh.redstoneflux.impl.EnergyStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

/**
 * @author mikhail
 * block energy handler tail
 */
public class BlockEnergyHandlerTail extends TileEntity implements IEnergyReceiver, IEnergyProvider, ITickable {

    protected EnergyStorage storage = new EnergyStorage(32000, 1000, 1000);

    @Override
    public void readFromNBT(NBTTagCompound nbt) {

        super.readFromNBT(nbt);
        storage.readFromNBT(nbt);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {

        super.writeToNBT(nbt);
        storage.writeToNBT(nbt);
        return nbt;
    }

    @Override
    public boolean canConnectEnergy(EnumFacing from) {

        return true;
    }

    /* IEnergyReceiver */
    @Override
    public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {

        return storage.receiveEnergy(maxReceive, simulate);
    }

    /* IEnergyProvider */
    @Override
    public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {

        return storage.extractEnergy(maxExtract, simulate);
    }

    /* IEnergyHandler */
    @Override
    public int getEnergyStored(EnumFacing from) {

        return storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(EnumFacing from) {

        return storage.getMaxEnergyStored();
    }

    @Override
    public void update() {
        TileEntity f = this.world.getTileEntity(this.pos.up());
        if (f instanceof IEnergyReceiver) {
            IEnergyReceiver UpEntity = ((IEnergyReceiver) f);
            this.storage.extractEnergy(UpEntity.receiveEnergy(EnumFacing.DOWN, this.storage.extractEnergy(this.storage.getMaxExtract(), true), false), false);
        }

    }

}