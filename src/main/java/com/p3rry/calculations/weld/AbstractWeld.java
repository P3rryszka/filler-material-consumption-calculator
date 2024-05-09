package com.p3rry.calculations.weld;

import com.p3rry.consts.QualityLevel;

public abstract class AbstractWeld {
    protected double thickness;
    protected QualityLevel qualityLevel;

    public AbstractWeld(double thickness, QualityLevel qualityLevel) {
        this.thickness = thickness;
        this.qualityLevel = qualityLevel;
    }
}
