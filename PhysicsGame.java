import city.cs.engine.UserView;
import city.cs.engine.World;
import objects.Player;
import org.jbox2d.common.Vec2;
import points.PointsChangeEvent;
import points.PointsChangeListener;
import points.PointsHandler;

import javax.swing.*;

public class PhysicsGame {
	public static void main(String[] args) {
		World world = new World();

		// Create character
		Player player = new Player(world);

		// Build level one
		float[] platforms = {6, 1.5f, -3, 4, 1.5f, -1.5f, 2.5f};
		Vec2[] enemies = {
				new Vec2(5, -4.5f),
				new Vec2(2.5f, 0.5f),
				new Vec2(-4, 5.5f),
				new Vec2(5, 8)
		};

		Level level1 = new Level(platforms, enemies, player);
		level1.drawTo(world);

		// Display points
		final JLabel pointsLabel = new JLabel("Points: 0");
		pointsLabel.setLocation(10, 10);
		PointsHandler.addChangeListener(new PointsChangeListener() {
			@Override
			public void changed(PointsChangeEvent pointsChangeEvent) {
				pointsLabel.setText(String.format("Points: %d", pointsChangeEvent.points));
			}
		});


		// Create the view and stuff. Everything below here is boring.
		UserView view = new UserView(world, 600, 700);
		view.add(pointsLabel);
		view.setView(new Vec2(0, 0), 32);

		final JFrame frame = new JFrame("Game");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.add(view);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);

		frame.addKeyListener(new GameKeyDispatcher(player, world));

//		view.setGridResolution(1);
//		JFrame debugView = new DebugViewer(world, 500, 500);

		world.start();
	}
}
