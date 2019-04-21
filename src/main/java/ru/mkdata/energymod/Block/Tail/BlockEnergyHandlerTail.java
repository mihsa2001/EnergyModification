package ru.mkdata.energymod.Block.Tail;

import cofh.redstoneflux.api.IEnergyProvider;
import cofh.redstoneflux.api.IEnergyReceiver;
import ic2.api.tile.IEnergyStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import ru.mkdata.energymod.Block.EEnergyStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mikhail
 * block energy handler tail
 */
public class BlockEnergyHandlerTail extends TileEntity implements IEnergyReceiver, IEnergyProvider, ITickable {
    //TODO переписать взмимодействие с RF используя setEnergyStored из {@link EnergyStorage}
    protected EEnergyStorage storage = new EEnergyStorage(30000);

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

    @Override
    public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {

        return storage.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {

        return storage.extractEnergy(maxExtract, simulate);
    }

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
        List<TileEntity> tileEntityList = new ArrayList<>();
        tileEntityList.add(this.world.getTileEntity(this.pos.up()));
        tileEntityList.add(this.world.getTileEntity(this.pos.down()));
        tileEntityList.add(this.world.getTileEntity(this.pos.west()));
        tileEntityList.add(this.world.getTileEntity(this.pos.south()));
        tileEntityList.add(this.world.getTileEntity(this.pos.east()));
        tileEntityList.add(this.world.getTileEntity(this.pos.north()));
        for (TileEntity ent : tileEntityList) {
            if (ent instanceof IEnergyStorage) {
                IEnergyStorage UpEntity = ((IEnergyStorage) ent);
                if (this.storage.getEnergyStored() > 0) {
                    int may_receive = UpEntity.getCapacity() - UpEntity.getStored();
                    if (may_receive > this.storage.getEnergyStored()) may_receive = this.storage.getEnergyStored();
                    UpEntity.addEnergy(this.storage.getEnergyStored());
                    this.storage.extractEnergy(may_receive, false);
                }

            }
        }
    }
}