/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 3 Feb 2013
package vazkii.tinkerer.magic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * IWand
 *
 * Items with this interface are considered wands.
 *
 * @author Vazkii
 */
public interface IWand {

	/** Handles a keystroke from the spell change keybind, this is
	 * called SERVER side, clients only send out a packet **/
	public void handleKeystroke(EntityPlayer player, ItemStack stack);

}
