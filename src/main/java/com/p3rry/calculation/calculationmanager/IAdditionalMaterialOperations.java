package com.p3rry.calculation.calculationmanager;

@FunctionalInterface
public interface IAdditionalMaterialOperations {
    double calculateNeededAdditionalMaterial(double jointMass);
    default double calculateCylinderShapeMass(double density, double diameter,
                                              double length) {
        return density * Math.PI
                * Math.pow((diameter / 2.0), 2.0) * length;
    }
}
