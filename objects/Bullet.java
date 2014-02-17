package objects;

import city.cs.engine.*;

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
	 * @param cheat If true, bullet will bounce off walls.
	 */
	public Bullet(World world, final boolean cheat) {
		super(world);

		this.setGravityScale(10);

		this.addCollisionListener(new CollisionListener() {
			@Override
			public void collide(CollisionEvent collisionEvent) {
				Body otherBody = collisionEvent.getOtherBody();

				if (otherBody instanceof Player) {
					Bullet.this.destroy();

					// @todo: Remove milestone 1 code
					System.out.println("Player hit by bullet; destroying bullet.");
				}

				if (otherBody instanceof Tile && ((Tile) otherBody).getKillBullets() && !cheat) {
					Bullet.this.destroy();

					// @todo: Remove milestone 1 code
					System.out.println("Bullet hit wall; removing bullet.");
				}
			}
		});

		SolidFixture fixture = new SolidFixture(this, bullet);
		fixture.setRestitution(1); // Bouncy bullets!
		fixture.setFriction(0);
	}
}
