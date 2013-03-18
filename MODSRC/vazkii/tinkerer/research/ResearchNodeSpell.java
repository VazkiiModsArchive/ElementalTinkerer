/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 26 Jan 2013
package vazkii.tinkerer.research;

import vazkii.tinkerer.client.helper.IconHelper.UnboundIcon;
import vazkii.tinkerer.client.helper.IconHelper.UnboundIcon.Spritesheet;
import vazkii.tinkerer.magic.Spell;
import vazkii.tinkerer.magic.SpellLibrary;
import vazkii.tinkerer.reference.ResearchReference;

/**
 * ResearchNodeSpell
 *
 * A Research Node which is a spell (requires the Magical Attuning
 * research by default)
 *
 * @author Vazkii
 */
public class ResearchNodeSpell extends ResearchNode {

	public ResearchNodeSpell(short index, String label, String displayName, ResearchType type, short spell, boolean passive) {
		super(index, label, displayName, type);
		Spell spellObj = passive ? SpellLibrary.allPassives.get(spell) : SpellLibrary.allSpells.get(spell);
		setUnboundIcon(new UnboundIcon(Spritesheet.MAGIC, spellObj));
	}

	@Override
	public boolean isAvailable(PlayerResearch research) {
		short requirement = this.requirement;
		setRequirement(ResearchReference.ID_ATTUNER);
		boolean isAttunerGotten = super.isAvailable(research);
		setRequirement(requirement);
		return isAttunerGotten && super.isAvailable(research);
	}
}
