package com.p3rry.calculations;

import com.p3rry.calculations.additionalmaterial.WeldingMethodFactory;
import com.p3rry.consts.WeldingMethodType;
import com.p3rry.utlis.InputParser;
import com.p3rry.components.weldingmethod.AbstractWeldingMethodPanel;
import lombok.NonNull;

public class AdditionalMaterialCalculator {
    private static final String MESSAGE = "Invalid input data type!";

    public static double calculateGmawAdditionalMaterial(@NonNull AbstractWeldingMethodPanel weldingMethodPanelType, double mass) {
        try {
            double wireSpoolMass = InputParser.parseDoubleWeldingMethodPanel(weldingMethodPanelType, 0);

            var weldingMethod = WeldingMethodFactory.createWeldingMethod(WeldingMethodType.GMAW, wireSpoolMass);
            return weldingMethod.calculateNeededAdditionalMaterial(mass);
        } catch (NumberFormatException e) {
            throw new RuntimeException(MESSAGE + e.getMessage());
        }
    }

    public static double calculateSmawAdditionalMaterial(@NonNull AbstractWeldingMethodPanel weldingMethodPanelType, double mass,
                                                         double density) {
       try {
           double electrodeLength = InputParser.parseDoubleWeldingMethodPanel(weldingMethodPanelType, 0);
           double electrodeDiameter = InputParser.parseDoubleWeldingMethodPanel(weldingMethodPanelType, 1);

           var weldingMethod = WeldingMethodFactory.createWeldingMethod(WeldingMethodType.SMAW, electrodeLength, electrodeDiameter, density);
           return weldingMethod.calculateNeededAdditionalMaterial(mass);
       } catch (NumberFormatException e) {
           throw new RuntimeException(MESSAGE + e.getMessage());
       }
    }

    public static double calculateGtawAdditionalMaterial(@NonNull AbstractWeldingMethodPanel weldingMethodPanelType, double mass,
                                                         double density) {
        try {
            double rodLength = InputParser.parseDoubleWeldingMethodPanel(weldingMethodPanelType, 0);
            double rodDiameter = InputParser.parseDoubleWeldingMethodPanel(weldingMethodPanelType, 1);

            var weldingMethod = WeldingMethodFactory.createWeldingMethod(WeldingMethodType.GTAW, rodLength, rodDiameter, density);
            return weldingMethod.calculateNeededAdditionalMaterial(mass);
        } catch (NumberFormatException e) {
            throw new RuntimeException(MESSAGE + e.getMessage());
        }
    }
}
