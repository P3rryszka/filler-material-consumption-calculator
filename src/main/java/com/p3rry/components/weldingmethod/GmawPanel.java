package com.p3rry.components.weldingmethod;

public class GmawPanel extends AbstractWeldingMethodPanel {
    @Override
    public void addComponents() {
        panel.add(weldingMethodLabels.getNameInfo());
        panel.add(weldingMethodLabels.getWireSpoolMassLabel());
        panel.add(weldingMethodTextComponents.getWireSpoolMassTextField());
    }

    @Override
    public void setComponents() {
        weldingMethodLabels.getNameInfo().setBounds(10,0,
                WeldingMethodLabels.LABEL_WIDTH, WeldingMethodLabels.LABEL_HEIGHT);
        weldingMethodLabels.getWireSpoolMassLabel().setBounds(10, 30,
                WeldingMethodLabels.LABEL_WIDTH, WeldingMethodLabels.LABEL_HEIGHT);
        weldingMethodTextComponents.getWireSpoolMassTextField().setBounds(10,60,
                WeldingMethodTextComponents.TEXT_COMPONENT_WIDTH, WeldingMethodTextComponents.TEXT_COMPONENT_HEIGHT);

        weldingMethodLabels.getNameInfo().setText("GMAW");
    }
}
