package com.p3rry.calculation.weld.filletweld;

import com.p3rry.calculation.calculationmanager.IAngleOperations;
import com.p3rry.calculation.calculationmanager.IGrooveOperations;
import com.p3rry.calculation.calculationmanager.IWeldOperations;
import com.p3rry.calculation.weld.AbstractFilletWeld;
import com.p3rry.consts.QualityLevel;

public class TSingleSidedJoint extends AbstractFilletWeld implements IGrooveOperations, IAngleOperations, IWeldOperations {
    public TSingleSidedJoint(double thickness, QualityLevel qualityLevel,
                             double legSize) {
        super(thickness, qualityLevel, legSize);
    }

    @Override
    public double calculateGrooveWidth() {
        return (2.0 *convertIntoDegrees(ANGLE,2.0) * this.legSize);
    }

    @Override
    public double calculateGrooveSectionArea() {
        return (Math.pow(this.legSize, 2.0) * convertIntoDegrees(ANGLE,2.0));
    }

    @Override
    public double calculateWeldSectionArea() {
       double weldFaceWidth = super.calculateWeldFaceWidth(this.calculateGrooveWidth());

       return this.calculateGrooveSectionArea() +
               super.calculateWeldFaceSectionArea(weldFaceWidth,
                       calculateWeldFaceHeight(qualityLevel, thickness, weldFaceWidth));
    }
}
