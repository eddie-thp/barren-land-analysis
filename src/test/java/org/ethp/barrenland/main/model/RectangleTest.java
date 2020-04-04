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


}
