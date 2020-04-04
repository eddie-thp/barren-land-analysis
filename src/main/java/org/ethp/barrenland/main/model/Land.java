package org.ethp.barrenland.main.model;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Land {
	private int width;
	private int height;
	private boolean[][] fertile;

	public Land(int width, int height) throws InvalidParameterException 
	{
		
		if (width <= 0)
		{
			throw new InvalidParameterException("Width smaller or equals to 0.");
		}
		
		if (height <= 0)
		{
			throw new InvalidParameterException("Height smaller or equals to 0.");
		}
		
		this.width = width;
		this.height = height;
		this.fertile = new boolean[width][height];

		for (int x = 0; x < width; x++) {
			Arrays.fill(fertile[x], Boolean.TRUE);
		}
	}

	public Land(int width, int height, Set<Rectangle> barrenRectangles) throws InvalidParameterException {
		this(width, height);

		for (Rectangle rectangle : barrenRectangles) {
			setBarrenRectangle(rectangle);
		}
	}

	private void setBarrenRectangle(Rectangle rectangle) throws InvalidParameterException {
		
		final int left = rectangle.getLeft();
		final int bottom = rectangle.getBottom();
		final int right = rectangle.getRight();
		final int top = rectangle.getTop();
		
		if ((left < 0 || left >= width)
				|| (right < 0 || right >= width)
				|| (bottom < 0 || bottom >= height)
				|| (top < 0 || top >= height)) {
			throw new InvalidParameterException("Barren area boundaries are outside of the land area.");
		}
		
		for (int x = left; x <= right; x++) {
			for (int y = bottom; y <= top; y++) {
				this.fertile[x][y] = false;
			}
		}
	}

	/**
	 * Get the total area of the land
	 * @return total area of the land
	 */
	public int getTotalArea() {
		return width * height;
	}

	/**
	 * Gets the sum of all fertile areas
	 * @return total fertile area
	 */
	public int getTotalFertileArea() {
		int fertileArea = 0;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (this.fertile[x][y]) {
					fertileArea++;
				}
			}
		}
		return fertileArea;
	}

	/**
	 * Gets the sum of all barren areas
	 * @return total barren area
	 */
	public int getTotalBarrenArea() {
		int barrenArea = 0;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (!this.fertile[x][y]) {
					barrenArea++;
				}
			}
		}
		return barrenArea;
	}

	public List<Integer> getFertileAreas() {
		// TODO bla bla
		return null;
	}

}
