package com.p3rry.calculation.weld;

import com.p3rry.utlis.InputMessages;
import com.p3rry.consts.CommonProperties;
import com.p3rry.consts.QualityLevel;

import java.util.Optional;

public abstract class AbstractWeld {
    protected double thickness;
    protected QualityLevel qualityLevel;

    public AbstractWeld(double thickness, QualityLevel qualityLevel) {
        this.thickness = Optional.of(thickness)
                .filter(t -> t >= CommonProperties.THICKNESS_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(
                            CommonProperties.THICKNESS_LIMIT, "<", "Thickness"
                    );
                    return new IllegalArgumentException("Thickness cannot be < " + CommonProperties.THICKNESS_LIMIT);
                });

        this.qualityLevel = Optional.ofNullable(qualityLevel)
                .filter(ql -> ql.equals(QualityLevel.B) || ql.equals(QualityLevel.C) ||
                        ql.equals(QualityLevel.D))
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBeDiffThan(QualityLevel.B, QualityLevel.C, QualityLevel.D);
                    return new IllegalArgumentException("Quality level can be: " + QualityLevel.B + " " + QualityLevel.C +
                            " " + QualityLevel.D);
                });
    }
}