/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 9 Jan 2013
package vazkii.tinkerer.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.storage.ISaveHandler;
import vazkii.tinkerer.reference.ResourcesReference;
import cpw.mods.fml.relauncher.ReflectionHelper;

/**
 * IOHelper
 *
 * Helper Class for IO, mainly manages caching.
 *
 * @author Vazkii
 */
public class IOHelper {

	/** Gets the directory of the world (the world root directory, doesn't
	 * apply to dimensions) **/
	public static File getWorldDirectory(World world) {
		try {
			ISaveHandler worldsaver = world.getSaveHandler();
			IChunkLoader loader = worldsaver.getChunkLoader(world.provider);
			File file = ReflectionHelper.<File, AnvilChunkLoader> getPrivateValue(AnvilChunkLoader.class, (AnvilChunkLoader) loader, 3);
			return file.getName().contains("DIM") ? file.getParentFile() : file;
		} catch (Exception e) {
			return null;
		}
	}

	/** Gets a file with NBT (Named Binary Tag) data, creates one, or the data required
	 * if the file passed in isn't a NBT file. The file passed in must exist **/
	public static File createOrGetNBTFile(File f) {
		try {
			CompressedStreamTools.readCompressed(new FileInputStream(f));
		} catch (Exception e) {
			NBTTagCompound cmp = new NBTTagCompound();
			try {
				CompressedStreamTools.writeCompressed(cmp, new FileOutputStream(f));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		return f;
	}

	/** Gets a file passed in, creates it if it doesn't exist **/
	public static File createOrGetFile(File f) {
		if (!f.exists()) try {
			f.getParentFile().mkdirs();
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return f;
	}

	/** Gets the NBTTagCompound in a file, creates one if there is none **/
	public static NBTTagCompound getTagCompoundInFile(File f) {
		try {
			NBTTagCompound cmp = CompressedStreamTools.readCompressed(new FileInputStream(f));
			return cmp;
		} catch (IOException e) {
			NBTTagCompound cmp = new NBTTagCompound();
			try {
				CompressedStreamTools.writeCompressed(cmp, new FileOutputStream(f));
				return getTagCompoundInFile(f);
			} catch (IOException e1) {
				return null;
			}
		}
	}

	/** Injects an NBTTagCompound into an file that holds NBT data **/
	public static boolean injectNBTToFile(NBTTagCompound cmp, File f) {
		try {
			CompressedStreamTools.writeCompressed(cmp, new FileOutputStream(f));
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public static NBTTagCompound getWorldCache(World world) {
		File worldCacheFile = createOrGetFile(new File(getWorldDirectory(world), ResourcesReference.CACHE_FILE_NAME));
		return getTagCompoundInFile(createOrGetNBTFile(worldCacheFile));
	}

	public static void updateWorldNBTTagCompound(World world, NBTTagCompound cmp) {
		File worldCacheFile = createOrGetFile(new File(getWorldDirectory(world), ResourcesReference.CACHE_FILE_NAME));
		injectNBTToFile(cmp, worldCacheFile);
	}

	public static NBTTagCompound getPlayerWorldCache(World world, String player) {
		File playerCacheFile = createOrGetFile(new File(new File(getWorldDirectory(world), String.format(ResourcesReference.WORLD_PLAYER_CACHE_FOLDER, player)), ResourcesReference.CACHE_FILE_NAME));
		return getTagCompoundInFile(createOrGetNBTFile(playerCacheFile));
	}

	public static void updatePlayerNBTTagCompound(World world, String player, NBTTagCompound cmp) {
		File playerCacheFile = createOrGetFile(new File(new File(getWorldDirectory(world), String.format(ResourcesReference.WORLD_PLAYER_CACHE_FOLDER, player)), ResourcesReference.CACHE_FILE_NAME));
		injectNBTToFile(cmp, playerCacheFile);
	}

}
