package ru.mkdata.energymod;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.mkdata.energymod.proxy.CommonProxy;

@Mod(modid = EnergyMod.MODID, name = EnergyMod.NAME, version = EnergyMod.VERSION)
public class EnergyMod
{
    @SidedProxy(clientSide = "ru.mkdata.energymod.proxy.ClientProxy", serverSide = "ru.mkdata.energymod.proxy.CommonProxy")
    public static CommonProxy proxy;
    public static final String MODID = "energymod";
    public static final String NAME = "Energy Modification";
    public static final String VERSION = "1.0";
    public static CreativeTabs tabENERGY = new EnergyModTab();

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}
