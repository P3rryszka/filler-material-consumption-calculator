package com.p3rry.calculations.calculationsmanagement;

import com.p3rry.consts.Properties;
import com.p3rry.consts.QualityLevel;
import lombok.NonNull;

@FunctionalInterface
public interface IWeldFaceOperations {
    double calculateWeldFaceWidth(double grooveWidth);

    default double calculateWeldFaceHeight(@NonNull QualityLevel qualityLevel, double thickness, double weldFaceWidth) {
        if(thickness >= 0.5 && qualityLevel == QualityLevel.B)
            return (1 + (0.1 * weldFaceWidth));
        else if(thickness >= 0.5 && qualityLevel == QualityLevel.C)
            return (1 + (0.15 * weldFaceWidth));
        else if(thickness >= 0.5 && qualityLevel == QualityLevel.D)
            return (1 + (0.25 * weldFaceWidth));
        else
            throw new IllegalArgumentException("Issue with quality level value or thickness value. " +
                    "Quality level can be: " + QualityLevel.B + " " + QualityLevel.C + " " + QualityLevel.D +
                    " and thickness cannot be less than " + Properties.THICKNESS_LIMIT);
    }

    default double calculateWeldFaceSectionArea(double weldFaceWidth, double weldFaceHeight) {
        return (2.0 / 3.0 * weldFaceWidth * weldFaceHeight);
    }
}
