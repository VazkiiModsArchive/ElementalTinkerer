/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 11 Mar 2013
package vazkii.tinkerer.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import vazkii.tinkerer.item.ICraftingHandle;
import cpw.mods.fml.common.ICraftingHandler;

/**
 * ItemCraftingHandler
 *
 * Handler for crafting items, triggers ICraftingHandle.
 *
 * @author Vazkii
 */
public class ItemCraftingHandler implements ICraftingHandler {

	public static final ItemCraftingHandler INSTANCE = new ItemCraftingHandler();

	private ItemCraftingHandler() { }

	@Override
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix) {
		for(int i = 0; i < craftMatrix.getSizeInventory(); i++) {
			ItemStack stack = craftMatrix.getStackInSlot(i);
			if(stack != null && stack.getItem() != null && stack.getItem() instanceof ICraftingHandle)
				((ICraftingHandle)stack.getItem()).onUseInCrafting(player, item, craftMatrix, i);
		}
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item) {
		// NO-OP
	}

}
