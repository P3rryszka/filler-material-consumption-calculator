package com.p3rry.components.panels.jointpanel.jointbuttweldpanel;

import com.p3rry.components.panels.jointpanel.AbstractJointPanel;

public class NoBevelJointPanel extends AbstractJointPanel {
    @Override
    public void addToList() {
        labelsList.add(jointLabels.getThicknessLabel());
        labelsList.add(jointLabels.getGapLabel());
        labelsList.add(jointLabels.getLengthLabel());
        labelsList.add(jointLabels.getQualityLevelLabel());

        textComponentsList.add(jointTextComponents.getThicknessTextField());
        textComponentsList.add(jointTextComponents.getGapTextField());
        textComponentsList.add(jointTextComponents.getLengthTextField());
        textComponentsList.add(jointTextComponents.getQualityLevelTextField());
    }
}
