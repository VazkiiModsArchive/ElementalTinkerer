/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 17 Jan 2013
package vazkii.tinkerer.tile.container;

import net.minecraft.inventory.InventoryCrafting;

/**
 * IDualCraftingInventory
 *
 * Interface for dual crafting inventories. Any double crafing
 * inventories are just instances of InventoryCrafting to be
 * able to be passed to IRecipe instances. It has actually,
 * two inventories. An implementation of this is thhe Elemental
 * Tinkering Altar, which has a 5x5 inventory (shaped) for the
 * main recipe and a 2x2 inventory (shapeless) for the catalysts.
 *
 * @author Vazkii
 */
public interface IDualCraftingInventory {

	public InventoryCrafting getFirstInventory();

	public InventoryCrafting getSecondInventory();

}
