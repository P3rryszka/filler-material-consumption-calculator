package com.p3rry.components.weldingmethod;

public class SmawPanel extends AbstractWeldingMethodPanel {
    @Override
    public void addComponents() {
        panel.add(weldingMethodLabels.getNameInfo());
        panel.add(weldingMethodLabels.getElectrodeLengthLabel());
        panel.add(weldingMethodTextComponents.getElectrodeLengthTextField());
        panel.add(weldingMethodLabels.getElectrodeDiameterLabel());
        panel.add(weldingMethodTextComponents.getElectrodeDiameterTextField());
    }

    @Override
    public void setComponents() {
        weldingMethodLabels.getNameInfo().setBounds(10,0,
                WeldingMethodLabels.LABEL_WIDTH, WeldingMethodLabels.LABEL_HEIGHT);
        weldingMethodLabels.getElectrodeLengthLabel().setBounds(10, 30,
                WeldingMethodLabels.LABEL_WIDTH, WeldingMethodLabels.LABEL_HEIGHT);
        weldingMethodTextComponents.getElectrodeLengthTextField().setBounds(10,60,
                WeldingMethodTextComponents.TEXT_COMPONENT_WIDTH, WeldingMethodTextComponents.TEXT_COMPONENT_HEIGHT);
        weldingMethodLabels.getElectrodeDiameterLabel().setBounds(10, 100,
                WeldingMethodLabels.LABEL_WIDTH, WeldingMethodLabels.LABEL_HEIGHT);
        weldingMethodTextComponents.getElectrodeDiameterTextField().setBounds(10, 130,
                WeldingMethodTextComponents.TEXT_COMPONENT_WIDTH, WeldingMethodTextComponents.TEXT_COMPONENT_HEIGHT);

        weldingMethodLabels.getNameInfo().setText("SMAW");
    }
}
