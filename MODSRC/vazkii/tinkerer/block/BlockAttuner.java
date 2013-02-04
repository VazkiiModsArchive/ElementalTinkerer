/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 20 Jan 2013
package vazkii.tinkerer.block;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.tinkerer.ElementalTinkerer;
import vazkii.tinkerer.client.handler.ClientTickHandler;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.reference.GuiIDs;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.research.PlayerResearch;
import vazkii.tinkerer.tile.TileEntityAttuner;
import vazkii.tinkerer.tile.TileEntityElementalTinkeringAltar;

/**
 * BlockAttuner
 *
 * The Attuner Block, used to set the various spells and
 * abilities per player.
 *
 * @author Vazkii
 */
public class BlockAttuner extends BlockETContainer {

	public BlockAttuner(int par1) {
		super(par1, ResourcesReference.BLOCK_INDEX_ATTUNER_TOP, Material.iron);
	}

	@Override
	public int getBlockTextureFromSide(int par1) {
		return par1 == 0 || par1 == 1 ? super.getBlockTextureFromSide(par1) : ResourcesReference.BLOCK_INDEX_ATTUNER_GLASS;
	}

	@Override
    public boolean isOpaqueCube() {
        return false;
    }

	@Override
    public boolean renderAsNormalBlock() {
        return false;
    }
	
	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityAttuner();
	}
	
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        player.openGui(ElementalTinkerer.instance, GuiIDs.ID_ATTUNER, world, x, y, z);

        return true;
    }
}
