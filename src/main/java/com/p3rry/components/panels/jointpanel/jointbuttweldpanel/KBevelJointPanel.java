package com.p3rry.components.panels.jointpanel.jointbuttweldpanel;

import com.p3rry.components.panels.jointpanel.AbstractJointPanel;

public class KBevelJointPanel extends AbstractJointPanel {
    @Override
    public void addToList() {
        labelsList.add(jointLabels.getThicknessLabel());
        labelsList.add(jointLabels.getGapLabel());
        labelsList.add(jointLabels.getBevelAngleLabel());
        labelsList.add(jointLabels.getBeadLabel());
        labelsList.add(jointLabels.getLengthLabel());
        labelsList.add(jointLabels.getQualityLevelLabel());

        textComponentsList.add(jointTextComponents.getThicknessTextField());
        textComponentsList.add(jointTextComponents.getGapTextField());
        textComponentsList.add(jointTextComponents.getBevelAngleTextField());
        textComponentsList.add(jointTextComponents.getBeadTextField());
        textComponentsList.add(jointTextComponents.getLengthTextField());
        textComponentsList.add(jointTextComponents.getQualityLevelTextField());

    }
}
