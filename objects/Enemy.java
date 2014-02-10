package objects;

import city.cs.engine.*;
import helpers.StepAdapter;

/**
 * Create a new DynamicBody for an enemy.
 */
public class Enemy extends StaticBody {
	private static final Shape shape = new BoxShape(0.5f, 0.5f);
	private static final BodyImage imageLeft = new BodyImage("data/platformtiles/alien_left.png");
	private static final BodyImage imageRight = new BodyImage("data/platformtiles/alien_right.png");

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

				if (player.getPosition().x < Enemy.this.getPosition().x) {
					if (Enemy.this.getImage() == imageRight) {
						Enemy.this.setImage(imageLeft);
					}
				} else if (Enemy.this.getImage() == imageLeft) {
					Enemy.this.setImage(imageRight);
				}
			}
		});
	}
}
