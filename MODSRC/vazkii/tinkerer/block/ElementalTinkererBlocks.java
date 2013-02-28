/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Dec 2012
package vazkii.tinkerer.block;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.item.ElementalTinkererItems;
import vazkii.tinkerer.item.ItemMetadataCompatBlock;
import vazkii.tinkerer.reference.BlockIDs;
import vazkii.tinkerer.reference.BlockNames;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.research.ResearchLibrary;
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
						elementiumGemBlock,
						elementalistTinkeringAltar,
						catalystCapsule,
						attuner,
						voidGateway,
						// RESERVED
						scavenger,
						glowstoneAir;

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

		elementalistTinkeringAltar = new BlockElementalistTinkeringAltar(BlockIDs.elementalistTinkeringAltar)
						.setHardness(5F)
						.setResistance(2000F)
						.setStepSound(Block.soundMetalFootstep)
						.setBlockName(BlockNames.ELEMENTALIST_TINKERING_ALTAR_NAME);

		catalystCapsule = new BlockCatalystCapsule(BlockIDs.catalystCapsule)
						.setHardness(8F)
						.setResistance(2000F)
						.setLightValue(0.8F)
						.setStepSound(Block.soundMetalFootstep)
						.setBlockName(BlockNames.CATALYST_CAPSULE_NAME);

		attuner = new BlockAttuner(BlockIDs.attuner)
						.setHardness(8F)
						.setResistance(2000F)
						.setLightValue(1F)
						.setStepSound(Block.soundMetalFootstep)
						.setBlockName(BlockNames.ATTUNER_NAME);

		voidGateway = new BlockVoidGateway(BlockIDs.voidGateway)
						.setHardness(8F)
						.setResistance(2000F)
						.setLightValue(0.2F)
						.setStepSound(Block.soundMetalFootstep)
						.setBlockName(BlockNames.VOID_GATEWAY_NAME);

		scavenger = new BlockScavenger(BlockIDs.scavenger)
						.setHardness(1.3F)
						.setResistance(6F)
						.setStepSound(Block.soundStoneFootstep)
						.setBlockName(BlockNames.SCAVENGER_NAME);

		glowstoneAir = new BlockGlowstoneAir(BlockIDs.glowstoneAir)
						.setBlockName(BlockNames.GLOWSTONE_AIR_NAME);

		// Register them in the game
		GameRegistry.registerBlock(elementiumOre, ItemMetadataCompatBlock.class, BlockNames.ELEMENTIUM_ORE_NAME);
		GameRegistry.registerBlock(elementiumOreSpawner, ItemMetadataCompatBlock.class, BlockNames.ELEMENTIUM_ORE_SPAWNER_NAME);
		GameRegistry.registerBlock(elementalDesk, BlockNames.ELEMENTAL_DESK_NAME);
		GameRegistry.registerBlock(elementiumGemBlock, BlockNames.ELEMENTIUM_GEM_BLOCK_NAME);
		GameRegistry.registerBlock(elementalistTinkeringAltar, BlockNames.ELEMENTALIST_TINKERING_ALTAR_NAME);
		GameRegistry.registerBlock(catalystCapsule, BlockNames.CATALYST_CAPSULE_NAME);
		GameRegistry.registerBlock(attuner, BlockNames.ATTUNER_NAME);
		GameRegistry.registerBlock(voidGateway, BlockNames.VOID_GATEWAY_NAME);
		GameRegistry.registerBlock(scavenger, BlockScavenger.ItemScavenger.class, BlockNames.SCAVENGER_NAME);
		GameRegistry.registerBlock(glowstoneAir, BlockNames.GLOWSTONE_AIR_NAME);

		// Name the blocks
		LanguageRegistry.addName(elementiumOre, BlockNames.ELEMENTIUM_ORE_DISPLAY_NAME);
		LanguageRegistry.addName(elementiumOreSpawner, BlockNames.ELEMENTIUM_ORE_SPAWNER_DISPLAY_NAME);
		LanguageRegistry.addName(elementalDesk, BlockNames.ELEMENTAL_DESK_DISPLAY_NAME);
		LanguageRegistry.addName(elementiumGemBlock, BlockNames.ELEMENTIUM_GEM_BLOCK_DISPLAY_NAME);
		LanguageRegistry.addName(elementalistTinkeringAltar, BlockNames.ELEMENTALIST_TINKERING_ALTAR_DISPLAY_NAME);
		LanguageRegistry.addName(catalystCapsule, BlockNames.CATALYST_CAPSULE_DISPLAY_NAME);
		LanguageRegistry.addName(attuner, BlockNames.ATTUNER_DISPLAY_NAME);
		LanguageRegistry.addName(voidGateway, BlockNames.VOID_GATEWAY_DISPLAY_NAME);
		LanguageRegistry.addName(scavenger, BlockNames.SCAVENGER_DISPLAY_NAME);
		LanguageRegistry.addName(glowstoneAir, BlockNames.GLOWSTONE_AIR_NAME);

		// Add the items to the researches
		ResearchHelper.setIconicItem(new ItemStack(elementiumOre, 1, -1), ResearchReference.ID_ELEMENTIUM_ORE);
		ResearchHelper.setIconicItem(new ItemStack(elementalDesk), ResearchReference.ID_ELEMENTAL_DESK);
		ResearchHelper.setIconicItem(new ItemStack(catalystCapsule), ResearchReference.ID_CATALYST_CAPSULE);
		ResearchHelper.setIconicItem(new ItemStack(voidGateway), ResearchReference.ID_VOID_GATEWAY);
	}

	public static void initBlockRecipes() {
		// Elemental Desk Recipe
		CraftingManager.getInstance().func_92051_a(new ItemStack(elementalDesk),
				"GEG", "BBB", "P P",
				'G', Item.ingotGold,
				'E', ElementalTinkererItems.elementiumGem,
				'B', ElementalTinkererItems.elementalBark,
				'P', new ItemStack(Block.planks, 1, -1));
		ResearchLibrary.allNodes.get(ResearchReference.ID_ELEMENTAL_DESK).bindLatestCraftingRecipe();

		// Elementium Block Recipe
		CraftingManager.getInstance().func_92051_a(new ItemStack(elementiumGemBlock),
				"GGG", "GGG", "GGG",
				'G', ElementalTinkererItems.elementiumGem);

		// Elementalist Tinkering Altar Recipe
		CraftingManager.getInstance().func_92051_a(new ItemStack(elementalistTinkeringAltar),
				"GIG", "GCG", "GIG",
				'C', Block.workbench,
				'G', ElementalTinkererItems.elementiumGem,
				'I', Block.blockSteel);
		ResearchLibrary.allNodes.get(ResearchReference.ID_ELEMENTAL_TINKERING).bindLatestCraftingRecipe();
	}
}
