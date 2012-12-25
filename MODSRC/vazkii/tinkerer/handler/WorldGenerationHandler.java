/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 24 Dec 2012
package vazkii.tinkerer.handler;

import java.util.Random;

import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import vazkii.tinkerer.reference.BlockIDs;
import vazkii.tinkerer.reference.MiscReference;
import vazkii.tinkerer.reference.WorldGenRates;
import vazkii.tinkerer.world.WorldGenElementiumOre;
import cpw.mods.fml.common.IWorldGenerator;

/**
 * WorldGenerationHandler
 *
 * Main Handler for Ore Generation
 *
 * @author Vazkii
 */
public final class WorldGenerationHandler implements IWorldGenerator {

	public static final WorldGenerationHandler INSTANCE = new WorldGenerationHandler();
	
	private WorldGenerationHandler() { }
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		// Generate Elementium Ore
		tryGenerateElementium(random, world, chunkX, chunkZ);
	}
	
	/** Generate Elementium Ore in the world, passing in the world instance and chunk coords **/
	public void tryGenerateElementium(Random worldRand, World world, int chunkX, int chunkZ) {
		if(worldRand.nextInt(100) < WorldGenRates.ELEMENTIUM_ORE_RARITY) {
			int posX = chunkX + worldRand.nextInt(MiscReference.CHUNK_LENGHT);
			int posY = MathHelper.getRandomIntegerInRange(worldRand, WorldGenRates.ELEMENTIUM_ORE_HEIGHT_MIN, WorldGenRates.ELEMENTIUM_ORE_HEIGHT_MAX);
			int posZ = chunkZ + worldRand.nextInt(MiscReference.CHUNK_LENGHT);
			WorldGenElementiumOre.INSTANCE.generate(world, worldRand, posX, posY, posZ);
		}
	}

}
