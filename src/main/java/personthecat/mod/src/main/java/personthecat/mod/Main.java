package personthecat.mod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import personthecat.mod.advancements.AdvancementMap;
import personthecat.mod.config.ConfigFile;
import personthecat.mod.config.JsonReader;
import personthecat.mod.config.ModConfigReader;
import personthecat.mod.proxy.CommonProxy;
import personthecat.mod.util.Reference;
import personthecat.mod.util.handlers.RegistryHandler;
import personthecat.mod.world.gen.DisableVanillaOreGen;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES)
public class Main
{
	public static boolean isQuarkLoaded() {return Loader.isModLoaded("quark");}
	public static boolean isMineralogyLoaded() {return Loader.isModLoaded("mineralogy");}
	public static boolean isUndergroundBiomesLoaded() {return Loader.isModLoaded("undergroundbiomes");}
	public static boolean isEarthworksLoaded() {return Loader.isModLoaded("earthworks");}
	
	public static boolean isBaseMetalsLoaded() {return Loader.isModLoaded("basemetals");}
	public static boolean isBiomesOPlentyLoaded() {return Loader.isModLoaded("biomesoplenty");}
	public static boolean isGlassHeartsLoaded() {return Loader.isModLoaded("glasshearts");}
	public static boolean isIceAndFireLoaded() {return Loader.isModLoaded("iceandfire");}
	public static boolean isSimpleOresLoaded() {return Loader.isModLoaded("simpleores");}
	public static boolean isThermalFoundationLoaded() {return Loader.isModLoaded("thermalfoundation");}
	public static boolean isImmersiveEngineeringLoaded() {return Loader.isModLoaded("immersiveengineering");}
	public static boolean isEmbersLoaded() {return Loader.isModLoaded("embers");}
	public static boolean isThaumcraftLoaded() {return Loader.isModLoaded("thaumcraft");}
	public static boolean isModernMetalsLoaded() {return Loader.isModLoaded("modernmetals");}

	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event)
	{
		ConfigFile.init();
		RegistryHandler.registerDefaultProperties();
		if (isQuarkLoaded()) ModConfigReader.readQuarkConfig();		
		JsonReader.loadNewProperties();
		proxy.createAndRegisterResourcePack();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		MinecraftForge.ORE_GEN_BUS.register(DisableVanillaOreGen.class);
		RegistryHandler.otherRegistries();
		FurnaceRecipes.addRecipes();
		ModConfigReader.disableModGeneration(); //For some reason, this causes crashes elsewhere.
	}
	
	@EventHandler
	public static void postInit(FMLPostInitializationEvent event)
	{
		AdvancementMap.loadAdvancementList();
	}	
}