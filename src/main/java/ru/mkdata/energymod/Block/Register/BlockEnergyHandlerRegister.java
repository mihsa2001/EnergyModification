package ru.mkdata.energymod.Block.Register;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.mkdata.energymod.Block.BlockEnergyHandler;

/**
 * @author mikhail
 * proxy register for BlockEnergyGandler
 */
public class BlockEnergyHandlerRegister {

    public static final Block
            Energy_Converter = new BlockEnergyHandler("converter", Material.ROCK, 10.0F, 10.0F, SoundType.STONE);

    public static void register() {

        setBlockRegister(Energy_Converter);
        setItemBlockRegister(Energy_Converter);
        GameRegistry.registerTileEntity(((BlockEnergyHandler) Energy_Converter).getTileEntityClass(), Energy_Converter.getRegistryName().toString());
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender() {

        setItemBlockRender(Energy_Converter);
    }

    private static void setBlockRegister(Block block) {

        ForgeRegistries.BLOCKS.register(block);
    }

    private static void setItemBlockRegister(Block block) {

        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }

    @SideOnly(Side.CLIENT)
    private static void setItemBlockRender(Block block) {

        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}