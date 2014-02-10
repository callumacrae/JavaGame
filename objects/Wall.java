package objects;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;

/**
 * Create a new wall. Basically just exists for instanceof checks.
 */
public class Wall extends StaticBody {
	private static final Shape wallShape = new BoxShape(0.5f, 13);

	public Wall(World world) {
		super(world, wallShape);
	}
}
