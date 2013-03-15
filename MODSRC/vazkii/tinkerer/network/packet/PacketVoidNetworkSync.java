/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 10 Mar 2013
package vazkii.tinkerer.network.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import vazkii.tinkerer.block.voidStorage.NetworkEntry;
import vazkii.tinkerer.reference.NetworkReference;
import vazkii.tinkerer.tile.TileEntityVoidNetwork;
import cpw.mods.fml.common.network.Player;

/**
 * PacketVoidNetworkSync
 *
 * Packet to sync the data in the Void Network tiles.
 *
 * @author Vazkii
 */
public class PacketVoidNetworkSync extends ETPacket {

	public static final PacketVoidNetworkSync RECIEVER_INSTANCE = new PacketVoidNetworkSync();

	/** Constructor with no params required for the reciever end **/
	private PacketVoidNetworkSync() { }

	TileEntityVoidNetwork network;

	public PacketVoidNetworkSync(TileEntityVoidNetwork tile) {
		network = tile;
	}

	@Override
	public ByteArrayOutputStream asOutputStream() throws IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(stream);
		writeSubchannel(data);
		data.writeInt(network.xCoord);
		data.writeInt(network.yCoord);
		data.writeInt(network.zCoord);

		int nonNulls = network.getNonNullEntries();
		data.writeInt(network.getNonNullEntries());
		for(int i = 0; i < nonNulls; i++) // Sanity check and sorting were already performed
			network.entries[i].writeToPacket(data);

		return stream;
	}

	@Override
	public boolean readPayload(Packet250CustomPayload packet, INetworkManager manager, Player player, String subchannel) throws IOException {
		if(subchannel.equals(getSubchannel()) && player != null && player instanceof EntityPlayer) {
			ByteArrayInputStream stream = new ByteArrayInputStream(packet.data);
			DataInputStream inputStream = new DataInputStream(stream);
			skipSubchannel(inputStream);
			int x = inputStream.readInt();
			int y = inputStream.readInt();
			int z = inputStream.readInt();
			TileEntityVoidNetwork network = (TileEntityVoidNetwork) ((EntityPlayer)player).worldObj.getBlockTileEntity(x, y, z);

			int entries = inputStream.readInt();
			NetworkEntry[] entryArray = new NetworkEntry[entries];
			for(int i = 0; i < entries; i++)
				entryArray[i] = NetworkEntry.getFromPacket(inputStream);

			network.entries = entryArray;
			network.sanityCheckEntrySize();

			return true;
		}
		return false;
	}

	@Override
	public String getSubchannel() {
		return NetworkReference.SUBCHANNEL_VOID_NETWORK_SYNC;
	}

}
