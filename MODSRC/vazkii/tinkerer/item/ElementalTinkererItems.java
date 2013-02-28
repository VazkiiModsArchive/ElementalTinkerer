/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Dec 2012
package vazkii.tinkerer.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import vazkii.tinkerer.block.ElementalTinkererBlocks;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.reference.ItemIDs;
import vazkii.tinkerer.reference.ItemNames;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.research.ResearchLibrary;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * ElementalTinkererItems
 *
 * This class holds the items for the mod.
 *
 * @author Vazkii
 */
public final class ElementalTinkererItems {

	public static Item elementiumGem,
					   elementalBook,
					   elementalistLexicon,
					   catalyst,
					   elementalBark,
					   wand,
					   elementiumIngot,
					   elementiumDust,
					   elementiumDetector,
					   oddClaw,
					   locationGem,
					   gem,
					   enderParticle,
					   gaseousGlowstone;

	public static void init() {
		// Construct the items
		elementiumGem = new ItemElementiumGem(ItemIDs.elementiumGem).setItemName(ItemNames.ELEMENTIUM_GEM_NAME);
		elementalBook = new ItemElementalBook(ItemIDs.elementalBook).setItemName(ItemNames.ELEMENT_BOOK_NAME);
		elementalistLexicon = new ItemElementalistLexicon(ItemIDs.elementalistLexicon).setItemName(ItemNames.ELEMENTALIST_LEXICON_NAME);
		catalyst = new ItemCatalyst(ItemIDs.catalyst).setItemName(ItemNames.CATALYST_ITEM_NAME);
		elementalBark = new ItemET(ItemIDs.elementalBark).setItemName(ItemNames.ELEMENTAL_BARK_ITEM_NAME).setIconIndex(ResourcesReference.ITEM_INDEX_ELEMENTAL_BARK);
		wand = new ItemWand(ItemIDs.wand).setItemName(ItemNames.WAND_NAME);
		elementiumIngot = new ItemElementiumIngot(ItemIDs.elementiumIngot).setItemName(ItemNames.ELEMENTIUM_INGOT_NAME);
		elementiumDust = new ItemET(ItemIDs.elementiumDust).setItemName(ItemNames.ELEMENTIUM_DUST_NAME).setIconIndex(ResourcesReference.ITEM_INDEX_ELEMENTIUM_DUST);
		elementiumDetector = new ItemElementiumDetector(ItemIDs.elementiumDetector).setItemName(ItemNames.ELEMENTIUM_DETECTOR_NAME);
		oddClaw = new ItemOddClaw(ItemIDs.oddClaw).setItemName(ItemNames.ODD_CLAW_NAME);
		locationGem = new ItemLocationGem(ItemIDs.locationGem).setItemName(ItemNames.LOCATION_GEM_NAME);
		gem = new ItemGem(ItemIDs.gem).setItemName(ItemNames.GEM_NAME);
		enderParticle = new ItemEnderParticle(ItemIDs.enderParticle).setItemName(ItemNames.ENDER_PARTICLE_NAME);
		gaseousGlowstone = new ItemGlowstoneAir(ItemIDs.gaseousGlowstone).setItemName(ItemNames.GASEOUS_GLOWSTONE_NAME);

		// Name the items
		LanguageRegistry.addName(elementiumGem, ItemNames.ELEMENTIUM_GEM_DISPLAY_NAME);
		LanguageRegistry.addName(elementalistLexicon, ItemNames.ELEMENTALIST_LEXICON_DISPLAY_NAME);
		LanguageRegistry.addName(elementalBark, ItemNames.ELEMENTAL_BARK_ITEM_DISPLAY_NAME);
		LanguageRegistry.addName(elementiumIngot, ItemNames.ELEMENTIUM_INGOT_DISPLAY_NAME);
		LanguageRegistry.addName(elementiumDust, ItemNames.ELEMENTIUM_DUST_DISPLAY_NAME);
		LanguageRegistry.addName(elementiumDetector, ItemNames.ELEMENTIUM_DETECTOR_DISPLAY_NAME);
		LanguageRegistry.addName(oddClaw, ItemNames.ODD_CLAW_DISPLAY_NAME);
		LanguageRegistry.addName(locationGem, ItemNames.LOCATION_GEM_DISPLAY_NAME);
		LanguageRegistry.addName(enderParticle, ItemNames.ENDER_PARTICLE_DISPLAY_NAME);
		LanguageRegistry.addName(gaseousGlowstone, ItemNames.GASEOUS_GLOWSTONE_DISPLAY_NAME);

		// Add the items to the researches
		ResearchHelper.setIconicItem(new ItemStack(elementiumGem), ResearchReference.ID_ELEMENTIUM_GEM);
		ResearchHelper.setIconicItem(new ItemStack(elementalBook, 1, -1), ResearchReference.ID_RESEARCH_BOOKS);
		ResearchHelper.setIconicItem(new ItemStack(elementalistLexicon), ResearchReference.ID_ELEMENTALIST_LEXICON);
		for(int i = 0; i < 16; i++)
			ResearchHelper.setIconicItem(new ItemStack(catalyst, 1, i), (short) (ResearchReference.ID_CATALYST_START + i));
		ResearchHelper.setIconicItem(new ItemStack(elementalBark), ResearchReference.ID_ELEMENTAL_BARK);
		for(int i = 0; i < 4; i++)
			ResearchHelper.setIconicItem(new ItemStack(wand, 1, i), (short) (ResearchReference.ID_WAND_START + i));
		ResearchHelper.setIconicItem(new ItemStack(elementiumIngot), ResearchReference.ID_ELEMENTIUM_INGOT);
		ResearchHelper.setIconicItem(new ItemStack(elementiumDust), ResearchReference.ID_ELEMENTIUM_DUST);
		ResearchHelper.setIconicItem(new ItemStack(elementiumDetector), ResearchReference.ID_ELEMENTIUM_DETECTOR);
		ResearchHelper.setIconicItem(new ItemStack(locationGem), ResearchReference.ID_LOCATION_GEM);
		ResearchHelper.setIconicItem(new ItemStack(enderParticle), ResearchReference.ID_ENDER_ABSORPTION);
	}

	public static void initItemRecipes() {
		// Elementalist Lexicon Recipe
		CraftingManager.getInstance().func_92051_a(new ItemStack(elementalistLexicon),
				" G ", "GBG", " G ",
				'G', elementiumGem,
				'B', Item.book);
		ResearchLibrary.allNodes.get(ResearchReference.ID_ELEMENTALIST_LEXICON).bindLatestCraftingRecipe();

		// Elementium Gem Recipe
		CraftingManager.getInstance().addShapelessRecipe(new ItemStack(elementiumGem, 9),
				ElementalTinkererBlocks.elementiumGemBlock);

		CraftingManager.getInstance().addShapelessRecipe(new ItemStack(elementiumGem),
				elementiumDust, elementiumDust, Item.diamond);
		ResearchLibrary.allNodes.get(ResearchReference.ID_ELEMENTIUM_GEM).bindLatestCraftingRecipe();

		// Elemental Bark Recipe
		CraftingManager.getInstance().func_92051_a(new ItemStack(elementalBark),
				" G ", "GLG", " G ",
				'G', elementiumGem,
				'L', new ItemStack(Block.wood, -1));
		ResearchLibrary.allNodes.get(ResearchReference.ID_ELEMENTAL_BARK).bindLatestCraftingRecipe();

		// Elementium Ingot Recipe
		CraftingManager.getInstance().func_92051_a(new ItemStack(elementiumIngot),
				"DDD", "DID", "DDD",
				'D', elementiumDust,
				'I', Item.ingotGold);
		ResearchLibrary.allNodes.get(ResearchReference.ID_ELEMENTIUM_INGOT).bindLatestCraftingRecipe();

		// Catalyst Recipes
		for(int i = 0; i < 4; i++) {
			CraftingManager.getInstance().addShapelessRecipe(new ItemStack(catalyst, 1, i),
					new ItemStack(elementalBook, 1, i), elementiumDust);
			ResearchLibrary.allNodes.get((short) (ResearchReference.ID_CATALYST_START + i)).bindLatestCraftingRecipe();
		}

		// Elementium Detector Recipe
		CraftingManager.getInstance().func_92051_a(new ItemStack(elementiumDetector),
				" SG", " SS", "S  ",
				'S', Item.stick,
				'G', elementiumGem);
		ResearchLibrary.allNodes.get(ResearchReference.ID_ELEMENTIUM_DETECTOR).bindLatestCraftingRecipe();
	}
}
