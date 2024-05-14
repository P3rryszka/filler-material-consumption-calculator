package com.p3rry.calculations.additionalmaterial;

import com.p3rry.calculations.calculationsmanagement.IAdditionalMaterialOperations;
import com.p3rry.consts.Properties;
import com.p3rry.utlis.InputMessages;

import java.util.Optional;

public class SmawAdditionalMaterial implements IAdditionalMaterialOperations {
    public static final double EFFECTIVE_ELECTRODE_LENGTH_FACTOR = 0.875;

    private double effectiveElectrodeLength;
    private double electrodeDiameter;


    public SmawAdditionalMaterial(double electrodeLength, double electrodeDiameter) {
        this.effectiveElectrodeLength = Optional.of(electrodeLength * EFFECTIVE_ELECTRODE_LENGTH_FACTOR)
                .filter(el -> el > Properties.ELECTRODE_LENGTH_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(Properties.ELECTRODE_LENGTH_LIMIT, "LE");
                    return new IllegalArgumentException("Electrode length cannot be <= " +
                            Properties.ELECTRODE_LENGTH_LIMIT);
                });

        this.electrodeDiameter = Optional.of(electrodeDiameter)
                .filter(ed -> ed > Properties.ELECTRODE_DIAMETER_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(Properties.ELECTRODE_DIAMETER_LIMIT, "LE");
                    return new IllegalArgumentException("Electrode diameter cannot be <= " +
                           Properties.ELECTRODE_DIAMETER_LIMIT);
                });
    }

    @Override
    public double calculateNeededAdditionalMaterial(double jointMass) {
        return jointMass / ((Properties.STEEL_DENSITY_KG_MM3 * Math.PI *
                Math.pow((electrodeDiameter/2.0), 2.0) * effectiveElectrodeLength) *
                Properties.DEPOSITED_METAL_YIELD * Properties.WELD_SPATTER_FACTOR) *
                Properties.DESTROY_FACTOR;
    }
}
