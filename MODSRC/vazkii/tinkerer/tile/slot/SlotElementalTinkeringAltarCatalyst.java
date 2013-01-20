/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 18 Jan 2013
package vazkii.tinkerer.tile.slot;

import net.minecraft.item.ItemStack;
import vazkii.tinkerer.item.ItemCatalyst;
import vazkii.tinkerer.tile.TileEntityElementalTinkeringAltar;

/**
 * SlotElementalTinkeringAltarCatalyst
 *
 * Slot for the Elemental Tinkering Altar GUI that can
 * only take a catalyst item, if there is a catalyst
 * container in the location correspondent to it's index.
 *
 * @author Vazkii
 */
public class SlotElementalTinkeringAltarCatalyst extends SlotElementalTinkeringAltar {

	public SlotElementalTinkeringAltarCatalyst(TileEntityElementalTinkeringAltar altar, int par2, int par3, int par4) {
		super(altar, par2, par3, par4);
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack) {
		return super.isItemValid(par1ItemStack)
			   && par1ItemStack.getItem() != null
			   && par1ItemStack.getItem() instanceof ItemCatalyst
			   && altar.hasCatalystCapsuleOnSide(slotNumber);
	}

}
