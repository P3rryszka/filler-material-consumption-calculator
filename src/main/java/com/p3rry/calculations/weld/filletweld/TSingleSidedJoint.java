package com.p3rry.calculations.weld.filletweld;

import com.p3rry.calculations.calculationsmanagement.IAngleOperations;
import com.p3rry.calculations.calculationsmanagement.IGrooveOperations;
import com.p3rry.calculations.calculationsmanagement.IWeldOperations;
import com.p3rry.calculations.weld.AbstractFilletWeld;
import com.p3rry.consts.QualityLevel;

public class TSingleSidedJoint extends AbstractFilletWeld implements IGrooveOperations, IAngleOperations, IWeldOperations {
    public TSingleSidedJoint(double thickness, QualityLevel qualityLevel,
                             double legSize) {
        super(thickness, qualityLevel, legSize);
    }

    @Override
    public double calculateGrooveWidth() {
        return (2.0 *convertIntoDegrees(super.angle,2.0) * this.legSize);
    }

    @Override
    public double calculateGrooveSectionArea() {
        return (Math.pow(this.legSize, 2.0) * convertIntoDegrees(super.angle,2.0));
    }

    @Override
    public double calculateWeldSectionArea() {
       double weldFaceWidth = super.calculateWeldFaceWidth(this.calculateGrooveWidth());

       return this.calculateGrooveSectionArea() +
               super.calculateWeldFaceSectionArea(weldFaceWidth,
                       calculateWeldFaceHeight(qualityLevel, thickness, weldFaceWidth));
    }
}
