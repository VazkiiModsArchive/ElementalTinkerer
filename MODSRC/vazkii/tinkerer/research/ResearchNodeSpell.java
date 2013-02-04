/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 26 Jan 2013
package vazkii.tinkerer.research;

import vazkii.tinkerer.reference.ResearchReference;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * ResearchNodeSpell
 *
 * A Research Node which is a spell (requires the Magical Attuning
 * research by default)
 *
 * @author Vazkii
 */
public class ResearchNodeSpell extends ResearchNode {

	public ResearchNodeSpell(short index, String label, String displayName, int spriteIndex, ResearchType type) {
		super(index, ResourcesReference.MAGIC_SPRITESHEET, label, displayName, spriteIndex, type);
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
