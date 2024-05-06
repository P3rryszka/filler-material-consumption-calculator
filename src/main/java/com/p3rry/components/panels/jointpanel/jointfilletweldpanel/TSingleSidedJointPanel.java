package com.p3rry.components.panels.jointpanel.jointfilletweldpanel;

import com.p3rry.components.panels.jointpanel.AbstractJointPanel;

public class TSingleSidedJointPanel extends AbstractJointPanel {
    @Override
    public void addToList() {
        labelsList.add(jointLabels.getThicknessLabel());
        labelsList.add(jointLabels.getLegSizeLabel());
        labelsList.add(jointLabels.getLengthLabel());
        labelsList.add(jointLabels.getQualityLevelLabel());

        textComponentsList.add(jointTextComponents.getThicknessTextField());
        textComponentsList.add(jointTextComponents.getLegSizeTextField());
        textComponentsList.add(jointTextComponents.getLengthTextField());
        textComponentsList.add(jointTextComponents.getQualityLevelTextField());
    }
}
