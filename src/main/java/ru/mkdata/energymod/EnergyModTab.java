package ru.mkdata.energymod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * @author mikhail
 */
public class EnergyModTab extends CreativeTabs {

    public EnergyModTab() {
        super("EnergyMod");

    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(Items.IRON_AXE);
    }
}