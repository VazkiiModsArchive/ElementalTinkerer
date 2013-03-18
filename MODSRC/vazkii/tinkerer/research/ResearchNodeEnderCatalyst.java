/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 19 Feb 2013
package vazkii.tinkerer.research;

import vazkii.tinkerer.reference.ResearchReference;

/**
 * ResearchNodeEnderCatalyst
 *
 * A Research node for the ender catalyst items. Requires the
 * passed in requirement and the Ender Absorption node.
 *
 * @author Vazkii
 */
public class ResearchNodeEnderCatalyst extends ResearchNode {

	public ResearchNodeEnderCatalyst(short index, String label, String displayName, ResearchType type) {
		super(index, label, displayName, type);
	}

	@Override
	public boolean isAvailable(PlayerResearch research) {
		short requirement = this.requirement;
		setRequirement(ResearchReference.ID_ENDER_ABSORPTION);
		boolean isEnderAbsorptionGotten = super.isAvailable(research);
		setRequirement(requirement);
		return isEnderAbsorptionGotten && super.isAvailable(research);
	}

}
