package com.p3rry.components.joint.jointbuttweldpanel;

import com.p3rry.components.joint.AbstractJointPanel;

public class UBevelJointPanel extends AbstractJointPanel {
    @Override
    public void addToList() {
        labelsList.add(jointLabel.getThicknessLabel());
        labelsList.add(jointLabel.getGapLabel());
        labelsList.add(jointLabel.getBevelAngleLabel());
        labelsList.add(jointLabel.getBeadLabel());
        labelsList.add(jointLabel.getRoundingLabel());
        labelsList.add(jointLabel.getLengthLabel());
        labelsList.add(jointLabel.getQualityLevelLabel());

        textComponentsList.add(jointTextComponent.getThicknessTextField());
        textComponentsList.add(jointTextComponent.getGapTextField());
        textComponentsList.add(jointTextComponent.getBevelAngleTextField());
        textComponentsList.add(jointTextComponent.getBeadTextField());
        textComponentsList.add(jointTextComponent.getRoundingTextField());
        textComponentsList.add(jointTextComponent.getLengthTextField());
        textComponentsList.add(jointTextComponent.getQualityLevelTextField());
    }
}
