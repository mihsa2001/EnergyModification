package ru.mkdata.energymod.Block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import ru.mkdata.energymod.Block.Entity.BlockTileEntity;
import ru.mkdata.energymod.Block.Tail.BlockEnergyHandlerTail;
import ru.mkdata.energymod.EnergyMod;

public class BlockEnergyHandler extends BlockTileEntity<BlockEnergyHandlerTail> {

    public BlockEnergyHandler(String name, Material material, float hardness, float resistanse, SoundType soundType) {

        super(name, material, hardness, resistanse, soundType);
        this.setCreativeTab(EnergyMod.tabENERGY);
        //this.setHarvestLevel("pickaxe", 3);
    }
    @Override
    public boolean onBlockActivated(World world, BlockPos position, IBlockState blockState, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {

        if (!world.isRemote) {

            BlockEnergyHandlerTail tileEntity = getTileEntity(world, position);
            player.sendMessage(new TextComponentString("Count: " + tileEntity.getEnergyStored(EnumFacing.DOWN)));
        }

        return true;
    }

    @Override
    public Class<BlockEnergyHandlerTail> getTileEntityClass() {

        return BlockEnergyHandlerTail.class;
    }

    @Override
    public BlockEnergyHandlerTail createTileEntity(World world, IBlockState blockState) {

        return new BlockEnergyHandlerTail();
    }
}
