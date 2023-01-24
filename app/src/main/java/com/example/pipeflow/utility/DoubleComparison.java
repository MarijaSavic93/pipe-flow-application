package com.example.pipeflow.utility;

public class DoubleComparison {
    public static final double EPSILON = 1E-6;

    public boolean firstGreaterThanSecondDouble(double a, double b){
        return a - b > EPSILON;
    }
    public boolean equalsForDoubles(double a, double b){
        return Math.abs(a - b) <= EPSILON;
    }
    public boolean firstGreaterOrEqualToSecondDouble(double a, double b){
        return firstGreaterThanSecondDouble(a, b) || equalsForDoubles(a, b);
    }
}
