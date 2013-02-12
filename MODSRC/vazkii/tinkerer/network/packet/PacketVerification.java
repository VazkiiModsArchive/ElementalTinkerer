/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 2 Jan 2013
package vazkii.tinkerer.network.packet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;

import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import vazkii.tinkerer.ElementalTinkerer;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.reference.NetworkReference;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * PacketVerification
 *
 * Packet to verify that this server has Elemental Tinkerer.
 *
 * @author Vazkii
 */
public class PacketVerification extends ETPacket {

	public static final PacketVerification INSTANCE = new PacketVerification();

	@Override
	public ByteArrayOutputStream asOutputStream() throws IOException {
		return emptyStream();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean readPayload(Packet250CustomPayload packet, INetworkManager manager, Player player, String subchannel) {
		if(subchannel.equals(getSubchannel())) {
			PacketHelper.currentServerVerified = true;
			ServerData data = MiscHelper.getMc().getServerData();
			String display = "N/A";
			if(data != null)
				display = data.serverIP;
			ElementalTinkerer.logger.log(Level.INFO, "Server " + display + " was verified to have Elemental Tinkerer.");
			return true;
		}
		return false;
	}

	@Override
	public String getSubchannel() {
		return NetworkReference.SUBCHANNEL_VERIFY;
	}
}
