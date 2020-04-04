package org.ethp.barrenland.main.model;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Land extends Rectangle {

	private class LandUnit {
		int x;
		int y;

		public LandUnit(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(Integer.valueOf(x), Integer.valueOf(y));
		}

		@Override
		public boolean equals(Object object) {
			if (object == null || !(object instanceof LandUnit)) {
				return false;
			}

			LandUnit unit = (LandUnit) object;
			return (this.x == unit.x && this.y == unit.y);
		}
	}

	private Set<LandUnit> fertileArea;

	public Land(int width, int height) throws InvalidParameterException {
		super(0, 0, width - 1, height - 1);

		if (width <= 0) {
			throw new InvalidParameterException("Width smaller or equals to 0.");
		}

		if (height <= 0) {
			throw new InvalidParameterException("Height smaller or equals to 0.");
		}

		fertileArea = new HashSet<>();

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				fertileArea.add(new LandUnit(x, y));
			}
		}
	}

	public Land(int width, int height, Set<Rectangle> barrenRectangles) throws InvalidParameterException {
		this(width, height);

		for (Rectangle rectangle : barrenRectangles) {
			setBarrenRectangle(rectangle);
		}
	}

	private void setBarrenRectangle(Rectangle rectangle) throws InvalidParameterException {

		final int width = getWidth();
		final int height = getHeight();

		final int left = rectangle.getLeft();
		final int bottom = rectangle.getBottom();
		final int right = rectangle.getRight();
		final int top = rectangle.getTop();

		if ((left < 0 || left >= width) || (right < 0 || right >= width) || (bottom < 0 || bottom >= height)
				|| (top < 0 || top >= height)) {
			throw new InvalidParameterException("Barren area boundaries are outside of the land area.");
		}

		for (int x = left; x <= right; x++) {
			for (int y = bottom; y <= top; y++) {
				fertileArea.remove(new LandUnit(x, y));
			}
		}
	}

	/**
	 * Gets the sum of all fertile areas
	 * 
	 * @return total fertile area
	 */
	public int getTotalFertileArea() {
		return fertileArea.size();
	}

	/**
	 * Gets the sum of all barren areas
	 * 
	 * @return total barren area
	 */
	public int getTotalBarrenArea() {
		return getArea() - getTotalFertileArea();
	}

	private boolean isFertile(final LandUnit unit) {
		return fertileArea.contains(unit);
	}

	public List<Integer> getFertileAreas() {
		// Iterate over the fertile area units, grouping them in a set if they are
		// connected
		List<Set<LandUnit>> unitFertileAreas = new ArrayList<>();

		// Holds the fertile area being currently processed by the loop
		int currentFertileAreaIndex = -1;
		Set<LandUnit> currentFertileArea = null;

		final int width = getWidth();
		final int height = getHeight();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				LandUnit unit = new LandUnit(x, y);
				if (isFertile(unit)) {
					if (currentFertileArea == null) {
						currentFertileArea = new HashSet<>();
						unitFertileAreas.add(currentFertileArea);
						currentFertileAreaIndex = unitFertileAreas.size() - 1;
					}

					currentFertileArea.add(unit);

					if (y > 0) {
						LandUnit unitBelow = new LandUnit(x, y - 1);
						if (isFertile(unitBelow) && !currentFertileArea.contains(unitBelow)) {
							// Remove the currentFertileArea, find the fertile area where the unit below is
							// located, and merge the current onto it.
							unitFertileAreas.remove(currentFertileAreaIndex);

							for (int i = 0; i < unitFertileAreas.size(); i++) {
								Set<LandUnit> unitFertileArea = unitFertileAreas.get(i);
								if (unitFertileArea.contains(unitBelow)) {
									unitFertileArea.addAll(currentFertileArea);
									currentFertileArea = unitFertileArea;
									currentFertileAreaIndex = i;
									break;
								}
							}
						}
					}
				} else {
					currentFertileAreaIndex = -1;
					currentFertileArea = null;
				}
			}
		}

		// The area amount is the size of the fertile area
		List<Integer> fertileAreas = new ArrayList<>();
		for (Set<LandUnit> fertileArea : unitFertileAreas) {
			fertileAreas.add(fertileArea.size());
		}

		Collections.sort(fertileAreas);

		return fertileAreas;
	}
}
