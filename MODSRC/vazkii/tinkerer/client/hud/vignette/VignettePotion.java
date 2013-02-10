/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 5 Feb 2013
package vazkii.tinkerer.client.hud.vignette;

import net.minecraft.potion.Potion;
import vazkii.tinkerer.helper.MiscHelper;

/**
 * ViginettePotion
 *
 * A viginette that renders because of the existance and
 * prevailance of a potion effect, it's color is the
 * color of the potion effect.
 *
 * @author Vazkii
 */
public class VignettePotion extends Vignette {

	public int potion;

	public VignettePotion(int potion) {
		this(potion, Potion.potionTypes[potion].getLiquidColor());
	}

	public VignettePotion(int potion, int color) {
		this.potion = potion;
		this.color = color;
	}

	@Override
	public boolean shouldRender() {
		return MiscHelper.getClientPlayer().isPotionActive(potion);
	}
}
