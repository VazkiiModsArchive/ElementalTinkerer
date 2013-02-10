/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 5 Feb 2013
package vazkii.tinkerer.client.hud.vignette;

import vazkii.tinkerer.helper.MiscHelper;

/**
 * ViginetteLowHealth
 *
 * A Viginette that renders if the client player's
 * health is low (under 2)
 *
 * @author Vazkii
 */
public class VignetteLowHealth extends Vignette {

	public VignetteLowHealth() {
		super(0xFF0000);
	}

	@Override
	public boolean shouldRender() {
		return MiscHelper.getClientPlayer().getHealth() <= 4;
	}
}
