package objects;

import city.cs.engine.*;
import points.PointsHandler;

/**
 * Create a DynamicBody in the shape of an image. Handles appearance and
 * collisions, but does not handle position and velocity.
 */
public class Bullet extends DynamicBody {
	private static final Shape bullet = new CircleShape(0.1f);

	/**
	 * Creates the bullet.
	 *
	 * @param world The world to add the bullet to.
	 */
	public Bullet(World world) {
		super(world);

		this.setGravityScale(10);

		this.addCollisionListener(new CollisionListener() {
			@Override
			public void collide(CollisionEvent collisionEvent) {
				Body otherBody = collisionEvent.getOtherBody();

				if (otherBody instanceof Player) {
					Bullet.this.destroy();
				}

				if (otherBody instanceof Tile && ((Tile) otherBody).getKillBullets()) {
					Bullet.this.destroy();
				}

				if (otherBody instanceof Enemy) {
					Bullet.this.destroy();
					otherBody.destroy();

					PointsHandler.addPoints(5);
				}
			}
		});

		SolidFixture fixture = new SolidFixture(this, bullet);
		fixture.setRestitution(1); // Bouncy bullets!
		fixture.setFriction(0);
	}
}
