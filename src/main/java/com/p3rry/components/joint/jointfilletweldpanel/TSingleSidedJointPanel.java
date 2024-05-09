package com.p3rry.components.joint.jointfilletweldpanel;

import com.p3rry.components.joint.AbstractJointPanel;

public class TSingleSidedJointPanel extends AbstractJointPanel {
    @Override
    public void addToList() {
        labelsList.add(jointLabel.getThicknessLabel());
        labelsList.add(jointLabel.getLegSizeLabel());
        labelsList.add(jointLabel.getLengthLabel());
        labelsList.add(jointLabel.getQualityLevelLabel());

        textComponentsList.add(jointTextComponent.getThicknessTextField());
        textComponentsList.add(jointTextComponent.getLegSizeTextField());
        textComponentsList.add(jointTextComponent.getLengthTextField());
        textComponentsList.add(jointTextComponent.getQualityLevelTextField());
    }
}
