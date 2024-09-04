package com.p3rry.calculation.weld;

import com.p3rry.calculation.calculationmanager.IWeldFaceOperations;
import com.p3rry.utlis.InputMessages;
import com.p3rry.consts.CommonProperties;
import com.p3rry.consts.QualityLevel;

import java.util.Optional;

public abstract class AbstractButtWeld extends AbstractWeld implements IWeldFaceOperations {
    protected double gap;

    public AbstractButtWeld(double thickness, QualityLevel qualityLevel,
                            double gap) {
        super(thickness, qualityLevel);
        this.gap = Optional.of(gap)
                .filter(g -> g > CommonProperties.GAP_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(
                            CommonProperties.GAP_LIMIT, "<=", "Gap"
                    );
                    return new IllegalArgumentException("Gap cannot be <= " + CommonProperties.GAP_LIMIT);
                });
    }

    @Override
    public double calculateWeldFaceWidth(double grooveWidth) {
        return grooveWidth + CommonProperties.WELD_FACE_WIDTH_ALLOWANCE;
    }

    protected double setBead(double bead) {
        return Optional.of(bead)
                .filter(b -> b > CommonProperties.BEAD_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(
                            CommonProperties.BEAD_LIMIT, "<=", "Bead "
                    );
                    return new IllegalArgumentException("Bead cannot be <= " + CommonProperties.BEAD_LIMIT);
                });
    }

    protected double setBevelAngle(double bevelAngle) {
        return Optional.of(bevelAngle)
                .filter(ba -> ba >= CommonProperties.BOTTOM_BEVEL_ANGLE_LIMIT && ba <= CommonProperties.UPPER_BEVEL_ANGLE_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamMustBeInRange(
                            CommonProperties.BOTTOM_BEVEL_ANGLE_LIMIT, CommonProperties.UPPER_BEVEL_ANGLE_LIMIT, "Bevel angle"
                    );
                    return new IllegalArgumentException("Bevel angle cannot be < " + CommonProperties.BOTTOM_BEVEL_ANGLE_LIMIT +
                            " and > " + CommonProperties.UPPER_BEVEL_ANGLE_LIMIT);
                });
    }
}
