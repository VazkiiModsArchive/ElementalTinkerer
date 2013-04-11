/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 8 Apr 2013
package vazkii.tinkerer.magic.spell;

import net.minecraft.entity.player.EntityPlayer;
import vazkii.tinkerer.helper.Element;
import vazkii.tinkerer.magic.SpellType;
import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.SpellReference;

/**
 * SpellClearSky
 *
 * The Clear Sky spell.
 *
 * @author Vazkii
 */
public class SpellClearSky extends SpellImpl {

	public SpellClearSky() {
		super(SpellReference.ID_CLEAR_SKY,
				SpellReference.LABEL_CLEAR_SKY,
				SpellReference.DISPLAY_NAME_CLEAR_SKY,
				SpellType.ACTIVE,
				Element.AIR.ordinal());
		bindNode(ResearchReference.ID_CLEAR_SKY);
	}

	@Override
	public boolean cast(EntityPlayer player, boolean bonus) {
		if(!player.worldObj.getWorldInfo().isRaining()) {
			player.addChatMessage("It's not raining!");
			return false;
		} else player.worldObj.getWorldInfo().setRaining(false);

		return true;
	}

	@Override
	public int getCooldown(EntityPlayer player) {
		return SpellReference.COOLDOWN_CLEAR_SKY;
	}

}
