/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 11 Mar 2013
package vazkii.tinkerer.item;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import vazkii.tinkerer.reference.ItemIDs;

/**
 * SpellbindClothRecipe
 *
 * Recipes for the Spellbinding cloth. Any items made with this
 * recipe will dupe the item that isn't the cloth and remove
 * any enchan
 *
 * @author Vazkii
 */
public class SpellbindClothRecipe implements IRecipe {

	public static final SpellbindClothRecipe INSTANCE = new SpellbindClothRecipe();

	private SpellbindClothRecipe() { }

	@Override
	public boolean matches(InventoryCrafting var1, World var2) {
		boolean foundCloth = false;
		boolean foundEnchanted = false;
		for(int i = 0; i < var1.getSizeInventory(); i++) {
			ItemStack stack = var1.getStackInSlot(i);
			if(stack != null) {
				if(stack.isItemEnchanted() && !foundEnchanted)
					foundEnchanted = true;

				else if(stack.itemID == ItemIDs.spellbindCloth && !foundCloth)
					foundCloth = true;

				else return false; // Found an invalid item, breaking the recipe
			}
		}

		return foundCloth && foundEnchanted;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting var1) {
		ItemStack stackToDisenchant = null;
		for(int i = 0; i < var1.getSizeInventory(); i++) {
			ItemStack stack = var1.getStackInSlot(i);
			if(stack != null && stack.isItemEnchanted()) {
				stackToDisenchant = stack.copy();
				break;
			}
		}

		if(stackToDisenchant == null)
			return null;

		NBTTagCompound cmp = (NBTTagCompound) stackToDisenchant.getTagCompound().copy();
		cmp.removeTag("ench"); // Remove enchantments
		stackToDisenchant.setTagCompound(cmp);

		return stackToDisenchant;
	}

	@Override
    public int getRecipeSize() {
        return 10;
    }

	@Override
    public ItemStack getRecipeOutput() {
        return null;
    }

}
