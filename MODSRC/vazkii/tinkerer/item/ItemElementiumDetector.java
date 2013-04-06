/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 2 Feb 2013
package vazkii.tinkerer.item;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Icon;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import vazkii.tinkerer.client.helper.IconHelper;
import vazkii.tinkerer.entity.EntityElementiumLocator;
import vazkii.tinkerer.helper.ElementiumOreGenerationHelper;
import vazkii.tinkerer.helper.MathHelper;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.simpleanim.SimpleAnimations;

/**
 * ItemElementiumDetector
 *
 * The Elementium Detector item that "glows"
 * if there's elementium ore above or under the
 * player.
 *
 * @author Vazkii
 */
public class ItemElementiumDetector extends ItemET {

	public ItemElementiumDetector(int par1) {
		super(par1);
		setMaxDamage(64);
	}

	Icon[] icons = new Icon[2];

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		Vec3 vector = getParticleVector(par2World, par3EntityPlayer);
		if(vector != null && !par2World.isRemote) {
			List entitiesAround = par2World.getEntitiesWithinAABB(EntityElementiumLocator.class, AxisAlignedBB.getBoundingBox(par3EntityPlayer.posX - 3, par3EntityPlayer.posY - 3, par3EntityPlayer.posZ - 3, par3EntityPlayer.posX + 3, par3EntityPlayer.posY + 3, par3EntityPlayer.posZ + 3));

			if(entitiesAround.isEmpty()) {
				EntityElementiumLocator entity = new EntityElementiumLocator(par2World);
				entity.setLocationAndAngles(par3EntityPlayer.posX, par3EntityPlayer.posY + 1.6, par3EntityPlayer.posZ, 0, 0);
				entity.bindVector(vector);
				par2World.spawnEntityInWorld(entity);

				par1ItemStack.damageItem(1, par3EntityPlayer);
			}
		}

		return par1ItemStack;
	}


	public Vec3 getParticleVector(World world, EntityPlayer player) {
		Vec3 closest = null;

		for(Integer x : ElementiumOreGenerationHelper.veins.keySet()) {
			Map<Integer, ChunkCoordinates> map = ElementiumOreGenerationHelper.veins.get(x);
			for(Integer z : map.keySet()) {
				ChunkCoordinates vein = map.get(z);
				Vec3 vector = Vec3.createVectorHelper(vein.posX, 0, vein.posZ);
				double closestDistance = closest == null ? Double.MAX_VALUE : MathHelper.pointDistancePlane(closest.xCoord, closest.zCoord, player.posX, player.posZ);
				double currentDistance = MathHelper.pointDistancePlane(vector.xCoord, vector.zCoord, player.posX, player.posZ);

				if(currentDistance < closestDistance)
					closest = vector;
			}
		}

		if(closest != null)
			System.out.println("CLOSEST: " + closest);

		return closest;
	}

	@Override
	public void updateIcons(IconRegister par1IconRegister) {
		for(int i = 0; i < 2; i++)
			icons[i] =  IconHelper.forItem(par1IconRegister, this, i);
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		return EnumRarity.uncommon;
	}

	@Override
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	public boolean shouldRotateAroundWhenRendering() {
		return true;
	}

	@Override
	public boolean isFull3D() {
		return true;
	}

	@Override
	public Icon getIconFromDamageForRenderPass(int par1, int par2) {
		return icons[par2];
	}

	static final float hue = 280F / 360F; // Purple tone

	@Override
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
		if(par2 == 0)
			return super.getColorFromItemStack(par1ItemStack, par2);

		float saturation = (float) SimpleAnimations.ANIMATIONS[SimpleAnimations.ELEMENTIUM_DETECTOR_ANIM_INDEX].getCurrentValue() / (float) ResourcesReference.ANIM_MAX_ELEMENTIUM_DETECTOR;
		return Color.HSBtoRGB(hue, saturation, 0.9F);
	}
}
