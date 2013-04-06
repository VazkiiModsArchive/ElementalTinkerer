/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 18 Jan 2013
package vazkii.tinkerer.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import vazkii.tinkerer.client.helper.IconHelper;
import vazkii.tinkerer.handler.ConfigurationHandler;
import vazkii.tinkerer.simpleanim.SimpleAnimations;

/**
 * ItemElementiumIngot
 *
 * Elementium Ingot Item. This item is animated.
 *
 * @author Vazkii
 */
public class ItemElementiumIngot extends ItemET {

	public ItemElementiumIngot(int par1) {
		super(par1);
	}

	Icon[] icons = new Icon[8];

	@Override
	public void updateIcons(IconRegister par1IconRegister) {
		for(int i = 0; i < 8; i++)
			icons[i] =  IconHelper.forItem(par1IconRegister, this, i);
	}

	@Override
	public Icon getIconFromDamage(int par1) {
		return !ConfigurationHandler.elementiumIngotAnimate ? icons[0] :
			   icons[SimpleAnimations.ANIMATIONS[SimpleAnimations.ELEMENTIUM_INGOT_ANIM_INDEX].getCurrentValue()];
	}

}
