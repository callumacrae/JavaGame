package points;

import java.util.ArrayList;

/**
 * Handles points! Static because only one of these is ever needed.
 */
public class PointsHandler {
	private static int totalPoints = 0;
	private static ArrayList<PointsChangeListener> listeners = new ArrayList<PointsChangeListener>();

	/**
	 * Add a number of points.
	 *
	 * @param points Number of points to add.
	 */
	public static void addPoints(int points) {
		setPoints(totalPoints + points);

		if (points < 0) {
			System.out.println("Note: you should use removePoints for removing points.");
		}
	}

	/**
	 * Remove a number of points.
	 *
	 * @param points Number of points to remove.
	 */
	public static void removePoints(int points) {
		setPoints(totalPoints - points);

		if (points < 0) {
			System.out.println("Note: you should use addPoints for adding points.");
		}
	}

	/**
	 * Set the number of points to a specified value.
	 *
	 * @param points New number of points.
	 */
	public static void setPoints(int points) {
		PointsChangeEvent pointsChangeEvent = new PointsChangeEvent();

		pointsChangeEvent.points = points;
		pointsChangeEvent.oldPoints = totalPoints;
		pointsChangeEvent.difference = points - totalPoints;

		// We don't fire the event if the number of points hasn't changed
		if (pointsChangeEvent.difference == 0) {
			return;
		}

		pointsChangeEvent.good = pointsChangeEvent.difference > 0;

		for (PointsChangeListener listener : listeners) {
			listener.changed(pointsChangeEvent);
		}

		totalPoints = points;
	}

	/**
	 * Get the number of points.
	 *
	 * @return The number of points.
	 */
	public static int getPoints() {
		return totalPoints;
	}

	/**
	 * Add a listener to be fired when the number of points changes.
	 *
	 * @param listener The event listener to add.
	 */
	public static void addChangeListener(PointsChangeListener listener) {
		listeners.add(listener);
	}

	/**
	 * Remove a listener. It needs to be exactly the same object.
	 *
	 * @param listener The event listener to remove.
	 */
	public static void removeChangeListener(PointsChangeListener listener) {
		listeners.remove(listener);
	}
}
