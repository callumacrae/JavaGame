package levels.levels;

import levels.Level;
import objects.Player;
import org.jbox2d.common.Vec2;

/**
 * A level. Would be better in Levels.java, but requirements.
 */
public class Level3 extends Level {
	private static final float[] platforms = {6, 1.5f, -3, 4, 1.5f, -1.5f, 2.5f};
	private static final Vec2[] enemies = {
			new Vec2(5, -4.5f),
			new Vec2(2.5f, 0.5f),
			new Vec2(-4, 5.5f),
			new Vec2(5, 8)
	};

	public Level3(Player player) {
		super(platforms, enemies, player, new Vec2(0, -6));
	}
}
