/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 9 Feb 2013
package vazkii.tinkerer.item;

import java.awt.Color;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.tinkerer.client.handler.ClientTickHandler;
import vazkii.tinkerer.helper.MathHelper;
import vazkii.tinkerer.reference.GameReference;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * ItemOddClaw
 *
 * The Odd Claw item, it conjures a few arrows by right click (on cooldown)
 *
 * @author Vazkii
 */
public class ItemOddClaw extends ItemET {

	public ItemOddClaw(int par1) {
		super(par1);
		iconIndex = ResourcesReference.ITEM_INDEX_ODD_CLAW;
		setMaxStackSize(1);
		setMaxDamage(GameReference.ODD_CLAW_RECHARGE_TIME);
		setNoRepair();
	}

	@Override
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	public int getIconFromDamageForRenderPass(int par1, int par2) {
		return par2 == 1 ? iconIndex + 1 : iconIndex;
	}

	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		switch(par2) {
			case 0 : return 0xFFFFFF;

			case 1 : {
				int effDmg = -(par1ItemStack.getItemDamage() - par1ItemStack.getMaxDamage());
				float brightness = (float) (MathHelper.crossMuliply(par1ItemStack.getMaxDamage(), 0.8F, effDmg) + Math.cos(ClientTickHandler.elapsedClientTicks / 3D) * 0.2F);
				return Color.HSBtoRGB(60F / 360F, 1F, brightness);
			}
		}
		return super.getColorFromItemStack(par1ItemStack, par2);
	}

	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		if(par1ItemStack.getItemDamage() > 0 && par3Entity instanceof EntityLiving)
			par1ItemStack.setItemDamage(par1ItemStack.getItemDamage() - 1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if(par1ItemStack.getItemDamage() == 0) {
			if(!par2World.isRemote) {
				EntityArrow entity = new EntityArrow(par2World, par3EntityPlayer, 2F);
				entity.setIsCritical(true);
				entity.canBePickedUp = 0;
				if(Math.random() < GameReference.ODD_CLAW_FIRE_ARROW_CHANCE)
					entity.setFire(100);
				if(Math.random() < GameReference.ODD_CLAW_KB_ARROW_CHANCE)
					entity.setKnockbackStrength(1);

				par2World.spawnEntityInWorld(entity);
			}
			par1ItemStack.damageItem(par1ItemStack.getMaxDamage(), par3EntityPlayer);
		}
		return par1ItemStack;
	}

	@Override
	public int getDamageVsEntity(Entity par1Entity) {
		return 6;
	}

	@Override
    public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
        if (par2Block.blockID == Block.web.blockID)
            return 15.0F;
        else {
            Material var3 = par2Block.blockMaterial;
            return var3 != Material.plants && var3 != Material.vine && var3 != Material.coral && var3 != Material.leaves && var3 != Material.pumpkin ? 1.0F : 1.5F;
        }
    }
}
