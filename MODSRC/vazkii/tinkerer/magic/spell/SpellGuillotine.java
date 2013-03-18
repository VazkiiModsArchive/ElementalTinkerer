/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Feb 2013
package vazkii.tinkerer.magic.spell;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import vazkii.tinkerer.magic.SpellType;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * SpellGuillotine
 *
 * The Guillotine Spell.
 *
 * @author Vazkii
 */
public class SpellGuillotine extends SpellImpl {

	public SpellGuillotine() {
		super(SpellReference.ID_GUILLOTINE,
				SpellReference.LABEL_GUILLOTINE,
				SpellReference.DISPLAY_NAME_GUILLOTINE,
				SpellType.ACTIVE,
				5); // Pure
		bindNode(ResearchReference.ID_GUILLOTINE);
	}

	@Override
	public boolean castOnEntity(EntityPlayer player, boolean bonus, EntityLiving entity) {
		if(entity.getHealth() == 1 && (entity instanceof EntityZombie || entity instanceof EntitySkeleton || entity instanceof EntityPlayer || entity instanceof EntityCreeper)) {
			int damage = 0;
			NBTTagCompound specialCompound = null;
			if(entity instanceof EntitySkeleton && ((EntitySkeleton)entity).getSkeletonType() == 1) // Wither skeleton
				damage = 1;
			if(entity instanceof EntityZombie)
				damage = 2;
			if(entity instanceof EntityCreeper)
				damage = 4;
			if(entity instanceof EntityPlayer) {
				damage = 3;
				specialCompound = new NBTTagCompound();
				specialCompound.setString("SkullOwner", ((EntityPlayer)entity).username);
			}

			ItemStack stack = new ItemStack(Item.skull.itemID, 1, damage);
			if(specialCompound != null)
				stack.setTagCompound(specialCompound);

			entity.attackEntityFrom(DamageSource.magic, 1);
			EntityItem item = new EntityItem(entity.worldObj, entity.posX, entity.posY, entity.posZ, stack);
			entity.worldObj.spawnEntityInWorld(item);
			return true;
		}

		return false;
	}

	@Override
	public int getCooldown(EntityPlayer player) {
		return SpellReference.COOLDOWN_GUILLOUTINE;
	}

}
