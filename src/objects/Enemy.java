package objects;

import city.cs.engine.*;
import helpers.StepAdapter;

/**
 * Create a new DynamicBody for an enemy.
 *
 * Dynamic so that if it gains extra lives, it will move the first time it is
 * shot.
 */
public class Enemy extends DynamicBody {
	private static final Shape shape = new BoxShape(0.5f, 0.5f);
	private static final BodyImage imageLeft = new BodyImage("data/platformtiles/alien_left.png");
	private static final BodyImage imageRight = new BodyImage("data/platformtiles/alien_right.png");
	private static final BodyImage imageLeftBig = new BodyImage("data/platformtiles/alien_left.png", 1.5f);
	private static final BodyImage imageRightBig = new BodyImage("data/platformtiles/alien_right.png", 1.5f);

	private int lives = 1;

	/**
	 * Create a new DynamicBody with the alien image.
	 *
	 * @param world The world that the Enemy should be added to.
	 */
	public Enemy(World world, final Player player) {
		super(world, shape);

		this.setImage(imageLeft);

		// This makes the alien face the direction of our character.
		world.addStepListener(new StepAdapter() {
			@Override
			public void preStep(StepEvent stepEvent) {
				super.preStep(stepEvent);

				if (lives == 1) {
					if (player.getPosition().x < Enemy.this.getPosition().x) {
						if (Enemy.this.getImage() != imageLeft) {
							Enemy.this.setImage(imageLeft);
						}
					} else if (Enemy.this.getImage() != imageRight) {
						Enemy.this.setImage(imageRight);
					}
				}
				else {
					if (player.getPosition().x < Enemy.this.getPosition().x) {
						if (Enemy.this.getImage() != imageLeftBig) {
							Enemy.this.setImage(imageLeftBig);
						}
					} else if (Enemy.this.getImage() != imageRightBig) {
						Enemy.this.setImage(imageRightBig);
					}
				}


			}
		});
	}

	/**
	 * Get the "lives" of the Enemy. It should take this many bullets to
	 * kill it.
	 *
	 * @return The number of lives the Enemy has: 1, by default.
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * Sets the lives of the alien.
	 *
	 * @param lives The new lives of the alien.
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}
}
