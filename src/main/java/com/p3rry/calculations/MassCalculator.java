package com.p3rry.calculations;

import com.p3rry.calculations.weld.JointFactory;
import com.p3rry.calculations.weld.Mass;
import com.p3rry.components.joint.AbstractJointPanel;
import com.p3rry.consts.Description;
import com.p3rry.utlis.InputParser;
import com.p3rry.consts.QualityLevel;
import lombok.NonNull;

public class MassCalculator {
    private static final String MESSAGE = "Invalid input! Panel type is invalid! ";

    public static double calculateNoBevelJointMass(@NonNull AbstractJointPanel jointPanelType, double density) {
        try {
            double thickness = InputParser.parseDoubleJointPanelParams(jointPanelType, 0);
            double gap = InputParser.parseDoubleJointPanelParams(jointPanelType, 1);
            double length = InputParser.parseDoubleJointPanelParams(jointPanelType, 2);
            QualityLevel qualityLevel = InputParser.parseEnumJointPanelParams(jointPanelType, 3);

            var joint = JointFactory.createButtWeldJoint(Description.NO_BEVEL_JOINT, thickness, qualityLevel, gap);
            return new Mass(length, joint).calculateMass(density);
        } catch (NumberFormatException e) {
            throw new RuntimeException(MESSAGE + e.getMessage());
        }
    }

    public static double calculateVBevelJointMass(@NonNull AbstractJointPanel jointPanelType, double density) {
        try {
            double thickness = InputParser.parseDoubleJointPanelParams(jointPanelType, 0);
            double gap = InputParser.parseDoubleJointPanelParams(jointPanelType, 1);
            double bevelAngle = InputParser.parseDoubleJointPanelParams(jointPanelType, 2);
            double length = InputParser.parseDoubleJointPanelParams(jointPanelType, 3);
            QualityLevel qualityLevel = InputParser.parseEnumJointPanelParams(jointPanelType, 4);

            var joint = JointFactory.createButtWeldJoint(Description.V_BEVEL_JOINT, thickness, qualityLevel, gap, bevelAngle);
            return new Mass(length, joint).calculateMass(density);
        } catch (NumberFormatException e) {
            throw new RuntimeException(MESSAGE + e.getMessage());
        }
    }

   public static double calculateUBevelJoint(@NonNull AbstractJointPanel jointPanelType, double density) {
        try {
            double thickness = InputParser.parseDoubleJointPanelParams(jointPanelType, 0);
            double gap = InputParser.parseDoubleJointPanelParams(jointPanelType, 1);
            double bevelAngle = InputParser.parseDoubleJointPanelParams(jointPanelType, 2);
            double bead = InputParser.parseDoubleJointPanelParams(jointPanelType, 3);
            double rounding = InputParser.parseDoubleJointPanelParams(jointPanelType, 4);
            double length = InputParser.parseDoubleJointPanelParams(jointPanelType, 5);
            QualityLevel qualityLevel = InputParser.parseEnumJointPanelParams(jointPanelType, 6);

            var joint = JointFactory.createButtWeldJoint(Description.U_BEVEL_JOINT, thickness, qualityLevel, gap, bevelAngle, bead, rounding);
            return new Mass(length, joint).calculateMass(density);
        } catch (NumberFormatException e) {
            throw new RuntimeException(MESSAGE + e.getMessage());
        }
    }

    public static double calculateTSingleSidedJoint(@NonNull AbstractJointPanel jointPanelType, double density) {
        try {
            double thickness = InputParser.parseDoubleJointPanelParams(jointPanelType, 0);
            double legSize = InputParser.parseDoubleJointPanelParams(jointPanelType, 1);
            double length = InputParser.parseDoubleJointPanelParams(jointPanelType, 2);
            QualityLevel qualityLevel = InputParser.parseEnumJointPanelParams(jointPanelType, 3);

            var joint = JointFactory.createFilletWeldJoint(Description.T_SINGLE_SIDED_JOINT, thickness, qualityLevel, legSize);
            return new Mass(length, joint).calculateMass(density);
        } catch (NumberFormatException e) {
            throw new RuntimeException(MESSAGE + e.getMessage());
        }
    }

    public static double calculateYBevelJoint(@NonNull AbstractJointPanel jointPanelType, double density) {
        return calculateBevelJointWithTheseFiveParams(jointPanelType, density, Description.Y_BEVEL_JOINT);
    }

    public static double calculateKBevelJoint(@NonNull AbstractJointPanel jointPanelType, double density) {
        return calculateBevelJointWithTheseFiveParams(jointPanelType, density, Description.K_BEVEL_JOINT);
    }

    public static double calculateXBevelJoint(@NonNull AbstractJointPanel jointPanelType, double density) {
        return calculateBevelJointWithTheseFiveParams(jointPanelType, density, Description.X_BEVEL_JOINT);
    }

    private static double calculateBevelJointWithTheseFiveParams(@NonNull AbstractJointPanel jointPanelType, double density,
                                                                 @NonNull Description jointType) {
        try {
            double thickness = InputParser.parseDoubleJointPanelParams(jointPanelType, 0);
            double gap = InputParser.parseDoubleJointPanelParams(jointPanelType, 1);
            double bevelAngle = InputParser.parseDoubleJointPanelParams(jointPanelType, 2);
            double bead = InputParser.parseDoubleJointPanelParams(jointPanelType, 3);
            double length = InputParser.parseDoubleJointPanelParams(jointPanelType, 4);
            QualityLevel qualityLevel = InputParser.parseEnumJointPanelParams(jointPanelType, 5);

            var joint = JointFactory.createButtWeldJoint(jointType, thickness, qualityLevel, gap, bevelAngle, bead);
            return new Mass(length, joint).calculateMass(density);
        } catch (NumberFormatException e) {
            throw new RuntimeException(MESSAGE + e.getMessage());
        }
    }
}
