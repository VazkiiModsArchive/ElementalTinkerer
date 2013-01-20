/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 18 Jan 2013
package vazkii.tinkerer.tile.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * SlotElementalTinkeringAltarOutput
 *
 * The Output slot for the Elemental Tinkering Altar, this
 * slot, when an item is picked up from it sends an event.
 *
 * @author Vazkii
 */
public class SlotElementalTinkeringAltarOutput extends Slot {

	IInventory craftMatrix;

	public SlotElementalTinkeringAltarOutput(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
		craftMatrix = par1iInventory;
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack) {
		return false;
	}

	@Override
	public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack) {
        GameRegistry.onItemCrafted(par1EntityPlayer, par2ItemStack, craftMatrix);
		super.onPickupFromSlot(par1EntityPlayer, par2ItemStack);
	}
}
