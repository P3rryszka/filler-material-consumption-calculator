package com.p3rry.calculations.additionalmaterial;

import com.p3rry.calculations.calculationsmanagement.IAdditionalMaterialOperations;
import lombok.NonNull;

public class WeldingMethodFactory {
    private static final String MESSAGE = "Invalid welding method type or amount of passed parameters!";
    
    public static IAdditionalMaterialOperations createWeldingMethod(@NonNull String jointType, double wireSpoolMass) {
        if(jointType.equalsIgnoreCase("G"))
            return new GmawAdditionalMaterial(wireSpoolMass);
        else 
            throw new IllegalArgumentException(MESSAGE);
    }
    
    public static IAdditionalMaterialOperations createWeldingMethod(@NonNull String jointType, double length,
                                                                    double diameter, double density) {
        if(jointType.equalsIgnoreCase("S"))
            return new SmawAdditionalMaterial(length, diameter, density);
        else if(jointType.equalsIgnoreCase("GT"))
            return new GtawAdditionalMaterial(length, diameter, density);
        else
            throw new IllegalArgumentException(MESSAGE);
    }
}