import city.cs.engine.UserView;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

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

		// Create bird
		Bird bird = new Bird(world);
		bird.setPosition(new Vec2(0, -11));


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
