package com.p3rry.calculations.additionalmaterial;

import com.p3rry.calculations.calculationsmanagement.IAdditionalMaterialOperations;
import com.p3rry.consts.Properties;

import java.util.Optional;

public class GmawAdditionalMaterial implements IAdditionalMaterialOperations {
    public static final double EFFECTIVITY_WIRE_SPOOL_MASS_FACTOR = 0.986;

    private double effectiveWireSpoolMass;

    public GmawAdditionalMaterial(double wireSpoolMass) {
        this.effectiveWireSpoolMass = Optional.of(wireSpoolMass * EFFECTIVITY_WIRE_SPOOL_MASS_FACTOR)
                .filter(wsm -> wsm > Properties.WIRE_SPOOL_MASS_LIMIT)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("Wire spool mass cannot be <= " + Properties.WIRE_SPOOL_MASS_LIMIT);
                });
    }

    @Override
    public double calculateNeededAdditionalMaterial(double jointMass) {
        return jointMass / (effectiveWireSpoolMass  * Properties.WELD_SPATTER_FACTOR);
    }
}
