package com.p3rry.calculations.weld.buttweld;

import com.p3rry.calculations.calculationsmanagement.*;
import com.p3rry.calculations.weld.AbstractButtWeld;
import com.p3rry.consts.QualityLevel;

public class YBevelJoint extends AbstractButtWeld implements IGrooveOperations, IAngleOperations, IWeldOperations, IWeldFaceOperations, IRootOperations {
    private double bevelAngle;
    private double bead;

    public YBevelJoint(double thickness, QualityLevel qualityLevel,
                       double gap, double bevelAngle,
                       double bead) {
        super(thickness, qualityLevel, gap);
        this.bevelAngle = initializeBevelAngle(bevelAngle);
        this.bead = initializeBead(bead);
    }


    @Override
    public double calculateGrooveWidth() {
        return (2.0 * (thickness - bead) * convertIntoDegrees(bevelAngle, 2.0) + gap);
    }

    @Override
    public double calculateGrooveSectionArea() {
        return (thickness * gap + Math.pow(thickness - bead, 2.0) * convertIntoDegrees(bevelAngle, 2.0));
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
