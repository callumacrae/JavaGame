package levels;

import city.cs.engine.UserView;
import levels.levels.*;
import objects.Player;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Levels {
	private final ArrayList<Level> levels = new ArrayList<>();
	private boolean isActive = false;
	private final UserView view;
	private final JLabel levelLabel;

	private int latestIndex;

	/**
	 * Sets up the levels.
	 *
	 * @param player     The Player object.
	 * @param view       The view to draw the level to.
	 * @param levelLabel The label to display on completion of levels and the game.
	 */
	public Levels(Player player, final UserView view, final JLabel levelLabel) {
		this.view = view;
		this.levelLabel = levelLabel;

		levels.add(new Level1(player, levelLabel));
		levels.add(new Level2(player));
		levels.add(new Level3(player));
		levels.add(new Level4(player));
		levels.add(new Level5(player));
	}

	/**
	 * Starts the game. Draws specified level (usually 0) to the specified world.
	 *
	 * @param index The index of the level to start at. You probably want 0.
	 */
	public void start(final int index) {
		final Level level = levels.get(index);
		level.drawTo(view.getWorld());

		isActive = true;
		latestIndex = index;

		level.addEventListener(new LevelEventAdapter() {
			@Override
			public void levelComplete() {
				level.destroy();

				isActive = false;

				levelLabel.setVisible(true);

				if (index == levels.size() - 1) {
					levelLabel.setText("You won the game!");
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

						levelLabel.setVisible(false);

						start(index + 1);
					}
				});
			}
		});
	}

	/**
	 * Starts the game at level 0.
	 */
	public void start() {
		start(0);
	}

	/**
	 * Is there a level being played right now?
	 *
	 * @return True if level not being played.
	 */
	public boolean getInactive() {
		return !isActive;
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
