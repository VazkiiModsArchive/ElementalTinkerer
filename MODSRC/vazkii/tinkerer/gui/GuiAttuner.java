/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 26 Jan 2013
package vazkii.tinkerer.gui;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import org.lwjgl.opengl.GL11;

import vazkii.tinkerer.client.helper.RenderHelper;
import vazkii.tinkerer.helper.ObjectPair;
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.helper.SpellHelper;
import vazkii.tinkerer.magic.Spell;
import vazkii.tinkerer.magic.SpellLibrary;
import vazkii.tinkerer.network.packet.PacketClientSpells;
import vazkii.tinkerer.reference.FormattingCode;
import vazkii.tinkerer.reference.ResourcesReference;

/**
 * GuiAttuner
 *
 * The GUI for the Attuner, where the spells are selected.
 *
 * @author Vazkii
 */
public class GuiAttuner extends GuiScreen {

    int xStart,
	yStart;

    static boolean lookingAtSpells = true;
    static int page = 0; //VAZ_TODO Implement

    Spell spellLooking = null;
    boolean add = false;

    @Override
    public void initGui() {
    	super.initGui();
		xStart = (width - 237) / 2;
		yStart = (height - 124) / 2;
		buttonList.add(new GuiInvisibleButton(0, xStart + 142, yStart + 20, 8, 8));
    }

	@Override
	public void drawScreen(int par1, int par2, float par3) {
        spellLooking = null;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.func_98187_b(ResourcesReference.GUI_ATTUNER_TEXTURE);
        drawTexturedModalRect(xStart, yStart, 0, 0, 237, 124);

        fontRenderer.drawStringWithShadow(lookingAtSpells ? "Spells:" : "Passives:", xStart + 158, yStart + 20, 0xFFFFFF);

        List<Short> spells = lookingAtSpells ? SpellHelper.getAvailableSpells(ResearchHelper.clientResearch)
        						: SpellHelper.getAvailablePassives(ResearchHelper.clientResearch);
        int totalPages = (int) Math.ceil(spells.size() / 12D);
        String pageStr = page + 1 + "/" + totalPages;
        fontRenderer.drawStringWithShadow(pageStr, xStart + 157, yStart + 94, 0xFFFFFF);

        drawSpells : {
            for(int i = 0; i < 3; i++) {
            	for(int j = 0; j < 4; j++) {
            		int index = i * 4 + j;
            		if(spells.size() <= index)
            			break drawSpells;

            		int x = xStart + 140 + j * 18;
            		int y = yStart + 35 + i * 18;
            		Spell spell = lookingAtSpells ? SpellLibrary.allSpells.get(spells.get(index))
            						: SpellLibrary.allPassives.get(spells.get(index));
            		RenderHelper.renderSpellIconWithBackgroundAndFrame(spell, x, y, zLevel);
            		if(par1 >= x && par1 <= x + 18 && par2 >= y && par2 <= y + 18) {
            			spellLooking = spell;
            			add = true;
            		}
            	}
            }
        }

        List<ObjectPair<Point, Short>> spellList = RenderHelper.drawSpellCircle(
        				lookingAtSpells ? SpellHelper.clientSpells.getSpells() : SpellHelper.clientSpells.getPassives(),
        				xStart + 65, yStart + 62,
        				(int) Math.ceil(zLevel), 40, false, SpellHelper.clientSpells, !lookingAtSpells);

        List<Short> equippedSpells = new ArrayList();

        for(ObjectPair<Point, Short> pair : spellList) {
        	short spell = pair.object2;
        	equippedSpells.add(spell);
        	if(par1 >= pair.object1.x - 8 && par1 <= pair.object1.x + 10 && par2 >= pair.object1.y - 8 && par2 <= pair.object1.y + 10) {
        		spellLooking = lookingAtSpells ? SpellLibrary.allSpells.get(spell) : SpellLibrary.allPassives.get(spell);
        		add = false;
        	}
        }

        if(spellLooking != null)
        	RenderHelper.renderTooltip(par1, par2, spellLooking.displayName,
        							!add ? FormattingCode.GRAY + "(Click to Unequip)"
        							: equippedSpells.contains(spellLooking.index)
        							? FormattingCode.RED + "(Already Equipped)"
        							: equippedSpells.size() >= (lookingAtSpells ? 5 : 4) ?
                					FormattingCode.RED + "(Can't Equip, Full)"
                					: FormattingCode.GRAY + "(Click to Equip)");

        if(((GuiInvisibleButton) buttonList.get(0)).isHovered())
        	RenderHelper.renderTooltip(par1, par2, "Display " + (lookingAtSpells ? "Passives" : "Spells"));

		super.drawScreen(par1, par2, par3);
	}

	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		if(spellLooking != null) {
			if(add)
				if(lookingAtSpells)
					SpellHelper.clientSpells.addSpell(spellLooking.index);
				else SpellHelper.clientSpells.addPassive(spellLooking.index);
			else if(lookingAtSpells) SpellHelper.clientSpells.removeSpell(spellLooking.index);
				else SpellHelper.clientSpells.removePassive(spellLooking.index);
			PacketHelper.sendPacketToServer(new PacketClientSpells(SpellHelper.clientSpells));
		}
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		if(par1GuiButton.id == 0)
			lookingAtSpells = !lookingAtSpells;
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
