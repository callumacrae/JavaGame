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
	public static void main(String[] args) {
		World world = new World();
		world.setGravity(100);
		Player player = new Player(world);

		// Create the view
		UserView view = new GameView(world, 600, 700, player);
		view.setView(new Vec2(0, 0), 32);
		view.setLayout(new BorderLayout());


		// Display points
		final JLabel pointsLabel = new JLabel("Points: 0");
		PointsHandler.addChangeListener(new PointsChangeListener() {
			@Override
			public void changed(PointsChangeEvent pointsChangeEvent) {
				pointsLabel.setText(PointsHandler.pointsToString());
			}
		});
		pointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		view.add(pointsLabel, BorderLayout.NORTH);

		// Label for "Level Complete", "Game Complete" and start instructions.
		JLabel levelLabel = new JLabel("Level Complete!");
		levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		levelLabel.setVisible(false);

		Font defaultFont = UIManager.getDefaults().getFont("TabbedPane.font");
		levelLabel.setFont(new Font(defaultFont.getFontName(), defaultFont.getStyle(), 48));

		view.add(levelLabel, BorderLayout.CENTER);


		// Start the game
		Levels levels = new Levels(player, view, levelLabel);
		levels.start();
		player.addLevelsData(levels);


		// Set up the boring stuff.
		JFrame frame = new JFrame("Game");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.add(view);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);

		frame.addKeyListener(new GameKeyDispatcher(player, world, levels));

//		view.setGridResolution(1);
//		JFrame debugView = new DebugViewer(world, 500, 500);

		world.start();
	}
}
