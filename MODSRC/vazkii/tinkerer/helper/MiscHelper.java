/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 24 Dec 2012
package vazkii.tinkerer.helper;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * MiscHelper
 *
 * Helper for Misc. Features
 *
 * @author Vazkii
 */
public class MiscHelper {

	@SideOnly(Side.CLIENT)
	public static final Minecraft getMc() {
		return Minecraft.getMinecraft();
	}

	@SideOnly(Side.CLIENT)
	public static final EntityPlayer getClientPlayer() {
		return getMc().thePlayer;
	}

	public static final MinecraftServer getServer() {
		return MinecraftServer.getServer();
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

	/** Does a cross multiplication, where it returns d, where in (d = bc / a) **/
	public static double crossMuliply(int a, int b, int c) {
		return b*c / a;
	}
}
