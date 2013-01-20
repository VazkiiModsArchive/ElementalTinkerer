/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 17 Jan 2013
package vazkii.tinkerer.tile.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

/**
 * DummyContainer
 *
 * A dummy container that can not be interacted with. This is
 * used to create proper instances of the DummyInventoryCrafting
 * type.
 *
 * @author Vazkii
 */
public class DummyContainer extends Container {

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return false;
	}

}
