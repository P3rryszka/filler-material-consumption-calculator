package com.p3rry.calculation.weld.buttweld;

import com.p3rry.calculation.calculationmanager.IGrooveOperations;
import com.p3rry.calculation.calculationmanager.IWeldOperations;
import com.p3rry.calculation.weld.AbstractButtWeld;
import com.p3rry.consts.QualityLevel;

public class XBevelJoint extends AbstractButtWeld implements IGrooveOperations, IWeldOperations {
    private double bevelAngle;
    private double bead;

    public XBevelJoint(double thickness, QualityLevel qualityLevel,
                       double gap, double bevelAngle,
                       double bead) {
        super(thickness, qualityLevel, gap);
        this.bevelAngle = setBevelAngle(bevelAngle);
        this.bead = setBead(bead);
    }

    @Override
    public double calculateGrooveWidth() {
        return ((thickness - bead) * calculateTan(bevelAngle, 2.0) + gap);
    }

    @Override
    public double calculateGrooveSectionArea() {
        return (thickness * gap + (Math.pow((thickness - bead),2.0) / 2.0) * calculateTan(bevelAngle, 2.0));
    }

    @Override
    public double calculateWeldSectionArea() {
        double weldFaceWidth = super.calculateWeldFaceWidth(this.calculateGrooveWidth());
        double weldFaceHeight = calculateWeldFaceHeight(qualityLevel, thickness, weldFaceWidth);

        return calculateGrooveSectionArea() +
                2.0 * calculateWeldFaceSectionArea(weldFaceWidth, weldFaceHeight);
    }
}
