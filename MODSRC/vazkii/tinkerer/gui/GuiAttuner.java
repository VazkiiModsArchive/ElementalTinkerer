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
    
    Spell spellLooking = null;
    boolean add = false;

    @Override
    public void initGui() {
    	super.initGui();
		xStart = (width - 237) / 2;
		yStart = (height - 124) / 2;
    }
	
	@Override
	public void drawScreen(int par1, int par2, float par3) {
        spellLooking = null;
		int texture = mc.renderEngine.getTexture(ResourcesReference.GUI_ATTUNER_TEXTURE);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(texture);
        drawTexturedModalRect(xStart, yStart, 0, 0, 237, 124);
		
        List<Short> spells = SpellHelper.getAvailableSpells(ResearchHelper.clientResearch);
        drawSpells : {
            for(int i = 0; i < 3; i++) {
            	for(int j = 0; j < 4; j++) {
            		int index = i * 4 + j;
            		if(spells.size() <= index)
            			break drawSpells;
            		
            		int x = xStart + 140 + j * 18;
            		int y = yStart + 35 + i * 18;
            		Spell spell = SpellLibrary.allSpells.get(spells.get(index));
            		RenderHelper.renderSpellIconWithBackgroundAndFrame(spell, x, y, zLevel);
            		if(par1 >= x && par1 <= x + 18 && par2 >= y && par2 <= y + 18) {
            			spellLooking = spell;
            			add = true;
            		}
            	}
            }	
        }
        
        List<ObjectPair<Point,Short>> spellList = RenderHelper.drawSpellCircle(SpellHelper.clientSpells.getSpells(), xStart + 65, yStart + 62, (int) Math.ceil(zLevel), 40, false, SpellHelper.clientSpells);
        List<Short> equippedSpells = new ArrayList();
        
        for(ObjectPair<Point, Short> pair : spellList) {
        	short spell = pair.object2;
        	equippedSpells.add(spell);
        	if(par1 >= pair.object1.x - 8 && par1 <= pair.object1.x + 10 && par2 >= pair.object1.y - 8 && par2 <= pair.object1.y + 10) {
        		spellLooking = SpellLibrary.allSpells.get(spell);
        		add = false;
        	}
        }

        
        
        if(spellLooking != null)
        	RenderHelper.renderTooltip(par1, par2, spellLooking.displayName, 
        							!add ? (FormattingCode.GRAY + "(Click to Unequip)") 
        							: equippedSpells.contains(spellLooking.index) 
        							? (FormattingCode.RED + "(Already Equipped)") 
        							: equippedSpells.size() >= 5 ?
                					(FormattingCode.RED + "(Can't Equip, Full)")
                					: (FormattingCode.GRAY + "(Click to Equip)"));
        
		super.drawScreen(par1, par2, par3);
	}
	
	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		if(spellLooking != null) {
			if(add) SpellHelper.clientSpells.addSpell(spellLooking.index);
			else SpellHelper.clientSpells.removeSpell(spellLooking.index);
			PacketHelper.sendPacketToServer(new PacketClientSpells(SpellHelper.clientSpells));
		}
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
