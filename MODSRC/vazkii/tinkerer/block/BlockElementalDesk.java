/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 26 Dec 2012
package vazkii.tinkerer.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import vazkii.tinkerer.ElementalTinkerer;
import vazkii.tinkerer.reference.GuiIDs;
import vazkii.tinkerer.reference.RenderIDs;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.tile.TileEntityElementalDesk;

/**
 * BlockElementalDesk
 *
 * Elemental Desk block. It has a tile entity, a gui and some
 * other misc things.
 *
 * @author Vazkii
 */
public class BlockElementalDesk extends BlockETContainer {

	/** Metadata correspondent to the rotation of the player placing
	 * the block. **/
	public int[] metaPerRotation = new int[] {
			3, 2, 1, 4
	};

	public BlockElementalDesk(int par1) {
		super(par1, ResourcesReference.BLOCK_INDEX_TRANSPARENT, Material.wood);
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving) {
		super.onBlockPlacedBy(par1World, par2, par3, par4, par5EntityLiving);
        int metaToGet = MathHelper.floor_double(par5EntityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        par1World.setBlockMetadataWithNotify(par2, par3, par4, metaPerRotation[metaToGet]);
	}

    @Override
    public TileEntity createNewTileEntity(World var1) {
        return new TileEntityElementalDesk();
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
	public int getRenderType() {
		return RenderIDs.elementalDesk;
	}

	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (!world.isRemote) {
            TileEntityElementalDesk tile = (TileEntityElementalDesk) world.getBlockTileEntity(x, y, z);

            if (tile != null)
                player.openGui(ElementalTinkerer.instance, GuiIDs.ID_ELEMENTAL_DESK, world, x, y, z);
        }

        return true;
    }

	@Override
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6) {
            TileEntityElementalDesk tile = (TileEntityElementalDesk)par1World.getBlockTileEntity(par2, par3, par4);

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
                            EntityItem item = new EntityItem(par1World, (par2 + xOffset), (par3 + yOffset), (par4 + zOffset), new ItemStack(stack.itemID, dropSize, stack.getItemDamage()));

                            if (stack.hasTagCompound())
                                item.func_92014_d().setTagCompound((NBTTagCompound)stack.getTagCompound().copy());

                            item.motionX = ((float)par1World.rand.nextGaussian() / 20);
                            item.motionY = ((float)par1World.rand.nextGaussian() / 20 + 0.2F);
                            item.motionZ = ((float)par1World.rand.nextGaussian() / 20);
                            par1World.spawnEntityInWorld(item);
                        }
                    }
                }
            }

        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }
}
