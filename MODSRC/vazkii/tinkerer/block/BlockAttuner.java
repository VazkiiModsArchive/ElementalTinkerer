/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 20 Jan 2013
package vazkii.tinkerer.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import vazkii.tinkerer.ElementalTinkerer;
import vazkii.tinkerer.client.helper.IconHelper;
import vazkii.tinkerer.reference.GuiIDs;
import vazkii.tinkerer.tile.TileEntityAttuner;

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
		super(par1, Material.iron);
	}

	Icon[] icons = new Icon[2];

	@Override
	public void func_94332_a(IconRegister par1IconRegister) {
		for(int i = 0; i < 2; i++)
			icons[i] = IconHelper.forBlock(par1IconRegister, this, i);
	}

	@Override
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2) {
		return par1 == 0 || par1 == 1 ? icons[1] : icons[0];
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
