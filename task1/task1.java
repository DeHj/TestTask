package com.perflab.program;

import com.perflab.utils.ArgsCountException;
import com.perflab.utils.util;

import java.io.*;
import java.util.*;

public class task1 {
    public static void main(String[] args) throws FileNotFoundException, ArgsCountException {

        if (args.length < 1)
            throw new ArgsCountException();

        String fileName = util.getFullPath(args[0]);
        Scanner scanner = new Scanner(new FileReader(fileName));

        ArrayList<Double> input = new ArrayList<Double>();
        while (scanner.hasNext()) {
            input.add(scanner.nextDouble());
        }

        Collections.sort(input);

        toScreen(percentile(input, 0.9));
        toScreen(median(input));
        toScreen(max(input));
        toScreen(min(input));
        toScreen(average(input));
    }



    // The input is assumed to be sorted
    static double percentile(List<Double> input, double degree) {
        int n = input.size();
        double x = degree * (n - 1);
        int i = (int) x;
        double offset = x % 1;
        return input.get(i) + offset * (input.get(i + 1) - input.get(i));
    }

    // The input is assumed to be sorted
    static double median(List<Double> input) {
        int n = input.size();
        if (n % 2 == 1)
            return input.get(n / 2);
        else
            return (input.get(n / 2) + input.get(n / 2 - 1)) / 2;
    }

    // The input is assumed to be sorted
    static double max(List<Double> input) {
        return input.get(input.size() - 1);
    }

    static double min(List<Double> input) {
        return input.get(0);
    }

    // The input is assumed to be sorted
    static double average(List<Double> input) {
        double sum = 0;
        for (double item: input) {
            sum += item;
        }
        return sum / input.size();
    }



    // Formatted output of double
    static void toScreen(double d) {
        System.out.println(String.format("%.2f", d).replace(",", "."));
    }
}
