package com.p3rry.calculations.additionalmaterial;

import com.p3rry.calculations.calculationsmanagement.IAdditionalMaterialOperations;
import com.p3rry.consts.WeldingMethodType;
import lombok.NonNull;

public class WeldingMethodFactory {
    private static final String MESSAGE = "Invalid welding method type or amount of passed parameters!";
    
    public static IAdditionalMaterialOperations createWeldingMethod(@NonNull WeldingMethodType weldingMethodType, double wireSpoolMass) {
        if(weldingMethodType == WeldingMethodType.GMAW)
            return new GmawAdditionalMaterial(wireSpoolMass);
        else 
            throw new IllegalArgumentException(MESSAGE);
    }
    
    public static IAdditionalMaterialOperations createWeldingMethod(@NonNull WeldingMethodType weldingMethodType, double length,
                                                                    double diameter, double density) {
        if(weldingMethodType == WeldingMethodType.SMAW)
            return new SmawAdditionalMaterial(length, diameter, density);
        else if(weldingMethodType == WeldingMethodType.GTAW)
            return new GtawAdditionalMaterial(length, diameter, density);
        else
            throw new IllegalArgumentException(MESSAGE);
    }
}