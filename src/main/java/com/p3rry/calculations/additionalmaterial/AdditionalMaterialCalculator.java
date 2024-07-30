package com.p3rry.calculations.additionalmaterial;

import com.p3rry.components.weldingmethod.AbstractWeldingMethodPanel;
import com.p3rry.consts.Properties;
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

    public static double calculateSmawAdditionalMaterial(@NonNull AbstractWeldingMethodPanel weldingMethodPanelType, double mass) {
       try {
           double electrodeLength = parseAndCheckNullDouble(weldingMethodPanelType, 0);
           double electrodeDiameter = parseAndCheckNullDouble(weldingMethodPanelType, 1);
           double electrodeYield = parseAndCheckNullDouble(weldingMethodPanelType, 2);
           double fillerMaterialDensity = parseAndCheckNullDouble(weldingMethodPanelType, 3) * Properties.TO_KG_MM3_FACTOR;

           var weldingMethod = new SmawAdditionalMaterial(electrodeLength, electrodeDiameter, fillerMaterialDensity, electrodeYield);
           return weldingMethod.calculateNeededAdditionalMaterial(mass);
       } catch (NumberFormatException e) {
           throw new RuntimeException(MESSAGE + e.getMessage());
       }
    }

    public static double calculateGtawAdditionalMaterial(@NonNull AbstractWeldingMethodPanel weldingMethodPanelType, double mass) {
        try {
            double rodLength = parseAndCheckNullDouble(weldingMethodPanelType, 0);
            double rodDiameter = parseAndCheckNullDouble(weldingMethodPanelType, 1);
            double fillerMaterialDensity = parseAndCheckNullDouble(weldingMethodPanelType, 2) * Properties.TO_KG_MM3_FACTOR;

            var weldingMethod = new GtawAdditionalMaterial(rodLength, rodDiameter, fillerMaterialDensity);
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
