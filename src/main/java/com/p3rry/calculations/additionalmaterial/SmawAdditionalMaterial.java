package com.p3rry.calculations.additionalmaterial;

import com.p3rry.calculations.calculationsmanagement.IAdditionalMaterialOperations;
import com.p3rry.consts.Properties;
import com.p3rry.utlis.InputMessages;

import java.util.Optional;

public class SmawAdditionalMaterial implements IAdditionalMaterialOperations {
    private static final double EFFECTIVE_ELECTRODE_LENGTH_FACTOR = 0.875;
    private static final int FROM_PERCENT_FACTOR = 100;

    private double effectiveElectrodeLength;
    private double electrodeDiameter;
    private double density;
    private double electrodeYield;

    public SmawAdditionalMaterial(double electrodeLength, double electrodeDiameter,
                                  double density, double electrodeYield) {
        this.effectiveElectrodeLength = Optional.of(electrodeLength * EFFECTIVE_ELECTRODE_LENGTH_FACTOR)
                .filter(el -> el > Properties.ELECTRODE_LENGTH_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(Properties.ELECTRODE_LENGTH_LIMIT, "<=");
                    return new IllegalArgumentException("Electrode length cannot be <= " +
                            Properties.ELECTRODE_LENGTH_LIMIT);
                });

        this.electrodeDiameter = Optional.of(electrodeDiameter)
                .filter(ed -> ed > Properties.ELECTRODE_DIAMETER_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(Properties.ELECTRODE_DIAMETER_LIMIT, "<=");
                    return new IllegalArgumentException("Electrode diameter cannot be <= " +
                           Properties.ELECTRODE_DIAMETER_LIMIT);
                });

        this.density = Optional.of(density)
                .filter(d -> d > Properties.DENSITY_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(Properties.DENSITY_LIMIT, "<=");
                    return new IllegalArgumentException("Density cannot be <= " +
                            Properties.DENSITY_LIMIT);
                });

        this.electrodeYield = Optional.of(electrodeYield / FROM_PERCENT_FACTOR)
                .filter(ey -> ey >= Properties.ELECTRODE_YIELD)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(Properties.ELECTRODE_YIELD, "<");
                    return new IllegalArgumentException("Electrode yield cannot be < " +
                            Properties.ELECTRODE_YIELD);
                });
    }

    @Override
    public double calculateNeededAdditionalMaterial(double jointMass) {
        return jointMass / (calculateCylinderVolume(density, electrodeDiameter, effectiveElectrodeLength) *
                electrodeYield * Properties.WELD_SPATTER_FACTOR *
                Properties.DESTROY_FACTOR);
    }
}
