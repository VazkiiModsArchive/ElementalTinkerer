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
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.reference.NetworkReference;
import vazkii.tinkerer.research.PlayerResearch;
import vazkii.tinkerer.tile.TileEntityElementalTinkeringAltar;
import vazkii.tinkerer.tile.container.ContainerElementalistTinkeringAltar;
import cpw.mods.fml.common.network.Player;

/**
 * PacketElementalistTinkeringAltarStartRecipe
 *
 * A Packet sent to start a recipe in the elementalist's
 * tinkering altar.
 *
 * @author Vazkii
 */
public class PacketElementalistTinkeringAltarStartRecipe extends ETPacket {

	public static final PacketElementalistTinkeringAltarStartRecipe RECIEVER_INSTANCE = new PacketElementalistTinkeringAltarStartRecipe();

	/** Constructor with no params required for the reciever end **/
	private PacketElementalistTinkeringAltarStartRecipe() { }

	TileEntityElementalTinkeringAltar altar;

	public PacketElementalistTinkeringAltarStartRecipe(TileEntityElementalTinkeringAltar altar) {
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
		return stream;
	}

	@Override
	public boolean readPayload(Packet250CustomPayload packet, INetworkManager manager, Player player, String subchannel) throws IOException {
		if(subchannel.equals(getSubchannel()) && player != null && player instanceof EntityPlayer) {
			EntityPlayer entityPlayer = (EntityPlayer)player;
			if(entityPlayer.openContainer != null && entityPlayer.openContainer instanceof ContainerElementalistTinkeringAltar) {
				ByteArrayInputStream stream = new ByteArrayInputStream(packet.data);
				DataInputStream inputStream = new DataInputStream(stream);
				skipSubchannel(inputStream);
				int x = inputStream.readInt();
				int y = inputStream.readInt();
				int z = inputStream.readInt();
				ContainerElementalistTinkeringAltar container = (ContainerElementalistTinkeringAltar)entityPlayer.openContainer;
				if(container.altar.xCoord == x && container.altar.yCoord == y && container.altar.zCoord == z && !container.altar.getIsCreating()) {
					TileEntityElementalTinkeringAltar tile = (TileEntityElementalTinkeringAltar) entityPlayer.worldObj.getBlockTileEntity(x, y, z);
					PlayerResearch research = ResearchHelper.getResearchDataForPlayer(entityPlayer.username);
					if(research != null) {
						ItemStack stack = tile.getCraftingPrevision(research);
						if(stack != null)
							container.altar.setCreating(true);
						// There is a valid recipe, the player has the research for it
					}
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public String getSubchannel() {
		return NetworkReference.SUBCHANNEL_ELEMENTAL_TINKERING_START_RECIPE;
	}

}
