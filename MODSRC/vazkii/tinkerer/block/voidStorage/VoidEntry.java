/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 22 Feb 2013
package vazkii.tinkerer.block.voidStorage;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * VoidEntry
 *
 * An entry on the world void map. This contains an
 * ItemStack and a long for how many there are in
 * it.
 *
 * @author Vazkii
 */
public final class VoidEntry {

	int x, z;

	public ItemStack stack;
	public long qtd;

	public VoidEntry(ItemStack stack) {
		this(stack, 0);
	}

	public VoidEntry(ItemStack stack, long qtd) {
		this.stack = stack;
		this.qtd = qtd;
	}

	public void writeToNBT(NBTTagCompound cmp) {
		NBTTagCompound stackCmp = new NBTTagCompound();
		if(stack != null) {
			stack.writeToNBT(stackCmp);
			cmp.setCompoundTag("stack", stackCmp);
			cmp.setLong("qtd", qtd);
		}
	}

	public static VoidEntry createFromNBT(NBTTagCompound cmp) {
		if(!cmp.hasKey("qtd") || !cmp.hasKey("stack"))
			return null;

		NBTTagCompound stackCmp = cmp.getCompoundTag("stack");
		ItemStack stack = ItemStack.loadItemStackFromNBT(stackCmp);
		long qtd = cmp.getLong("qtd");

		return new VoidEntry(stack, qtd);
	}

}
