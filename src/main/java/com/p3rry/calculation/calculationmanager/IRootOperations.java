package com.p3rry.calculation.calculationmanager;

import com.p3rry.consts.CommonProperties;
import com.p3rry.consts.QualityLevel;
import lombok.NonNull;

public interface IRootOperations {
    default double calculateRootWidth(double gap) {
        return CommonProperties.ROOT_WIDTH_ALLOWANCE + gap;
    }

    default double calculateRootSectionArea(double rootWidth, double rootHeight) {
        return (2.0/3.0 * rootWidth * rootHeight);
    }

    default double calculateRootHeight(@NonNull QualityLevel qualityLevel, double thickness, double rootWidth) {
        if(thickness >= 0.5 && thickness < 3 && qualityLevel.equals(QualityLevel.B))
            return (1 + (0.1 * rootWidth));
        else if(thickness >= 0.5 && thickness < 3 && qualityLevel.equals(QualityLevel.C))
            return (1 + (0.3 * rootWidth));
        else if(thickness >= 0.5 && thickness < 3 && qualityLevel.equals(QualityLevel.D))
            return (1 + (0.6 * rootWidth));
        else if(thickness >= 3 && qualityLevel.equals(QualityLevel.B))
            return (1 + (0.2 * rootWidth));
        else if(thickness >= 3 && qualityLevel.equals(QualityLevel.C))
            return (1 + (0.6 * rootWidth));
        else if(thickness >= 3 && qualityLevel.equals(QualityLevel.D))
            return (1 + rootWidth);
        else
            throw new IllegalArgumentException("Issue with quality level value or thickness value. " +
                    "Quality level can be: " + QualityLevel.B + " " + QualityLevel.C + " " + QualityLevel.D +
                    " and thickness cannot be less than " + CommonProperties.THICKNESS_LIMIT);
    }
}
