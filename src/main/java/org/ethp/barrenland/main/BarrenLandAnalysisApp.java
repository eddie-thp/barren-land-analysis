package org.ethp.barrenland.main;

import java.util.HashSet;
import java.util.Set;

import org.ethp.barrenland.main.model.Land;
import org.ethp.barrenland.main.model.Rectangle;

public class BarrenLandAnalysisApp {
	
    private static final int LAND_WIDTH = 400;
    private static final int LAND_HEIGHT = 600;

	public static void main(String[] args) {
        Rectangle barrenArea = new Rectangle(0, 292, 399, 307);

        Set<Rectangle> barrentAreas = new HashSet<>();
        barrentAreas.add(barrenArea);

        Land land = new Land(LAND_WIDTH, LAND_HEIGHT, barrentAreas);

        System.out.println("RECT W " + barrenArea.getWidth());
        System.out.println("RECT H " + barrenArea.getHeight());
        System.out.println("RECT Area " + barrenArea.getArea());
        System.out.println("LAND Area " + land.getArea());
        System.out.println("FERTILE AREA " + ((land.getArea() - barrenArea.getArea()) / 2));

	}

}
