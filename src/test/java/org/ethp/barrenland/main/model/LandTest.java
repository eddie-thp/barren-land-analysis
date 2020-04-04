package org.ethp.barrenland.main.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.hamcrest.core.IsIterableContaining;
import org.junit.Test;

public class LandTest {

	private static final int LAND_WIDTH = 400;
	private static final int LAND_HEIGHT = 600;

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidWidthException() {
		new Land(0, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidHeightException() {
		new Land(1, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidBarrenRectangleException() {

		Rectangle barrenArea = new Rectangle(0, 0, 9, 9);

		Set<Rectangle> barrentAreas = new HashSet<>();
		barrentAreas.add(barrenArea);

		new Land(1, 1, barrentAreas);
	}

	@Test
	public void testFertileLand() {
		Land land = new Land(400, 600);
		final int TOTAL_AREA = LAND_WIDTH * LAND_HEIGHT;
		assertEquals(TOTAL_AREA, land.getArea());
		assertEquals(TOTAL_AREA, land.getTotalFertileArea());
		assertEquals(0, land.getTotalBarrenArea());

		final List<Integer> fertileAreas = land.getFertileAreas();
		assertEquals(1, fertileAreas.size());

		MatcherAssert.assertThat(fertileAreas, IsIterableContaining.hasItems(240000));
	}

	@Test
	public void testBarrenLand() {
		Rectangle barrenArea = new Rectangle(0, 0, 399, 599);

		Set<Rectangle> barrentAreas = new HashSet<>();
		barrentAreas.add(barrenArea);

		Land land = new Land(400, 600, barrentAreas);
		final int TOTAL_AREA = LAND_WIDTH * LAND_HEIGHT;
		assertEquals(TOTAL_AREA, land.getArea());
		assertEquals(0, land.getTotalFertileArea());
		assertEquals(TOTAL_AREA, land.getTotalBarrenArea());

		final List<Integer> fertileAreas = land.getFertileAreas();
		assertTrue(fertileAreas.isEmpty());
	}

	@Test
	public void testFirstTechnicalSample() {
		Rectangle barrenArea = new Rectangle(0, 292, 399, 307);

		Set<Rectangle> barrentAreas = new HashSet<>();
		barrentAreas.add(barrenArea);

		Land land = new Land(400, 600, barrentAreas);
		final int TOTAL_AREA = LAND_WIDTH * LAND_HEIGHT;
		assertEquals(TOTAL_AREA, land.getArea());
		assertEquals(TOTAL_AREA - barrenArea.getArea(), land.getTotalFertileArea());
		assertEquals(barrenArea.getArea(), land.getTotalBarrenArea());

		final List<Integer> fertileAreas = land.getFertileAreas();
		assertEquals(2, fertileAreas.size());

		MatcherAssert.assertThat(fertileAreas, IsIterableContainingInOrder.contains(116800, 116800));
	}

	@Test
	public void testSecondTechnicalSample() {
		Rectangle barrenArea0 = new Rectangle(48, 192, 351, 207);
		Rectangle barrenArea1 = new Rectangle(48, 392, 351, 407);
		Rectangle barrenArea2 = new Rectangle(120, 52, 135, 547);
		Rectangle barrenArea3 = new Rectangle(260, 52, 275, 547);

		Set<Rectangle> barrentAreas = new HashSet<>();
		barrentAreas.add(barrenArea0);
		barrentAreas.add(barrenArea1);
		barrentAreas.add(barrenArea2);
		barrentAreas.add(barrenArea3);

		Land land = new Land(400, 600, barrentAreas);
		final int TOTAL_AREA = LAND_WIDTH * LAND_HEIGHT;
		assertEquals(TOTAL_AREA, land.getArea());
		assertEquals(215424, land.getTotalFertileArea());
		assertEquals(24576, land.getTotalBarrenArea());

		final List<Integer> fertileAreas = land.getFertileAreas();
		assertEquals(2, fertileAreas.size());

		MatcherAssert.assertThat(fertileAreas, IsIterableContainingInOrder.contains(22816, 192608));
	}

	
	@Test
	public void testSecondScenarioExtended() {
		Rectangle barrenArea0 = new Rectangle(0, 192, 399, 207);
		Rectangle barrenArea1 = new Rectangle(0, 392, 399, 407);
		Rectangle barrenArea2 = new Rectangle(120, 0, 135, 599);
		Rectangle barrenArea3 = new Rectangle(260, 0, 275, 599);

		Set<Rectangle> barrentAreas = new HashSet<>();
		barrentAreas.add(barrenArea0);
		barrentAreas.add(barrenArea1);
		barrentAreas.add(barrenArea2);
		barrentAreas.add(barrenArea3);
		
		Land land = new Land(400, 600, barrentAreas);
		final int TOTAL_AREA = LAND_WIDTH * LAND_HEIGHT;
		assertEquals(TOTAL_AREA, land.getArea());
		assertEquals(209024, land.getTotalFertileArea());
		assertEquals(30976, land.getTotalBarrenArea());

		final List<Integer> fertileAreas = land.getFertileAreas();
		assertEquals(9, fertileAreas.size());
		
		MatcherAssert.assertThat(fertileAreas, IsIterableContainingInOrder.contains(22080, 22816, 22816, 23040, 23040, 23808, 23808, 23808, 23808));
	}

}
