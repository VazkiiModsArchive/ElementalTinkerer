/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 2 Feb 2013
package vazkii.tinkerer.simpleanim;

/**
 * RestrictedIntegerCycle
 *
 * An integer cycle that must fulfil a specific condition
 * to carry on with another cycle.
 *
 * @author Vazkii
 */
public class RestrictedIntegerCycle extends TickBasedIntegerCycle {

	public RestrictedIntegerCycle(int startIndex, int finalIndex, int ticks) {
		super(startIndex, finalIndex, ticks);
	}

	@Override
	public int getCurrentValue() {
		return currentIndex;
	}

	@Override
	void iterate() {
		if(currentIndex == startIndex && !canRestartCycle())
			return;

		super.iterate();
	}

	/** Overridable method that returns if a new cycle can be started **/
	boolean canRestartCycle() {
		return true;
	}
}
