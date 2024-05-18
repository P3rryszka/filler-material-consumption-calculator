package com.p3rry.components.weldingmethod;

public class GtawPanel extends AbstractWeldingMethodPanel{
    @Override
    public void addToList() {
        labelsList.add(weldingMethodLabels.getNameInfo());
        labelsList.add(weldingMethodLabels.getRodLengthLabel());
        labelsList.add(weldingMethodLabels.getRodDiameterLabel());

        textComponentsList.add(weldingMethodTextComponents.getRodLengthTextField());
        textComponentsList.add(weldingMethodTextComponents.getRodDiameterTextField());
    }

    @Override
    public void setComponents() {
        labelsList.get(0).setBounds(10,0,
                WeldingMethodLabels.LABEL_WIDTH, WeldingMethodLabels.LABEL_HEIGHT);
        labelsList.get(1).setBounds(10, 30,
                WeldingMethodLabels.LABEL_WIDTH, WeldingMethodLabels.LABEL_HEIGHT);
        textComponentsList.get(0).setBounds(10,60,
                WeldingMethodTextComponents.TEXT_COMPONENT_WIDTH, WeldingMethodTextComponents.TEXT_COMPONENT_HEIGHT);
        labelsList.get(2).setBounds(10, 100,
                WeldingMethodLabels.LABEL_WIDTH, WeldingMethodLabels.LABEL_HEIGHT);
        textComponentsList.get(1).setBounds(10, 130,
                WeldingMethodTextComponents.TEXT_COMPONENT_WIDTH, WeldingMethodTextComponents.TEXT_COMPONENT_HEIGHT);

        weldingMethodLabels.getNameInfo().setText("GTAW");
    }
}
