package com.p3rry.calculations.calculationsmanagement;

import com.p3rry.consts.Properties;
import com.p3rry.consts.QualityLevel;

public interface IRootOperations {
    default double calculateRootWidth(double gap) {
        return Properties.ROOT_WIDTH_ALLOWANCE + gap;
    }

    default double calculateRootSectionArea(double rootWidth, double rootHeight) {
        return (2.0/3.0 * rootWidth * rootHeight);
    }

    default double calculateRootHeight(QualityLevel qualityLevel, double thickness, double rootWidth) {
        if(thickness >= 0.5 && thickness < 3 && qualityLevel == QualityLevel.B)
            return (1 + (0.1 * rootWidth));
        else if(thickness >= 0.5 && thickness < 3 && qualityLevel == QualityLevel.C)
            return (1 + (0.3 * rootWidth));
        else if(thickness >= 0.5 && thickness < 3 && qualityLevel == QualityLevel.D)
            return (1 + (0.6 * rootWidth));
        else if(thickness >= 3 && qualityLevel == QualityLevel.B)
            return (1 + (0.2 * rootWidth));
        else if(thickness >= 3 && qualityLevel == QualityLevel.C)
            return (1 + (0.6 * rootWidth));
        else if(thickness >= 3 && qualityLevel == QualityLevel.D)
            return (1 + rootWidth);
        else
            throw new IllegalArgumentException("Issue with quality level value or thickness value. " +
                    "Quality level can be: " + QualityLevel.B + " " + QualityLevel.C + " " + QualityLevel.D +
                    " and thickness cannot be less than " + Properties.THICKNESS_LIMIT);
    }
}
