/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 11 Mar 2013
package vazkii.tinkerer.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

/**
 * ICraftingHandle
 *
 * Signifies that the item should be notified of when it's used in crafting.
 *
 * @author Vazkii
 */
public interface ICraftingHandle {

	public void onUseInCrafting(EntityPlayer player, ItemStack stack, IInventory craftingMatrix, int matrixIndex);

}
