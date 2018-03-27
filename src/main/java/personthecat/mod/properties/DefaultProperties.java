package personthecat.mod.properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.Loader;
import personthecat.mod.Main;
import personthecat.mod.config.ConfigFile;
import personthecat.mod.config.JsonReader;
import personthecat.mod.util.Reference;

public class DefaultProperties
{
	public static final PropertyGroup VANILLA = new PropertyGroup("minecraft"); 
	public static final PropertyGroup ICEANDFIRE = new PropertyGroup("iceandfire");
	public static final PropertyGroup SIMPLEORES = new PropertyGroup("simpleores"); 
	public static final PropertyGroup BASEMETALS = new PropertyGroup("basemetals");
	public static final PropertyGroup BIOMESOPLENTY = new PropertyGroup("biomesoplenty");
	public static final PropertyGroup GLASSHEARTS = new PropertyGroup("glasshearts");
	public static final PropertyGroup DONT_SPAWN = new PropertyGroup("impossiblemodthatdoesntexist"); 
	
	private static final String SAME = "thisvaluedoesntactuallymatter";
	
	public enum DefaultOreProperties
	{
			//				languageKey, 		hardness, level, dropsBlk, 	drop, dropMeta, 			dropAlt, dropAltMeta,  		leastDrop, mostDrop, leastXp, mostXp,		group
			COAL_ORE( 		"oreCoal", 					3.0F, 0, false, 	"coal",	0,						"coal_ore", 0,					1, 		1, 		0, 		2,			VANILLA),
			DIAMOND_ORE(	"oreDiamond", 				3.0F, 2, false, 	"diamond", 0, 					"diamond_ore", 0,				1, 		1, 		3, 		7,			VANILLA),
			EMERALD_ORE(	"oreEmerald", 				3.0F, 2, false, 	"emerald", 0,  					"emerald_ore", 0,				1, 		1, 		3, 		7,			VANILLA),
			GOLD_ORE(		"oreGold", 					3.0F, 2, true, 		"gold_ore", 0, 					SAME, 0,						1, 		1, 		0, 		0,			VANILLA),
			IRON_ORE(		"oreIron", 					3.0F, 1, true,		"iron_ore", 0, 					SAME, 0,						1, 		1, 		0, 		0,			VANILLA),
			LAPIS_ORE(		"oreLapis", 				3.0F, 2, false, 	"dye", 4,						"lapis_ore", 0,					4, 		8, 		2, 		5,			VANILLA),
			REDSTONE_ORE(	"oreRedstone", 				3.0F, 2, false, 	"redstone", 0,					"redstone_ore", 0,				4,	 	5, 		1, 		5,			VANILLA),
		LIT_REDSTONE_ORE(	"oreRedstone", 				3.0F, 2, false, 	"redstone", 0,					"redstone_ore",	0,				4, 		5, 		1, 		5,			VANILLA), //Still has to get created.
		    QUARTZ_ORE(		"oreQuartz",				3.0F, 1, false,		"quartz", 0,					"quartz_ore", 0,				1,		1,		2,		5,			DONT_SPAWN),
 ICEANDFIRE_SAPPHIRE_ORE(	"iceandfire.sapphireOre",	3.0F, 2, false, 	"iceandfire:sapphire_gem",0,	"iceandfire:sapphire_ore", 0,	1, 		1,		0,		0,			ICEANDFIRE),
 ICEANDFIRE_SILVER_ORE(		"iceandfire.silverOre",		3.0F, 2, true, 		"iceandfire:silver_ore",0, 		SAME, 0,						1, 		1,		0,		0,			ICEANDFIRE),
 SIMPLEORES_ADAMANTIUM_ORE(	"adamantium_ore",			5.0F, 2, true, 		"simpleores:adamantium_ore",0, 	SAME, 0,						1, 		1,		0,		0,			SIMPLEORES),
 SIMPLEORES_COPPER_ORE(		"copper_ore",				1.7F, 1, true, 		"simpleores:copper_ore",0,		SAME, 0,						1, 		1,		0,		0,			SIMPLEORES),
 SIMPLEORES_MYTHRIL_ORE(	"mythril_ore",				4.0F, 2, true, 		"simpleores:mythril_ore",0,		SAME, 0,						1, 		1,		0,		0,			SIMPLEORES),
 SIMPLEORES_TIN_ORE(		"tin_ore",					3.0F, 1, true, 		"simpleores:tin_ore",0, 		SAME, 0,						1, 		1,		0,		0,			SIMPLEORES),
 SIMPLEORES_ONYX_ORE(		"onyx_ore",					7.0F, 3, false,		"simpleores:onyx_gem",0,		"simpleores:onyx_ore", 0,		1,		1,		0,		0,			DONT_SPAWN),
 BASEMETALS_ANTIMONY_ORE( 	"basemetals.antimony_ore",	1.0F, 0, true, 		"basemetals:antimony_ore",0, 	SAME, 0,						1,		1,		0,		0,			BASEMETALS),
 BASEMETALS_BISMUTH_ORE( 	"basemetals.bismuth_ore",	1.0F, 0, true, 		"basemetals:bismuth_ore",0, 	SAME, 0,						1,		1,		0,		0,			BASEMETALS),
 BASEMETALS_COPPER_ORE( 	"basemetals.copper_ore",	4.0F, 1, true, 		"basemetals:copper_ore",0, 		SAME, 0,						1,		1,		0,		0,			BASEMETALS),
 BASEMETALS_LEAD_ORE( 		"basemetals.lead_ore",		1.0F, 0, true, 		"basemetals:lead_ore",0, 		SAME, 0,						1,		1,		0,		0,			BASEMETALS),
 BASEMETALS_MERCURY_ORE( 	"basemetals.mercury_ore",	1.0F, 0, true, 		"basemetals:mercury_ore",0, 	SAME, 0,						1,		1,		0,		0,			BASEMETALS),
 BASEMETALS_NICKEL_ORE( 	"basemetals.nickel_ore",	4.0F, 1, true, 		"basemetals:nickel_ore",0, 		SAME, 0,						1,		1,		0,		0,			BASEMETALS),
 BASEMETALS_PEWTER_ORE( 	"basemetals.pewter_ore",	1.0F, 0, true,  	"basemetals:pewter_ore",0, 		SAME, 0,						1,		1,		0,		0,			BASEMETALS),
 BASEMETALS_PLATINUM_ORE( 	"basemetals.platinum_ore",	3.0F, 1, true,  	"basemetals:platinum_ore",0,	SAME, 0,						1,		1,		0,		0,			BASEMETALS),
 BASEMETALS_SILVER_ORE( 	"basemetals.silver_ore",	5.0F, 1, true,  	"basemetals:silver_ore",0, 		SAME, 0,						1,		1,		0,		0,			BASEMETALS),
 BASEMETALS_TIN_ORE( 		"basemetals.tin_ore", 		1.0F, 1, true, 		"basemetals:tin_ore",0, 		SAME, 0,						1,		1,		0,		0,			BASEMETALS),
 BASEMETALS_ZINC_ORE( 		"basemetals.zinc_ore",		1.0F, 0, true,  	"basemetals:zinc_ore",0, 		SAME, 0,						1,		1,		0,		0,			BASEMETALS),
 BASEMETALS_ADAMANTINE_ORE( "basemetals.adamantine_ore",12.0F,4, true,  	"basemetals:adamantine_ore",0, 	SAME, 0,						1,		1,		0,		0,			DONT_SPAWN),
 BASEMETALS_COLDIRON_ORE(	"basemetals.coldiron_ore",	7.0F, 2, true,  	"basemetals:coldiron_ore",0, 	SAME, 0,						1,		1,		0,		0,			DONT_SPAWN),
 BASEMETALS_CUPRONICKEL_ORE("basemetals.cupronickel_ore",6.0F,2, true, 		"basemetals:cupronickel_ore",0, SAME, 0,						1,		1,		0,		0,			DONT_SPAWN),
 BASEMETALS_STARSTEEL_ORE(	"basemetals.starsteel_ore", 10.0F,3, true,  	"basemetals:starstell_ore",0, 	SAME, 0,						1,		1,		0,		0,			DONT_SPAWN),
 BIOMESOPLENTY_AMBER_ORE(	"gem_ore.amber_ore",		3.0F, 2, false, 	"biomesoplenty:gem",7,  		"biomesoplenty:gem_ore",7, 		1, 		1, 		3, 		7,			BIOMESOPLENTY),
 BIOMESOPLENTY_MALACHITE_ORE("gem_ore.malachite_ore",	3.0F, 2, false, 	"biomesoplenty:gem",5, 			"biomesoplenty:gem_ore",5,		1, 		1, 		3, 		7,			BIOMESOPLENTY),
 BIOMESOPLENTY_PERIDOT_ORE(	"gem_ore.peridot_ore",		3.0F, 2, false,		"biomesoplenty:gem",2,  		"biomesoplenty:gem_ore",2, 		1, 		1, 		3, 		7,			BIOMESOPLENTY),
 BIOMESOPLENTY_RUBY_ORE(	"gem_ore.ruby_ore",			3.0F, 2, false, 	"biomesoplenty:gem",1, 			"biomesoplenty:gem_ore",1, 		1, 		1, 		3, 		7,			BIOMESOPLENTY),
 BIOMESOPLENTY_SAPPHIRE_ORE("gem_ore.sapphire_ore",		3.0F, 2, false,		"biomesoplenty:gem",6,  		"biomesoplenty:gem_ore",6, 		1, 		1, 		3, 		7,			BIOMESOPLENTY),
 BIOMESOPLENTY_TANZANITE_ORE("gem_ore.tanzanite_ore",	3.0F, 2, false, 	"biomesoplenty:gem",4, 			"biomesoplenty:gem_ore",4, 		1, 		1, 		3, 		7,			BIOMESOPLENTY),
 BIOMESOPLENTY_TOPAZ_ORE(	"gem_ore.topaz_ore",		3.0F, 2, false,		"biomesoplenty:gem",3,  		"biomesoplenty:gem_ore",3, 		1, 		1, 		3, 		7,			BIOMESOPLENTY),
 BIOMESOPLENTY_AMETHYST_ORE("gem_ore.amethyst_ore",		3.0F, 3, false,		"biomesoplenty:gem",0,  		"biomesoplenty:gem_ore",0, 		1, 		1, 		3, 		7,			BIOMESOPLENTY),
 GLASSHEARTS_AGATE_ORE(		"glasshearts.ore.agate",	3.0F, 2, false,		"glasshearts:gem", 7,  			"glasshearts:ore", 7, 			1, 		1, 		0, 		0,			GLASSHEARTS),
 GLASSHEARTS_AMETHYST_ORE(	"glasshearts.ore.amethyst",	3.0F, 2, false,		"glasshearts:gem", 0,  			"glasshearts:ore", 0, 			1, 		1, 		0, 		0,			GLASSHEARTS),
 GLASSHEARTS_ONYX_ORE(		"glasshearts.ore.onyx",		3.0F, 2, false, 	"glasshearts:gem", 5,  			"glasshearts:ore", 5, 			1, 		1, 		0, 		0,			GLASSHEARTS),
 GLASSHEARTS_OPAL_ORE(		"glasshearts.ore.opal",		3.0F, 2, false, 	"glasshearts:gem", 4, 			"glasshearts:ore", 4, 			1, 		1, 		0, 		0,			GLASSHEARTS),
 GLASSHEARTS_RUBY_ORE(		"glasshearts.ore.ruby",		3.0F, 2, false,		"glasshearts:gem", 1,  			"glasshearts:ore", 1, 			1, 		1, 		0, 		0,			GLASSHEARTS),
 GLASSHEARTS_SAPPHIRE_ORE(	"glasshearts.ore.sapphire",	3.0F, 2, false, 	"glasshearts:gem", 3,  			"glasshearts:ore", 3, 			1, 		1, 		0, 		0,			GLASSHEARTS),
 GLASSHEARTS_TOPAZ_ORE(		"glasshearts.ore.topaz",	3.0F, 2, false, 	"glasshearts:gem", 2,  			"glasshearts:ore", 2, 			1, 		1, 		0, 		0,			GLASSHEARTS);

		private DefaultOreProperties(String languageKey, float hardness, int level, boolean isDropBlock, String drop, int dropMeta, String dropAlt, int dropAltMeta, int leastDrop, int mostDrop, int leastXp, int mostXp, PropertyGroup group)
		{
			dropAlt = dropAlt == SAME ? drop : dropAlt;
			boolean canCreateVariants = true;

			JsonObject obj = JsonReader.getProperties(toString().toLowerCase(), "OreProperties.json");
			
			if (obj != null)
			{				
				canCreateVariants = obj.get("createOverworldVariants") != null ? obj.get("createOverworldVariants").getAsBoolean() : true;
				languageKey = obj.get("languageKey") != null ? obj.get("languageKey").getAsString() : languageKey;
				hardness = obj.get("hardness") != null ? obj.get("hardness").getAsFloat() : hardness;
				level = obj.get("harvestLevel") != null ? obj.get("harvestLevel").getAsInt() : level;
				isDropBlock = obj.get("isDropBlock") != null ? obj.get("isDropBlock").getAsBoolean() : isDropBlock;
				drop = obj.get("drop") != null ? obj.get("drop").getAsString() : drop;
				dropMeta = obj.get("dropMeta") != null ? obj.get("dropMeta").getAsInt() : dropMeta;
				dropAlt = obj.get("dropAlt") != null ? obj.get("dropAlt").getAsString() : dropAlt;
				dropAltMeta = obj.get("dropAltMeta") != null ? obj.get("dropAltMeta").getAsInt() : dropAltMeta;
				leastDrop = obj.get("leastDrop") != null ? obj.get("leastDrop").getAsInt() : leastDrop;
				mostDrop = obj.get("mostDrop") != null ? obj.get("mostDrop").getAsInt() : mostDrop;
				leastXp = obj.get("leastXp") != null ? obj.get("leastXp").getAsInt() : leastXp;
				mostXp = obj.get("mostXp") != null ? obj.get("mostXp").getAsInt() : mostXp;
			}
			
			OreProperties newProperties = new OreProperties(toString().toLowerCase(), languageKey, hardness, level, isDropBlock, drop, dropMeta, dropAlt, dropAltMeta, leastDrop, mostDrop, leastXp, mostXp);
			
			if (canCreateVariants) group.addProperties(newProperties);
		}
		
		static
		{			
			VANILLA.setConditions(ConfigFile.vanillaSupport);												VANILLA.register();
			ICEANDFIRE.setConditions((Main.isIceAndFireLoaded && ConfigFile.iceAndFireSupport));			ICEANDFIRE.register();
			SIMPLEORES.setConditions((Main.isSimpleOresLoaded && ConfigFile.simpleOresSupport));			SIMPLEORES.register();
			BASEMETALS.setConditions((Main.isBaseMetalsLoaded && ConfigFile.baseMetalsSupport));			BASEMETALS.register();
			BIOMESOPLENTY.setConditions((Main.isBiomesOPlentyLoaded && ConfigFile.biomesOPlentySupport));	BIOMESOPLENTY.register();
			GLASSHEARTS.setConditions((Main.isGlassHeartsLoaded && ConfigFile.glassHeartsSupport));			GLASSHEARTS.register();
			
			//Strange placement--this has to happen at this time, though.
			JsonReader.loadNewProperties();
		}
	}
	
	private static final Type[] NO_TYPE = new Type[] {};
	private static final String[] NO_NAMES = new String[] {};
	
	public enum DefaultWorldGenProperties
	{
			//						Count, chance,	minY, 	maxY,	biomeMatcher
			COAL_ORE(		 		20, 	17, 	0, 		128,	NO_TYPE, 								NO_NAMES),
			DIAMOND_ORE(	 		8, 		1, 		0, 		16,		NO_TYPE, 								NO_NAMES),
			EMERALD_ORE(			3, 		2, 		0, 		32,		new Type[] {Type.MOUNTAIN}, 			NO_NAMES),
			GOLD_ORE(				9, 		2, 		0, 		32,		NO_TYPE, 								NO_NAMES),
			IRON_ORE(				9, 		20, 	0, 		64,		NO_TYPE, 								NO_NAMES),
			LAPIS_ORE(				7, 		2, 		0, 		32,		NO_TYPE, 								NO_NAMES),
			REDSTONE_ORE(			8, 		8, 		0, 		32,		NO_TYPE, 								NO_NAMES),
		LIT_REDSTONE_ORE(			8, 		8, 		0, 		32,		NO_TYPE, 								NO_NAMES),
			QUARTZ_ORE(				9,		20,		0,		128,	NO_TYPE,								NO_NAMES),
 ICEANDFIRE_SAPPHIRE_ORE(			3,		2,		4,		32,		NO_TYPE, 								new String[] {"iceandfire:glacier"}),
 ICEANDFIRE_SILVER_ORE(				9,		2,		4,		32,		NO_TYPE, 								NO_NAMES),
 SIMPLEORES_ADAMANTIUM_ORE(			6,		6,		1,		30,		NO_TYPE,								NO_NAMES),
 SIMPLEORES_COPPER_ORE(				10,		35,		1,		90,		NO_TYPE, 								NO_NAMES),
 SIMPLEORES_MYTHRIL_ORE(			8,		10,		1,		40,		NO_TYPE, 								NO_NAMES),
 SIMPLEORES_TIN_ORE(				10,		30,		1,		90,		NO_TYPE, 								NO_NAMES),
 SIMPLEORES_ONYX_ORE(				6,		6,		1,		127,	NO_TYPE,								NO_NAMES),
 BASEMETALS_ANTIMONY_ORE( 			0,		0,		0,		0,		NO_TYPE, 								NO_NAMES),
 BASEMETALS_BISMUTH_ORE( 			0,		0,		0,		0,		NO_TYPE, 								NO_NAMES),
 BASEMETALS_COPPER_ORE( 			8,		10,		0,		96,		NO_TYPE, 								NO_NAMES),
 BASEMETALS_LEAD_ORE( 				8,		5,		0,		64,		NO_TYPE, 								NO_NAMES),
 BASEMETALS_MERCURY_ORE( 			8,		3,		0,		32,		NO_TYPE, 								NO_NAMES),
 BASEMETALS_NICKEL_ORE( 			8,		1,		32,		96,		NO_TYPE, 								NO_NAMES),
 BASEMETALS_PEWTER_ORE( 			0,		0,		0,		0,		NO_TYPE, 								NO_NAMES),
 BASEMETALS_PLATINUM_ORE( 			8,		1,		1,		32,		NO_TYPE, 								NO_NAMES),
 BASEMETALS_SILVER_ORE( 			8,		4,		0,		32,		NO_TYPE, 								NO_NAMES),
 BASEMETALS_TIN_ORE( 				8,		10,		0,		128,	NO_TYPE, 								NO_NAMES),
 BASEMETALS_ZINC_ORE( 				8,		5,		0,		96,		NO_TYPE, 								NO_NAMES),
 BASEMETALS_ADAMANTINE_ORE(			8,		2,		0,		128,	NO_TYPE, 								NO_NAMES),
 BASEMETALS_COLDIRON_ORE(			8,		5,		0,		128,	NO_TYPE, 								NO_NAMES),
 BASEMETALS_CUPRONICKEL_ORE(		0,		0,		0,		0,		NO_TYPE, 								NO_NAMES),
 BASEMETALS_STARSTEEL_ORE(			8,		5,		0,		255,	NO_TYPE, 								NO_NAMES),
 BIOMESOPLENTY_AMBER_ORE(			4,		1,		4,		32,		new Type[] {Type.FOREST}, 				NO_NAMES),
 BIOMESOPLENTY_MALACHITE_ORE(		4,		1,		4,		32,		new Type[] {Type.SWAMP}, 				NO_NAMES),
 BIOMESOPLENTY_PERIDOT_ORE(			4,		1,		4,		32,		new Type[] {Type.PLAINS},				NO_NAMES),
 BIOMESOPLENTY_RUBY_ORE(			4,		1,		4,		32,		new Type[] {Type.DRY, Type.SANDY}, 		NO_NAMES),
 BIOMESOPLENTY_SAPPHIRE_ORE(		4,		1,		4,		32,		new Type[] {Type.OCEAN, Type.WATER},	NO_NAMES),
 BIOMESOPLENTY_TANZANITE_ORE(		4,		1,		4,		32,		new Type[] {Type.SNOWY}, 				NO_NAMES),
 BIOMESOPLENTY_TOPAZ_ORE(			4,		1,		4,		32,		new Type[] {Type.JUNGLE, Type.BEACH},	NO_NAMES),
 BIOMESOPLENTY_AMETHYST_ORE(		4,		1,		4,		32,		NO_TYPE, 								NO_NAMES),
 GLASSHEARTS_AGATE_ORE(				3, 		2, 		0, 		32,		NO_TYPE, 								NO_NAMES),
 GLASSHEARTS_AMETHYST_ORE(			3, 		2, 		0, 		32,		NO_TYPE, 								NO_NAMES),
 GLASSHEARTS_ONYX_ORE(				3, 		2, 		0, 		32,		NO_TYPE, 								NO_NAMES),
 GLASSHEARTS_OPAL_ORE( 				3, 		2, 		0, 		32,		NO_TYPE, 								NO_NAMES),
 GLASSHEARTS_RUBY_ORE(				3, 		2, 		0, 		32,		NO_TYPE, 								NO_NAMES),
 GLASSHEARTS_SAPPHIRE_ORE( 			3, 		2, 		0, 		32,		NO_TYPE, 								NO_NAMES),
 GLASSHEARTS_TOPAZ_ORE(				3, 		2, 		0, 		32,		NO_TYPE, 								NO_NAMES);
				
		private DefaultWorldGenProperties(int blockCount, int chance, int minHeight, int maxHeight, Type[] biomeType, String[] biomeLookup)
		{
			JsonObject obj = JsonReader.getProperties(toString().toLowerCase(), "WorldGenProperties.json");
			
			if (obj != null)
			{
				blockCount = obj.get("blockCount") != null ? obj.get("blockCount").getAsInt() : blockCount;
				chance = obj.get("chance") != null ? obj.get("chance").getAsInt() : chance;
				minHeight = obj.get("minHeight") != null ? obj.get("minHeight").getAsInt() : minHeight;
				maxHeight = obj.get("maxHeight") != null ? obj.get("maxHeight").getAsInt() : maxHeight;
				
				List<String> biomeNameList = new ArrayList<String>();
				List<Type> biomeTypeList = new ArrayList<Type>();
				
				JsonArray biomeNames = obj.get("biomeNameList").getAsJsonArray();
				for (JsonElement element : biomeNames)
				{
					biomeNameList.add(element.getAsString());
				}
				
				JsonArray biomeTypes = obj.get("biomeTypeList").getAsJsonArray();
				for (JsonElement element: biomeTypes)
				{
					Type type = Type.getType(element.getAsString());
					biomeTypeList.add(type);
				}
				
				new WorldGenProperties(toString().toLowerCase(), blockCount, chance, minHeight, maxHeight, biomeTypeList, biomeNameList);
			}
			
			else new WorldGenProperties(toString().toLowerCase(), blockCount, chance, minHeight, maxHeight, biomeType, biomeLookup);
		}
	}
	
	public enum DefaultRecipeProperties
	{
		//						result,							meta, quantity,	xp
		COAL_ORE(		 		"coal", 						0, 		1, 		0.1F),
		DIAMOND_ORE(	 		"diamond", 						0, 		1, 		1.0F),
		EMERALD_ORE(			"emerald", 						0, 		1, 		1.0F),
		GOLD_ORE(				"gold_ingot", 					0, 		1, 		1.0F),
		IRON_ORE(				"iron_ingot", 					0, 		1, 		0.7F),
		LAPIS_ORE(				"dye", 							4, 		1, 		0.2F),
		REDSTONE_ORE(			"redstone",						0, 		1, 		0.7F),
	LIT_REDSTONE_ORE(			"redstone", 					0, 		1, 		0.7F),
		QUARTZ_ORE(				"quartz",						0,		1,		0.2F),
ICEANDFIRE_SAPPHIRE_ORE(		"iceandfire:sapphire_gem",		0,		1,		1.0F),
ICEANDFIRE_SILVER_ORE(			"iceandfire:silver_ingot",		0,		1,		1.0F),
SIMPLEORES_ADAMANTIUM_ORE(		"simpleores:adamantium_ingot",	0,		1,		0.7F),
SIMPLEORES_COPPER_ORE(			"simpleores:copper_ingot",		0,		1,		0.4F),
SIMPLEORES_MYTHRIL_ORE(			"simpleores:mythril_ingot",		0,		1,		0.7F),
SIMPLEORES_TIN_ORE(				"simpleores:tin_ingot",			0,		1,		0.4F),
SIMPLEORES_ONYX_ORE(			"simpleores:onyx_gem",			0,		1,		0.7F),
BASEMETALS_ANTIMONY_ORE( 		"basemetals:antimony_ingot",	0,		1,		0.1F),
BASEMETALS_BISMUTH_ORE( 		"basemetals:bismuth_ingot",		0,		1,		0.1F),
BASEMETALS_COPPER_ORE( 			"basemetals:copper_ingot",		0,		1,		0.5F),
BASEMETALS_LEAD_ORE( 			"basemetals:lead_ingot",		0,		1,		0.1F),
BASEMETALS_MERCURY_ORE( 		"basemetals:mercury_ingot",		0,		1,		0.1F),
BASEMETALS_NICKEL_ORE( 			"basemetals:nickel_ingot",		0,		1,		0.1F),
BASEMETALS_PEWTER_ORE( 			"basemetals:pewter_ingot",		0,		1,		0.1F),
BASEMETALS_PLATINUM_ORE( 		"basemetals:platinum_ingot",	0,		1,		0.1F),
BASEMETALS_SILVER_ORE( 			"basemetals:silver_ingot",		0,		1,		0.1F),
BASEMETALS_TIN_ORE( 			"basemetals:tin_ingot",			0,		1,		0.1F),
BASEMETALS_ZINC_ORE( 			"basemetals:zinc_ingot",		0,		1,		0.1F),
BASEMETALS_ADAMANTINE_ORE(		"basemetals:adamantine_ingot",	0,		1,		0.0F),
BASEMETALS_COLDIRON_ORE(		"basemetals:coldiron_ingot",	0,		1,		0.7F),
BASEMETALS_CUPRONICKEL_ORE(		"basemetals:cupronickel_ingot",	0,		1,		0.6F),
BIOMESOPLENTY_AMBER_ORE(		"biomesoplenty:gem",			7,		1,		1.2F),
BIOMESOPLENTY_MALACHITE_ORE(	"biomesoplenty:gem",			5,		1,		1.2F),
BIOMESOPLENTY_PERIDOT_ORE(		"biomesoplenty:gem",			2,		1,		1.2F),
BIOMESOPLENTY_RUBY_ORE(			"biomesoplenty:gem",			1,		1,		1.2F),
BIOMESOPLENTY_SAPPHIRE_ORE(		"biomesoplenty:gem",			6,		1,		1.2F),
BIOMESOPLENTY_TANZANITE_ORE(	"biomesoplenty:gem",			4,		1,		1.2F),
BIOMESOPLENTY_TOPAZ_ORE(		"biomesoplenty:gem",			3,		1,		1.2F),
BIOMESOPLENTY_AMETHYST_ORE(		"biomesoplenty:gem",			0,		1,		1.2F),
GLASSHEARTS_AGATE_ORE(			"glasshearts:gem", 				7, 		1, 		1.2F),
GLASSHEARTS_AMETHYST_ORE(		"glasshearts:gem", 				0, 		1, 		1.2F),
GLASSHEARTS_ONYX_ORE(			"glasshearts:gem", 				5, 		1, 		1.2F),
GLASSHEARTS_OPAL_ORE( 			"glasshearts:gem", 				4, 		1, 		1.2F),
GLASSHEARTS_RUBY_ORE(			"glasshearts:gem", 				1, 		1, 		1.2F),
GLASSHEARTS_SAPPHIRE_ORE( 		"glasshearts:gem", 				3, 		1, 		1.2F),
GLASSHEARTS_TOPAZ_ORE(			"glasshearts:gem", 				2, 		1, 		1.2F);
		
		DefaultRecipeProperties(String result, int resultMeta, int quantity, float xp)
		{
			JsonObject obj = JsonReader.getProperties(toString().toLowerCase(), "RecipeProperties.json");
			
			if (obj != null)
			{
				result = obj.get("result") != null ? obj.get("result").getAsString() : result;
				resultMeta = obj.get("resultMeta") != null ? obj.get("resultMeta").getAsInt() : resultMeta;
				quantity = obj.get("quantity") != null ? obj.get("quantiy").getAsInt() : quantity;
				xp = obj.get("xp") != null ? obj.get("xp").getAsFloat() : xp;
			}
			
			new RecipeProperties(toString().toLowerCase(), result, resultMeta, quantity, xp);
		}
	}
}
