/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 3 Feb 2013
package vazkii.tinkerer.client.hud;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * HudElement
 *
 * An element of the mod's Heads Up Display (HUD), these get called
 * in the client tick handler on render ticks to render the HUD on
 * screen.
 *
 * @author Vazkii
 */
@SideOnly(Side.CLIENT)
public abstract interface IHudElement {

	/** Should this be rendered? **/
	public boolean shouldRender();

	/** Renders the element **/
	public void render(float partialTicks);

	/** Pass in a client tick update, this
	 * is passed in every tick, regardless of
	 * shouldRender() **/
	public void clientTick();
}
