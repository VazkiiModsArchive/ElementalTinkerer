/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 17 Jan 2013
package vazkii.tinkerer.tile.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;

/**
 * DummyInventoryCrafting
 *
 * An InventoryCrafting instance that is a dummy, can not be accessed
 * trough regular means (crafting table) and doesn't have a container
 * linked to it. It's used to pass around data for the Elementalist
 * Tinkering Altar recipes.
 *
 * @author Vazkii
 */
public class DummyInventoryCrafting extends InventoryCrafting {

	public DummyInventoryCrafting(int width, int height) {
		super(new DummyContainer(), width, height);
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return false;
	}

}
