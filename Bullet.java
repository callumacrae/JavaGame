import city.cs.engine.*;

/**
 * Create a DynamicBody in the shape of an image. Handles appearance and
 * collisions, but does not handle position and velocity.
 */
public class Bullet extends DynamicBody {
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

				if (otherBody instanceof Bird || otherBody instanceof Wall) {
					Bullet.this.destroy();
				}

				if (otherBody instanceof Enemy) {
					Bullet.this.destroy();
					otherBody.destroy();
				}
			}
		});

		Shape shape = new CircleShape(0.2f);
		SolidFixture fixture = new SolidFixture(this, shape);
		fixture.setRestitution(1f);
		fixture.setFriction(0);
	}
}
