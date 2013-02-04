/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 24 Dec 2012
package vazkii.tinkerer.helper;

import java.awt.Point;

import vazkii.tinkerer.item.ItemWand;
import vazkii.tinkerer.lightning.Vector3;
import vazkii.tinkerer.magic.IWand;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * MiscHelper
 *
 * Helper for Misc. Features
 *
 * @author Vazkii
 */
public final class MiscHelper {

	@SideOnly(Side.CLIENT)
	public static Minecraft getMc() {
		return Minecraft.getMinecraft();
	}

	@SideOnly(Side.CLIENT)
	public static EntityClientPlayerMP getClientPlayer() {
		return getMc().thePlayer;
	}
	
	@SideOnly(Side.CLIENT)
	public static WorldClient getClientWorld() {
		return getMc().theWorld;
	}

	public static MinecraftServer getServer() {
		return MinecraftServer.getServer();
	}
	
	public static boolean isServerPVP() {
		return getServer().isPVPEnabled();
	}

	/** Compares two item stacks and checks if their ID
	 * and Metadata are equal. **/
	public static boolean areStacksEqualIgnoreSize(ItemStack stack1, ItemStack stack2) {
		return stack1 != null
				&& stack2 != null
				&& stack1.itemID == stack2.itemID
				&& (stack1.getItemDamage() == stack2.getItemDamage()
				|| stack1.getItemDamage() == -1
				|| stack2.getItemDamage() == -1);
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean doesClientPlayerHaveWand() {
		Minecraft mc = getMc();
		EntityPlayer clientPlayer = getClientPlayer();
		return !(clientPlayer == null ||
				clientPlayer.inventory.getCurrentItem() == null || 
				clientPlayer.inventory.getCurrentItem().getItem() == null ||
				!(clientPlayer.inventory.getCurrentItem().getItem() instanceof IWand) || 
				mc.currentScreen != null ||
				SpellHelper.clientSpells == null);
	}
}
