package com.p3rry.calculations.weld.buttweld;

import com.p3rry.calculations.calculationsmanagers.IAngleOperations;
import com.p3rry.calculations.calculationsmanagers.IGrooveOperations;
import com.p3rry.calculations.calculationsmanagers.IWeldOperations;
import com.p3rry.calculations.weld.AbstractButtWeld;
import com.p3rry.consts.QualityLevel;

public class XBevelJoint extends AbstractButtWeld implements IGrooveOperations, IAngleOperations, IWeldOperations {
    private double bevelAngle;
    private double bead;

    public XBevelJoint(double thickness, QualityLevel qualityLevel,
                       double gap, double bevelAngle,
                       double bead) {
        super(thickness, qualityLevel, gap);
        this.bevelAngle = initializeBevelAngle(bevelAngle);
        this.bead = initializeBead(bead);
    }

    @Override
    public double calculateGrooveWidth() {
        return ((thickness - bead) * convertIntoDegrees(bevelAngle, 2.0) + gap);
    }

    @Override
    public double calculateGrooveSectionArea() {
        return (thickness * gap + (Math.pow((thickness - bead),2.0) / 2.0) * convertIntoDegrees(bevelAngle, 2.0));
    }

    @Override
    public double calculateWeldSectionArea() {
        double weldFaceWidth = super.calculateWeldFaceWidth(this.calculateGrooveWidth());
        double weldFaceHeight = calculateWeldFaceHeight(qualityLevel, thickness, weldFaceWidth);

        return calculateGrooveSectionArea() +
                2.0 * calculateWeldFaceSectionArea(weldFaceWidth, weldFaceHeight);
    }
}
