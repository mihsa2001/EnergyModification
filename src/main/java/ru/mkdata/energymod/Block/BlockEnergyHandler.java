package ru.mkdata.energymod.Block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import ru.mkdata.energymod.Block.Entity.BlockTileEntity;
import ru.mkdata.energymod.Block.Tail.BlockEnergyHandlerTail;

public class BlockEnergyHandler extends BlockTileEntity<BlockEnergyHandlerTail> {

    public BlockEnergyHandler(String name, Material material, float hardness, float resistanse, SoundType soundType) {

        super(name, material, hardness, resistanse, soundType);
        this.setCreativeTab(CreativeTabs.REDSTONE);
        this.setHarvestLevel("pickaxe", 2);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos position, IBlockState blockState, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        BlockEnergyHandlerTail tail = (BlockEnergyHandlerTail) world.getTileEntity(position);
        if (!world.isRemote) {
            if (player.inventory.mainInventory.get(player.inventory.currentItem).getUnlocalizedName().equals("item.thermalfoundation.util.wrench0")) {
                if (player.isSneaking()) {
                    world.destroyBlock(position, true);
                } else {
                    player.sendMessage(new TextComponentString("Block store " + String.valueOf(tail.getEnergyStored(null))));
                }
            }
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
