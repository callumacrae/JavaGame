package points;

public interface PointsChangeListener {
	/**
	 * Fired when the number of points has changed.
	 *
	 * @see points.PointsChangeEvent
	 * @param pointsChangeEvent The event.
	 */
	public void changed(PointsChangeEvent pointsChangeEvent);
}
