package com.p3rry.calculations.weld;

import com.p3rry.calculations.calculationsmanagement.IWeldOperations;
import com.p3rry.calculations.weld.buttweld.*;
import com.p3rry.calculations.weld.filletweld.TSingleSidedJoint;
import com.p3rry.consts.Description;
import com.p3rry.consts.QualityLevel;
import lombok.NonNull;

public class JointFactory {
    private static final String MESSAGE = "Invalid joint type or amount of passed parameters!";

    public static IWeldOperations createButtWeldJoint(@NonNull Description jointType, double thickness,
                                                      @NonNull QualityLevel qualityLevel, double gap) {
        if(jointType == Description.NO_BEVEL_JOINT)
            return new NoBevelJoint(thickness, qualityLevel, gap);
        else
            throw new IllegalArgumentException(MESSAGE);
    }

    public static IWeldOperations createButtWeldJoint(@NonNull Description jointType, double thickness,
                                                      @NonNull QualityLevel qualityLevel, double gap,
                                                      double bevelAngle) {
        if(jointType == Description.V_BEVEL_JOINT)
            return new VBevelJoint(thickness, qualityLevel, gap, bevelAngle);
        else
            throw new IllegalArgumentException(MESSAGE);
    }

    public static IWeldOperations createButtWeldJoint(@NonNull Description jointType, double thickness,
                                                      @NonNull QualityLevel qualityLevel, double gap,
                                                      double bevelAngle, double bead) {
        switch (jointType) {
            case Y_BEVEL_JOINT -> {
                return new YBevelJoint(thickness, qualityLevel, gap, bevelAngle, bead);
            }
            case X_BEVEL_JOINT -> {
                return new XBevelJoint(thickness, qualityLevel, gap, bevelAngle, bead);
            }
            case K_BEVEL_JOINT -> {
                return new KBevelJoint(thickness, qualityLevel, gap, bevelAngle, bead);
            }
            default -> throw new IllegalArgumentException(MESSAGE);
        }
    }

    public static IWeldOperations createButtWeldJoint(@NonNull Description jointType, double thickness,
                                                      @NonNull QualityLevel qualityLevel, double gap,
                                                      double bevelAngle, double bead,
                                                      double rounding) {
        if(jointType == Description.U_BEVEL_JOINT)
            return new UBevelJoint(thickness, qualityLevel, gap, bevelAngle, bead, rounding);
        else
            throw new IllegalArgumentException(MESSAGE);
    }

    public static IWeldOperations createFilletWeldJoint(@NonNull Description jointType, double thickness,
                                                        @NonNull QualityLevel qualityLevel, double legSize) {
        if(jointType == Description.T_SINGLE_SIDED_JOINT)
            return new TSingleSidedJoint(thickness, qualityLevel, legSize);
        else
            throw new IllegalArgumentException(MESSAGE);
    }
}
