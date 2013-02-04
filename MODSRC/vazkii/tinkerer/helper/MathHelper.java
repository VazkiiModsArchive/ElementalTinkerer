/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 25 Jan 2013
package vazkii.tinkerer.helper;

import java.awt.Point;

/**
 * MathHelper
 *
 * Math Helper, what more can I say?
 *
 * @author Vazkii
 */
public final class MathHelper {

	/** Does a cross multiplication, where it returns d, where in (d = bc / a) **/
	public static double crossMuliply(int a, int b, int c) {
		return b * c / a;
	}
	
	/** Gets a point in a circle, given the degree, radius of the cricle
	 * and the circle origin. **/
	public static Point getPointInCircle(Point origin, int deg, int radius) {
		float radian = degreeToRadian(deg);
		int x = origin.x + (int) Math.round(radius * Math.cos(radian));
		int y = origin.y + (int) Math.round(radius * Math.sin(radian));
		return new Point(x, y);
	}
	
	/** Converts a degree to a radian. If the degree passed in is
	 * over 360 it keeps subtracting by 360. If it's under 0, it
	 * keeps adding 360.**/
	public static float degreeToRadian(float deg) {
		while(deg < 0)
			deg += 360;
		while(deg > 360)
			deg -= 360;
		
		return (float) (deg * (Math.PI / 180F));
	}
}
