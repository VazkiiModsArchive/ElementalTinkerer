/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 7 Apr 2013
package vazkii.tinkerer.client.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;

/**
 * RenderEmpty
 *
 * A Render type that has no rendering. Generally used for projectiles
 * who have particles for graphics.
 *
 * @author Vazkii
 */
public class RenderEmpty extends Render {

	public static final RenderEmpty INSTANCE = new RenderEmpty();

	@Override
	public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1) {
		// NO-OP
	}

}
