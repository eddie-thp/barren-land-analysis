package org.ethp.barrenland.main.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RectangleTest {

	@Test
	public void testConstructor() {
		final int left = 0;
		final int bottom = 10;
		final int right = 399;
		final int top = 509;

		Rectangle rectangle = new Rectangle(left, bottom, right, top);

		assertEquals(left, rectangle.getLeft());
		assertEquals(bottom, rectangle.getBottom());
		assertEquals(right, rectangle.getRight());
		assertEquals(top, rectangle.getTop());
		assertEquals(400, rectangle.getWidth());
		assertEquals(500, rectangle.getHeight());
		assertEquals(500 * 400, rectangle.getArea());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLeftNegativeException() {
		new Rectangle(-1, 0, 1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBottomNegativeException() {
		new Rectangle(0, -1, 1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLeftSmallerThanRightException() {
		new Rectangle(1, 0, 0, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBottomSmallerThanTopException() {
		new Rectangle(0, 1, 1, 0);
	}

}
