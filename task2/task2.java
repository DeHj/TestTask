package com.perflab.program;

import com.perflab.utils.ArgsCountException;
import com.perflab.utils.Point;
import com.perflab.utils.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class task2 {
    public static void main(String[] args) throws ArgsCountException, FileNotFoundException {

        if (args.length < 2)
            throw new ArgsCountException();

        int n = 4;
        ArrayList<Point> shape = new ArrayList<Point>(n);
        ArrayList<Point> points = new ArrayList<Point>();

        // Read shape from file:
        String fileName = util.getFullPath(args[0]);
        Scanner scanner = new Scanner(new FileReader(fileName));
        for (int i = 0; i < n; i++) {
            shape.add(new Point(scanner));
        }

        // Read points from file:
        fileName = util.getFullPath(args[1]);
        scanner = new Scanner(new FileReader(fileName));
        while (scanner.hasNext()) {
            points.add(new Point(scanner));
        }

        // Find position for every point relative to the shape:
        for (Point point: points) {
            System.out.println(pointPosition(shape, point));
        }
    }

    static int pointPosition(ArrayList<Point> shape, Point point) {
        int n = shape.size();

        // Check in case 0:
        for (Point p : shape) {
            if (point.equals(p))
                return 0;
        }

        // Check in case 1:
        for (int i = 0; i < n - 1; i++) {
            if (onSegment(shape.get(i), shape.get(i + 1), point))
                return 1;
        }
        if (onSegment(shape.get(0), shape.get(n - 1), point))
            return 1;

        // Check on 2 or 3 case:
        for (int i = 0; i < n - 1; i++) {
            if (onRight(shape.get(i), shape.get(i + 1), point) == false)
                return 3;
        }
        if (onRight(shape.get(n - 1), shape.get(0), point) == false)
            return 3;

        return 2;
    }

    static boolean onSegment(Point a, Point b, Point c) {
        if ((c.Y - a.Y) * (b.X - a.X) != (b.Y - a.Y) * (c.X - a.X))
            return false;
        return c.X >= Math.min(a.X, b.X) &&
                c.X <= Math.max(a.X, b.X) &&
                c.Y >= Math.min(a.Y, b.Y) &&
                c.Y <= Math.max(a.Y, b.Y);
    }

    static boolean onRight(Point a, Point b, Point c) {
        return (c.Y - a.Y) * (b.X - a.X) - (b.Y - a.Y) * (c.X - a.X) < 0;
    }
}
