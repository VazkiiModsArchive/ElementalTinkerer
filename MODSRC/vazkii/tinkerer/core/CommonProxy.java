/**
 * This Code is Open Source and distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 */
// Created @ 23 Dec 2012
package vazkii.tinkerer.core;

import java.awt.Color;

import net.minecraft.world.World;

/**
 * CommonProxy
 *
 * Common proxy, used between the client and the server. The client proxy extends
 * this class to add functionality only available on client side.
 *
 * @author Vazkii
 */
public class CommonProxy {

	/** Used by the client to map entity classes to their adequate
	 *  render instances. NO-OP in server side. **/
	public void mapEntityRenderers() {
		// NO-OP
	}

	/** Preloads the textures, to avoid render glitches, NO-OP
	 * in server side **/
	public void preloadTextures() {
		// NO-OP
	}

	/** Used by the client to spawn a colored portal particle at
	 * the location passed in, with the color and motion passed
	 * in. NO-OP in server side. **/
	public void spawnColoredPortalParticle(Color color, World world, double x, double y, double z, double motionX, double motionY, double motionZ) {
		// NO-OP
	}
}
