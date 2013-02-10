/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 2 Jan 2013
package vazkii.tinkerer.handler;

import net.minecraft.entity.player.EntityPlayer;
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.helper.SpellHelper;
import vazkii.tinkerer.network.packet.PacketVerification;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.network.Player;

/**
 * ConnectionHandler
 *
 * Handles connections, currently only just sends verification packets
 * to clients logging in.
 *
 * @author Vazkii
 */
public class PlayerTrackingHandler implements IPlayerTracker {

	public static final PlayerTrackingHandler INSTANCE = new PlayerTrackingHandler();

	@Override
	public void onPlayerLogin(EntityPlayer player) {
		PacketHelper.sendPacketToClient((Player) player, PacketVerification.INSTANCE);

		ResearchHelper.handlePlayerLogin(player);

		SpellHelper.handlePlayerLogin(player);
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {
		// NO-OP
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
		// NO-OP
	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {
		// NO-OP
	}
}
