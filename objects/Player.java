package objects;

import city.cs.engine.*;
import helpers.StepAdapter;
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

	/**
	 * Create a new DynamicBody. Hulk!
	 *
	 * @param world The world that the Player should be added to.
	 */
	public Player(World world) {
		super(world, shape);

		this.setImage(idleImage);
		this.setGravityScale(10);
		this.setFixedRotation(true);

		// Deduct points when the Player hits a bad guy
		this.addCollisionListener(new CollisionListener() {
			@Override
			public void collide(CollisionEvent collisionEvent) {
				if (collisionEvent.getOtherBody() instanceof Enemy) {
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
}