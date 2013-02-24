/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 13 Feb 2013
package vazkii.tinkerer.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.tinkerer.helper.ItemNBTHelper;
import vazkii.tinkerer.reference.FormattingCode;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * ItemLocationGem
 *
 * The Gem of Location item, this item stores some
 * NBT data for X, Y, Z and dimension. They can also
 * be colored for teleporting.
 *
 * @author Vazkii
 */
public class ItemLocationGem extends ItemET {

	public ItemLocationGem(int par1) {
		super(par1);
		iconIndex = ResourcesReference.ITEM_INDEX_LOCATION_GEM;
	}

	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		setValues(par1ItemStack, par4, par5, par6, par2EntityPlayer.dimension);

		return true;
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		if(isAttuned(par1ItemStack)) {
			par3List.add(FormattingCode.GRAY + "X: " + getX(par1ItemStack));
			par3List.add(FormattingCode.GRAY + "Y: " + getY(par1ItemStack));
			par3List.add(FormattingCode.GRAY + "Z: " + getZ(par1ItemStack));
			if(getDim(par1ItemStack) != par2EntityPlayer.dimension)
				par3List.add(FormattingCode.RED + "Different Dimension");
		}
	}

	@Override
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	public int getIconIndex(ItemStack stack, int pass) {
		return isAttuned(stack) ? iconIndex : iconIndex + 1;
	}

	@Override
	public boolean getShareTag() {
		return true;
	}

	public static void setValues(ItemStack stack, int x, int y, int z, int dim) {
		ItemNBTHelper.setInt(stack, "X", x);
		ItemNBTHelper.setInt(stack, "Y", y);
		ItemNBTHelper.setInt(stack, "Z", z);
		ItemNBTHelper.setInt(stack, "dim", dim);
	}

	public static boolean isAttuned(ItemStack stack) {
		return ItemNBTHelper.detectNBT(stack);
	}

	public static int getX(ItemStack stack) {
		if(!isAttuned(stack))
			return 0;

		return ItemNBTHelper.getInt(stack, "X", 0);
	}

	public static int getY(ItemStack stack) {
		if(!isAttuned(stack))
			return 0;

		return ItemNBTHelper.getInt(stack, "Y", 0);
	}

	public static int getZ(ItemStack stack) {
		if(!isAttuned(stack))
			return 0;

		return ItemNBTHelper.getInt(stack, "Z", 0);
	}

	public static int getDim(ItemStack stack) {
		if(!isAttuned(stack))
			return 0;

		return ItemNBTHelper.getInt(stack, "dim", 0);
	}

}
