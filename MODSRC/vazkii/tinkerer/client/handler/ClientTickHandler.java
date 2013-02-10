/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Dec 2012
package vazkii.tinkerer.client.handler;

import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedList;

import vazkii.tinkerer.client.hud.HudElementSpellCircle;
import vazkii.tinkerer.client.hud.HudElementSpellTooltip;
import vazkii.tinkerer.client.hud.HudElementVignette;
import vazkii.tinkerer.client.hud.IHudElement;
import vazkii.tinkerer.handler.PlayerSpellUpdateHandler;
import vazkii.tinkerer.helper.MiscHelper;
import vazkii.tinkerer.helper.PacketHelper;
import vazkii.tinkerer.helper.ResearchHelper;
import vazkii.tinkerer.helper.SpellHelper;
import vazkii.tinkerer.lightning.LightningBolt;
import vazkii.tinkerer.reference.AnnotationConstants;
import vazkii.tinkerer.simpleanim.SimpleAnimations;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * ClientTickHandler
 *
 * Tick Handler for all client sided purposes.
 *
 * @author Vazkii
 */
@SideOnly(Side.CLIENT)
public class ClientTickHandler implements ITickHandler {

	public static final ClientTickHandler INSTANCE = new ClientTickHandler();

	/** The total client ticks that have elapsed **/
	public static long elapsedClientTicks = 0L;

	/** The total render ticks that have elapsed **/
	public static long elapsedRenderTicks = 0L;

	/** The Current partial ticks, passed in every render tick **/
	public static float currentPartialTicks;

	private Collection<IHudElement> hudElements;

	private ClientTickHandler() {
		hudElements = new LinkedList();
		addHudElement(HudElementSpellCircle.INSTANCE);
		addHudElement(HudElementSpellTooltip.INSTANCE);
		addHudElement(HudElementVignette.INSTANCE);
	}

	public void addHudElement(IHudElement element) {
		if(!hudElements.contains(element))
			hudElements.add(element);
	}

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if(type.equals(EnumSet.of(TickType.CLIENT)))
			clientTick();
		else renderTick((Float) tickData[0]); // tickData[0] is the render tick.
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT, TickType.RENDER);
	}

	@Override
	public String getLabel() {
		return AnnotationConstants.MOD_ID;
	}

	/** Called every Client Tick  **/
	public void clientTick() {
		// Update Tick for the Simple Animations
		SimpleAnimations.updateTick();

		// Update the Lightning
		LightningBolt.update();

		checkWorld();

		// Send a tick to update the client cooldowns
		PlayerSpellUpdateHandler.clientUpdate();

		for(IHudElement element : hudElements)
			element.clientTick(); // Notify all the hud elements of the client tick

		++elapsedClientTicks;
	}

	/** Checks if there's no client world, if so, resets the "current server verified"
	 * flag for networking purposes, and clears all the world based research data **/
	public void checkWorld() {
		if(MiscHelper.getMc().theWorld == null) {
			PacketHelper.currentServerVerified = false;
			ResearchHelper.clientResearch = null;
			ResearchHelper.researchForPlayers.clear();
			ResearchHelper.hasReadResearch = false;
			SpellHelper.clientSpells = null;
		}
	}

	/** Called every Render Tick  **/
	public void renderTick(float partialTicks) {
		currentPartialTicks = partialTicks;

		for(IHudElement element : hudElements)
			if(element.shouldRender())
				element.render(partialTicks);

		++elapsedRenderTicks;
	}
}