package com.p3rry.components.weldingmethod;

public class GmawPanel extends AbstractWeldingMethodPanel {
    @Override
    public void addToList() {
        labelsList.add(weldingMethodLabels.getNameInfo());
        labelsList.add(weldingMethodLabels.getWireSpoolMassLabel());

        textComponentsList.add(weldingMethodTextComponents.getWireSpoolMassTextField());
    }

    @Override
    public void setComponents() {
        labelsList.getFirst().setBounds(10,0,
                WeldingMethodLabels.LABEL_WIDTH, WeldingMethodLabels.LABEL_HEIGHT);
        labelsList.get(1).setBounds(10, 30,
                WeldingMethodLabels.LABEL_WIDTH, WeldingMethodLabels.LABEL_HEIGHT);
        textComponentsList.getFirst().setBounds(10,60,
                WeldingMethodTextComponents.TEXT_COMPONENT_WIDTH, WeldingMethodTextComponents.TEXT_COMPONENT_HEIGHT);

        weldingMethodLabels.getNameInfo().setText("GMAW");
    }
}
