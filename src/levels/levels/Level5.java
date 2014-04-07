package levels.levels;

import levels.Level;
import levels.LevelEventListener;
import objects.Gate;
import objects.Player;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;

/**
 * A level. Would be better in Levels.java, but requirements.
 */
public class Level5 extends Level {
	private static final float[] platforms = {6, 5, -5, 5, -5, 5, -5};
	private static final Vec2[] enemies = {
			new Vec2(3.5f, 8),
			new Vec2(-3.5f, 5.5f),
			new Vec2(3.5f, 3),
			new Vec2(-3.5f, 0.5f),
			new Vec2(3.5f, -2),
			new Vec2(-3.5f, -4.5f),
			new Vec2(5, -7)
	};

	public Level5(final Player player) {
		super(platforms, enemies, player, new Vec2(-5, 8.5f));

		final ArrayList<Gate> gates = new ArrayList<>();

		this.addEventListener(new LevelEventListener() {
			@Override
			public void levelStart() {
				for (float y = -7f; y <= 8; y += 2.5f) {
					Gate gate = new Gate(player.getWorld());
					gate.setPosition(new Vec2(0, y));

					gates.add(gate);
				}
			}

			@Override
			public void levelComplete() {
				gates.forEach(Gate::destroy);
			}
		});
	}
}
