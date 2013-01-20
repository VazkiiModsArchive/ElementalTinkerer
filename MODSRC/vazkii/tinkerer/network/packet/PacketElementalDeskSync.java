/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 28 Dec 2012
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
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.reference.NetworkReference;
import vazkii.tinkerer.tile.TileEntityElementalDesk;
import cpw.mods.fml.common.network.Player;

/**
 * PacketElementalDeskSync
 *
 * Packet to sync the Elemental Desk Tile Entities.
 *
 * @author Vazkii
 */
public class PacketElementalDeskSync extends ETPacket {

	public static final PacketElementalDeskSync RECIEVER_INSTANCE = new PacketElementalDeskSync();

	/** Constructor with no params required for the reciever end **/
	private PacketElementalDeskSync() { }

	TileEntityElementalDesk desk;

	public PacketElementalDeskSync(TileEntityElementalDesk desk) {
		this.desk = desk;
	}

	@Override
	public ByteArrayOutputStream asOutputStream() throws IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(stream);
		writeSubchannel(data);
		data.writeInt(desk.xCoord);
		data.writeInt(desk.yCoord);
		data.writeInt(desk.zCoord);
		for(int i=0; i<4; i++)
			data.writeByte(desk.getCharge(i));
		data.writeInt(desk.getProgress());
		data.writeBoolean(desk.getIsAdvancing());
		PacketHelper.writeItemStackIntoStream(desk.getStackInSlot(4), data);
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
			TileEntityElementalDesk desk = (TileEntityElementalDesk) ((EntityPlayer)player).worldObj.getBlockTileEntity(x, y, z);
			for(int i=0; i<4; i++)
				desk.setCharge(i, inputStream.readByte());
			int progress = inputStream.readInt();
			boolean advancing = inputStream.readBoolean();
			ItemStack stack = PacketHelper.getItemStackFromStream(inputStream);
			desk.setAdvancing(advancing);
			desk.setProgress(progress);
			desk.setInventorySlotContents(4, stack);
			return true;
		}
		return false;
	}

	@Override
	public String getSubchannel() {
		return NetworkReference.SUBCHANNEL_ELEMENTAL_DESK_SYNC;
	}

}
