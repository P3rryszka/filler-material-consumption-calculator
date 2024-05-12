package com.p3rry.calculations.weld.buttweld;

import com.p3rry.calculations.calculationsmanagement.*;
import com.p3rry.calculations.weld.AbstractButtWeld;
import com.p3rry.consts.QualityLevel;

public class NoBevelJoint extends AbstractButtWeld implements IGrooveOperations, IAngleOperations, IWeldOperations, IWeldFaceOperations, IRootOperations {
    public NoBevelJoint(double thickness, QualityLevel qualityLevel, double gap) {
        super(thickness, qualityLevel, gap);
    }

    @Override
    public double calculateGrooveWidth() {
        return gap;
    }

    @Override
    public double calculateGrooveSectionArea() {
        return thickness * gap;
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
