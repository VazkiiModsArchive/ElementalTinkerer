/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 11 Mar 2013
package vazkii.tinkerer.item;

import java.awt.Color;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import vazkii.tinkerer.reference.GameReference;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * ItemSpellbindCloth
 *
 * The Spellbind cloth item. It has a glow to it and gets damaged
 * when used in crafting recipes.
 *
 * @author Vazkii
 */
public class ItemSpellbindCloth extends ItemET {

	public ItemSpellbindCloth(int par1) {
		super(par1);
		iconIndex = ResourcesReference.ITEM_INDEX_SPELLBIND_CLOTH;
		setMaxDamage(GameReference.SPELLBIND_CLOTH_USES);
		setMaxStackSize(1);

		CraftingManager.getInstance().getRecipeList().add(SpellbindClothRecipe.INSTANCE);
	}

	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		return Color.HSBtoRGB(0.75F, ((float) par1ItemStack.getMaxDamage() - (float) par1ItemStack.getItemDamage()) / par1ItemStack.getMaxDamage() * 0.5F, 1F);
	}

	@Override
	public boolean hasContainerItem() {
		return true;
	}

	@Override
	public ItemStack getContainerItemStack(ItemStack itemStack) {
		ItemStack copyStack = itemStack.copy();
		copyStack.setItemDamage(copyStack.getItemDamage() + 1);
		if(copyStack.getItemDamage() >= copyStack.getMaxDamage())
			return null;

		return copyStack;
	}

	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack par1ItemStack) {
		return false;
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack) {
		return par1ItemStack.getItemDamage() == 0;
	}
}
