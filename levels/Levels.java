package levels;

import city.cs.engine.UserView;
import city.cs.engine.World;
import objects.Player;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Levels {
	private ArrayList<Level> levels = new ArrayList<Level>();
	private boolean isActive = false;

	private int latestIndex;

	/**
	 * Sets up the levels.
	 *
	 * @param player The Player object.
	 */
	public Levels(Player player) {
		{
			float[] platforms = {6, 3};
			Vec2[] enemies = {
					new Vec2(5, -4.5f)
			};

			levels.add(new Level(platforms, enemies, player, new Vec2(-4, -6)));
		}

		{
			float[] platforms = {6, 4.5f, 3, 1.5f, -4.5f, -3, -1.5f};
			Vec2[] enemies = {
					new Vec2(5, -7),
					new Vec2(5, -4.5f),
					new Vec2(5, -2f),
					new Vec2(5, 0.5f),
					new Vec2(-5, 3),
					new Vec2(-5, 5.5f),
					new Vec2(-5, 8)
			};

			levels.add(new Level(platforms, enemies, player, new Vec2(-4, -6)));
		}

		{
			float[] platforms = {6, 1.5f, -3, 4, 1.5f, -1.5f, 2.5f};
			Vec2[] enemies = {
					new Vec2(5, -4.5f),
					new Vec2(2.5f, 0.5f),
					new Vec2(-4, 5.5f),
					new Vec2(5, 8)
			};

			levels.add(new Level(platforms, enemies, player, new Vec2(0, -6)));
		}

		{
			float[] platforms = {-4, -3, -2, 1, 0.5f, -3.5f, 0.5f};
			Vec2[] enemies = {
					new Vec2(5.2f, 8),
					new Vec2(-5, -7)
			};

			levels.add(new Level(platforms, enemies, player, new Vec2(6, 0)));
		}

		{
			float[] platforms = {6, 5, -5, 5, -5, 5, -5};
			Vec2[] enemies = {
					new Vec2(3.5f, 8),
					new Vec2(-3.5f, 5.5f),
					new Vec2(3.5f, 3),
					new Vec2(-3.5f, 0.5f),
					new Vec2(3.5f, -2),
					new Vec2(-3.5f, -4.5f),
					new Vec2(5, -7)
			};

			levels.add(new Level(platforms, enemies, player, new Vec2(-5, 8.5f)));
		}
	}

	/**
	 * Starts the game. Draws specified level (usually 0) to the specified world.
	 *
	 * @param index         The index of the level to start at. You probably want 0.
	 * @param world         The world to draw the level to.
	 * @param view          The view to draw the level to.
	 * @param completeLabel The label to display on completion of levels and the game.
	 */
	public void start(final int index, final World world, final UserView view, final JLabel completeLabel) {
		final Level level = levels.get(index);
		level.drawTo(world);

		isActive = true;
		latestIndex = index;

		level.addEventListener(new LevelEventListener() {
			@Override
			public void levelComplete() {
				level.destroy();

				isActive = false;

				completeLabel.setVisible(true);

				if (index == levels.size() - 1) {
					completeLabel.setText("You won the game!");
					return;
				}

				view.addMouseListener(new MouseAdapter() {
					private boolean called = false;

					@Override
					public void mouseClicked(MouseEvent e) {
						super.mouseClicked(e);

						if (called) {
							return;
						}

						called = true;

						completeLabel.setVisible(false);

						start(index + 1, world, view, completeLabel);
					}
				});
			}
		});
	}

	/**
	 * Is there a level being played right now?
	 *
	 * @return True if level being played.
	 */
	public boolean getActive() {
		return isActive;
	}

	/**
	 * Get the level being played right now.
	 *
	 * @return The current level.
	 */
	public Level getCurrentLevel() {
		return levels.get(latestIndex);
	}
}
