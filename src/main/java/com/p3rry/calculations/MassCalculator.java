package com.p3rry.calculations;

import com.p3rry.calculations.weld.JointFactory;
import com.p3rry.calculations.weld.Mass;
import com.p3rry.components.joint.AbstractJointPanel;
import com.p3rry.components.utlis.InputParser;
import com.p3rry.consts.QualityLevel;
import lombok.NonNull;

public class MassCalculator {
    public static double calculateNoBevelJointMass(@NonNull AbstractJointPanel jointPanelType, double density) {
        try {
            double thickness = InputParser.parseDoubleBevelJointParams(jointPanelType, 0);
            double gap = InputParser.parseDoubleBevelJointParams(jointPanelType, 1);
            double length = InputParser.parseDoubleBevelJointParams(jointPanelType, 2);
            QualityLevel qualityLevel = InputParser.parseEnumBevelJointParams(jointPanelType, 3);

            var joint = JointFactory.createButtWeldJoint("N", thickness, qualityLevel, gap);
            return new Mass(length, joint).calculateMass(density);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid input!" + e.getMessage());
        }
    }

    public static double calculateVBevelJointMass(@NonNull AbstractJointPanel jointPanelType, double density) {
        try {
            double thickness = InputParser.parseDoubleBevelJointParams(jointPanelType, 0);
            double gap = InputParser.parseDoubleBevelJointParams(jointPanelType, 1);
            double bevelAngle = InputParser.parseDoubleBevelJointParams(jointPanelType, 2);
            double length = InputParser.parseDoubleBevelJointParams(jointPanelType, 3);
            QualityLevel qualityLevel = InputParser.parseEnumBevelJointParams(jointPanelType, 4);

            var joint = JointFactory.createButtWeldJoint("V", thickness, qualityLevel, gap, bevelAngle);
            return new Mass(length, joint).calculateMass(density);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid input!" + e.getMessage());
        }
    }

   public static double calculateUBevelJoint(@NonNull AbstractJointPanel jointPanelType, double density) {
        try {
            double thickness = InputParser.parseDoubleBevelJointParams(jointPanelType, 0);
            double gap = InputParser.parseDoubleBevelJointParams(jointPanelType, 1);
            double bevelAngle = InputParser.parseDoubleBevelJointParams(jointPanelType, 2);
            double bead = InputParser.parseDoubleBevelJointParams(jointPanelType, 3);
            double rounding = InputParser.parseDoubleBevelJointParams(jointPanelType, 4);
            double length = InputParser.parseDoubleBevelJointParams(jointPanelType, 5);
            QualityLevel qualityLevel = InputParser.parseEnumBevelJointParams(jointPanelType, 6);

            var joint = JointFactory.createButtWeldJoint("U", thickness, qualityLevel, gap, bevelAngle, bead, rounding);
            return new Mass(length, joint).calculateMass(density);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid input!" + e.getMessage());
        }
    }

    public static double calculateTSingleSidedJoint(@NonNull AbstractJointPanel jointPanelType, double density) {
        try {
            double thickness = InputParser.parseDoubleBevelJointParams(jointPanelType, 0);
            double legSize = InputParser.parseDoubleBevelJointParams(jointPanelType, 1);
            double length = InputParser.parseDoubleBevelJointParams(jointPanelType, 2);
            QualityLevel qualityLevel = InputParser.parseEnumBevelJointParams(jointPanelType, 3);

            var joint = JointFactory.createFilletWeldJoint("T", thickness, qualityLevel, legSize);
            return new Mass(length, joint).calculateMass(density);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid input!" + e.getMessage());
        }
    }

    public static double calculateYBevelJoint(@NonNull AbstractJointPanel jointPanelType, double density) {
        return calculateBevelJointWithTheseFiveParams(jointPanelType, density, "Y");
    }

    public static double calculateKBevelJoint(@NonNull AbstractJointPanel jointPanelType, double density) {
        return calculateBevelJointWithTheseFiveParams(jointPanelType, density, "K");
    }

    public static double calculateXBevelJoint(@NonNull AbstractJointPanel jointPanelType, double density) {
        return calculateBevelJointWithTheseFiveParams(jointPanelType, density, "X");
    }

    private static double calculateBevelJointWithTheseFiveParams(@NonNull AbstractJointPanel jointPanelType, double density,
                                                                 @NonNull String jointType) {
        try {
            double thickness = InputParser.parseDoubleBevelJointParams(jointPanelType, 0);
            double gap = InputParser.parseDoubleBevelJointParams(jointPanelType, 1);
            double bevelAngle = InputParser.parseDoubleBevelJointParams(jointPanelType, 2);
            double bead = InputParser.parseDoubleBevelJointParams(jointPanelType, 3);
            double length = InputParser.parseDoubleBevelJointParams(jointPanelType, 4);
            QualityLevel qualityLevel = InputParser.parseEnumBevelJointParams(jointPanelType, 5);

            var joint = JointFactory.createButtWeldJoint(jointType, thickness, qualityLevel, gap, bevelAngle, bead);
            return new Mass(length, joint).calculateMass(density);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid input!" + e.getMessage());
        }
    }
}
