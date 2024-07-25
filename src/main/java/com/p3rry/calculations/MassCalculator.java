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
    private static final String MESSAGE = "Invalid input! Panel type is invalid! ";
    private static final String MESSAGE_CHECKER_PARSER = "One from parsing values is null!";

    public static double calculateNoBevelJointMass(@NonNull AbstractJointPanel jointPanelType, double density) {
        try {
            double thickness = parseAndCheckNullDouble(jointPanelType, 0);
            double gap = parseAndCheckNullDouble(jointPanelType, 1);
            double length = parseAndCheckNullDouble(jointPanelType, 2);
            QualityLevel qualityLevel = parseAndCheckNullQualityLevel(jointPanelType, 3);

            var joint = new NoBevelJoint(thickness, qualityLevel, gap);
            return new Mass(length, joint).calculateMass(density);
        } catch (NumberFormatException e) {
            throw new RuntimeException(MESSAGE + e.getMessage());
        }
    }

    public static double calculateVBevelJointMass(@NonNull AbstractJointPanel jointPanelType, double density) {
        try {
            double thickness = parseAndCheckNullDouble(jointPanelType, 0);
            double gap = parseAndCheckNullDouble(jointPanelType, 1);
            double bevelAngle = parseAndCheckNullDouble(jointPanelType, 2);
            double length = parseAndCheckNullDouble(jointPanelType, 3);
            QualityLevel qualityLevel = parseAndCheckNullQualityLevel(jointPanelType, 4);

            var joint = new VBevelJoint(thickness, qualityLevel, gap, bevelAngle);
            return new Mass(length, joint).calculateMass(density);
        } catch (NumberFormatException e) {
            throw new RuntimeException(MESSAGE + e.getMessage());
        }
    }

   public static double calculateUBevelJoint(@NonNull AbstractJointPanel jointPanelType, double density) {
        try {
            double thickness = parseAndCheckNullDouble(jointPanelType, 0);
            double gap = parseAndCheckNullDouble(jointPanelType, 1);
            double bevelAngle = parseAndCheckNullDouble(jointPanelType, 2);
            double bead = parseAndCheckNullDouble(jointPanelType, 3);
            double rounding = parseAndCheckNullDouble(jointPanelType, 4);
            double length = parseAndCheckNullDouble(jointPanelType, 5);
            QualityLevel qualityLevel = parseAndCheckNullQualityLevel(jointPanelType, 6);

            var joint = new UBevelJoint(thickness, qualityLevel, gap, bevelAngle, bead, rounding);
            return new Mass(length, joint).calculateMass(density);
        } catch (NumberFormatException e) {
            throw new RuntimeException(MESSAGE + e.getMessage());
        }
    }

    public static double calculateTSingleSidedJoint(@NonNull AbstractJointPanel jointPanelType, double density) {
        try {
            double thickness = parseAndCheckNullDouble(jointPanelType, 0);
            double legSize = parseAndCheckNullDouble(jointPanelType, 1);
            double length = parseAndCheckNullDouble(jointPanelType, 2);
            QualityLevel qualityLevel = parseAndCheckNullQualityLevel(jointPanelType, 3);

            var joint = new TSingleSidedJoint(thickness, qualityLevel, legSize);
            return new Mass(length, joint).calculateMass(density);
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

    private static double calculateBevelJointWithTheseFiveParams(@NonNull AbstractJointPanel jointPanelType,
                                                                 double density, JointType jointType) {
        try {
            double thickness = parseAndCheckNullDouble(jointPanelType, 0);
            double gap = parseAndCheckNullDouble(jointPanelType, 1);
            double bevelAngle = parseAndCheckNullDouble(jointPanelType, 2);
            double bead = parseAndCheckNullDouble(jointPanelType , 3);
            double length = parseAndCheckNullDouble(jointPanelType, 4);
            QualityLevel qualityLevel = parseAndCheckNullQualityLevel(jointPanelType, 5);

            IWeldOperations joint = switch (jointType) {
                case Y_BEVEL_JOINT -> new YBevelJoint(thickness, qualityLevel, gap, bevelAngle, bead);
                case K_BEVEL_JOINT -> new KBevelJoint(thickness, qualityLevel, gap, bevelAngle, bead);
                case X_BEVEL_JOINT -> new XBevelJoint(thickness, qualityLevel, gap, bevelAngle, bead);
                default ->
                        throw new IllegalArgumentException("Unsupported joint type!");
            };
            return new Mass(length, joint).calculateMass(density);
        } catch (NumberFormatException e) {
            throw new RuntimeException(MESSAGE + e.getMessage());
        }
    }

    private static double parseAndCheckNullDouble(@NonNull AbstractJointPanel jointPanelType, int index) {
        return Optional.ofNullable(jointPanelType.getTextComponentsList().get(index).getText())
                .map(Double::parseDouble)
                .orElseThrow(() -> new IllegalArgumentException(MESSAGE_CHECKER_PARSER));
    }

   private static QualityLevel parseAndCheckNullQualityLevel(@NonNull AbstractJointPanel jointPanelType, int index) {
        return Optional.ofNullable(jointPanelType.getTextComponentsList().get(index).getText())
                .map(QualityLevel::valueOf)
                .orElseThrow(() -> new IllegalArgumentException(MESSAGE_CHECKER_PARSER));
    }
}
