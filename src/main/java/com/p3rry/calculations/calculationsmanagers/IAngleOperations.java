package com.p3rry.calculations.calculationsmanagers;

public interface IAngleOperations {
    default double convertIntoDegrees(double value, double divider) {
        return Math.tan(Math.toRadians(value/divider));
    }
}
