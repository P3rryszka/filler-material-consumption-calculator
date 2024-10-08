package com.p3rry.calculation.additionalmaterial;

import com.p3rry.calculation.calculationmanager.IFillerMaterialOperations;
import com.p3rry.consts.CommonProperties;
import com.p3rry.utlis.InputMessages;

import java.util.Optional;

public class SmawMethod implements IFillerMaterialOperations {
    private static final double EFFECTIVE_ELECTRODE_LENGTH_FACTOR = 0.875;
    private static final int PERCENT_YIELD_FACTOR = 100;
    private static final double ELECTRODE_LENGTH_LIMIT = 0;
    private static final double ELECTRODE_DIAMETER_LIMIT = 0;
    private static final double ELECTRODE_YIELD_LIMIT = 100;
    private static final double DESTROY_FACTOR = 1.01;

    private double effectiveElectrodeLength;
    private double electrodeDiameter;
    private double density;
    private double electrodeYield;

    public SmawMethod(double electrodeLength, double electrodeDiameter,
                      double density, double electrodeYield) {
        this.effectiveElectrodeLength = Optional.of(electrodeLength * EFFECTIVE_ELECTRODE_LENGTH_FACTOR)
                .filter(el -> el > ELECTRODE_LENGTH_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(
                            ELECTRODE_LENGTH_LIMIT, "<=", "Electrode length"
                    );
                    return new IllegalArgumentException("Electrode length cannot be <= " +
                            ELECTRODE_LENGTH_LIMIT);
                });

        this.electrodeDiameter = Optional.of(electrodeDiameter)
                .filter(ed -> ed > ELECTRODE_DIAMETER_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(
                            ELECTRODE_DIAMETER_LIMIT, "<=", "Electrode diameter"
                    );
                    return new IllegalArgumentException("Electrode diameter cannot be <= " +
                           ELECTRODE_DIAMETER_LIMIT);
                });

        this.density = Optional.of(density)
                .filter(d -> d > CommonProperties.FILLER_MATERIAL_DENSITY)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(
                            CommonProperties.FILLER_MATERIAL_DENSITY, "<=", "Filler material density"
                    );
                    return new IllegalArgumentException("Filler material density cannot be <= " +
                            CommonProperties.FILLER_MATERIAL_DENSITY);
                });

        this.electrodeYield = Optional.of(electrodeYield)
                .filter(ey -> ey >= ELECTRODE_YIELD_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(
                            ELECTRODE_YIELD_LIMIT, "<", "Electrode yield"
                    );
                    return new IllegalArgumentException("Electrode yield cannot be < " +
                            ELECTRODE_YIELD_LIMIT);
                });
    }

    @Override
    public double calculateFillerMaterial(double jointMass) {
        return jointMass / (
                calculateCylinderShapeMass(density, electrodeDiameter, effectiveElectrodeLength) *
                    (electrodeYield / PERCENT_YIELD_FACTOR) *
                        CommonProperties.WELD_SPATTER_FACTOR *
                        DESTROY_FACTOR
        );
    }
}
