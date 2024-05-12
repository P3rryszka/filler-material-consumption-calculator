package com.p3rry.calculations.weld;

import com.p3rry.calculations.calculationsmanagement.IWeldOperations;
import com.p3rry.components.utlis.InputMessages;
import com.p3rry.consts.Properties;
import lombok.NonNull;

import java.util.Optional;

public class Mass {
    private double length;
    private IWeldOperations weldSectionArea;

    public Mass(double length, @NonNull IWeldOperations weldSectionArea) {
        this.length = Optional.of(length)
                .filter(l -> l > Properties.LENGTH_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParameterCannotBe(Properties.LENGTH_LIMIT, "LE");
                    return new IllegalArgumentException("Length cannot be <= " + Properties.LENGTH_LIMIT);
                });
        this.weldSectionArea = weldSectionArea;
    }

    private double calculateVolume() {
        return weldSectionArea.calculateWeldSectionArea() * length;
    }

    public double calculateMass(double density) {
        return calculateVolume() * density;
    }
}
