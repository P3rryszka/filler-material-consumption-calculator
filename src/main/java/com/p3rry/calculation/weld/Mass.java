package com.p3rry.calculation.weld;

import com.p3rry.calculation.calculationmanager.IWeldOperations;
import com.p3rry.utlis.InputMessages;
import lombok.NonNull;

import java.util.Optional;

public class Mass {
    private static final double LENGTH_LIMIT = 0;
    private static final double BASE_MATERIAL_DENSITY_LIMIT = 0;
    private double length;
    private IWeldOperations weldSectionArea;
    private double baseMaterialDensity;

    public Mass(double length, @NonNull IWeldOperations weldSectionArea,
                double baseMaterialDensity) {
        this.length = Optional.of(length)
                .filter(l -> l > LENGTH_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(
                            LENGTH_LIMIT, "<=", "Length"
                    );
                    return new IllegalArgumentException("Length cannot be <= " + LENGTH_LIMIT);
                });

        this.weldSectionArea = Optional.ofNullable(weldSectionArea)
                .orElseThrow(() ->
                        new IllegalArgumentException("Area of weld section is null!")
                );

        this.baseMaterialDensity = Optional.of(baseMaterialDensity)
                .filter(bmd -> bmd > BASE_MATERIAL_DENSITY_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(
                            BASE_MATERIAL_DENSITY_LIMIT, "<=", "Base material density"
                    );
                    return new IllegalArgumentException("Base material density cannot be <= " + BASE_MATERIAL_DENSITY_LIMIT);
                });
    }

    private double calculateVolume() {
        return weldSectionArea.calculateWeldSectionArea() * length;
    }

    public double calculateMass() {
        return calculateVolume() * baseMaterialDensity;
    }
}
