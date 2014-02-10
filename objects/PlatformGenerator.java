package objects;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * A class to generate platforms.
 */
public class PlatformGenerator {
	private PlatformGenerator() {}

	/**
	 * Generate a platform from a length and y position.
	 *
	 * @param world The world to add the platform to.
	 * @param length The desired length of the platform. If positive, will be
	 *               positioned on the right. If negative, will be positioned
	 *               on the left.
	 * @param y The desired y position of the platform.
	 */
	public static void generate(World world, float length, float y) {
		Shape shape = new BoxShape(Math.abs(length), 0.5f);

		Body platform = new StaticBody(world, shape);
		platform.setPosition(new Vec2(12 * (length > 0 ? 1 : -1) - length, y));
	}
}
