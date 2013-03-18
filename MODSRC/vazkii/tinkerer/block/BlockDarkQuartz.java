/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 18 Mar 2013
package vazkii.tinkerer.block;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import vazkii.tinkerer.client.helper.IconHelper;

/**
 * BlockDarkQuartz
 *
 * The Dark Quartz block. It acts like the regular Quartz block.
 * The code is copied from the BlockQuartz class. It's the same
 * thing.
 *
 * @author Vazkii
 */
public class BlockDarkQuartz extends BlockET {
	private static final String[] field_94418_b = new String[] {"darkQuartz0", "chiseledDarkQuartz0", "pillarDarkQuartz0", null, null};
	private Icon[] field_94419_c;
	private Icon field_94414_cO;
	private Icon field_94415_cP;
	private Icon field_94416_cQ;
	private Icon field_94417_cR;

	public BlockDarkQuartz(int par1) {
		super(par1, Material.rock);
	}

	@Override
    public Icon getBlockTextureFromSideAndMetadata(int par1, int par2) {
        if (par2 != 2 && par2 != 3 && par2 != 4) {
            if (par1 != 1 && (par1 != 0 || par2 != 1)) {
                if (par1 == 0)
                	return field_94417_cR;
                else {
                    if (par2 < 0 || par2 >= field_94419_c.length)
                        par2 = 0;

                    return field_94419_c[par2];
                }
            }
            else return par2 == 1 ? field_94414_cO : field_94416_cQ;
        }
        else return par2 == 2 && (par1 == 1 || par1 == 0) ? field_94415_cP : par2 == 3 && (par1 == 5 || par1 == 4) ? field_94415_cP : par2 == 4 && (par1 == 2 || par1 == 3) ? field_94415_cP : field_94419_c[par2];
    }

	@Override
	public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9) {
        if (par9 == 2) {
            switch (par5) {
                case 0:
                case 1:
                    par9 = 2;
                    break;
                case 2:
                case 3:
                    par9 = 4;
                    break;
                case 4:
                case 5:
                    par9 = 3;
            }
        }

        return par9;
    }


	@Override
	public int damageDropped(int par1) {
		return par1 != 3 && par1 != 4 ? par1 : 2;
	}

	@Override
	protected ItemStack createStackedBlock(int par1) {
		return par1 != 3 && par1 != 4 ? super.createStackedBlock(par1) : new ItemStack(blockID, 1, 2);
	}

	@Override
	public int getRenderType() {
		return 39;
	}

	@Override
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
		par3List.add(new ItemStack(par1, 1, 2));
	}

	@Override
	public void func_94332_a(IconRegister par1IconRegister) {
		field_94419_c = new Icon[field_94418_b.length];

		for (int i = 0; i < field_94419_c.length; ++i) {
			if (field_94418_b[i] == null)
				field_94419_c[i] = field_94419_c[i - 1];
			else field_94419_c[i] = IconHelper.forName(par1IconRegister, field_94418_b[i]);
		}

		field_94416_cQ = IconHelper.forName(par1IconRegister, "darkQuartz1");
		field_94414_cO = IconHelper.forName(par1IconRegister, "chiseledDarkQuartz1");
		field_94415_cP = IconHelper.forName(par1IconRegister, "pillarDarkQuartz1");
		field_94417_cR = IconHelper.forName(par1IconRegister, "darkQuartz1");
	}
}
