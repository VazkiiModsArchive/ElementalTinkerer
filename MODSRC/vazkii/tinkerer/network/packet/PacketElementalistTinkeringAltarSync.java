/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 20 Jan 2013
package vazkii.tinkerer.network.packet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import vazkii.tinkerer.reference.NetworkReference;
import vazkii.tinkerer.tile.TileEntityElementalTinkeringAltar;
import cpw.mods.fml.common.network.Player;

/**
 * PacketElementalistTinkeringAltarSync
 *
 * Packet to sync the Elementalist Tinkering Altar Tile Entities.
 *
 * @author Vazkii
 */
public class PacketElementalistTinkeringAltarSync extends ETPacket {

	public static final PacketElementalistTinkeringAltarSync RECIEVER_INSTANCE = new PacketElementalistTinkeringAltarSync();

	/** Constructor with no params required for the reciever end **/
	private PacketElementalistTinkeringAltarSync() { }

	TileEntityElementalTinkeringAltar altar;

	public PacketElementalistTinkeringAltarSync(TileEntityElementalTinkeringAltar altar) {
		this.altar = altar;
	}

	@Override
	public ByteArrayOutputStream asOutputStream() throws IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(stream);
		writeSubchannel(data);
		data.writeInt(altar.xCoord);
		data.writeInt(altar.yCoord);
		data.writeInt(altar.zCoord);
		data.writeBoolean(altar.getIsCreating());
		data.writeInt(altar.getProgress());
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
			TileEntityElementalTinkeringAltar altar = (TileEntityElementalTinkeringAltar) ((EntityPlayer)player).worldObj.getBlockTileEntity(x, y, z);
			altar.setCreating(inputStream.readBoolean());
			altar.setProgress(inputStream.readInt());
			return true;
		}
		return false;
	}

	@Override
	public String getSubchannel() {
		return NetworkReference.SUBCHANNEL_ELEMENTAL_TINKERING_ALTAR_SYNC;
	}

}
