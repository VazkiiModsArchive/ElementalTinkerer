/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 9 Jan 2013
package vazkii.tinkerer.research;

import java.util.Map;
import java.util.TreeMap;

import vazkii.tinkerer.item.ItemCatalyst;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.ResourcesReference;

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

		// Elemental Bark Research Nodes
		registerNode(new ResearchNode(ResearchReference.ID_ELEMENTAL_BARK,
				  ResourcesReference.ITEMS_SPRITESHEET,
				  ResearchReference.LABEL_ELEMENTAL_BARK,
				  ResearchReference.DISPLAY_NAME_ELEMENTAL_BARK,
				  ResourcesReference.ITEM_INDEX_ELEMENTAL_BARK,
				  ResearchType.ITEM)
				  .setDefaultEnabled()
				  .addToCategory(pure));
	}

	public static void registerNode(ResearchNode node) {
		allNodes.put(node.index, node);
	}

}
