package org.ethp.barrenland.main;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.ethp.barrenland.main.model.Land;
import org.ethp.barrenland.main.model.Rectangle;

/**
 * Main class that executes the sample scenarios described in technical
 * assessment document
 * 
 * Sample1: {"0 292 399 307"}
 * Sample2: {"48 192 351 207", "48 392 351 407", "120 52 135 547", "260 52 275 547"}
 */
public class BarrenLandAnalysisApp {

	private static final int LAND_WIDTH = 400;
	private static final int LAND_HEIGHT = 600;

	public static void main(String[] args) {
		Set<Rectangle> barrenAreas = getBarrenAreasFromStdIn();
		Land land = new Land(LAND_WIDTH, LAND_HEIGHT, barrenAreas);
		printFertileAreas(land);
	}

	private static Set<Rectangle> getBarrenAreasFromStdIn() {
		Set<Rectangle> barrenAreas = new HashSet<>();
		 
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("[\\s\"]");
		
		do {
			// Scan { or ,
			scanner.next();
			if (barrenAreas.size() > 0) {
				// Scan empty char between space and "
				scanner.next();
			}
			// Scan the rectangle
			final int left = scanner.nextInt();
			final int bottom = scanner.nextInt();
			final int right = scanner.nextInt();
			final int top = scanner.nextInt();
			barrenAreas.add(new Rectangle(left, bottom, right, top));
		} while(scanner.hasNext(","));
		
		scanner.close();
		
		return barrenAreas;
	}

	private static void printFertileAreas(Land land) {
		final List<Integer> fertileAreas = land.getFertileAreas();
		for (Integer fertileArea : fertileAreas) {
			System.out.print(fertileArea);
			System.out.print(" ");
		}

		System.out.println();
	}

}
