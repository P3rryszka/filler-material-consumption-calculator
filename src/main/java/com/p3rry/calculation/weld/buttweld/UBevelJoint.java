package com.p3rry.calculation.weld.buttweld;

import com.p3rry.calculation.calculationmanager.*;
import com.p3rry.calculation.weld.AbstractButtWeld;
import com.p3rry.utlis.InputMessages;
import com.p3rry.consts.CommonProperties;
import com.p3rry.consts.QualityLevel;

import java.util.Optional;

public class UBevelJoint extends AbstractButtWeld implements IGrooveOperations, IAngleOperations, IWeldOperations, IWeldFaceOperations, IRootOperations {
    private double bevelAngle;
    private double bead;
    private double rounding;

    public UBevelJoint(double thickness, QualityLevel qualityLevel,
                       double gap, double bevelAngle,
                       double bead, double rounding) {
        super(thickness, qualityLevel, gap);
        this.bevelAngle = setBevelAngle(bevelAngle);
        this.bead = setBead(bead);
        this.rounding = Optional.of(rounding)
                .filter(r -> r > CommonProperties.ROUNDING_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(
                            CommonProperties.ROUNDING_LIMIT, "<=", "Rounding"
                    );
                    return new IllegalArgumentException("Rounding cannot be <= " + CommonProperties.ROUNDING_LIMIT);
                });
    }

    @Override
    public double calculateGrooveWidth() {
        return (2.0 * convertIntoDegrees(bevelAngle, 2.0) *
                (thickness - (rounding + bead)) +
                2.0 * rounding + gap);
    }

    @Override
    public double calculateGrooveSectionArea() {
        return (((Math.PI * Math.pow(rounding, 2.0)) / 4.0) +
                thickness * gap +
                (2 * rounding + (thickness - (rounding + bead)) *
                        convertIntoDegrees(bevelAngle, 2.0)) *
                        (thickness - (rounding + bead)));
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
