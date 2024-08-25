package com.p3rry.components.joint;

import com.p3rry.consts.JointType;
import lombok.NonNull;

import java.util.Arrays;

public class JointPanelFactory {
    public static JointPanel createJointPanel(@NonNull JointType jointType) {
        JointLabel jointLabel = new JointLabel();
        JointTextComponent jointTextComponent = new JointTextComponent();

        switch (jointType) {
            case NO_BEVEL_JOINT -> {
                return JointPanel.builder()
                        .labelsList(
                                Arrays.asList(
                                        jointLabel.getThicknessLabel(),
                                        jointLabel.getGapLabel(),
                                        jointLabel.getLengthLabel(),
                                        jointLabel.getQualityLevelLabel()
                                )
                        )
                        .textComponentsList(
                                Arrays.asList(
                                        jointTextComponent.getThicknessTextField(),
                                        jointTextComponent.getGapTextField(),
                                        jointTextComponent.getLengthTextField(),
                                        jointTextComponent.getQualityLevelTextField()
                                )
                        )
                        .build();
            }
            case V_BEVEL_JOINT -> {
                return JointPanel.builder()
                        .labelsList(
                                Arrays.asList(
                                        jointLabel.getThicknessLabel(),
                                        jointLabel.getGapLabel(),
                                        jointLabel.getBevelAngleLabel(),
                                        jointLabel.getLengthLabel(),
                                        jointLabel.getQualityLevelLabel()
                                )
                        )
                        .textComponentsList(
                                Arrays.asList(
                                        jointTextComponent.getThicknessTextField(),
                                        jointTextComponent.getGapTextField(),
                                        jointTextComponent.getBevelAngleTextField(),
                                        jointTextComponent.getLengthTextField(),
                                        jointTextComponent.getQualityLevelTextField()
                                )
                        )
                        .build();
            }
            case U_BEVEL_JOINT -> {
                return JointPanel.builder()
                        .labelsList(
                                Arrays.asList(
                                        jointLabel.getThicknessLabel(),
                                        jointLabel.getGapLabel(),
                                        jointLabel.getBevelAngleLabel(),
                                        jointLabel.getBeadLabel(),
                                        jointLabel.getRoundingLabel(),
                                        jointLabel.getLengthLabel(),
                                        jointLabel.getQualityLevelLabel()
                                )
                        )
                        .textComponentsList(
                                Arrays.asList(
                                        jointTextComponent.getThicknessTextField(),
                                        jointTextComponent.getGapTextField(),
                                        jointTextComponent.getBevelAngleTextField(),
                                        jointTextComponent.getBeadTextField(),
                                        jointTextComponent.getRoundingTextField(),
                                        jointTextComponent.getLengthTextField(),
                                        jointTextComponent.getQualityLevelTextField()
                                )
                        )
                        .build();
            }
            case T_SINGLE_SIDED_JOINT -> {
               return JointPanel.builder()
                       .labelsList(
                               Arrays.asList(
                                       jointLabel.getThicknessLabel(),
                                       jointLabel.getLegSizeLabel(),
                                       jointLabel.getLengthLabel(),
                                       jointLabel.getQualityLevelLabel()
                               )
                       )
                       .textComponentsList(
                               Arrays.asList(
                                       jointTextComponent.getThicknessTextField(),
                                       jointTextComponent.getLegSizeTextField(),
                                       jointTextComponent.getLengthTextField(),
                                       jointTextComponent.getQualityLevelTextField()
                               )
                       )
                       .build();
            }
            case Y_BEVEL_JOINT, K_BEVEL_JOINT, X_BEVEL_JOINT -> {
                return JointPanel.builder()
                        .labelsList(
                                Arrays.asList(
                                        jointLabel.getThicknessLabel(),
                                        jointLabel.getGapLabel(),
                                        jointLabel.getBevelAngleLabel(),
                                        jointLabel.getBeadLabel(),
                                        jointLabel.getLengthLabel(),
                                        jointLabel.getQualityLevelLabel()
                                )
                        )
                        .textComponentsList(
                                Arrays.asList(
                                        jointTextComponent.getThicknessTextField(),
                                        jointTextComponent.getGapTextField(),
                                        jointTextComponent.getBevelAngleTextField(),
                                        jointTextComponent.getBeadTextField(),
                                        jointTextComponent.getLengthTextField(),
                                        jointTextComponent.getQualityLevelTextField()
                                )
                        )
                        .build();
            }
            default ->
                    throw new IllegalArgumentException("Invalid joint type!");
        }
    }
}
