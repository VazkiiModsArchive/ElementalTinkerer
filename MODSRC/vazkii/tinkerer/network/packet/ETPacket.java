/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 28 Dec 2012
package vazkii.tinkerer.network.packet;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.Player;

/**
 * ETPacket
 *
 * Not really a packet, it's more of a class that creates and reads packet
 * 250s with data passed in.
 *
 * @author Vazkii
 */
public abstract class ETPacket {

	/** Generates a ByteArrayOutputStream to be sent. **/
	public abstract ByteArrayOutputStream asOutputStream() throws IOException;

	/** Tries to read the packet, returns false if it's not intended to read
	 *  this type of packet, true if it read it, stopping the execution of
	 *  further attempts to pass in the packet **/
	public abstract boolean readPayload(Packet250CustomPayload packet, INetworkManager manager, Player player, String subchannel) throws IOException;

	/** Gets the subchannel used for populating the packet. A
	 * subchannel is for this mod only, since there is only
	 * one channel for the packets in the mod, the subchannel is used
	 * to sort them after they have been passed to the mod's packet handler **/
	public abstract String getSubchannel();

	/** Returns an empty ByteArrayOutputStream **/
	public ByteArrayOutputStream emptyStream() throws IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(stream);
		writeSubchannel(data);
		return stream;
	}

	/** Has the data input stream instance skip the subchannel. **/
	public final void skipSubchannel(DataInputStream stream) throws IOException  {
		stream.readUTF();
	}

	/** Writes the subchannel to the packet **/
	public final void writeSubchannel(DataOutputStream stream) throws IOException {
		stream.writeUTF(getSubchannel());
	}
}
