package com.p3rry.calculations;

import com.p3rry.calculations.calculationsmanagers.IWeldOperations;
import com.p3rry.calculations.weld.Mass;
import com.p3rry.calculations.weld.buttweld.*;
import com.p3rry.calculations.weld.filletweld.TSingleSidedJoint;
import com.p3rry.components.joint.AbstractJointPanel;
import com.p3rry.consts.JointType;
import com.p3rry.consts.QualityLevel;
import lombok.NonNull;

import java.util.Optional;

public class MassCalculator {
    private static final String MESSAGE = "Invalid input within selected joint panel!";
    private static final String MESSAGE_CHECKER_PARSER = "Parsing value within selected joint panel is null!";

    public static double calculateNoBevelJointMass(@NonNull AbstractJointPanel jointPanelType, double density) {
        try {
            double thickness = parseJointPanelDoubleValue(jointPanelType, 0);
            double gap = parseJointPanelDoubleValue(jointPanelType, 1);
            double length = parseJointPanelDoubleValue(jointPanelType, 2);
            QualityLevel qualityLevel = parseJointPanelQualityLevelValue(jointPanelType, 3);

            var joint = new NoBevelJoint(thickness, qualityLevel, gap);
            return new Mass(length, joint, density)
                    .calculateMass();
        } catch (NumberFormatException e) {
            throw new RuntimeException(MESSAGE + e.getMessage());
        }
    }

    public static double calculateVBevelJointMass(@NonNull AbstractJointPanel jointPanelType, double density) {
        try {
            double thickness = parseJointPanelDoubleValue(jointPanelType, 0);
            double gap = parseJointPanelDoubleValue(jointPanelType, 1);
            double bevelAngle = parseJointPanelDoubleValue(jointPanelType, 2);
            double length = parseJointPanelDoubleValue(jointPanelType, 3);
            QualityLevel qualityLevel = parseJointPanelQualityLevelValue(jointPanelType, 4);

            var joint = new VBevelJoint(thickness, qualityLevel, gap, bevelAngle);
            return new Mass(length, joint, density)
                    .calculateMass();
        } catch (NumberFormatException e) {
            throw new RuntimeException(MESSAGE + e.getMessage());
        }
    }

   public static double calculateUBevelJoint(@NonNull AbstractJointPanel jointPanelType, double density) {
        try {
            double thickness = parseJointPanelDoubleValue(jointPanelType, 0);
            double gap = parseJointPanelDoubleValue(jointPanelType, 1);
            double bevelAngle = parseJointPanelDoubleValue(jointPanelType, 2);
            double bead = parseJointPanelDoubleValue(jointPanelType, 3);
            double rounding = parseJointPanelDoubleValue(jointPanelType, 4);
            double length = parseJointPanelDoubleValue(jointPanelType, 5);
            QualityLevel qualityLevel = parseJointPanelQualityLevelValue(jointPanelType, 6);

            var joint = new UBevelJoint(thickness, qualityLevel, gap, bevelAngle, bead, rounding);
            return new Mass(length, joint, density)
                    .calculateMass();
        } catch (NumberFormatException e) {
            throw new RuntimeException(MESSAGE + e.getMessage());
        }
    }

    public static double calculateTSingleSidedJoint(@NonNull AbstractJointPanel jointPanelType, double density) {
        try {
            double thickness = parseJointPanelDoubleValue(jointPanelType, 0);
            double legSize = parseJointPanelDoubleValue(jointPanelType, 1);
            double length = parseJointPanelDoubleValue(jointPanelType, 2);
            QualityLevel qualityLevel = parseJointPanelQualityLevelValue(jointPanelType, 3);

            var joint = new TSingleSidedJoint(thickness, qualityLevel, legSize);
            return new Mass(length, joint, density)
                    .calculateMass();
        } catch (NumberFormatException e) {
            throw new RuntimeException(MESSAGE + e.getMessage());
        }
    }

    public static double calculateYBevelJoint(@NonNull AbstractJointPanel jointPanelType, double density) {
        return calculateBevelJointWithTheseFiveParams(jointPanelType, density, JointType.Y_BEVEL_JOINT);
    }

    public static double calculateKBevelJoint(@NonNull AbstractJointPanel jointPanelType, double density) {
        return calculateBevelJointWithTheseFiveParams(jointPanelType, density, JointType.K_BEVEL_JOINT);
    }

    public static double calculateXBevelJoint(@NonNull AbstractJointPanel jointPanelType, double density) {
        return calculateBevelJointWithTheseFiveParams(jointPanelType, density, JointType.X_BEVEL_JOINT);
    }

    private static double calculateBevelJointWithTheseFiveParams(@NonNull AbstractJointPanel jointPanelType, double density,
                                                                 @NonNull JointType jointType) {
        try {
            double thickness = parseJointPanelDoubleValue(jointPanelType, 0);
            double gap = parseJointPanelDoubleValue(jointPanelType, 1);
            double bevelAngle = parseJointPanelDoubleValue(jointPanelType, 2);
            double bead = parseJointPanelDoubleValue(jointPanelType , 3);
            double length = parseJointPanelDoubleValue(jointPanelType, 4);
            QualityLevel qualityLevel = parseJointPanelQualityLevelValue(jointPanelType, 5);

            IWeldOperations joint = switch (jointType) {
                case Y_BEVEL_JOINT -> new YBevelJoint(thickness, qualityLevel, gap, bevelAngle, bead);
                case K_BEVEL_JOINT -> new KBevelJoint(thickness, qualityLevel, gap, bevelAngle, bead);
                case X_BEVEL_JOINT -> new XBevelJoint(thickness, qualityLevel, gap, bevelAngle, bead);
                default ->
                        throw new IllegalArgumentException("Unsupported joint type!");
            };
            return new Mass(length, joint, density)
                    .calculateMass();
        } catch (NumberFormatException e) {
            throw new RuntimeException(MESSAGE + e.getMessage());
        }
    }

    private static double parseJointPanelDoubleValue(@NonNull AbstractJointPanel jointPanelType, int index) {
        return Optional.ofNullable(jointPanelType.getTextComponentsList().get(index).getText())
                .map(Double::parseDouble)
                .orElseThrow(() -> new IllegalArgumentException(MESSAGE_CHECKER_PARSER));
    }

   private static QualityLevel parseJointPanelQualityLevelValue(@NonNull AbstractJointPanel jointPanelType, int index) {
        return Optional.ofNullable(jointPanelType.getTextComponentsList().get(index).getText())
                .map(QualityLevel::valueOf)
                .orElseThrow(() -> new IllegalArgumentException(MESSAGE_CHECKER_PARSER));
    }
}
