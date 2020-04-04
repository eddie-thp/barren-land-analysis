package org.ethp.barrenland.main.model;

public class Rectangle {

	private int left;
	private int bottom;
	private int right;
	private int top;

	public Rectangle(int left, int bottom, int right, int top) throws IllegalArgumentException {

		if (left >= right) {
			throw new IllegalArgumentException("Left equals or greater than right");
		}

		if (bottom >= top) {
			throw new IllegalArgumentException("Bottom equals or greater than top");
		}

		this.left = left;
		this.bottom = bottom;
		this.right = right;
		this.top = top;
	}

	public int getLeft() {
		return left;
	}

	public int getBottom() {
		return bottom;
	}

	public int getRight() {
		return right;
	}

	public int getTop() {
		return top;
	}

	public int getWidth() {
		return (right - left) + 1;
	}

	public int getHeight() {
		return (top - bottom) + 1;
	}

	public int getArea() {
		return getWidth() * getHeight();
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(getArea());
	}

	@Override
	public boolean equals(Object object) {
		if (object == null || !(object instanceof Rectangle)) {
			return false;
		}

		Rectangle rect = (Rectangle) object;

		return (this.left == rect.left && this.bottom == rect.bottom && this.right == rect.right
				&& this.top == rect.top);
	}
}