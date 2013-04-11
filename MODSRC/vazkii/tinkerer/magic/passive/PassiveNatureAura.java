/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 10 Feb 2013
package vazkii.tinkerer.magic.passive;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * PassiveNatureAura
 *
 * The Nature Aura passive.
 *
 * @author Vazkii
 */
public class PassiveNatureAura extends PassiveImpl {

	public PassiveNatureAura() {
		super(SpellReference.PID_NATURE_AURA,
				SpellReference.LABEL_NATURE_AURA,
				SpellReference.DISPLAY_NAME_NATURE_AURA,
				Element.EARTH.ordinal());
		bindNode(ResearchReference.ID_NATURE_AURA);
	}

	@Override
	public void cast(EntityPlayer player) {
		int x = (int) player.posX;
		int y = (int) player.posY - 1;
		int z = (int) player.posZ;
		if(player.worldObj.getBlockId(x, y, z) == Block.dirt.blockID)
			player.worldObj.setBlock(x, y, z, Block.grass.blockID, 0, 2);
	}
}
