package com.p3rry.calculation.additionalmaterial;

import com.p3rry.ui.weldingmethod.WeldingMethodPanel;
import com.p3rry.consts.CommonProperties;
import lombok.NonNull;

import java.util.Optional;

public class AdditionalMaterialCalculator {
    private static final String MESSAGE = "Invalid input data type!";

    public static double calculateGmawAdditionalMaterial(@NonNull WeldingMethodPanel weldingMethodPanelType, double mass) {
        try {
            double wireSpoolMass = parseAndCheckNullDouble(weldingMethodPanelType, 0);

            var weldingMethod = new GmawAdditionalMaterial(wireSpoolMass);
            return weldingMethod.calculateNeededAdditionalMaterial(mass);
        } catch (NumberFormatException e) {
            throw new RuntimeException(MESSAGE + e.getMessage());
        }
    }

    public static double calculateSmawAdditionalMaterial(@NonNull WeldingMethodPanel weldingMethodPanelType, double mass) {
       try {
           double electrodeLength = parseAndCheckNullDouble(weldingMethodPanelType, 0);
           double electrodeDiameter = parseAndCheckNullDouble(weldingMethodPanelType, 1);
           double electrodeYield = parseAndCheckNullDouble(weldingMethodPanelType, 2);
           double fillerMaterialDensity = parseAndCheckNullDouble(weldingMethodPanelType, 3) * CommonProperties.TO_KG_MM3_FACTOR;

           var weldingMethod = new SmawAdditionalMaterial(electrodeLength, electrodeDiameter, fillerMaterialDensity, electrodeYield);
           return weldingMethod.calculateNeededAdditionalMaterial(mass);
       } catch (NumberFormatException e) {
           throw new RuntimeException(MESSAGE + e.getMessage());
       }
    }

    public static double calculateGtawAdditionalMaterial(@NonNull WeldingMethodPanel weldingMethodPanelType, double mass) {
        try {
            double rodLength = parseAndCheckNullDouble(weldingMethodPanelType, 0);
            double rodDiameter = parseAndCheckNullDouble(weldingMethodPanelType, 1);
            double fillerMaterialDensity = parseAndCheckNullDouble(weldingMethodPanelType, 2) * CommonProperties.TO_KG_MM3_FACTOR;

            var weldingMethod = new GtawAdditionalMaterial(rodLength, rodDiameter, fillerMaterialDensity);
            return weldingMethod.calculateNeededAdditionalMaterial(mass);
        } catch (NumberFormatException e) {
            throw new RuntimeException(MESSAGE + e.getMessage());
        }
    }

    private static double parseAndCheckNullDouble(@NonNull WeldingMethodPanel weldingMethodPanelType, int index) {
        return Optional.ofNullable(weldingMethodPanelType.getTextComponentsList().get(index).getText())
                .map(Double::parseDouble)
                .orElseThrow(() -> new IllegalArgumentException("One from parsing values is null!"));
    }
}
