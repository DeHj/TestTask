package com.perflab.utils;

public class TimeSegment {
    public int from;
    public int to;
    public int visitorsCount;

    public TimeSegment(int a, int b, int vC) {
        from = a;
        to = b;
        visitorsCount = vC;
    }
}
