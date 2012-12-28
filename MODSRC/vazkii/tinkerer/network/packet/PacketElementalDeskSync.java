/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 28 Dec 2012
package vazkii.tinkerer.network.packet;

import vazkii.tinkerer.tile.TileEntityElementalDesk;
import net.minecraft.network.packet.Packet250CustomPayload;

/**
 * PacketElementalDeskSync
 *
 * Packet to sync the Elemental Desk
 *
 * @author Vazkii
 */
public class PacketElementalDeskSync extends ETPacket {
	
	/** Constructor with no params required for the reciever end **/
	public PacketElementalDeskSync() { }
	
	TileEntityElementalDesk desk;
	
	public PacketElementalDeskSync(TileEntityElementalDesk desk) {
		this.desk = desk;
	}
	
	@Override
	public Packet250CustomPayload asCustomPayload() {
		return null;
	}

	@Override
	public boolean readPayload(Packet250CustomPayload packet) {
		return false;
	}

}
