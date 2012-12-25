/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 24 Dec 2012
package vazkii.tinkerer.helper;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
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
}
