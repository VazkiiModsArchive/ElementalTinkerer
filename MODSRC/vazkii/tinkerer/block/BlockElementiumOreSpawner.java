/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 24 Dec 2012
package vazkii.tinkerer.block;

import java.awt.Color;

import net.minecraft.world.World;
import vazkii.tinkerer.ElementalTinkerer;
import vazkii.tinkerer.client.handlers.ClientTickHandler;
import vazkii.tinkerer.entity.EntityElementiumGuardian;
import vazkii.tinkerer.reference.EntityReference;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * BlockElementiumOreSpawner
 *
 * Special Block, looks just like Elementium Ore but spawns an
 * Elementium Guardian when broken. Generated alongside the
 * Elementium Ore clusters.
 *
 * @author Vazkii
 */
public class BlockElementiumOreSpawner extends BlockElementiumOre {

	public BlockElementiumOreSpawner(int par1) {
		super(par1);
	}
	
	/** Used to spawn the Guardian, copy of the same method on the BlockSilverfish
	 * class, except this one spawns a different mob with different particles. **/
	@Override
    public void onBlockDestroyedByPlayer(World par1World, int par2, int par3, int par4, int par5) {
        if (!par1World.isRemote) {
            EntityElementiumGuardian entityGuardian = new EntityElementiumGuardian(par1World);
            entityGuardian.setLocationAndAngles(par2 + 0.5D, par3, par4 + 0.5D, 0.0F, 0.0F);
            par1World.spawnEntityInWorld(entityGuardian);
        }

        super.onBlockDestroyedByPlayer(par1World, par2, par3, par4, par5);
	}

}
