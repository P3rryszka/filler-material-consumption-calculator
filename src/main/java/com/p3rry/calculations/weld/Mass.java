package com.p3rry.calculations.weld;

import com.p3rry.calculations.calculationsmanagers.IWeldOperations;
import com.p3rry.utlis.InputMessages;
import com.p3rry.consts.Properties;
import lombok.NonNull;

import java.util.Optional;

public class Mass {
    private double length;
    private IWeldOperations weldSectionArea;
    private double baseMaterialDensity;

    public Mass(double length, @NonNull IWeldOperations weldSectionArea,
                double baseMaterialDensity) {
        this.length = Optional.of(length)
                .filter(l -> l > Properties.LENGTH_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(Properties.LENGTH_LIMIT, "<=");
                    return new IllegalArgumentException("Length cannot be <= " + Properties.LENGTH_LIMIT);
                });

        this.weldSectionArea = Optional.ofNullable(weldSectionArea)
                .orElseThrow(() ->
                        new IllegalArgumentException("Area of weld section is null!")
                );

        this.baseMaterialDensity = Optional.of(baseMaterialDensity)
                .filter(bmd -> bmd > Properties.BASE_MATERIAL_DENSITY_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(Properties.BASE_MATERIAL_DENSITY_LIMIT, "<=");
                    return new IllegalArgumentException("Base material density cannot be <= " + Properties.BASE_MATERIAL_DENSITY_LIMIT);
                });
    }

    private double calculateVolume() {
        return weldSectionArea.calculateWeldSectionArea() * length;
    }

    public double calculateMass() {
        return calculateVolume() * baseMaterialDensity;
    }
}
