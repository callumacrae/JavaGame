import city.cs.engine.UserView;
import city.cs.engine.World;
import objects.Player;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class GameView extends UserView {
	private Player player;

	public GameView(World world, int i, int i2, Player player) {
		super(world, i, i2);

		this.player = player;
	}

	@Override
	protected void paintBackground(Graphics2D graphics2D) {
		Vec2 playerPosition = player.getPosition();

		// Calls to Math.max and Math.min to ensure that the image doesn't go off screen
		int backgroundX = Math.max(-400, Math.min(0, -100 - (int) (playerPosition.x * 10)));
		int backgroundY = Math.max(-260, Math.min(0, -100 + (int) (playerPosition.y * 10)));

		Image backgroundImage = Toolkit.getDefaultToolkit().getImage("data/background.jpg");
		graphics2D.drawImage(backgroundImage, backgroundX, backgroundY, this);
	}
}
