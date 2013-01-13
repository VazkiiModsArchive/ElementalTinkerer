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
import vazkii.tinkerer.reference.ItemIDs;
import vazkii.tinkerer.reference.ItemNames;
import vazkii.tinkerer.reference.ResourcesReference;
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
					   wand;

	public static void init() {
		// Construct the items
		elementiumGem = new ItemElementiumGem(ItemIDs.elementiumGem).setItemName(ItemNames.ELEMENTIUM_GEM_NAME);
		elementalBook = new ItemElementalBook(ItemIDs.elementalBook).setItemName(ItemNames.ELEMENT_BOOK_NAME);
		elementalistLexicon = new ItemElementalistLexicon(ItemIDs.elementalistLexicon).setItemName(ItemNames.ELEMENTALIST_LEXICON_NAME);
		catalyst = new ItemCatalyst(ItemIDs.catalyst).setItemName(ItemNames.CATALYST_ITEM_NAME);
		elementalBark = new ItemET(ItemIDs.elementalBark).setItemName(ItemNames.ELEMENTAL_BARK_ITEM_NAME).setIconIndex(ResourcesReference.ITEM_INDEX_ELEMENTAL_BARK);
		wand = new ItemWand(ItemIDs.wand).setItemName(ItemNames.WAND_NAME);

		// Name the items
		LanguageRegistry.addName(elementiumGem, ItemNames.ELEMENTIUM_GEM_DISPLAY_NAME);
		LanguageRegistry.addName(elementalistLexicon, ItemNames.ELEMENTALIST_LEXICON_DISPLAY_NAME);
		LanguageRegistry.addName(elementalBark, ItemNames.ELEMENTAL_BARK_ITEM_DISPLAY_NAME);
	}

	public static void initItemRecipes() {
		CraftingManager.getInstance().func_92051_a(new ItemStack(elementalistLexicon),
				" G ", "GBG", " G ",
				'G', elementiumGem,
				'B', Item.book);

		CraftingManager.getInstance().addShapelessRecipe(new ItemStack(elementiumGem, 9),
				ElementalTinkererBlocks.elementiumGemBlock);

		CraftingManager.getInstance().func_92051_a(new ItemStack(elementalBark),
				" G ", "GLG", " G ",
				'G', elementiumGem,
				'L', new ItemStack(Block.wood, -1));
	}

}
