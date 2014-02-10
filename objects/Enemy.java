package objects;

import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;

/**
 * Create a new DynamicBody for an enemy.
 */
public class Enemy extends StaticBody {
	private static final Shape shape = new PolygonShape(0.149f, 0.975f, 0.775f, 0.193f, 0.772f, -0.099f, 0.401f, -0.928f, -0.36f, -0.922f, -0.719f, -0.025f, -0.725f, 0.163f, -0.14f, 0.972f);

	/**
	 * Create a new DynamicBody in the shape of a bird with the bird image.
	 *
	 * @param world The world that the Bird should be added to.
	 */
	public Enemy(World world) {
		super(world, shape);

//		this.setImage(new BodyImage("data/yellow-bird.gif", 2.25f));
	}
}
