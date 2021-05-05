package com.perflab.program;

import com.perflab.utils.ArgsCountException;
import com.perflab.utils.InputDataException;
import com.perflab.utils.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class task3 {
    public static void main(String[] args) throws ArgsCountException, FileNotFoundException, InputDataException {

        if (args.length < 1)
            throw new ArgsCountException();

        String path = util.getFullPath(args[0]);

        ArrayList<Double> cash1 = readFromFile(path + "\\Cash1.txt");
        ArrayList<Double> cash2 = readFromFile(path + "\\Cash2.txt");
        ArrayList<Double> cash3 = readFromFile(path + "\\Cash3.txt");
        ArrayList<Double> cash4 = readFromFile(path + "\\Cash4.txt");
        ArrayList<Double> cash5 = readFromFile(path + "\\Cash5.txt");

        if (cash1.size() != cash2.size() ||
                cash1.size() != cash3.size() ||
                cash1.size() != cash4.size() ||
                cash1.size() != cash5.size())
            throw new InputDataException();

        int n = cash1.size();

        int iMax = 0;
        double maxSum = cash1.get(iMax) + cash2.get(iMax) + cash3.get(iMax) + cash4.get(iMax) + cash5.get(iMax);
        for (int i = 1; i < n; i++) {
            double sum = cash1.get(i) + cash2.get(i) + cash3.get(i) + cash4.get(i) + cash5.get(i);
            if (sum > maxSum)
            {
                maxSum = sum;
                iMax = i;
            }
        }

        System.out.println(iMax + 1);
    }

    private static ArrayList<Double> readFromFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(fileName));
        ArrayList<Double> result = new ArrayList<>(16);
        while (scanner.hasNext()) {
            result.add(Double.parseDouble(scanner.
                    nextLine().
                    replace("\\n", "")));
        }
        return result;
    }
}
