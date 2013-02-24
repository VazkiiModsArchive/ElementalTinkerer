/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 27 Jan 2013
package vazkii.tinkerer.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.helper.SpellHelper;
import vazkii.tinkerer.magic.PlayerSpellData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * PlayerSpellUpdateHandler
 *
 * Handler to tick the players' spells, synced with
 * the tick handler.
 *
 * @author Vazkii
 */
public class PlayerSpellUpdateHandler {

	public static final PlayerSpellUpdateHandler INSTANCE = new PlayerSpellUpdateHandler();

	private PlayerSpellUpdateHandler() { }

	@ForgeSubscribe
	public void onPlayerUpdate(LivingUpdateEvent event) {
		if(event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.entityLiving;
			if(!player.worldObj.isRemote) {
				PlayerSpellData spellData = SpellHelper.getSpellDataForPlayer(player.username);
				serverUpdate(spellData, player);
			}
		}
	}

	public static void serverUpdate(PlayerSpellData spellData, EntityPlayer player) {
		spellData.tick(player);
		SpellHelper.updateSpells(player.worldObj, spellData);
	}

	@SideOnly(Side.CLIENT)
	public static void clientUpdate() {
		Minecraft mc = MiscHelper.getMc();
		if(SpellHelper.clientSpells != null && (!mc.isSingleplayer() || mc.currentScreen == null || !mc.currentScreen.doesGuiPauseGame()))
			SpellHelper.clientSpells.tick(mc.thePlayer);
		// Tick only the cooldowns,
		// passives happen in the server side
	}

}
