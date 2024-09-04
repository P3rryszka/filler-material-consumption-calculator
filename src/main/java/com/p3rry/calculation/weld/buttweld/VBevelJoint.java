package com.p3rry.calculation.weld.buttweld;

import com.p3rry.calculation.calculationmanager.*;
import com.p3rry.calculation.weld.AbstractButtWeld;
import com.p3rry.consts.QualityLevel;

public class VBevelJoint extends AbstractButtWeld implements IGrooveOperations, IAngleOperations, IWeldOperations, IWeldFaceOperations, IRootOperations {
    private double bevelAngle;

    public VBevelJoint(double thickness, QualityLevel qualityLevel,
                       double gap, double bevelAngle) {
        super(thickness, qualityLevel, gap);
        this.bevelAngle = setBevelAngle(bevelAngle);
    }

    @Override
    public double calculateGrooveWidth() {
        return (2.0 * thickness * convertIntoDegrees(bevelAngle, 2.0) + gap);
    }

    @Override
    public double calculateGrooveSectionArea() {
        return (thickness * (gap + thickness * convertIntoDegrees(bevelAngle, 2.0)));
    }

    @Override
    public double calculateWeldSectionArea() {
        double weldFaceWidth = super.calculateWeldFaceWidth(this.calculateGrooveWidth());
        double weldFaceHeight = calculateWeldFaceHeight(qualityLevel, thickness, weldFaceWidth);
        double rootWidth = calculateRootWidth(gap);
        double rootHeight = calculateRootHeight(qualityLevel, thickness, rootWidth);

        return calculateGrooveSectionArea() +
                calculateWeldFaceSectionArea(weldFaceWidth, weldFaceHeight) +
                calculateRootSectionArea(rootWidth, rootHeight);
    }
}
