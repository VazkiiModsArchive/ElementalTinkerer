/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Dec 2012
package vazkii.tinkerer.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

import org.lwjgl.input.Keyboard;

import vazkii.tinkerer.client.helper.IconHelper;
import vazkii.tinkerer.gui.CreativeTabET;
import vazkii.tinkerer.reference.MiscReference;

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
	public void func_94581_a(IconRegister par1IconRegister) {
		iconIndex = IconHelper.forItem(par1IconRegister, this);
	}

	/**
	 * Adds the info to flat the item as a WIP.
	 */
	public static void addWIPInfo(List<String> list) {
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			list.add("This item is a Work In Progress.");
			list.add("It is not fully implemented, and");
			list.add("no support for it will be given for now.");
		} else
			list.add("WIP (SHIFT for + info)");
	}

	/**
	 * Adds the info to flat the item as NYI.
	 */
	public static void addNYIInfo(List<String> list) {
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			list.add("This item is Not Yet Implemented.");
			list.add("It is in the game, but it has no");
			list.add("use as of yet. It will in the future.");
		} else
			list.add("NYI (SHIFT for + info)");
	}
}
