package com.p3rry.calculations.weld;

import com.p3rry.components.utlis.InputMessages;
import com.p3rry.consts.Properties;
import com.p3rry.consts.QualityLevel;

import java.util.Optional;

public abstract class AbstractWeld {
    protected double thickness;
    protected QualityLevel qualityLevel;

    public AbstractWeld(double thickness, QualityLevel qualityLevel) {
        this.thickness = Optional.of(thickness)
                .filter(t -> t >= Properties.THICKNESS_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(Properties.THICKNESS_LIMIT, "L");
                    return new IllegalArgumentException("Thickness cannot be < " + Properties.THICKNESS_LIMIT);
                });

        this.qualityLevel = Optional.ofNullable(qualityLevel)
                .filter(ql -> ql.equals(QualityLevel.B) || ql.equals(QualityLevel.C) ||
                        ql.equals(QualityLevel.D))
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(QualityLevel.B, QualityLevel.C, QualityLevel.D);
                    return new IllegalArgumentException("Quality level can be: " + QualityLevel.B + " " + QualityLevel.C +
                            " " + QualityLevel.D);
                });
    }
}