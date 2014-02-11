import city.cs.engine.StaticBody;
import city.cs.engine.World;
import objects.*;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;
import java.util.Arrays;

public class Level {
	private float[] platformLengths;
	private Vec2[] enemiesStart;
	private Player player;

	private ArrayList<StaticBody> bodyArray = new ArrayList<StaticBody>();

	/**
	 * Create level with initial level data.
	 * @param platformLengths Array of platform length floats.
	 * @param enemiesStart    Array of Vec2 objects for enemy start positions.
	 * @param player          The player.
	 */
	public Level(float[] platformLengths, Vec2[] enemiesStart, Player player) {
		this.platformLengths = platformLengths;
		this.enemiesStart = enemiesStart;
		this.player = player;
	}

	/**
	 * Draw the level to the world.
	 *
	 * @param world The world to draw the level to.
	 */
	public void drawTo(World world) {
		// Build the walls
		bodyArray.addAll(Arrays.asList(
				WallGenerator.generateWall(world, 6.25f)
		));
		bodyArray.addAll(Arrays.asList(
				WallGenerator.generateWall(world, -6.25f)
		));

		// Create platforms
		for (int i = 0; i < platformLengths.length; i++) {
			Tile[] tiles = PlatformGenerator.generate(world, platformLengths[i], 2.5f * i - 8f);
			bodyArray.addAll(Arrays.asList(tiles));
		}

		// Draw enemies
		for (Vec2 enemyPosition : enemiesStart) {
			Enemy enemy = new Enemy(world, player);
			enemy.setPosition(enemyPosition);

			bodyArray.add(enemy);
		}
	}

	/**
	 * Remove the level from the world.
	 *
	 * BROKEN. Not sure why.
	 */
	public void destroy() {
		for (StaticBody body : bodyArray) {
			body.destroy();
		}
	}
}
