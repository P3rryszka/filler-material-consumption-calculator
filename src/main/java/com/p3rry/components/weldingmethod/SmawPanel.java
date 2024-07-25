package com.p3rry.components.weldingmethod;

public class SmawPanel extends AbstractWeldingMethodPanel {
    @Override
    public void addToList() {
        labelsList.add(weldingMethodLabels.getNameInfo());
        labelsList.add(weldingMethodLabels.getElectrodeLengthLabel());
        labelsList.add(weldingMethodLabels.getElectrodeDiameterLabel());
        labelsList.add(weldingMethodLabels.getElectrodeYield());

        textComponentsList.add(weldingMethodTextComponents.getElectrodeLengthTextField());
        textComponentsList.add(weldingMethodTextComponents.getElectrodeDiameterTextField());
        textComponentsList.add(weldingMethodTextComponents.getElectordeYieldTextField());
    }

    @Override
    public void setComponents() {
       labelsList.getFirst().setBounds(10, 0,
                WeldingMethodLabels.LABEL_WIDTH, WeldingMethodLabels.LABEL_HEIGHT);
       positionComponentsWithOffset(labelsList, 10, 30,
                WeldingMethodLabels.LABEL_WIDTH, WeldingMethodLabels.LABEL_HEIGHT,
                1, 70);
       positionComponentsWithOffset(textComponentsList, 10, 60,
                WeldingMethodTextComponents.TEXT_COMPONENT_WIDTH, WeldingMethodTextComponents.TEXT_COMPONENT_HEIGHT,
                0, 70);

       weldingMethodLabels.getNameInfo().setText("SMAW");
    }
}
