package com.p3rry.calculation.calculationmanager;

public interface IAngleOperations {
    default double convertIntoDegrees(double value, double divider) {
        return Math.tan(Math.toRadians(value/divider));
    }
}
