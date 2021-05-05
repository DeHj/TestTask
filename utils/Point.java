package com.perflab.utils;

import java.util.Scanner;

public class Point {
    public double X;
    public double Y;

    public Point(Scanner scanner) {
        String[] xy  = scanner.nextLine().
                replace("\\n", " ").
                split(" ");

        X = Double.parseDouble(xy[0]);
        Y = Double.parseDouble(xy[1]);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj.getClass() != this.getClass())
            return  false;

        final Point point = (Point) obj;
        return (point.X == this.X && point.Y == this.Y);
    }
}
