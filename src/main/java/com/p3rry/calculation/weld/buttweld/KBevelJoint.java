package com.p3rry.calculation.weld.buttweld;

import com.p3rry.calculation.calculationmanager.IGrooveOperations;
import com.p3rry.calculation.calculationmanager.IWeldOperations;
import com.p3rry.calculation.weld.AbstractButtWeld;
import com.p3rry.consts.QualityLevel;

public class KBevelJoint extends AbstractButtWeld implements IGrooveOperations, IWeldOperations {
    private double bevelAngle;
    private double bead;

    public KBevelJoint(double thickness, QualityLevel qualityLevel,
                       double gap, double bevelAngle,
                       double bead) {
        super(thickness, qualityLevel, gap);
        this.bevelAngle = setBevelAngle(bevelAngle);
        this.bead = setBead(bead);
    }

    @Override
    public double calculateGrooveWidth() {
        return (calculateTan(bevelAngle, 1.0) * ((thickness - bead) / 2.0) + gap);
    }

    @Override
    public double calculateGrooveSectionArea() {
        return (calculateTan(bevelAngle, 1.0) * Math.pow(((thickness - bead) / 2.0),2.0) + thickness * gap);
    }

    @Override
    public double calculateWeldSectionArea() {
        double weldFaceWidth = super.calculateWeldFaceWidth(this.calculateGrooveWidth());
        double weldFaceHeight = calculateWeldFaceHeight(qualityLevel, thickness, weldFaceWidth);

        return calculateGrooveSectionArea() +
                2.0 * calculateWeldFaceSectionArea(weldFaceWidth, weldFaceHeight);
    }
}
