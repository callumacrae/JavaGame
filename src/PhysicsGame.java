import city.cs.engine.UserView;
import city.cs.engine.World;
import levels.Levels;
import objects.Player;
import org.jbox2d.common.Vec2;
import points.HighScore;
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
		final String pointsText = "Points: %s, High Score: %s";
		final float highScore = HighScore.getHighScore();
		String highScoreString = PointsHandler.pointsToString(highScore);

		String text = String.format(pointsText, 0, highScoreString);
		final JLabel pointsLabel = new JLabel(text);

		PointsHandler.addChangeListener(new PointsChangeListener() {
			@Override
			public void changed(PointsChangeEvent pointsChangeEvent) {
				float points = PointsHandler.getPoints();
				if (points > highScore) {
					HighScore.setHighScore(points);
				}

				String pointsStr = PointsHandler.pointsToString();
				float high = Math.max(highScore, points);
				String highStr = PointsHandler.pointsToString(high);

				if (pointsStr.equals(highStr)) {
					highStr += "!";
				}

				String text = String.format(pointsText, pointsStr, highStr);
				pointsLabel.setText(text);
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
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.add(view);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension windowSize = frame.getSize();
		Point frameLocation = new Point(dim.width / 2 - windowSize.width / 2,
				dim.height / 2 - windowSize.height / 2);
		frame.setLocation(frameLocation);

		Draggable.makeDraggable(frame, view, frameLocation);


		frame.addKeyListener(new GameKeyDispatcher(player, world, levels));

//		view.setGridResolution(1);
//		JFrame debugView = new DebugViewer(world, 500, 500);

		world.start();
	}
}
