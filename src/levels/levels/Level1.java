package levels.levels;

import levels.Level;
import levels.LevelEventListener;
import objects.Player;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;

/**
 * A level. Would be better in Levels.java, but requirements.
 */
public class Level1 extends Level {
	private final static float[] platforms = {6, 3};
	private final static Vec2[] enemies = {
			new Vec2(5, -4.5f)
	};

	public Level1(Player player, final JLabel levelLabel) {
		super(platforms, enemies, player, new Vec2(-4, -6));

		// Display instructions
		this.addEventListener(new LevelEventListener() {
			Font oldFont;
			String oldText;

			@Override
			public void levelStart() {
				oldFont = levelLabel.getFont();
				oldText = levelLabel.getText();

				levelLabel.setFont(UIManager.getDefaults().getFont("TabbedPane.font"));
				levelLabel.setText("<html><center>" +
						"Use the arrow keys to move and space to fire.<br>" +
						"Kill the aliens! Don't touch them.</center></html>");
				levelLabel.setVisible(true);
			}

			@Override
			public void levelComplete() {
				levelLabel.setFont(oldFont);
				levelLabel.setText(oldText);
			}
		});
	}
}
