/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Dec 2012
package vazkii.tinkerer.item;

import cpw.mods.fml.common.registry.LanguageRegistry;
import vazkii.tinkerer.reference.ItemIDs;
import vazkii.tinkerer.reference.ItemNames;
import net.minecraft.item.Item;

/**
 * ElementalTinkererItems
 *
 * This class holds the items for the mod.
 *
 * @author Vazkii
 */
public final class ElementalTinkererItems {

	public static Item elementiumGem;
	
	public static void init() {
		// Construct the items
		elementiumGem = new ItemElementiumGem(ItemIDs.elementiumGem).setItemName(ItemNames.ELEMENTIUM_GEM_NAME);
		
		// Name the items
		LanguageRegistry.addName(elementiumGem, ItemNames.ELEMENTIUM_GEM_DISPLAY_NAME);
	}
	
}
