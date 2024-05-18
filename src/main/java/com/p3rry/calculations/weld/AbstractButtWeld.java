package com.p3rry.calculations.weld;

import com.p3rry.calculations.calculationsmanagement.IWeldFaceOperations;
import com.p3rry.utlis.InputMessages;
import com.p3rry.consts.Properties;
import com.p3rry.consts.QualityLevel;

import java.util.Optional;

public abstract class AbstractButtWeld extends AbstractWeld implements IWeldFaceOperations {
    protected double gap;

    public AbstractButtWeld(double thickness, QualityLevel qualityLevel,
                            double gap) {
        super(thickness, qualityLevel);
        this.gap = Optional.of(gap)
                .filter(g -> g > Properties.GAP_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(Properties.GAP_LIMIT, "<=");
                    return new IllegalArgumentException("Gap cannot be <= " + Properties.GAP_LIMIT);
                });
    }

    @Override
    public double calculateWeldFaceWidth(double grooveWidth) {
        return grooveWidth + Properties.WELD_FACE_WIDTH_ALLOWANCE;
    }

    protected double initializeBead(double bead) {
        return Optional.of(bead)
                .filter(b -> b > Properties.BEAD_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(Properties.BEAD_LIMIT, "<=");
                    return new IllegalArgumentException("Bead cannot be <= " + Properties.BEAD_LIMIT);
                });
    }

    protected double initializeBevelAngle(double bevelAngle) {
        return Optional.of(bevelAngle)
                .filter(ba -> ba >= Properties.BOTTOM_BEVEL_ANGLE_LIMIT && ba <= Properties.UPPER_BEVEL_ANGLE_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(Properties.BOTTOM_BEVEL_ANGLE_LIMIT, Properties.UPPER_BEVEL_ANGLE_LIMIT);
                    return new IllegalArgumentException("Bevel angle cannot be < " + Properties.BOTTOM_BEVEL_ANGLE_LIMIT +
                            " and > " + Properties.UPPER_BEVEL_ANGLE_LIMIT);
                });
    }
}
