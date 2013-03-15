/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 25 Jan 2013
package vazkii.tinkerer.helper;

import java.awt.Point;

import net.minecraft.entity.Entity;
import vazkii.tinkerer.lightning.Vector3;

/**
 * MathHelper
 *
 * Math Helper, what more can I say?
 *
 * @author Vazkii
 */
public final class MathHelper {

	/** Does a cross multiplication, where it returns d, where in (d = bc / a) **/
	public static double crossMuliply(double a, double b, double c) {
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

	/** Gets the distance between two points in the same plane **/
	public static double pointDistancePlane(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	/** Gets the distance between two points in space **/
	public static double pointDistanceSpace(double x1, double y1, double z1, double x2, double y2, double z2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2));
	}

	public static void moveEntityTowardsPos(Entity entity, double x, double y, double z, float speed) {
		Vector3 originalPosVector = new Vector3(x, y, z);
		Vector3 entityVector = Vector3.fromEntityCenter(entity);
		Vector3 finalVector = originalPosVector.subtract(entityVector).normalize().multiply(speed);
		entity.motionX = finalVector.x;
		entity.motionY = finalVector.y;
		entity.motionZ = finalVector.z;
	}

	public static void moveEntityAwayFromPos(Entity entity, double x, double y, double z, float speed) {
		moveEntityTowardsPos(entity, x, y, z, speed);
		entity.motionX = -entity.motionX;
		entity.motionY = -entity.motionY;
		entity.motionZ = -entity.motionZ;
	}
}
