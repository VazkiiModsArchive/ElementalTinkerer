/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 3 Feb 2013
package vazkii.tinkerer.client.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import vazkii.tinkerer.client.helper.RenderHelper;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.helper.SpellHelper;
import vazkii.tinkerer.reference.EffectReference;

/**
 * HudElementSpellCircle
 *
 * The Spell circle HUD, rendered when the player is holding a
 * wand and has spells.
 *
 * @author Vazkii
 */
public class HudElementSpellCircle implements IHudElement {

	public static final HudElementSpellCircle INSTANCE = new HudElementSpellCircle();

	private HudElementSpellCircle() { }

	@Override
	public boolean shouldRender() {
		return MiscHelper.doesClientPlayerHaveWand();
	}

	@Override
	public void render(float partialTicks) {
		Minecraft mc = MiscHelper.getMc();
		ScaledResolution res = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);

		RenderHelper.drawSpellCircle(SpellHelper.clientSpells.getSpells(), res.getScaledWidth() / 2, res.getScaledHeight() / 2, 0, EffectReference.SPELL_CIRCLE_RADIUS, SpellHelper.clientSpells, false);
	}

	@Override
	public void clientTick() {
		// NO-OP
	}
}
