package com.p3rry.calculations.weld;
import com.p3rry.calculations.calculationsmanagement.IWeldFaceOperations;
import com.p3rry.utlis.InputMessages;
import com.p3rry.consts.Properties;
import com.p3rry.consts.QualityLevel;

import java.util.Optional;

public abstract class AbstractFilletWeld extends AbstractWeld implements IWeldFaceOperations {
    protected final double angle = 90;
    protected double legSize;

    public AbstractFilletWeld(double thickness, QualityLevel qualityLevel,
                              double legSize) {
        super(thickness, qualityLevel);
        this.legSize = Optional.of(legSize)
                .filter(ls -> ls > Properties.LEG_SIZE_LIMIT)
                .orElseThrow(() -> {
                    InputMessages.displayThisParamCannotBe(Properties.LEG_SIZE_LIMIT, "<=");
                    return new IllegalArgumentException("Leg size cannot be <= " + Properties.LEG_SIZE_LIMIT);
                });
    }

    @Override
    public double calculateWeldFaceWidth(double grooveWidth) {
        return grooveWidth;
    }
}
