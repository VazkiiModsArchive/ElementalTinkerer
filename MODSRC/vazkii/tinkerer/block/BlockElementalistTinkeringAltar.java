/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 13 Jan 2013
package vazkii.tinkerer.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import vazkii.tinkerer.ElementalTinkerer;
import vazkii.tinkerer.client.helper.IconHelper;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.reference.GuiIDs;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.research.PlayerResearch;
import vazkii.tinkerer.tile.TileEntityElementalTinkeringAltar;

/**
 * BlockElementalistTinkeringAltar
 *
 * The Elementalist Tinkering Altar block. This class handles
 * the texture, sided sprites and some important things
 * relating to the tile entity.
 *
 * @author Vazkii
 */
public class BlockElementalistTinkeringAltar extends BlockETContainer {

	public BlockElementalistTinkeringAltar(int par1) {
		super(par1, Material.rock);
	}

	Icon[] icons = new Icon[6];

	@Override
	public void func_94332_a(IconRegister par1IconRegister) {
		for(int i = 0; i < 6; i++)
			icons[i] = IconHelper.forBlock(par1IconRegister, this, i);
	}

	@Override
	public Icon getBlockTextureFromSideAndMetadata(int par1, int par2) {
		return icons[par1];
	}

	@Override
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
            TileEntityElementalTinkeringAltar tile = (TileEntityElementalTinkeringAltar)par1World.getBlockTileEntity(par2, par3, par4);

            if (tile != null) {
                for (int i = 0; i < tile.getSizeInventory(); ++i) {
                    ItemStack stack = tile.getStackInSlot(i);

                    if (stack != null) {
                        float xOffset = par1World.rand.nextFloat() * 0.8F + 0.1F;
                        float yOffset = par1World.rand.nextFloat() * 0.8F + 0.1F;
                        float zOffset = par1World.rand.nextFloat() * 0.8F + 0.1F;

                        while (stack.stackSize > 0) {
                            int dropSize = par1World.rand.nextInt(21) + 10;

                            if (dropSize > stack.stackSize)
                                dropSize = stack.stackSize;

                            stack.stackSize -= dropSize;
                            EntityItem item = new EntityItem(par1World, par2 + xOffset, par3 + yOffset, par4 + zOffset, new ItemStack(stack.itemID, dropSize, stack.getItemDamage()));

                            if (stack.hasTagCompound())
                                item.getEntityItem().setTagCompound((NBTTagCompound)stack.getTagCompound().copy());

                            item.motionX = (float)par1World.rand.nextGaussian() / 20;
                            item.motionY = (float)par1World.rand.nextGaussian() / 20 + 0.2F;
                            item.motionZ = (float)par1World.rand.nextGaussian() / 20;
                            par1World.spawnEntityInWorld(item);
                        }
                    }
                }
            }

        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (!world.isRemote) {
            TileEntityElementalTinkeringAltar tile = (TileEntityElementalTinkeringAltar) world.getBlockTileEntity(x, y, z);

            if(tile != null) {
        		player.openGui(ElementalTinkerer.instance, GuiIDs.ID_ELEMENTALIST_TINKERING_ALTAR, world, x, y, z);
        		PlayerResearch research = ResearchHelper.getResearchDataForPlayer(player.username);
        		if(research.isResearchCompleted(ResearchReference.ID_ELEMENTIUM_INGOT))
        			ResearchHelper.formulateResearchNode(ResearchReference.ID_CATALYST_CAPSULE, player, ResearchReference.CATEGORY_NAME_PURE);
            }
        }

        return true;
    }

	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileEntityElementalTinkeringAltar();
	}
}
