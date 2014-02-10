import city.cs.engine.DynamicBody;
import city.cs.engine.StepEvent;
import city.cs.engine.World;
import helpers.StepAdapter;
import objects.Bullet;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Handle all key events in the game.
 */
public class GameKeyDispatcher extends KeyAdapter {
	private DynamicBody birdBody;
	private World world;

	private int horizontal;

	/**
	 * Set up the event handler.
	 *
	 * @param birdBody The main character.
	 * @param world The world.
	 */
	public GameKeyDispatcher(DynamicBody birdBody, World world) {
		this.birdBody = birdBody;
		this.world = world;

		// Reapply force on ever step to simulate a continuous force.
		world.addStepListener(new StepAdapter() {
			@Override
			public void preStep(StepEvent stepEvent) {
				GameKeyDispatcher.this.birdBody.applyForce(new Vec2(horizontal, 0));
			}
		});
	}

	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);

		switch (e.getKeyCode()) {

			// Throw a bullet
			case KeyEvent.VK_SPACE:
				Vec2 birdVelocity = birdBody.getLinearVelocity();
				float xVelocity = birdVelocity.x / 2 + 12 * (birdVelocity.x >= 0 ? 1 : -1);

				Vec2 position = birdBody.getPosition();
				position.x += 0.9f * (birdVelocity.x >= 0 ? 1 : -1) + xVelocity / 20;
				position.y += 0.5f;

				final DynamicBody bullet = new Bullet(world);
				bullet.setPosition(position);
				bullet.setLinearVelocity(new Vec2(xVelocity, 0));
				break;

			// Make the main character jump
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				Vec2 currentVelocity = birdBody.getLinearVelocity();

				// For some reason, this takes a while to get to 0
				if (Math.abs(currentVelocity.y) < 0.001) {
					birdBody.setLinearVelocity(new Vec2(currentVelocity.x, 30));
				}
				break;

			// Apply a horizontal force
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				this.horizontal = -200;
				break;

			// Apply a horizontal force
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				this.horizontal = 200;
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);

		switch (e.getKeyCode()) {

			// Cancel the force when the left or right key is released.
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_A:
			case KeyEvent.VK_D:
				this.horizontal = 0;
				break;
		}
	}
}