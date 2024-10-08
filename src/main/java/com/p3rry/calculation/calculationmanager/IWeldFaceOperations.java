package com.p3rry.calculation.calculationmanager;

import com.p3rry.consts.CommonProperties;
import com.p3rry.consts.QualityLevel;
import lombok.NonNull;

@FunctionalInterface
public interface IWeldFaceOperations {
    double calculateWeldFaceWidth(double grooveWidth);

    default double calculateWeldFaceSectionArea(double weldFaceWidth, double weldFaceHeight) {
        return (2.0 / 3.0 * weldFaceWidth * weldFaceHeight);
    }

    default double calculateWeldFaceHeight(@NonNull QualityLevel qualityLevel, double thickness, double weldFaceWidth) {
        if(thickness >= 0.5 && qualityLevel.equals(QualityLevel.B))
            return (1 + (0.1 * weldFaceWidth));
        else if(thickness >= 0.5 && qualityLevel.equals(QualityLevel.C))
            return (1 + (0.15 * weldFaceWidth));
        else if(thickness >= 0.5 && qualityLevel.equals(QualityLevel.D))
            return (1 + (0.25 * weldFaceWidth));
        else
            throw new IllegalArgumentException("Issue with quality level value or thickness value. " +
                    "Quality level can be: " + QualityLevel.B + " " + QualityLevel.C + " " + QualityLevel.D +
                    " and thickness cannot be less than " + CommonProperties.THICKNESS_LIMIT);
    }
}
