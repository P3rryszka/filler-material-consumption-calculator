package com.p3rry.calculations;

import com.p3rry.calculations.additionalmaterial.WeldingMethodFactory;
import com.p3rry.utlis.InputParser;
import com.p3rry.components.weldingmethod.AbstractWeldingMethodPanel;
import lombok.NonNull;

public class AdditionalMaterialCalculator {
    private static final String MESSAGE = "Invalid input data type!";
    public static double calculateGmawAdditionalMaterial(@NonNull AbstractWeldingMethodPanel weldingMethodPanelType, double mass) {
        try {
            double wireSpoolMass = InputParser.parseWireSpoolMassParam(weldingMethodPanelType);

            var weldingMethod = WeldingMethodFactory.createWeldingMethod("G", wireSpoolMass);
            return weldingMethod.calculateNeededAdditionalMaterial(mass);
        } catch (NumberFormatException e) {
            throw new RuntimeException(MESSAGE + e.getMessage());
        }
    }

    public static double calculateSmawAdditionalMaterial(@NonNull AbstractWeldingMethodPanel weldingMethodPanelType, double mass,
                                                         double density) {
       try {
           double electrodeLength = InputParser.parseElectrodeLengthParam(weldingMethodPanelType);
           double electrodeDiameter = InputParser.parseElectrodeDiameterParam(weldingMethodPanelType);

           var weldingMethod = WeldingMethodFactory.createWeldingMethod("S", electrodeLength, electrodeDiameter, density);
           return weldingMethod.calculateNeededAdditionalMaterial(mass);
       } catch (NumberFormatException e) {
           throw new RuntimeException(MESSAGE + e.getMessage());
       }
    }

    public static double calculateGtawAdditionalMaterial(@NonNull AbstractWeldingMethodPanel weldingMethodPanelType, double mass,
                                                         double density) {
        try {
            double rodLength = InputParser.parseRodLengthParam(weldingMethodPanelType);
            double rodDiameter = InputParser.parseRodDiameterParam(weldingMethodPanelType);

            var weldingMethod = WeldingMethodFactory.createWeldingMethod("GT", rodLength, rodDiameter, density);
            return weldingMethod.calculateNeededAdditionalMaterial(mass);
        } catch (NumberFormatException e) {
            throw new RuntimeException(MESSAGE + e.getMessage());
        }
    }
}
