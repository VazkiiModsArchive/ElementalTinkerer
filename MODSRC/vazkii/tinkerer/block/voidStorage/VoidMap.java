/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 22 Feb 2013
package vazkii.tinkerer.block.voidStorage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import vazkii.tinkerer.helper.IOHelper;

/**
 * VoidMap
 *
 * This class stores data for the void storage blocks
 * for the current world, per coord in collections.
 *
 * @author Vazkii
 */
public class VoidMap {

	public static VoidMap theMap;

	public Map<Integer, Map<Integer, VoidEntry>> entriesAtCoords;
	public final boolean clientSide;

	public VoidMap() {
		this(false);
	}

	public VoidMap(boolean client) {
		entriesAtCoords = new HashMap();
		clientSide = client;
	}

	public void readFromNBT(NBTTagCompound subCmp) {
		Collection<NBTBase> tags = subCmp.getTags();
		for(NBTBase tag : tags) {
			String name = tag.getName();
			if(tag instanceof NBTTagCompound && name.startsWith("x")) {
				NBTTagCompound xCmp = (NBTTagCompound)tag;
				int x = Integer.parseInt(name.substring(1));
				Collection<NBTBase> xTags = xCmp.getTags();
				for(NBTBase tag1 : xTags) {
					String name1 = tag1.getName();
					if(tag1 instanceof NBTTagCompound && name1.startsWith("y")) {
						NBTTagCompound entryCmp = (NBTTagCompound)tag1;
						int y = Integer.parseInt(name1.substring(1));
						VoidEntry entry = VoidEntry.createFromNBT(entryCmp);
						setEntryAtCoord(x, y, entry);
					}
				}
			}
		}
	}

	public void writeToNBT(World world) {
		NBTTagCompound worldCmp = IOHelper.getWorldCache(world);
		NBTTagCompound subCmp = new NBTTagCompound();
		writeToNBT(subCmp);
		worldCmp.setCompoundTag("voidStorage", subCmp);
		IOHelper.updateWorldNBTTagCompound(world, worldCmp);
	}

	public void writeToNBT(NBTTagCompound subCmp) {
		for(int x : entriesAtCoords.keySet()) {
			NBTTagCompound xCmp = new NBTTagCompound();
			Map<Integer, VoidEntry> entriesAtX = entriesAtCoords.get(x);
			for(int y : entriesAtX.keySet()) {
				NBTTagCompound entryCmp = new NBTTagCompound();
				VoidEntry entry = entriesAtX.get(y);
				if(entry != null)
					entry.writeToNBT(entryCmp);
				xCmp.setCompoundTag("y" + y, entryCmp);
			}
			subCmp.setCompoundTag("x" + x, xCmp);
		}
	}

	public void setEntryAtCoord(int x, int y, VoidEntry entry) {
		if(!entriesAtCoords.containsKey(x))
			entriesAtCoords.put(x, new HashMap());

		Map<Integer, VoidEntry> entriesAtX = entriesAtCoords.get(x);
		entriesAtX.put(y, entry);
	}

	public VoidEntry getEntryAtCoord(int x, int y) {
		if(!entriesAtCoords.containsKey(x))
			return null;

		Map<Integer, VoidEntry> entriesAtX = entriesAtCoords.get(x);

		if(!entriesAtX.containsKey(y))
			return null;

		return entriesAtX.get(y);
	}
}
