/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 12 Mar 2013
package vazkii.tinkerer.item;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import vazkii.tinkerer.helper.ItemNBTHelper;
import vazkii.tinkerer.reference.FormattingCode;
import vazkii.tinkerer.reference.GameReference;

/**
 * ItemMysticalTalisman
 *
 * The Mystical Talisman item, it pulls all XP around the player
 * into itself and allows to turn it back into XP bottles.
 *
 * @author Vazkii
 */
public class ItemMysticalTalisman extends ItemET {

	public ItemMysticalTalisman(int par1) {
		super(par1);
		setMaxStackSize(1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if(par3EntityPlayer.isSneaking()) {
			int dmg = par1ItemStack.getItemDamage();
			par1ItemStack.setItemDamage(dmg == 0 ? 1 : 0);
		} else if(getXP(par1ItemStack) >= GameReference.ENCHANTING_BOTTLE_COST) {
			boolean has = par3EntityPlayer.inventory.consumeInventoryItem(Item.glassBottle.itemID);
			if(has) {
				if(!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Item.expBottle, 1)) && !par2World.isRemote)
					par3EntityPlayer.dropItem(Item.expBottle.itemID, 1);
				int xp = getXP(par1ItemStack);
				setXP(par1ItemStack, xp - GameReference.ENCHANTING_BOTTLE_COST);
			}
		}

		return par1ItemStack;
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		if(par1ItemStack.getItemDamage() == 1 && !par2World.isRemote) {
			int r = GameReference.MYSTICAL_TALISMAN_RANGE;
			int currentXP = getXP(par1ItemStack);
			int xpToAdd = 0;
			int maxXP = GameReference.MYSTICAL_TALISMAN_MAX_XP - currentXP; // Max, to prevent overflow.
			if(maxXP <= 0) {
				par1ItemStack.setItemDamage(0);
				return; // Can't take any XP.
			}

			AxisAlignedBB boundingBox = AxisAlignedBB.getBoundingBox(par3Entity.posX - r, par3Entity.posY - r, par3Entity.posZ - r, par3Entity.posX + r, par3Entity.posY + r, par3Entity.posZ + r);
			List<EntityXPOrb> orbs = par2World.getEntitiesWithinAABB(EntityXPOrb.class, boundingBox);

			for(EntityXPOrb orb : orbs) {
				if(!orb.isDead) {
					int xp = orb.getXpValue();
					if(xpToAdd + xp <= maxXP) {
						xpToAdd += xp;
						orb.setDead();
					}

					maxXP -= xpToAdd;

					if(maxXP <= 0)
						break;
				}
			}

			setXP(par1ItemStack, Math.min(GameReference.MYSTICAL_TALISMAN_MAX_XP, currentXP + xpToAdd));
		}
	}

	@Override
	public boolean getShareTag() {
		return true;
	}

	@Override
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		par3List.add("XP: " + getXP(par1ItemStack));
		if(getXP(par1ItemStack) >= GameReference.MYSTICAL_TALISMAN_MAX_XP)
			par3List.add(FormattingCode.RED + "Full");
		else if(par1ItemStack.getItemDamage() == 0)
			par3List.add(FormattingCode.RED + "Not Absorbing");
		else par3List.add(FormattingCode.GREEN + "Absorbing");
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack) {
		return par1ItemStack.getItemDamage() == 1;
	}

	public static boolean hasCmp(ItemStack stack) {
		return ItemNBTHelper.detectNBT(stack);
	}

	public static int getXP(ItemStack stack) {
		return ItemNBTHelper.getInt(stack, "XP", 0);
	}

	public static void setXP(ItemStack stack, int xp) {
		ItemNBTHelper.setInt(stack, "XP", xp);
	}
}
