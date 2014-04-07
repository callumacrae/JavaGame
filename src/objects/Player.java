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
	private static final Shape shape = new BoxShape(0.5f, 0.5f, new Vec2(-0.1f, 0));
	private static final Shape fingerShape = new BoxShape(0.2f, 0.2f, new Vec2(0.5f, 0.1f));
	private static final BodyImage walkingRightImage = new BodyImage("data/SumoHulkBrawler/walking_right.gif");
	private static final BodyImage walkingLeftImage = new BodyImage("data/SumoHulkBrawler/walking_left.gif");
	private static final BodyImage idleImage = new BodyImage("data/SumoHulkBrawler/idle.gif");
	private static final BodyImage kisiLeft = new BodyImage("data/kisi/left.png");
	private static final BodyImage kisiRight = new BodyImage("data/kisi/right.png");

	private Levels levels;
	private boolean ninja = false;

	/**
	 * Create a new DynamicBody. Hulk!
	 *
	 * @param world The world that the Player should be added to.
	 */
	public Player(World world) {
		super(world);
		new SolidFixture(this, shape);
		new SolidFixture(this, fingerShape);

		this.setImage(idleImage);
		this.setFixedRotation(true);

		// Deduct points when the Player hits a bad guy
		this.addCollisionListener(collisionEvent -> {
			if (collisionEvent.getOtherBody() instanceof Enemy) {
				if (ninja) {
					return;
				}

				if (levels != null) {
					levels.getCurrentLevel().restorePlayer();
				} else {
					System.out.println("ERROR: Levels data not specified.");
				}

				Enemy enemy = (Enemy) collisionEvent.getOtherBody();
				enemy.setLives(enemy.getLives() + 1);

				PointsHandler.removePoints(10);
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
					Player.this.setImage(ninja ? kisiRight : walkingRightImage);
				} else if (velocity.x < -0.1 && currentImage != walkingLeftImage) {
					Player.this.setImage(ninja ? kisiLeft : walkingLeftImage);
				} else if (Math.abs(velocity.x) < 0.1 && currentImage != idleImage) {
					Player.this.setImage(ninja ? kisiRight : idleImage);
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

	/**
	 * Turns the player into a ninja.
	 */
	public void ninja() {
		ninja = true;
	}

	/**
	 * Returns whether the player is a ninja or not.
	 *
	 * @return True if player is a ninja.
	 */
	public boolean isNinja() {
		return ninja;
	}
}
