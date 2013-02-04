/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 29 Jan 2013
package vazkii.tinkerer.magic;

import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.reference.ResourcesReference;
import net.minecraft.client.renderer.RenderEngine;
import net.minecraft.potion.Potion;

/**
 * PotionET
 *
 * A potion effect from Elemental Tinkerer. Makes most of the important
 * methods visible and allows for setting a custom spritesheet.
 *
 * @author Vazkii
 */
public class PotionET extends Potion {

	boolean useCustomSpritesheet;
	
	public PotionET(int par1, boolean par2, int par3) {
		this(par1, par2, par3, true);
	}
	
	public PotionET(int par1, boolean par2, int par3, boolean useCustomSpritesheet) {
		super(par1, par2, par3);
		this.useCustomSpritesheet = useCustomSpritesheet;
	}
	
	@Override
	public PotionET setPotionName(String par1Str) {
		return (PotionET) super.setPotionName(par1Str);
	}
	
	@Override
	public PotionET setEffectiveness(double par1) {
		return (PotionET) super.setEffectiveness(par1);
	}
	
	public PotionET setIconIndex(int par1) {
		return (PotionET) super.setIconIndex(par1 % 8 * 8, par1 / 8);
	}
	
	@Override
	public int getStatusIconIndex() {
		if(useCustomSpritesheet) {
			RenderEngine render = MiscHelper.getMc().renderEngine;
			render.bindTexture(render.getTexture(ResourcesReference.POTIONS_SPRITESHEET));
		}
		return super.getStatusIconIndex();
	}
}
