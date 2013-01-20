/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 18 Jan 2013
package vazkii.tinkerer.tile.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import vazkii.tinkerer.tile.TileEntityElementalTinkeringAltar;

/**
 * SlotElementalTinkeringAltar
 *
 * Slot for the Elemental Tinkering Altar
 * that can only take items if the altar
 * isnt' doing a recipe.
 *
 * @author Vazkii
 */
public class SlotElementalTinkeringAltar extends Slot {

	TileEntityElementalTinkeringAltar altar;

	public SlotElementalTinkeringAltar(TileEntityElementalTinkeringAltar altar, int par2, int par3, int par4) {
		super(altar, par2, par3, par4);
		this.altar = altar;
	}

	@Override
	public boolean canTakeStack(EntityPlayer par1EntityPlayer) {
		return !altar.getIsCreating();
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return !altar.getIsCreating();
	}

}
