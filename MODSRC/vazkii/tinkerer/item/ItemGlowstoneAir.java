/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 26 Feb 2013
package vazkii.tinkerer.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.tinkerer.reference.BlockIDs;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * ItemGlowstoneAir
 *
 * The item for the Glowstone Air, it places it at the player location.
 *
 * @author Vazkii
 */
public class ItemGlowstoneAir extends ItemET {

	public ItemGlowstoneAir(int par1) {
		super(par1);
		iconIndex = ResourcesReference.ITEM_INDEX_GASEOUS_GLOWSTONE;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		int x = (int) par3EntityPlayer.posX;
		int y = (int) par3EntityPlayer.posY + 1;
		int z = (int) par3EntityPlayer.posZ;
		boolean air = par2World.isAirBlock(x, y, z);

		if(air) {
			if(!par2World.isRemote)
				par2World.setBlockAndMetadata(x, y, z, BlockIDs.glowstoneAir, 5);
			par2World.scheduleBlockUpdate(x, y, z, BlockIDs.glowstoneAir, 10);
		}

		return par1ItemStack;
	}

}
