/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 28 Jan 2013
package vazkii.tinkerer.handler;

import java.util.EnumSet;

import vazkii.tinkerer.reference.AnnotationConstants;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

/**
 * TickHandler
 *
 * General Tick Handler
 *
 * @author Vazkii
 */
public class TickHandler implements ITickHandler {

	public static final TickHandler INSTANCE = new TickHandler();

	/** The total ticks that have elapsed **/
	public static long elapsedTicks = 0L;

	private TickHandler() { }

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		// NO-OP
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		++elapsedTicks;
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.SERVER);
	}

	@Override
	public String getLabel() {
		return AnnotationConstants.MOD_ID;
	}

}
