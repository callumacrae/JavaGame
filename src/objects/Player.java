package objects;

import city.cs.engine.*;
import helpers.StepAdapter;
import levels.Levels;
import org.jbox2d.common.Vec2;
import points.PointsHandler;

/**
 * Create a new DynamicBody for our character.
 */
public class Player extends DynamicBody {
	private static final Shape shape = new BoxShape(0.5f, 0.5f);
	private static final BodyImage walkingRightImage = new BodyImage("data/SumoHulkBrawler/walking_right.gif");
	private static final BodyImage walkingLeftImage = new BodyImage("data/SumoHulkBrawler/walking_left.gif");
	private static final BodyImage idleImage = new BodyImage("data/SumoHulkBrawler/idle.gif");

	private Levels levels;

	/**
	 * Create a new DynamicBody. Hulk!
	 *
	 * @param world The world that the Player should be added to.
	 */
	public Player(World world) {
		super(world, shape);

		this.setImage(idleImage);
		this.setFixedRotation(true);

		// Deduct points when the Player hits a bad guy
		this.addCollisionListener(new CollisionListener() {
			@Override
			public void collide(CollisionEvent collisionEvent) {
				if (collisionEvent.getOtherBody() instanceof Enemy) {
					if (levels != null) {
						levels.getCurrentLevel().restorePlayer();
					} else {
						System.out.println("ERROR: Levels data not specified.");
					}

					// @todo: Remove milestone 1 code
					System.out.println("Player ran into Enemy; restore player to level start.");

					PointsHandler.removePoints(10);
				}
			}
		});

		// Make sure the correct image is being used
		world.addStepListener(new StepAdapter() {
			@Override
			public void preStep(StepEvent stepEvent) {
				super.preStep(stepEvent);

				Vec2 velocity = Player.this.getLinearVelocity();
				BodyImage currentImage = Player.this.getImage();

				if (velocity.x > 0.1 && currentImage != walkingRightImage) {
					Player.this.setImage(walkingRightImage);
				} else if (velocity.x < -0.1 && currentImage != walkingLeftImage) {
					Player.this.setImage(walkingLeftImage);
				} else if (Math.abs(velocity.x) < 0.1 && currentImage != idleImage) {
					Player.this.setImage(idleImage);
				}
			}
		});
	}

	/**
	 * Add levels data: as Levels wants access to Player and Player wants
	 * access to Levels, this cannot be done in the constructor.
	 *
	 * @param levels The Levels object.
	 */
	public void addLevelsData(Levels levels) {
		this.levels = levels;
	}
}
