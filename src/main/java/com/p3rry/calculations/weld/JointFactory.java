package com.p3rry.calculations.weld;

import com.p3rry.calculations.calculationsmanagement.IWeldOperations;
import com.p3rry.calculations.weld.buttweld.*;
import com.p3rry.calculations.weld.filletweld.TSingleSidedJoint;
import com.p3rry.consts.QualityLevel;
import lombok.NonNull;

public class JointFactory {
    private static final String MESSAGE = "Invalid joint type or amount of passed parameters!";

    public static IWeldOperations createButtWeldJoint(@NonNull String jointType, double thickness,
                                                      @NonNull QualityLevel qualityLevel, double gap) {
        if(jointType.equalsIgnoreCase("N"))
            return new NoBevelJoint(thickness, qualityLevel, gap);
        else
            throw new IllegalArgumentException(MESSAGE);
    }

    public static IWeldOperations createButtWeldJoint(@NonNull String jointType, double thickness,
                                                      @NonNull QualityLevel qualityLevel, double gap,
                                                      double bevelAngle) {
        if(jointType.equalsIgnoreCase("V"))
            return new VBevelJoint(thickness, qualityLevel, gap, bevelAngle);
        else
            throw new IllegalArgumentException(MESSAGE);
    }

    public static IWeldOperations createButtWeldJoint(@NonNull String jointType, double thickness,
                                                      @NonNull QualityLevel qualityLevel, double gap,
                                                      double bevelAngle, double bead) {
        if(jointType.equalsIgnoreCase("Y"))
            return new YBevelJoint(thickness, qualityLevel, gap, bevelAngle, bead);
        else if (jointType.equalsIgnoreCase("X"))
            return new XBevelJoint(thickness, qualityLevel, gap, bevelAngle, bead);
        else if (jointType.equalsIgnoreCase("K"))
            return new KBevelJoint(thickness, qualityLevel, gap, bevelAngle, bead);
        else
            throw new IllegalArgumentException(MESSAGE);
    }

    public static IWeldOperations createButtWeldJoint(@NonNull String jointType, double thickness,
                                                      @NonNull QualityLevel qualityLevel, double gap,
                                                      double bevelAngle, double bead,
                                                      double rounding) {
        if(jointType.equalsIgnoreCase("U"))
            return new UBevelJoint(thickness, qualityLevel, gap, bevelAngle, bead, rounding);
        else
            throw new IllegalArgumentException(MESSAGE);
    }

    public static IWeldOperations createFilletWeldJoint(@NonNull String jointType, double thickness,
                                                        @NonNull QualityLevel qualityLevel, double legSize) {
        if(jointType.equalsIgnoreCase("T"))
            return new TSingleSidedJoint(thickness, qualityLevel, legSize);
        else
            throw new IllegalArgumentException(MESSAGE);
    }
}
