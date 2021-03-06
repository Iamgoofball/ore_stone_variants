package personthecat.mod.util.handlers;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.FileResourcePack;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import personthecat.mod.compat.GeolosysCompat;
import personthecat.mod.config.ConfigFile;
import personthecat.mod.init.BlockInit;
import personthecat.mod.init.ItemInit;
import personthecat.mod.properties.DefaultProperties;
import personthecat.mod.properties.WorldGenProperties;
import personthecat.mod.properties.DefaultProperties.DefaultOreProperties;
import personthecat.mod.properties.DefaultProperties.DefaultRecipeProperties;
import personthecat.mod.properties.DefaultProperties.DefaultWorldGenProperties;
import personthecat.mod.util.ZipTools;
import personthecat.mod.util.interfaces.IHasModel;
import personthecat.mod.world.gen.DisableVanillaOreGen;
import personthecat.mod.world.gen.WorldGenCustomOres;

@EventBusSubscriber
public class RegistryHandler
{		
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onItemRegister(RegistryEvent.Register<Item> event) 
	{
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		BlockInit.init(); //This really needs to happen as late as possible for dynamic blocks..
		
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));		
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for(Item item : ItemInit.ITEMS)
		{
			if (item instanceof IHasModel) ((IHasModel) item).registerModels();
		}
		
		for(Block block : BlockInit.BLOCKS)
		{
			if (block instanceof IHasModel) ((IHasModel) block).registerModels();
		}
	}
	
	public static void onRegisterNewResourcesBadly()
	{
		try
		{
			List<IResourcePack> defaultResourcePacks = ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), "defaultResourcePacks", "field_110449_ao");
			defaultResourcePacks.add(new FileResourcePack(ZipTools.RESOURCE_PACK));
			
			Minecraft.getMinecraft().refreshResources();
		} 
		
		catch (SecurityException | IllegalArgumentException e) {System.out.println("Could not register new default resourcepack.");}	
	}
	
	public static void registerDefaultProperties()
	{
		DefaultWorldGenProperties[] initWorldGenPropertiesHaxThisDevSux = DefaultWorldGenProperties.values();
		DefaultRecipeProperties[] initRecipePropertyHaxGetALife = DefaultRecipeProperties.values();
	}
	
	public static void registerAPIComms()
	{
		if (Loader.isModLoaded("geolosys") && ConfigFile.isSupportEnabled("geolosys"))
		{
			GeolosysCompat.enableGeolosysVeinGeneration();
		}
	}
	
	public static void registerGenerators()
	{
		if (!ConfigFile.isGenerationDisabledGlobally())
		{
			if (ConfigFile.largeOreClusters) WorldGenProperties.enableLargeClusterMode();
			
			GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), Integer.MAX_VALUE);
			
			MinecraftForge.ORE_GEN_BUS.register(DisableVanillaOreGen.class);
		}
	}
}
