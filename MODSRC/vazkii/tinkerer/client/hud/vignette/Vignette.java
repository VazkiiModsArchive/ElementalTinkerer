/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 5 Feb 2013
package vazkii.tinkerer.client.hud.vignette;

/**
 * Viginette
 *
 * A Viginette to be rendered on screen, this renders the viginette
 * texture on screen with a color. It contains a method to check
 * if it should be rendered or not.
 *
 * @author Vazkii
 */
public abstract class Vignette {

	int color = 0;

	public Vignette () { }
	// Regular constructor available if no color needs to be set at construction

	public Vignette(int color) {
		this.color = color;
	}

	public abstract boolean shouldRender();

	/** Gets the color to colorize the viginette render **/
	public int getColor(float partialTicks) {
		return color;
	}
}
