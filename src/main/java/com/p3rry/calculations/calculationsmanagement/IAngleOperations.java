package com.p3rry.calculations.calculationsmanagement;

public interface IAngleOperations {
    default double convertIntoDegrees(double value, double divider) {
        return Math.tan(Math.toRadians(value/divider));
    }
}
