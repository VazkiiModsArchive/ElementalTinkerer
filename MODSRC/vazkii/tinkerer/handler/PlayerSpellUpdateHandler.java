/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 27 Jan 2013
package vazkii.tinkerer.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraftforge.event.ForgeSubscribe;
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
	
	public static void serverUpdate() {
		ServerConfigurationManager manager = MiscHelper.getServer().getConfigurationManager();
		for(PlayerSpellData spellData : SpellHelper.spellsForPlayers.values()) {
			EntityPlayer player = manager.getPlayerForUsername(spellData.playerLinkedTo);
			if(player != null)
				serverUpdate(spellData, player);
		}
	}
	
	public static void serverUpdate(PlayerSpellData spellData, EntityPlayer player) {
		spellData.tick();
		SpellHelper.updateSpells(player.worldObj, spellData);
	}
	
	@SideOnly(Side.CLIENT)
	public static void clientUpdate() {
		if(SpellHelper.clientSpells != null)
			SpellHelper.clientSpells.tickCooldowns(); 
		// Tick only the cooldowns,
		// passives happen in the server side
	}

}
