/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Feb 2013
package vazkii.tinkerer.network.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import vazkii.tinkerer.block.voidStorage.VoidEntry;
import vazkii.tinkerer.block.voidStorage.VoidMap;
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.reference.NetworkReference;
import cpw.mods.fml.common.network.Player;

/**
 * PacketVoidStorage
 *
 * A Packet with the Void Storage info. This is sent by a void gateway
 * and allows the client to get it's own data of void storage for
 * renderers.
 *
 * @author Vazkii
 */
public class PacketVoidStorage extends ETPacket {

	public static final PacketVoidStorage RECIEVER_INSTANCE = new PacketVoidStorage();

	/** Constructor with no params required for the reciever end **/
	private PacketVoidStorage() { }

	VoidEntry entry;
	int x, z;

	public PacketVoidStorage(VoidEntry entry, int x, int z) {
		this.entry = entry;
		this.x = x;
		this.z = z;
	}

	@Override
	public ByteArrayOutputStream asOutputStream() throws IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(stream);
		writeSubchannel(data);
		data.writeInt(x);
		data.writeInt(z);
		data.writeLong(entry.qtd);
		PacketHelper.writeItemStackIntoStream(entry.stack, data);
		return stream;
	}

	@Override
	public boolean readPayload(Packet250CustomPayload packet, INetworkManager manager, Player player, String subchannel) throws IOException {
		if(subchannel.equals(getSubchannel()) && player != null && player instanceof EntityPlayer) {
			ByteArrayInputStream stream = new ByteArrayInputStream(packet.data);
			DataInputStream inputStream = new DataInputStream(stream);
			skipSubchannel(inputStream);
			int x = inputStream.readInt();
			int z = inputStream.readInt();
			long qtd = inputStream.readLong();
			ItemStack stack = PacketHelper.getItemStackFromStream(inputStream);
			if(VoidMap.theMap != null)
				VoidMap.theMap.setEntryAtCoord(x, z, new VoidEntry(stack, qtd));
			return true;
		}

		return false;
	}

	@Override
	public String getSubchannel() {
		return NetworkReference.SUBCHANNEL_VOID_STORAGE;
	}

}
