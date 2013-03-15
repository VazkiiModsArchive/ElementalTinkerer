/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 9 Mar 2013
package vazkii.tinkerer.item;

import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.MinecraftForge;
import vazkii.tinkerer.gui.CreativeTabET;
import vazkii.tinkerer.reference.MiscReference;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * ItemETTool
 *
 * Elemental Tinkerer implementation of ItemTool.
 *
 * @author Vazkii
 */
public class ItemETTool extends ItemTool {

	private static final Block[][] blocksEffectiveness = new Block[][] {
		ItemSpade.blocksEffectiveAgainst,
		ItemPickaxe.blocksEffectiveAgainst,
		ItemAxe.blocksEffectiveAgainst,
	};

	private static final String[] toolClasses = new String[] {
		"shovel", "pickaxe", "axe"
	};

	protected ItemETTool(int par1, int par2, EnumToolMaterial par3EnumToolMaterial, int toolType) {
		super(par1 - MiscReference.ITEM_INDEX_SHIFT, toolType, par3EnumToolMaterial, blocksEffectiveness[toolType]);
		// Pass in accurate IDs, negating the index shift
		setCreativeTab(CreativeTabET.INSTANCE);
		iconIndex = par2;
		MinecraftForge.setToolClass(this, toolClasses[toolType], par3EnumToolMaterial.getHarvestLevel());
	}

	@Override
	public String getTextureFile() {
		return ResourcesReference.ITEMS_SPRITESHEET;
	}
}
