package com.p3rry.calculation.calculationmanager;

public interface IGrooveOperations {
    double calculateGrooveWidth();
    double calculateGrooveSectionArea();

    default double calculateTan(double value, double divider) {
        return Math.tan(Math.toRadians(value/divider));
    }
}
