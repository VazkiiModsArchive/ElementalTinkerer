/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 10 Jan 2013
package vazkii.tinkerer.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

/**
 * GuiInvisibleButton
 *
 * A Gui Button that doesn't render the Button.
 *
 * @author Vazkii
 */
public class GuiInvisibleButton extends GuiButton {

	public GuiInvisibleButton(int par1, int par2, int par3, int par4, int par5) {
		super(par1, par2, par3, par4, par5, "");
	}

	/** Helper method to get if the button is being hovered. Used
	 * to help drawing tooltips.
	 */
	public boolean isHovered() {
		return getHoverState(field_82253_i) == 2;
 	}

	@Override
	public void drawButton(Minecraft par1Minecraft, int par2, int par3) {
		field_82253_i = par2 >= xPosition && par3 >= yPosition && par2 < xPosition + width && par3 < yPosition + height;
	}

}
