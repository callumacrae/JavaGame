package levels.levels;

import levels.Level;
import objects.Player;
import org.jbox2d.common.Vec2;

/**
 * A level. Would be better in Levels.java, but requirements.
 */
public class Level2 extends Level {
	private static final float[] platforms = {6, 4.5f, 3, 1.5f, -4.5f, -3, -1.5f};
	private static final Vec2[] enemies = {
			new Vec2(5, -7),
			new Vec2(5, -4.5f),
			new Vec2(5, -2f),
			new Vec2(5, 0.5f),
			new Vec2(-5, 3),
			new Vec2(-5, 5.5f),
			new Vec2(-5, 8)
	};

	public Level2(Player player) {
		super(platforms, enemies, player, new Vec2(-4, -6));
	}
}
