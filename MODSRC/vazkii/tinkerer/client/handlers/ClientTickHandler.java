/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Dec 2012
package vazkii.tinkerer.client.handlers;

import java.util.EnumSet;

import vazkii.tinkerer.reference.AnnotationConstants;
import vazkii.tinkerer.simpleanim.SimpleAnimations;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

/**
 * ClientTickHandler
 *
 * Tick Handler for all client sided purposes.
 *
 * @author Vazkii
 */
public class ClientTickHandler implements ITickHandler {

	public static final ClientTickHandler INSTANCE = new ClientTickHandler();
	
	/** The total client ticks that have elapsed **/
	public static long elapsedClientTicks = 0L;
	
	/** The total render ticks that have elapsed **/
	public static long elapsedRenderTicks = 0L;
	
	/** The Current partial ticks, passed in every render tick **/
	public static float currentPartialTicks;
	
	private ClientTickHandler() { }
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		if(type.equals(EnumSet.of(TickType.CLIENT)))
			clientTick();
		else renderTick((Float) tickData[0]); // tickData[0] is the render tick.
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {}

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
	
		++elapsedClientTicks;
	}
	
	/** Called every Render Tick  **/
	public void renderTick(float partialTicks) {
		currentPartialTicks = partialTicks;
		++elapsedRenderTicks;
	}
}
