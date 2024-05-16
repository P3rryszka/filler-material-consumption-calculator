package com.p3rry.components.weldingmethod;

public class GtawPanel extends AbstractWeldingMethodPanel{
    @Override
    public void addComponents() {
        panel.add(weldingMethodLabels.getNameInfo());
        panel.add(weldingMethodLabels.getRodLengthLabel());
        panel.add(weldingMethodTextComponents.getRodLengthTextField());
        panel.add(weldingMethodLabels.getRodDiameterLabel());
        panel.add(weldingMethodTextComponents.getRodDiameterTextField());
    }

    @Override
    public void setComponents() {
        weldingMethodLabels.getNameInfo().setBounds(10,0,
                WeldingMethodLabels.LABEL_WIDTH, WeldingMethodLabels.LABEL_HEIGHT);
        weldingMethodLabels.getRodLengthLabel().setBounds(10, 30,
                WeldingMethodLabels.LABEL_WIDTH, WeldingMethodLabels.LABEL_HEIGHT);
        weldingMethodTextComponents.getRodLengthTextField().setBounds(10,60,
                WeldingMethodTextComponents.TEXT_COMPONENT_WIDTH, WeldingMethodTextComponents.TEXT_COMPONENT_HEIGHT);
        weldingMethodLabels.getRodDiameterLabel().setBounds(10, 100,
                WeldingMethodLabels.LABEL_WIDTH, WeldingMethodLabels.LABEL_HEIGHT);
        weldingMethodTextComponents.getRodDiameterTextField().setBounds(10, 130,
                WeldingMethodTextComponents.TEXT_COMPONENT_WIDTH, WeldingMethodTextComponents.TEXT_COMPONENT_HEIGHT);

        weldingMethodLabels.getNameInfo().setText("GTAW");
    }
}
