/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 7 Apr 2013
package vazkii.tinkerer.magic.spell;

import net.minecraft.entity.player.EntityPlayer;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.magic.SpellType;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * SpellRainCalling
 *
 * The Rain Calling Spell.
 *
 * @author Vazkii
 */
public class SpellRainCalling extends SpellImpl {

	public SpellRainCalling() {
		super(SpellReference.ID_RAIN_CALLING,
				SpellReference.LABEL_RAIN_CALLING,
				SpellReference.DISPLAY_NAME_RAIN_CALLING,
				SpellType.CHARGE,
				Element.WATER.ordinal());
		bindNode(ResearchReference.ID_RAIN_CALLING);
	}

	@Override
	public boolean cast(EntityPlayer player, boolean bonus) {
		if(player.worldObj.getWorldInfo().isRaining()) {
			player.addChatMessage("It's already raining!");
			return false;
		} else player.worldObj.getWorldInfo().setRaining(true);

		return true;
	}

	@Override
	public int getCooldown(EntityPlayer player) {
		return SpellReference.COOLDOWN_RAIN_CALLING;
	}

}
