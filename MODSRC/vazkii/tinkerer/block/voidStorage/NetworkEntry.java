/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 10 Mar 2013
package vazkii.tinkerer.block.voidStorage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.tinkerer.tile.TileEntityVoidGateway;

/**
 * NetworkEntry
 *
 * An Entry for the Void Network tile entity. These hold XYZ coords for
 * a Void Gateway.
 *
 * @author Vazkii
 */
public class NetworkEntry implements Comparable<NetworkEntry> {

	public final int x, y, z;
	public int keep = 0;
	public boolean autoStore = false;

	public NetworkEntry(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public NetworkEntry(int x, int y, int z, int keep, boolean autoStore) {
		this(x, y, z);
		this.keep = keep;
		this.autoStore = autoStore;
	}

	public void writeToNBT(NBTTagCompound cmp) {
		cmp.setInteger("x", x);
		cmp.setInteger("y", y);
		cmp.setInteger("z", z);
		cmp.setInteger("keep", keep);
		cmp.setBoolean("autoStore", autoStore);
	}

	public static NetworkEntry getFromNBT(NBTTagCompound cmp) {
		if(!cmp.hasKey("x") || !cmp.hasKey("y") || !cmp.hasKey("z"))
			return null;

		int x = cmp.getInteger("x");
		int y = cmp.getInteger("y");
		int z = cmp.getInteger("z");
		int keep = cmp.hasKey("keep") ? cmp.getInteger("keep") : 0;
		boolean autoStore = cmp.hasKey("autoStore") ? cmp.getBoolean("autoStore") : false;

		return new NetworkEntry(x, y, z, keep, autoStore);
	}

	public void writeToPacket(DataOutputStream dataStream) throws IOException {
		dataStream.writeInt(x);
		dataStream.writeInt(y);
		dataStream.writeInt(z);
		dataStream.writeInt(keep);
		dataStream.writeBoolean(autoStore);
	}

	public static NetworkEntry getFromPacket(DataInputStream dataStream) throws IOException{
		int x = dataStream.readInt();
		int y = dataStream.readInt();
		int z = dataStream.readInt();
		int keep = dataStream.readInt();
		boolean autoStore = dataStream.readBoolean();

		return new NetworkEntry(x, y, z, keep, autoStore);
	}

	public TileEntityVoidGateway getTile(World world) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		if(tile == null || !(tile instanceof TileEntityVoidGateway))
				return null;
		return (TileEntityVoidGateway)tile;
	}

	public VoidEntry getEntry(World world) {
		TileEntityVoidGateway tileAt = getTile(world);
		if(tileAt == null)
			return null;
		return VoidMap.theMap.getEntryAtCoord(x, z);
	}

	@Override
	public int compareTo(NetworkEntry o) {
		return 0;
		// The class has to implement Comparable, otherwise
		// the Arrays.sort() method can't be called. With this, it sorts
		// null values to the bottom and does nothing with the regular ones
	}

}
