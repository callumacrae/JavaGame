import city.cs.engine.UserView;
import city.cs.engine.World;
import levels.Levels;
import objects.Player;
import org.jbox2d.common.Vec2;
import points.PointsChangeEvent;
import points.PointsChangeListener;
import points.PointsHandler;

import javax.swing.*;
import java.awt.*;

public class PhysicsGame {
	private static JLabel completeLabel;

	public static void main(String[] args) {
		World world = new World();

		// Create the view
		final UserView view = new UserView(world, 600, 700);
		view.setView(new Vec2(0, 0), 32);
		view.setLayout(new BorderLayout());


		// Display points
		final JLabel pointsLabel = new JLabel("Points: 0");
		PointsHandler.addChangeListener(new PointsChangeListener() {
			@Override
			public void changed(PointsChangeEvent pointsChangeEvent) {
				pointsLabel.setText(String.format("Points: %d", pointsChangeEvent.points));
			}
		});
		pointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		view.add(pointsLabel, BorderLayout.NORTH);

		// Create "level complete" label
		completeLabel = new JLabel("Level Complete!");
		completeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		completeLabel.setVisible(false);

		Font defaultFont = UIManager.getDefaults().getFont("TabbedPane.font");
		completeLabel.setFont(new Font(defaultFont.getFontName(), defaultFont.getStyle(), 48));

		view.add(completeLabel, BorderLayout.CENTER);


		// Create character
		Player player = new Player(world);

		// Start the game
		Levels levels = new Levels(player);
		levels.start(0, world, view, completeLabel);


		// Set up the boring stuff.
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
