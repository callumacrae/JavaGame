package points;

/**
 * Event fired when the number of points is changed.
 */
public class PointsChangeEvent {
	// The new number of points
	public int points;

	// The old number of points
	public int oldPoints;

	// Points gained. Can be negative. This will never be zero!
	public int difference;

	// True if points gained is greater than zero.
	public boolean good;
}
