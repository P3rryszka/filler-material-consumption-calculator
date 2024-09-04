package com.p3rry.calculation.additionalmaterial;

import com.p3rry.calculation.calculationmanager.IAdditionalMaterialOperations;
import com.p3rry.consts.CommonProperties;
import com.p3rry.utlis.InputMessages;

import java.util.Optional;

public class GtawAdditionalMaterial implements IAdditionalMaterialOperations{
    private static final double EFFECTIVE_ROD_LENGTH = 0.96;
    private static final double ROD_LENGTH_LIMIT = 0;
    private static final double ROD_DIAMETER_LIMIT = 0;
    private double effectiveRodLength;
    private double rodDiameter;
    private double density;

    public GtawAdditionalMaterial(double effectiveRodLength, double rodDiameter,
                                  double density) {
        this.effectiveRodLength = Optional.of(effectiveRodLength * EFFECTIVE_ROD_LENGTH)
                .filter(rl -> rl > ROD_LENGTH_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(
                            ROD_LENGTH_LIMIT, "<=", "Rod length"
                    );
                    return new IllegalArgumentException("Rod length cannot be <= " + ROD_LENGTH_LIMIT);
                });

        this.rodDiameter = Optional.of(rodDiameter)
                .filter(rd -> rd > ROD_DIAMETER_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(
                            ROD_DIAMETER_LIMIT, "<=", "Rod diameter"
                    );
                    return new IllegalArgumentException("Rod diameter cannot be <= " + ROD_DIAMETER_LIMIT);
                });

        this.density = Optional.of(density)
                .filter(d -> d > CommonProperties.FILLER_MATERIAL_DENSITY_MATERIAL)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(
                            CommonProperties.FILLER_MATERIAL_DENSITY_MATERIAL, "<=", "Filler material density"
                    );
                    return new IllegalArgumentException("Filler material density cannot be <= " + CommonProperties.FILLER_MATERIAL_DENSITY_MATERIAL);
                });
    }

    @Override
    public double calculateNeededAdditionalMaterial(double jointMass) {
        return jointMass / (
                calculateCylinderShapeMass(density,
                        rodDiameter,
                        effectiveRodLength)
        );
    }
}
