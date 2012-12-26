/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Dec 2012
package vazkii.tinkerer.gui;

import net.minecraft.creativetab.CreativeTabs;
import vazkii.tinkerer.reference.AnnotationConstants;
import vazkii.tinkerer.reference.FormattingCode;
import vazkii.tinkerer.reference.ItemIDs;

/**
 * CreativeTabET
 *
 * The Creative Tab for Elemental Tinkerer.
 *
 * @author Vazkii
 */
public class CreativeTabET extends CreativeTabs {

	public static final CreativeTabs INSTANCE = new CreativeTabET();

	private CreativeTabET() {
		super(AnnotationConstants.MOD_ID);
	}

	@Override
	public String getTranslatedTabLabel() {
		return FormattingCode.ITALICS + AnnotationConstants.MOD_NAME;
	}

	@Override
	public int getTabIconItemIndex() {
		return ItemIDs.elementiumGem;
	}

}
