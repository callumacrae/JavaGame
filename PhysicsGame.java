import city.cs.engine.UserView;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;
import points.PointsChangeEvent;
import points.PointsChangeListener;
import points.PointsHandler;

import javax.swing.*;

public class PhysicsGame {
	public static void main(String[] args) {
		World world = new World();

		// Build the walls
		new Wall(world).setPosition(new Vec2(-12.5f, 0));
		new Wall(world).setPosition(new Vec2(12.5f, 0));

		// Create platforms
		int[] lengths = {12, 3, -6, 8, 3, -3, 5};
		for (int i = 0; i < lengths.length; i++) {
			PlatformGenerator.generate(world, lengths[i], 3.5f * i - 12.5f);
		}

		// Create enemies
		Vec2[] enemies = {
			new Vec2(10, -7.5f),
			new Vec2(5, -0.5f),
			new Vec2(-7, 6.5f),
			new Vec2(10, 10)
		};
		for (Vec2 enemyPosition : enemies) {
			new Enemy(world).setPosition(enemyPosition);
		}

		// Create bird
		Bird bird = new Bird(world);
		bird.setPosition(new Vec2(0, -11));

		PointsHandler.addChangeListener(new PointsChangeListener() {
			@Override
			public void changed(PointsChangeEvent pointsChangeEvent) {
				System.out.println(pointsChangeEvent.points);
			}
		});



		// Create the view and stuff. Everything below here is boring.
		UserView view = new UserView(world, 600, 600);

		final JFrame frame = new JFrame("Game");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.add(view);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);

		frame.addKeyListener(new GameKeyDispatcher(bird, world));

//		view.setGridResolution(1);
//		JFrame debugView = new DebugViewer(world, 500, 500);

		world.start();
	}
}
