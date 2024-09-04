package com.p3rry.calculation.weld;

import com.p3rry.calculation.calculationmanager.IWeldFaceOperations;
import com.p3rry.utlis.InputMessages;
import com.p3rry.consts.CommonProperties;
import com.p3rry.consts.QualityLevel;

import java.util.Optional;

public abstract class AbstractFilletWeld extends AbstractWeld implements IWeldFaceOperations {
    protected static final double ANGLE = 90;
    protected double legSize;

    public AbstractFilletWeld(double thickness, QualityLevel qualityLevel,
                              double legSize) {
        super(thickness, qualityLevel);
        this.legSize = Optional.of(legSize)
                .filter(ls -> ls > CommonProperties.LEG_SIZE_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(
                            CommonProperties.LEG_SIZE_LIMIT, "<=", "Leg size"
                    );
                    return new IllegalArgumentException("Leg size cannot be <= " + CommonProperties.LEG_SIZE_LIMIT);
                });
    }

    @Override
    public double calculateWeldFaceWidth(double grooveWidth) {
        return grooveWidth;
    }
}
