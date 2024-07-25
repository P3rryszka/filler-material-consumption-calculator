package com.p3rry.calculations.additionalmaterial;

import com.p3rry.calculations.calculationsmanagers.IAdditionalMaterialOperations;
import com.p3rry.consts.Properties;
import com.p3rry.utlis.InputMessages;

import java.util.Optional;

public class GtawAdditionalMaterial implements IAdditionalMaterialOperations{
    private static final double EFFECTIVE_ROD_LENGTH = 0.96;
    private double effectiveRodLength;
    private double rodDiameter;
    private double density;

    public GtawAdditionalMaterial(double effectiveRodLength, double rodDiameter,
                                  double density) {
        this.effectiveRodLength = Optional.of(effectiveRodLength * EFFECTIVE_ROD_LENGTH)
                .filter(rl -> rl > Properties.ROD_LENGTH_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(Properties.ROD_LENGTH_LIMIT, "<=");
                    return new IllegalArgumentException("Rod length cannot be <= " +
                            Properties.ROD_LENGTH_LIMIT);
                });

        this.rodDiameter = Optional.of(rodDiameter)
                .filter(rd -> rd > Properties.ROD_LENGTH_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(Properties.ROD_DIAMETER_LIMIT, "<=");
                    return new IllegalArgumentException("Rod diameter cannot be <= " +
                            Properties.ROD_DIAMETER_LIMIT);
                });

        this.density = Optional.of(density)
                .filter(d -> d > Properties.DENSITY_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(Properties.DENSITY_LIMIT, "<=");
                    return new IllegalArgumentException("Density cannot be <= " +
                            Properties.DENSITY_LIMIT);
                });
    }

    @Override
    public double calculateNeededAdditionalMaterial(double jointMass) {
        return jointMass / (calculateCylinderVolume(density, rodDiameter, effectiveRodLength));
    }
}
