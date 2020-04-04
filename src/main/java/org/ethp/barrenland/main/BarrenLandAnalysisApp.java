package org.ethp.barrenland.main;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.ethp.barrenland.main.model.Land;
import org.ethp.barrenland.main.model.Rectangle;

public class BarrenLandAnalysisApp {

	private static final int LAND_WIDTH = 400;
	private static final int LAND_HEIGHT = 600;

	public static void main(String[] args) {
		executeSample0();
		executeSample1();
	}

	public static void executeSample0() {
		Rectangle barrenArea = new Rectangle(0, 292, 399, 307);

		Set<Rectangle> barrentAreas = new HashSet<>();
		barrentAreas.add(barrenArea);

		Land land = new Land(LAND_WIDTH, LAND_HEIGHT, barrentAreas);

		printFertileAreas(land);
	}

	public static void executeSample1() {
		Rectangle barrenArea0 = new Rectangle(48, 192, 351, 207);
		Rectangle barrenArea1 = new Rectangle(48, 392, 351, 407);
		Rectangle barrenArea2 = new Rectangle(120, 52, 135, 547);
		Rectangle barrenArea3 = new Rectangle(260, 52, 275, 547);

		Set<Rectangle> barrentAreas = new HashSet<>();
		barrentAreas.add(barrenArea0);
		barrentAreas.add(barrenArea1);
		barrentAreas.add(barrenArea2);
		barrentAreas.add(barrenArea3);

		Land land = new Land(LAND_WIDTH, LAND_HEIGHT, barrentAreas);

		printFertileAreas(land);
	}

	public static void printFertileAreas(Land land) {

		System.out.println("AREA: " + land.getArea());
		System.out.println("FERTILE AREA: " + land.getTotalFertileArea());
		System.out.println("BARREN AREA: " + land.getTotalBarrenArea());
		System.out.print("FERTILE AREAS: ");

		final List<Integer> fertileAreas = land.getFertileAreas();
		for (Integer fertileArea : fertileAreas) {
			System.out.print(fertileArea);
			System.out.print(" ");
		}

		System.out.println();
	}

}
