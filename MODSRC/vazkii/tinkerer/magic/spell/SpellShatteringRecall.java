/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 24 Feb 2013
package vazkii.tinkerer.magic.spell;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.item.ItemLocationGem;
import vazkii.tinkerer.magic.SpellType;
import vazkii.tinkerer.reference.FormattingCode;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.ResourcesReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * SpellShatteringRecall
 *
 * The Shattering Recall spell.
 *
 * @author Vazkii
 */
public class SpellShatteringRecall extends SpellImpl {

	public SpellShatteringRecall() {
		super(SpellReference.ID_SHATTERING_RECALL,
				SpellReference.LABEL_SHATTERING_RECALL,
				SpellReference.DISPLAY_NAME_SHATTERING_RECALL,
				ResourcesReference.MAGIC_INDEX_SHATTERING_RECALL,
				SpellType.ACTIVE,
				5); // Pure
		bindNode(ResearchReference.ID_SHATTERING_RECALL);
	}

	@Override
	public boolean cast(EntityPlayer player, boolean bonus) {
		int foundAt = -1;
		for(int i = 0; i < InventoryPlayer.getHotbarSize(); i++) {
			ItemStack stack = player.inventory.getStackInSlot(i);
			if(stack != null && stack.getItem() != null && stack.getItem() instanceof ItemLocationGem
					&& ItemLocationGem.isAttuned(stack) && ItemLocationGem.getDim(stack) == player.dimension) {
				if(foundAt != -1) {
					foundAt = -1;
					PacketHelper.sendMessageToPlayer(player, FormattingCode.RED + "You have more than one Gem of Locating in your hotbar!");
					break;
				}
				foundAt = i;
			}
		}
		if(foundAt != -1) {
			ItemStack stack = player.inventory.getStackInSlot(foundAt);
			int x = ItemLocationGem.getX(stack);
			int y = ItemLocationGem.getY(stack);
			int z = ItemLocationGem.getZ(stack);
			if(player instanceof EntityPlayerMP) {
				player.inventory.decrStackSize(foundAt, 1);
				((EntityPlayerMP)player).playerNetServerHandler.setPlayerLocation(x, y + 1.6, z, player.rotationYaw, player.rotationPitch);
				player.attackEntityFrom(DamageSource.magic, 4);
			}
			return true;
		}

		return false;
	}

	@Override
	public int getCooldown(EntityPlayer player) {
		return SpellReference.COOLDOWN_SHATTERING_RECALL;
	}

}
