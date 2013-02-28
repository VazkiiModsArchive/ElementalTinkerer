/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 26 Feb 2013
package vazkii.tinkerer.block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import vazkii.tinkerer.ElementalTinkerer;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * BlockGlowstoneAir
 *
 * The Glowstone Air block, it acts as air but has a light value.
 *
 * @author Vazkii
 */
public class BlockGlowstoneAir extends BlockET {

	public BlockGlowstoneAir(int par1) {
		super(par1, ResourcesReference.BLOCK_INDEX_TRANSPARENT, Material.air);
		setLightValue(0.85F);
		setBlockBounds(0, 0, 0, 0, 0, 0);
		setTickRandomly(true);
	}

	@Override
	public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		if(par5Random.nextFloat() < 0.015F)
			ElementalTinkerer.proxy.spawnGlowstoneAirParticle(par1World, par2 + 0.5, par3 + 0.5, par4 + 0.5, (par5Random.nextFloat() - 0.5) / 10, par5Random.nextFloat() / 20, (par5Random.nextFloat() - 0.5) / 10);
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
		int meta = par1World.getBlockMetadata(par2, par3, par4);
		if(meta != 0) {
			setAt(par1World, par2 - 1, par3, par4, meta - 1);
			setAt(par1World, par2 + 1, par3, par4, meta - 1);

			setAt(par1World, par2, par3 - 1, par4, meta - 1);
			setAt(par1World, par2, par3 + 1, par4, meta - 1);

			setAt(par1World, par2, par3, par4 - 1, meta - 1);
			setAt(par1World, par2, par3, par4 + 1, meta - 1);

			// Just in case...
			par1World.setBlockMetadata(par2, par3, par4, 0);

			ElementalTinkerer.proxy.spawnColoredPortalParticle(new Color(255, 215, 0), par1World, par2, par3, par4, par1World.rand.nextFloat(), par1World.rand.nextFloat(), par1World.rand.nextFloat());
		}
	}

	private void setAt(World world, int x, int y, int z, int meta) {
		if(world.isAirBlock(x, y, z) && world.getBlockId(x, y, z) != blockID) {
			if(!world.isRemote)
				world.setBlockAndMetadata(x, y, z, blockID, meta);
			world.scheduleBlockUpdate(x, y, z, blockID, 10);
		}
	}

	@Override
	public boolean isBlockNormalCube(World world, int x, int y, int z) {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	boolean registerInCreative() {
		return false;
	}

	@Override
	public boolean canDragonDestroy(World world, int x, int y, int z) {
		return false;
	}

	@Override
	public boolean canCollideCheck(int par1, boolean par2) {
		return false;
	}

	@Override
	public boolean canBeReplacedByLeaves(World world, int x, int y, int z) {
		return true;
	}

	@Override
	public boolean canDropFromExplosion(Explosion par1Explosion) {
		return false;
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
		return new ArrayList(); // Empty List
	}

	@Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
        return null;
    }

	@Override
	public boolean isAirBlock(World world, int x, int y, int z) {
		return true;
	}
}
