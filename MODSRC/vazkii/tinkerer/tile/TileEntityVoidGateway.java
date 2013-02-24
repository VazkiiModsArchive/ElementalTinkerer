/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 22 Feb 2013
package vazkii.tinkerer.tile;

import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import vazkii.tinkerer.block.voidStorage.VoidEntry;
import vazkii.tinkerer.block.voidStorage.VoidMap;
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.network.packet.PacketVoidStorage;

/**
 * TileEntityVoidGateway
 *
 * The Void Gateway Tile Entity. It is only used
 *
 * @author Vazkii
 */
public class TileEntityVoidGateway extends TileEntity {

	@Override
	public Packet getDescriptionPacket() {
		VoidEntry entry = VoidMap.theMap.getEntryAtCoord(xCoord, yCoord);
		if(entry == null)
			return null;

		return PacketHelper.generatePacket(new PacketVoidStorage(entry, xCoord, yCoord));
	}

}
