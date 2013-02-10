/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 9 Jan 2013
package vazkii.tinkerer.research;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import vazkii.tinkerer.block.ElementalTinkererBlocks;
import vazkii.tinkerer.item.ElementalTinkererItems;
import vazkii.tinkerer.item.ItemCatalyst;
import vazkii.tinkerer.item.ItemWand;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.research.trigger.OddClawTrigger;

/**
 * ResearchLibrary
 *
 * Class containing all the research nodes and categories.
 *
 * @author Vazkii
 */
public final class ResearchLibrary {

	public static Map<Short, ResearchNode> allNodes = new TreeMap();

	public static Map<Byte, ResearchCategory> categories = new TreeMap();

	public static final List<TinkeringAltarRecipe> recipes = new ArrayList();

	public static void initResearch() {
		ResearchCategory general = new ResearchCategory(0),
						 pure = new ResearchCategory(1),
						 water = new ResearchCategory(2),
						 air = new ResearchCategory(3),
						 earth = new ResearchCategory(4),
						 fire = new ResearchCategory(5);

		categories.put((byte) 0, general);
		categories.put((byte) 1, pure);
		categories.put((byte) 2, water);
		categories.put((byte) 3, air);
		categories.put((byte) 4 ,earth);
		categories.put((byte) 5, fire);

		// Elementium Ore Research Node
		registerNode(new ResearchNode(ResearchReference.ID_ELEMENTIUM_ORE,
					ResourcesReference.RESEARCH_SPRITESHEET,
					ResearchReference.LABEL_ELEMENTIUM_ORE,
					ResearchReference.DISPLAY_NAME_ELEMENTIUM_ORE,
					ResourcesReference.RESEARCH_INDEX_ELEMENTIUM_ORE,
					ResearchType.ITEM)
					.setDefaultEnabled()
					.addToCategory(general));

		// Elementium Gem Research Node
		registerNode(new ResearchNode(ResearchReference.ID_ELEMENTIUM_GEM,
				  ResourcesReference.RESEARCH_SPRITESHEET,
				  ResearchReference.LABEL_ELEMENTIUM_GEM,
				  ResearchReference.DISPLAY_NAME_ELEMENTIUM_GEM,
				  ResourcesReference.RESEARCH_INDEX_ELEMENTIUM_GEM,
				  ResearchType.ITEM)
				  .setDefaultEnabled()
				  .addToCategory(general));

		// Elemental Desk Research Node
		registerNode(new ResearchNode(ResearchReference.ID_ELEMENTAL_DESK,
				  ResourcesReference.RESEARCH_SPRITESHEET,
				  ResearchReference.LABEL_ELEMENTAL_DESK,
				  ResearchReference.DISPLAY_NAME_ELEMENTAL_DESK,
				  ResourcesReference.RESEARCH_INDEX_ELEMENTAL_DESK,
				  ResearchType.ITEM)
				  .setDefaultEnabled()
				  .addToCategory(pure));

		// Research Books Research Node
		registerNode(new ResearchNode(ResearchReference.ID_RESEARCH_BOOKS,
				  ResourcesReference.RESEARCH_SPRITESHEET,
				  ResearchReference.LABEL_RESEARCH_BOOKS,
				  ResearchReference.DISPLAY_NAME_RESEARCH_BOOKS,
				  ResourcesReference.RESEARCH_INDEX_RESEARCH_BOOKS,
				  ResearchType.ITEM)
				  .setDefaultEnabled()
				  .addToCategory(general));

		// Elementalist Lexicon Research Node
		registerNode(new ResearchNode(ResearchReference.ID_ELEMENTALIST_LEXICON,
				  ResourcesReference.ITEMS_SPRITESHEET,
				  ResearchReference.LABEL_ELEMENTALIST_LEXICON,
				  ResearchReference.DISPLAY_NAME_ELEMENTALIST_LEXICON,
				  ResourcesReference.ITEM_INDEX_ELEMENTALIST_LEXICON,
				  ResearchType.ITEM)
				  .setDefaultEnabled()
				  .addToCategory(general));

		// Catalyst Research Nodes
		for(short i = 0; i < 16; i++) {
			ResearchNode node = new ResearchNode((short) (ResearchReference.ID_CATALYST_START + i),
					  ResourcesReference.ITEMS_SPRITESHEET,
					  String.format(ResearchReference.LABEL_CATALSYT, i),
					  ItemCatalyst.nameFromMeta(i),
					  ResourcesReference.ITEM_INDEX_CATALYST_START + i,
					  ResearchType.ITEM)
					  .addToCategory(categories.get((byte) (ItemCatalyst.getElement(i) + 2)));
			if(i <= 3)
				node.setDefaultEnabled();
			else node.setRequirement((short) (node.index - 4));
			registerNode(node);
		}

		// Elemental Bark Research Node
		registerNode(new ResearchNode(ResearchReference.ID_ELEMENTAL_BARK,
				  ResourcesReference.ITEMS_SPRITESHEET,
				  ResearchReference.LABEL_ELEMENTAL_BARK,
				  ResearchReference.DISPLAY_NAME_ELEMENTAL_BARK,
				  ResourcesReference.ITEM_INDEX_ELEMENTAL_BARK,
				  ResearchType.ITEM)
				  .setDefaultEnabled()
				  .addToCategory(pure));

		// Elementium Dust Research Node
		registerNode(new ResearchNode(ResearchReference.ID_ELEMENTIUM_DUST,
				  ResourcesReference.ITEMS_SPRITESHEET,
				  ResearchReference.LABEL_ELEMENTIUM_DUST,
				  ResearchReference.DISPLAY_NAME_ELEMENTIUM_DUST,
				  ResourcesReference.ITEM_INDEX_ELEMENTIUM_DUST,
				  ResearchType.ITEM)
				  .setDefaultEnabled()
				  .addToCategory(pure));

		// Elementium Ingot Research Node
		registerNode(new ResearchNode(ResearchReference.ID_ELEMENTIUM_INGOT,
				  ResourcesReference.RESEARCH_SPRITESHEET,
				  ResearchReference.LABEL_ELEMENTIUM_INGOT,
				  ResearchReference.DISPLAY_NAME_ELEMENTIUM_INGOT,
				  ResourcesReference.RESERACH_INDEX_ELEMENTIUM_INGOT,
				  ResearchType.ITEM)
				  .addToCategory(pure));

		// Wand Research Nodes
		for(short i = 0; i < 4; i++) {
			registerNode(new ResearchNode((short) (ResearchReference.ID_WAND_START + i),
					  ResourcesReference.RESEARCH_SPRITESHEET,
					  String.format(ResearchReference.LABEL_WAND, i),
					  ItemWand.nameFromMeta(i),
					  ResourcesReference.RESEARCH_INDEX_WAND_START + i,
					  ResearchType.ITEM)
					  .addToCategory(categories.get((byte) (i + 2)))
					  .setNoBook());
		}

		// Elemental Tinkering Research Node
		registerNode(new ResearchNode(ResearchReference.ID_ELEMENTAL_TINKERING,
				  ResourcesReference.RESEARCH_SPRITESHEET,
				  ResearchReference.LABEL_ELEMENTAL_TINKERING,
				  ResearchReference.DISPLAY_NAME_ELEMENTAL_TINKERING,
				  ResourcesReference.RESEARCH_INDEX_ELEMENTAL_TINKERING,
				  ResearchType.ITEM)
				  .setDefaultEnabled()
				  .addToCategory(pure));

		// Catalyst Capsule Research Node
		registerNode(new ResearchNode(ResearchReference.ID_CATALYST_CAPSULE,
				  ResourcesReference.RESEARCH_SPRITESHEET,
				  ResearchReference.LABEL_CATALYST_CAPSULE,
				  ResearchReference.DISPLAY_NAME_CATALYST_CAPSULE,
				  ResourcesReference.RESEARCH_INDEX_CATALYST_CAPSULE,
				  ResearchType.ITEM)
				  .setNoBook()
				  .addToCategory(pure));

		// Magical Attuning Research Node
		registerNode(new ResearchNode(ResearchReference.ID_ATTUNER,
				  ResourcesReference.RESEARCH_SPRITESHEET,
				  ResearchReference.LABEL_ATTUNER,
				  ResearchReference.DISPLAY_NAME_ATTUNER,
				  ResourcesReference.RESEARCH_INDEX_ATTUNER,
				  ResearchType.ITEM)
				  .setNoBook()
				  .addToCategory(pure));

		// Thunderbolt Research Node
		registerNode(new ResearchNodeSpell(ResearchReference.ID_THUNDERBOLT,
					ResearchReference.LABEL_THUNDERBOLT,
					ResearchReference.DISPLAY_NAME_THUNDERBOLT,
					ResourcesReference.MAGIC_INDEX_THUNDERBOLT,
					ResearchType.SPELL)
					.addToCategory(air));

		// Frostbolt Research Node
		registerNode(new ResearchNodeSpell(ResearchReference.ID_FROSTBOLT,
					ResearchReference.LABEL_FROSTBOLT,
					ResearchReference.DISPLAY_NAME_FROSTBOLT,
					ResourcesReference.MAGIC_INDEX_FROSTBOLT,
					ResearchType.SPELL)
					.addToCategory(water));

		// Boulder Toss Research Node
		registerNode(new ResearchNodeSpell(ResearchReference.ID_BOULDER_TOSS,
					ResearchReference.LABEL_BOULDER_TOSS,
					ResearchReference.DISPLAY_NAME_BOULDER_TOSS,
					ResourcesReference.MAGIC_INDEX_BOULDER_TOSS,
					ResearchType.SPELL)
					.addToCategory(earth));

		// Fireball Research Node
		registerNode(new ResearchNodeSpell(ResearchReference.ID_FIREBALL,
					ResearchReference.LABEL_FIREBALL,
					ResearchReference.DISPLAY_NAME_FIREBALL,
					ResourcesReference.MAGIC_INDEX_FIREBALL,
					ResearchType.SPELL)
					.addToCategory(fire));

		// Aerial Push Research Node
		registerNode(new ResearchNodeSpell(ResearchReference.ID_AEREAL_PUSH,
					ResearchReference.LABEL_AEREAL_PUSH,
					ResearchReference.DISPLAY_NAME_AEREAL_PUSH,
					ResourcesReference.MAGIC_INDEX_AEREAL_PUSH,
					ResearchType.SPELL)
					.addToCategory(air));

		// Frostshock Research Node
		registerNode(new ResearchNodeSpell(ResearchReference.ID_FROSTSHOCK,
					ResearchReference.LABEL_FROSTSHOCK,
					ResearchReference.DISPLAY_NAME_FROSTSHOCK,
					ResourcesReference.MAGIC_INDEX_FROSTSHOCK,
					ResearchType.SPELL)
					.addToCategory(water));

		// Implosion Research Node
		registerNode(new ResearchNodeSpell(ResearchReference.ID_IMPLOSION,
					ResearchReference.LABEL_IMPLOSION,
					ResearchReference.DISPLAY_NAME_IMPLOSION,
					ResourcesReference.MAGIC_INDEX_IMPLOSION,
					ResearchType.SPELL)
					.addToCategory(earth));

		// Flame Ring Research Node
		registerNode(new ResearchNodeSpell(ResearchReference.ID_FLAME_RING,
					ResearchReference.LABEL_FLAME_RING,
					ResearchReference.DISPLAY_NAME_FLAME_RING,
					ResourcesReference.MAGIC_INDEX_FLAME_RING,
					ResearchType.SPELL)
					.addToCategory(fire));

		// Elementium Detector Research Node
		registerNode(new ResearchNode(ResearchReference.ID_ELEMENTIUM_DETECTOR,
				  ResourcesReference.RESEARCH_SPRITESHEET,
				  ResearchReference.LABEL_ELEMENTIUM_DETECTOR,
				  ResearchReference.DISPLAY_NAME_ELEMENTIUM_DETECTOR,
				  ResourcesReference.RESEARCH_INDEX_ELEMENTIUM_DETECTOR,
				  ResearchType.ITEM)
				  .setDefaultEnabled()
				  .addToCategory(general));

		// Odd Claw Research Node
		registerNode(new ResearchNode(ResearchReference.ID_ODD_CLAW,
				  ResourcesReference.ITEMS_SPRITESHEET,
				  ResearchReference.LABEL_ODD_CLAW,
				  ResearchReference.DISPLAY_NAME_ODD_CLAW,
				  ResourcesReference.ITEM_INDEX_ODD_CLAW,
				  ResearchType.ITEM)
				  .setNoBook()
				  .addToCategory(pure));
		MinecraftForge.EVENT_BUS.register(OddClawTrigger.INSTANCE);

		// Exetended Breath Research Node
		registerNode(new ResearchNodeSpell(ResearchReference.ID_EXTENDED_BREATH,
					ResearchReference.LABEL_EXTENDED_BREATH,
					ResearchReference.DISPLAY_NAME_EXTENDED_BREATH,
					ResourcesReference.MAGIC_INDEX_EXTENDED_BREATH,
					ResearchType.PASSIVE)
					.addToCategory(air));

		// Rain Accumulation Research Node
		registerNode(new ResearchNodeSpell(ResearchReference.ID_RAIN_ACCUMULATION,
				ResearchReference.LABEL_RAIN_ACCUMULATION,
				ResearchReference.DISPLAY_NAME_RAIN_ACCUMULATION,
				ResourcesReference.MAGIC_INDEX_RAIN_ACCUMULATION,
				ResearchType.PASSIVE)
				.addToCategory(water));

		// Nature Aura Research Node
		registerNode(new ResearchNodeSpell(ResearchReference.ID_NATURE_AURA,
				ResearchReference.LABEL_NATURE_AURA,
				ResearchReference.DISPLAY_NAME_NATURE_AURA,
				ResourcesReference.MAGIC_INDEX_NATURE_AURA,
				ResearchType.PASSIVE)
				.addToCategory(earth));

		// Burning Cloud Research Node
		registerNode(new ResearchNodeSpell(ResearchReference.ID_BURNING_CLOUD,
				ResearchReference.LABEL_BURNING_CLOUD,
				ResearchReference.DISPLAY_NAME_BURNING_CLOUD,
				ResourcesReference.MAGIC_INDEX_BURNING_CLOUD,
				ResearchType.PASSIVE)
				.addToCategory(fire));

		//VAZ_TODO Crisis Research Node

		// Undershirt Research Node
		registerNode(new ResearchNodeSpell(ResearchReference.ID_UNDERSHIRT,
				ResearchReference.LABEL_UNDERSHIRT,
				ResearchReference.DISPLAY_NAME_UNDERSHIRT,
				ResourcesReference.MAGIC_INDEX_UNDERSHIRT,
				ResearchType.PASSIVE)
				.addToCategory(pure));
	}

	public static void initTinkeringRecipes() {
		// Catalyst Recipes
		for(int i = 0; i < 8; i++) {
			TinkeringAltarRecipe.registerRecipe(new ItemStack(ElementalTinkererItems.catalyst, 1, 4 + i),
					"CC",
					"CC",
					'C', new ItemStack(ElementalTinkererItems.catalyst, 1, i));
			allNodes.get((short) (ResearchReference.ID_CATALYST_START + 4 + i)).bindLatestTinkeringRecipe();
		}

		// Wand Recipes
		for(int i = 0; i < 4; i++) {
			TinkeringAltarRecipe.registerRecipe(new ItemStack(ElementalTinkererItems.wand, 1, i),
					new ItemStack(ElementalTinkererItems.catalyst, 1, i),
					new ItemStack(ElementalTinkererItems.catalyst, 1, i),
					"  B  ",
					"  B  ",
					"  BBB",
					" G   ",
					"G    ",
					'B', ElementalTinkererItems.elementalBark,
					'G', Item.ingotGold);
			allNodes.get((short) (ResearchReference.ID_WAND_START + i)).bindLatestTinkeringRecipe();
		}

		// Attuner Recipe
		TinkeringAltarRecipe.registerRecipe(new ItemStack(ElementalTinkererBlocks.attuner),
			new ItemStack(ElementalTinkererItems.catalyst, 1, 0),
			new ItemStack(ElementalTinkererItems.catalyst, 1, 1),
			new ItemStack(ElementalTinkererItems.catalyst, 1, 2),
			new ItemStack(ElementalTinkererItems.catalyst, 1, 3),
			"IPIPI",
			"P   P",
			"I B I",
			"P   P",
			"IPIPI",
			'B', ElementalTinkererBlocks.elementiumGemBlock,
			'I', ElementalTinkererItems.elementiumIngot,
			'P', Block.thinGlass);
			allNodes.get(ResearchReference.ID_ATTUNER).bindLatestTinkeringRecipe();

		// Odd Claw Recipe
		TinkeringAltarRecipe.registerRecipe(new ItemStack(ElementalTinkererItems.oddClaw),
			new ItemStack(ElementalTinkererItems.catalyst, 1, 3),
			new ItemStack(ElementalTinkererItems.catalyst, 1, 5),
			new ItemStack(ElementalTinkererItems.catalyst, 1, 6),
			"E  EE",
			" EBI ",
			" G G ",
			" I I ",
			"I   I",
			'I', Item.ingotIron,
			'E', ElementalTinkererItems.elementiumIngot,
			'B', Item.bow,
			'G', Block.glowStone);
			allNodes.get(ResearchReference.ID_ODD_CLAW).bindLatestTinkeringRecipe();
	}

	public static void registerNode(ResearchNode node) {
		allNodes.put(node.index, node);
	}

}
