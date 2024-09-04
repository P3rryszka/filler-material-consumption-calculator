package com.p3rry.calculation.additionalmaterial;

import com.p3rry.calculation.calculationmanager.IAdditionalMaterialOperations;
import com.p3rry.consts.CommonProperties;
import com.p3rry.utlis.InputMessages;

import java.util.Optional;

public class GmawAdditionalMaterial implements IAdditionalMaterialOperations {
    private static final double EFFECTIVE_WIRE_SPOOL_MASS_FACTOR = 0.986;
    private static final double WIRE_SPOOL_MASS_LIMIT = 0;
    private static final double CUT_FACTOR = 1.05;

    private double effectiveWireSpoolMass;

    public GmawAdditionalMaterial(double wireSpoolMass) {
        this.effectiveWireSpoolMass = Optional.of(wireSpoolMass * EFFECTIVE_WIRE_SPOOL_MASS_FACTOR)
                .filter(wsm -> wsm > WIRE_SPOOL_MASS_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(
                            WIRE_SPOOL_MASS_LIMIT, "<=", "Wire spool mass"
                    );
                    return new IllegalArgumentException("Wire spool mass cannot be <= " + WIRE_SPOOL_MASS_LIMIT);
                });
    }

    @Override
    public double calculateNeededAdditionalMaterial(double jointMass) {
        return jointMass / (
                effectiveWireSpoolMass *
                CommonProperties.WELD_SPATTER_FACTOR *
                CUT_FACTOR
        );
    }
}
