package com.p3rry.calculations.weld;

import com.p3rry.calculations.calculationsmanagement.IWeldFaceOperations;
import com.p3rry.consts.Properties;
import com.p3rry.consts.QualityLevel;

public abstract class AbstractButtWeld extends AbstractWeld implements IWeldFaceOperations {
    protected double gap;

    public AbstractButtWeld(double thickness, QualityLevel qualityLevel,
                            double gap) {
        super(thickness, qualityLevel);
        this.gap = gap;
    }

    @Override
    public double calculateWeldFaceWidth(double grooveWidth) {
        return grooveWidth + Properties.WELD_FACE_WIDTH_ALLOWANCE;
    }
}
