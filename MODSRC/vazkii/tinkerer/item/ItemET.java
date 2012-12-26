/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Dec 2012
package vazkii.tinkerer.item;

import net.minecraft.item.Item;
import vazkii.tinkerer.gui.CreativeTabET;
import vazkii.tinkerer.reference.MiscReference;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * ItemET
 *
 * Parent class for blocks from Elemental Tinkerer
 *
 * @author Vazkii
 */
public class ItemET extends Item {

	public ItemET(int par1) {
		super(par1 - MiscReference.ITEM_INDEX_SHIFT);
		// Pass in accurate IDs, negating the index shift
		setCreativeTab(CreativeTabET.INSTANCE);
	}

	@Override
	public String getTextureFile() {
		return useDoubleResolution() ? ResourcesReference.ITEMS_32_SPRITESHEET : ResourcesReference.ITEMS_SPRITESHEET;
	}

	/**
	 * Flag to set if the item sprite is rendered in 32x32 rather than 16x16.
	 */
	public boolean useDoubleResolution() {
		return false;
	}
}
