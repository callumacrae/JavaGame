import city.cs.engine.World;
import objects.Enemy;
import objects.PlatformGenerator;
import objects.Player;
import org.jbox2d.common.Vec2;

public class Level {
	private float[] platforms;
	private Vec2[] enemiesStart;
	private Player player;

	public Level(float[] platforms, Vec2[] enemiesStart, Player player) {
		this.platforms = platforms;
		this.enemiesStart = enemiesStart;
		this.player = player;
	}

	public void drawTo(World world) {
		// Create platforms
		for (int i = 0; i < platforms.length; i++) {
			PlatformGenerator.generate(world, platforms[i], 2.5f * i - 8f);
		}

		// Draw enemies
		for (Vec2 enemyPosition : enemiesStart) {
			new Enemy(world, player).setPosition(enemyPosition);
		}

		// Start player
		player.setPosition(new Vec2(0, -6));
	}
}
