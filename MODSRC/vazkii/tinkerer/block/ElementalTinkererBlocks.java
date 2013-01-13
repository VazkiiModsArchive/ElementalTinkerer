/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Dec 2012
package vazkii.tinkerer.block;

import net.minecraft.block.Block;
import vazkii.tinkerer.item.ItemMetadataCompatBlock;
import vazkii.tinkerer.reference.BlockIDs;
import vazkii.tinkerer.reference.BlockNames;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * ElementalTinkererBlocks
 *
 * This class holds the blocks for the mod.
 *
 * @author Vazkii
 */
public final class ElementalTinkererBlocks {

	public static Block elementiumOre,
						elementiumOreSpawner,
						elementalDesk,
						elementiumGemBlock;
	public static void init() {
		// Construct the blocks
		elementiumOre = new BlockElementiumOre(BlockIDs.elementiumOre)
						.setHardness(2F)
						.setResistance(5F)
						.setLightValue(0.8F)
						.setStepSound(Block.soundStoneFootstep)
						.setBlockName(BlockNames.ELEMENTIUM_ORE_NAME);

		elementiumOreSpawner = new BlockElementiumOreSpawner(BlockIDs.elementiumOreSpawner)
						.setHardness(2F)
						.setResistance(5F)
						.setLightValue(0.8F)
						.setStepSound(Block.soundStoneFootstep)
						.setBlockName(BlockNames.ELEMENTIUM_ORE_SPAWNER_NAME);

		elementalDesk = new BlockElementalDesk(BlockIDs.elementalDesk)
						.setHardness(1F)
						.setStepSound(Block.soundWoodFootstep)
						.setBlockName(BlockNames.ELEMENTAL_DESK_NAME);

		elementiumGemBlock = new BlockElementiumGem(BlockIDs.elementiumGemBlock)
						.setHardness(3.0F)
						.setResistance(10.0F)
						.setStepSound(Block.soundMetalFootstep)
						.setBlockName(BlockNames.ELEMENTIUM_GEM_BLOCK_NAME);

		// Register them in the game
		GameRegistry.registerBlock(elementiumOre, ItemMetadataCompatBlock.class, BlockNames.ELEMENTIUM_ORE_NAME);
		GameRegistry.registerBlock(elementiumOreSpawner, ItemMetadataCompatBlock.class, BlockNames.ELEMENTIUM_ORE_SPAWNER_NAME);
		GameRegistry.registerBlock(elementalDesk, BlockNames.ELEMENTAL_DESK_NAME);
		GameRegistry.registerBlock(elementiumGemBlock, BlockNames.ELEMENTIUM_GEM_BLOCK_NAME);

		// Name the blocks
		LanguageRegistry.addName(elementiumOre, BlockNames.ELEMENTIUM_ORE_DISPLAY_NAME);
		LanguageRegistry.addName(elementiumOreSpawner, BlockNames.ELEMENTIUM_ORE_SPAWNER_DISPLAY_NAME);
		LanguageRegistry.addName(elementalDesk, BlockNames.ELEMENTAL_DESK_DISPLAY_NAME);
		LanguageRegistry.addName(elementiumGemBlock, BlockNames.ELEMENTIUM_GEM_BLOCK_DISPLAY_NAME);
	}
}
