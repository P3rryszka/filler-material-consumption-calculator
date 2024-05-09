package com.p3rry.calculations.weld;

import com.p3rry.calculations.calculationsmanagement.IWeldOperations;
import lombok.NonNull;

public class Mass {
    private double length;
    private IWeldOperations weldSectionArea;

    public Mass(double length, @NonNull IWeldOperations weldSectionArea) {
        this.length = length;
        this.weldSectionArea = weldSectionArea;
    }

    private double calculateVolume() {
        return weldSectionArea.calculateWeldSectionArea() * length;
    }

    public double calculateMass(double density) {
        return calculateVolume() * density;
    }
}
