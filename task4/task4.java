package com.perflab.program;

import com.perflab.utils.ArgsCountException;
import com.perflab.utils.InputDataException;
import com.perflab.utils.TimeSegment;
import com.perflab.utils.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class task4 {
    public static void main(String[] args) throws ArgsCountException, FileNotFoundException, InputDataException {

        if (args.length < 1)
            throw new ArgsCountException();

        // Time presented in minutes. 0 is 8:00 and 720 is 20:00
        // The peopleDiff array presents the difference of visitor count
        // 0 - no visitors. 3 - three visitors entered. -2 - two visitors exits
        int nMinutes = 721;
        int[] peopleDiff = new int[nMinutes + 1];

        String fileName = util.getFullPath(args[0]);
        Scanner scanner = new Scanner(new FileReader(fileName));

        // Finding the peopleDiff:
        while (scanner.hasNext()) {
            String[] inOut = scanner.nextLine().replace("\\n", "").split(" ");
            if (inOut.length < 2)
                throw new InputDataException();

            int in = timeToInt(inOut[0]);
            int out = timeToInt(inOut[1]);

            try {
                peopleDiff[in]++;
                peopleDiff[out]--;
            }
            catch (IndexOutOfBoundsException e) {
                throw new InputDataException();
            }
        }

        // The peopleCount array presents people count
        int[] peopleCount = new int[nMinutes + 1];

        peopleCount[0] = peopleDiff[0];
        for (int i = 1; i < nMinutes; i++) {
            peopleCount[i] = peopleCount[i - 1];

            if (peopleDiff[i] > 0)
                peopleCount[i] += peopleDiff[i];

            if (peopleDiff[i - 1] < 0)
                peopleCount[i] += peopleDiff[i - 1];
        }

        // Finding a time segment with the maximum number of visitors:
        ArrayList<TimeSegment> segments = new ArrayList<>(1);
        segments.add(new TimeSegment(0, 720, 0));

        int curFrom = 0;
        for (int i = 0; i < nMinutes; i++) {
            // Check in case of segment end:
            if (peopleCount[i + 1] < peopleCount[i]) {
                if (peopleCount[i] > segments.get(0).visitorsCount) {
                    segments.clear();
                    segments.add(new TimeSegment(curFrom, i, peopleCount[i]));
                }
                else if (peopleCount[i] == segments.get(0).visitorsCount) {
                    segments.add(new TimeSegment(curFrom, i, peopleCount[i]));
                }
            }
            // Check in case of segment start:
            if (peopleCount[i + 1] > peopleCount[i]) {
                curFrom = i + 1;
            }
        }

        for (TimeSegment segment: segments) {
            System.out.println(intToTime(segment.from) + " " + intToTime(segment.to));
        }
    }


    public static int timeToInt(String time) throws ArgsCountException {
        String[] hm = time.split(":");
        if (hm.length < 1)
            throw new ArgsCountException();

        return (Integer.parseInt(hm[0]) - 8) * 60 + Integer.parseInt(hm[1]);
    }

    public static String intToTime(int time) throws ArgsCountException {
        String hours = Integer.toString(time / 60 + 8);
        String minutes = Integer.toString(time % 60);
        if (minutes.length() == 1)
            minutes = "0" + minutes;
        return (hours + ":" + minutes);
    }
}