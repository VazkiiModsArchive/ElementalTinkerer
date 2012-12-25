/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Dec 2012
package vazkii.tinkerer.simpleanim;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * SimpleAnimation
 *
 * A simple class that cycles trough integers per tick.
 *
 * @author Vazkii
 */
public class TickBasedIntegerCycle {
	
	/** Starting index for the cycle **/
	private final int startIndex;
	/** Cycle ends here, start over when it reaches this **/
	private final int finalIndex;
	/** Ticks to wait before the next cycle entry **/
	private final int ticks;
	
	/** Ticks elapsed since the last iteration **/
	private int elapsedTicks;
	
	/** At what integer is the cycle **/
	private int currentIndex;
	
	public TickBasedIntegerCycle(int startIndex, int finalIndex, int ticks) {
		this.startIndex = currentIndex = startIndex; //Sync the current index with the initial
		this.finalIndex = finalIndex;
		this.ticks = ticks;
		this.elapsedTicks = 0;
	}
	
	/** Called on every client tick **/
	public void updateTick() {
		++elapsedTicks;
		if(elapsedTicks >= ticks)
			iterate();
	}
	
	/** Iterates to the next cycle entry, or the first entry if that's the case **/
	void iterate() {
		if(currentIndex >= finalIndex)
			currentIndex = startIndex;
		else currentIndex++;
		
		elapsedTicks = 0;
	}
	
	public int getCurrentIndex() {
		return currentIndex;
	}
}
