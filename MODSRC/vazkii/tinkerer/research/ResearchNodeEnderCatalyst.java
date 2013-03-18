/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 19 Feb 2013
package vazkii.tinkerer.research;

import vazkii.tinkerer.client.helper.IconHelper.UnboundIcon;
import vazkii.tinkerer.client.helper.IconHelper.UnboundIcon.Spritesheet;
import vazkii.tinkerer.item.ElementalTinkererItems;
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
		setUnboundIcon(new UnboundIcon(Spritesheet.ITEM, ElementalTinkererItems.catalyst, 12 + index - ResearchReference.ID_CATALYST_START));
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
