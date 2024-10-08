package com.p3rry.calculation.calculationmanager;

import com.p3rry.consts.CommonProperties;

@FunctionalInterface
public interface IFillerMaterialOperations {
    double calculateFillerMaterial(double jointMass);
    default double calculateCylinderShapeMass(double density, double diameter,
                                              double length) {
        return (density * CommonProperties.TO_KG_MM3_FACTOR) * Math.PI
                * Math.pow((diameter / 2.0), 2.0) * length;
    }
}