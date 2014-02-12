package levels;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import objects.*;
import org.jbox2d.common.Vec2;
import points.PointsHandler;

import java.util.ArrayList;
import java.util.Arrays;

public class Level {
	private float[] platformLengths;
	private Vec2[] enemiesStart;
	private Player player;
	private Vec2 startPosition;

	private int remainingEnemies;
	private ArrayList<LevelEventListener> listeners = new ArrayList<LevelEventListener>();

	private ArrayList<StaticBody> bodyArray = new ArrayList<StaticBody>();

	/**
	 * Create level with initial level data.
	 * @param platformLengths Array of platform length floats.
	 * @param enemiesStart    Array of Vec2 objects for enemy start positions.
	 * @param player          The player.
	 * @param startPosition   The start position of the player.
	 */
	public Level(float[] platformLengths, Vec2[] enemiesStart, Player player, Vec2 startPosition) {
		this.platformLengths = platformLengths;
		this.enemiesStart = enemiesStart;
		this.player = player;
		this.startPosition = startPosition;

		remainingEnemies = enemiesStart.length;
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
			final Enemy enemy = new Enemy(world, player);
			enemy.setPosition(enemyPosition);

			enemy.addCollisionListener(new CollisionListener() {
				@Override
				public void collide(CollisionEvent collisionEvent) {
					if (collisionEvent.getOtherBody() instanceof Bullet) {
						enemy.destroy();
						collisionEvent.getOtherBody().destroy();

						PointsHandler.addPoints(5);

						remainingEnemies--;

						if (remainingEnemies == 0) {
							for (LevelEventListener listener : listeners) {
								listener.levelComplete();
							}
						}
					}
				}
			});

			bodyArray.add(enemy);
		}

		// Position Player
		restorePlayer();
	}

	/**
	 * Remove the level from the world.
	 */
	public void destroy() {
		for (StaticBody body : bodyArray) {
			body.destroy();
		}
	}

	/**
	 * Moves the player back to the start point.
	 */
	public void restorePlayer() {
		player.setLinearVelocity(new Vec2(0, 0));
		player.setPosition(startPosition);
	}

	/**
	 * Adds an event listener.
	 *
	 * @param levelEventListener The listener to add.
	 */
	public void addEventListener(LevelEventListener levelEventListener) {
		listeners.add(levelEventListener);
	}

	/**
	 * Removes an event listener.
	 *
	 * @param levelEventListener The exact listener to remove.
	 */
	public void removeEventListener(LevelEventListener levelEventListener) {
		listeners.remove(levelEventListener);
	}
}
