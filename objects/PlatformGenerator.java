package objects;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 * A class to generate platforms.
 */
public class PlatformGenerator {
	private PlatformGenerator() {}

	private static final BodyImage platformLeft = new BodyImage("data/platformtiles/platform_left.png");
	private static final BodyImage platformCentre = new BodyImage("data/platformtiles/platform_centre.png");
	private static final BodyImage platformRight = new BodyImage("data/platformtiles/platform_right.png");

	private static final Shape shape = new BoxShape(0.5f, 0.5f);

	/**
	 * Generate a platform from a length and y position.
	 *
	 * @param world  The world to add the platform to.
	 * @param length The desired length of the platform. If positive, will be
	 *               positioned on the right. If negative, will be positioned
	 *               on the left.
	 * @param y      The desired y position of the platform.
	 */
	public static Tile[] generate(World world, float length, float y) {
		Tile[] tiles = new Tile[(int) Math.floor(Math.abs(length) * 2 + 1)];

		for (int i = -1; i < Math.abs(length) * 2; i++) {
			Tile tile = new Tile(world, shape);
			tile.setPosition(new Vec2((5.5f - (i == -1 ? -0.5f : i)) * (length > 0 ? 1 : -1), y));

			if (i == length * 2 - 1 && length != 6) {
				tile.setImage(platformLeft);
			} else if (-i == length * 2 + 1) {
				tile.setImage(platformRight);
			} else {
				tile.setImage(platformCentre);
			}

			tiles[i + 1] = tile;
		}

		return tiles;
	}
}
