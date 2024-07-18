package com.p3rry.calculations.additionalmaterial;

import com.p3rry.components.weldingmethod.AbstractWeldingMethodPanel;
import lombok.NonNull;

import java.util.Optional;

public class AdditionalMaterialCalculator {
    private static final String MESSAGE = "Invalid input data type!";

    public static double calculateGmawAdditionalMaterial(@NonNull AbstractWeldingMethodPanel weldingMethodPanelType, double mass) {
        try {
            double wireSpoolMass = parseAndCheckNullDouble(weldingMethodPanelType, 0);

            var weldingMethod = new GmawAdditionalMaterial(wireSpoolMass);
            return weldingMethod.calculateNeededAdditionalMaterial(mass);
        } catch (NumberFormatException e) {
            throw new RuntimeException(MESSAGE + e.getMessage());
        }
    }

    public static double calculateSmawAdditionalMaterial(@NonNull AbstractWeldingMethodPanel weldingMethodPanelType, double mass,
                                                         double density) {
       try {
           double electrodeLength = parseAndCheckNullDouble(weldingMethodPanelType, 0);
           double electrodeDiameter = parseAndCheckNullDouble(weldingMethodPanelType, 1);
           double electrodeYield = parseAndCheckNullDouble(weldingMethodPanelType, 2);

           var weldingMethod = new SmawAdditionalMaterial(electrodeLength, electrodeDiameter, density, electrodeYield);
           return weldingMethod.calculateNeededAdditionalMaterial(mass);
       } catch (NumberFormatException e) {
           throw new RuntimeException(MESSAGE + e.getMessage());
       }
    }

    public static double calculateGtawAdditionalMaterial(@NonNull AbstractWeldingMethodPanel weldingMethodPanelType, double mass,
                                                         double density) {
        try {
            double rodLength = parseAndCheckNullDouble(weldingMethodPanelType, 0);
            double rodDiameter = parseAndCheckNullDouble(weldingMethodPanelType, 1);

            var weldingMethod = new GtawAdditionalMaterial(rodLength, rodDiameter, density);
            return weldingMethod.calculateNeededAdditionalMaterial(mass);
        } catch (NumberFormatException e) {
            throw new RuntimeException(MESSAGE + e.getMessage());
        }
    }

    private static double parseAndCheckNullDouble(@NonNull AbstractWeldingMethodPanel weldingMethodPanelType, int index) {
        return Optional.ofNullable(weldingMethodPanelType.getTextComponentsList().get(index).getText())
                .map(Double::parseDouble)
                .orElseThrow(() -> new IllegalArgumentException("One from parsing values is null!"));
    }
}
