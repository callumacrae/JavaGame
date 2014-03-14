package levels.levels;

import levels.Level;
import objects.Player;
import org.jbox2d.common.Vec2;

/**
 * A level. Would be better in Levels.java, but requirements.
 */
public class Level4 extends Level {
	private static final float[] platforms = {-4, -3, -2, 1, 0.5f, -3.5f, 0.5f};
	private static final Vec2[] enemies = {
			new Vec2(5.2f, 8),
			new Vec2(-5, -7)
	};

	public Level4(Player player) {
		super(platforms, enemies, player, new Vec2(6, 0));
	}
}
