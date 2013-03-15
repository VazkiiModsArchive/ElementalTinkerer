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
import net.minecraft.tileentity.TileEntity;
import vazkii.tinkerer.reference.NetworkReference;
import vazkii.tinkerer.tile.TileEntityVoidNetwork;
import cpw.mods.fml.common.network.Player;

/**
 * PacketVoidNetworkButton
 *
 * Packet that signifies that a button in the Void Network
 * GUI was clicked. Processing happens in the server of course.
 *
 * @author Vazkii
 */
public class PacketVoidNetworkButton extends ETPacket {

	public static PacketVoidNetworkButton RECIEVER_INSTANCE = new PacketVoidNetworkButton();

	TileEntityVoidNetwork tile;
	int selected;
	int button;
	int number;

	/** Constructor with no params required for the reciever end **/
	private PacketVoidNetworkButton() { }

	public PacketVoidNetworkButton(TileEntityVoidNetwork tile, int selected, int button, int number) {
		this.tile = tile;
		this.selected = selected;
		this.button = button;
		this.number = number;
	}

	@Override
	public ByteArrayOutputStream asOutputStream() throws IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		DataOutputStream data = new DataOutputStream(stream);
		writeSubchannel(data);
		data.writeInt(tile.xCoord);
		data.writeInt(tile.yCoord);
		data.writeInt(tile.zCoord);
		data.writeInt(selected);
		data.writeInt(button);
		data.writeInt(number);
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
			TileEntity tile = ((EntityPlayer)player).worldObj.getBlockTileEntity(x, y, z);
			if(tile instanceof TileEntityVoidNetwork) {
				TileEntityVoidNetwork network = (TileEntityVoidNetwork) tile;
				int selected = inputStream.readInt();
				int button = inputStream.readInt();
				int number = inputStream.readInt();
				network.handleButtonClick((EntityPlayer)player, button, selected, number);
			}
			return true;
		}
		return false;
	}

	@Override
	public String getSubchannel() {
		return NetworkReference.SUBCHANNEL_VOID_NETWORK_BUTTON;
	}

}
