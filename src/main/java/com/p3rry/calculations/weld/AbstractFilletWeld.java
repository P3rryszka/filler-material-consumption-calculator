package com.p3rry.calculations.weld;
import com.p3rry.calculations.calculationsmanagement.IWeldFaceOperations;
import com.p3rry.consts.QualityLevel;

public abstract class AbstractFilletWeld extends AbstractWeld implements IWeldFaceOperations {
    protected final double angle = 90;
    protected double legSize;

    public AbstractFilletWeld(double thickness, QualityLevel qualityLevel,
                              double legSize) {
        super(thickness, qualityLevel);
        this.legSize = legSize;
    }

    @Override
    public double calculateWeldFaceWidth(double grooveWidth) {
        return grooveWidth;
    }
}
