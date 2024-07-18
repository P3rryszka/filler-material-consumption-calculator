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
       labelsList.get(0).setBounds(10, 0,
               WeldingMethodLabels.LABEL_WIDTH, WeldingMethodLabels.LABEL_HEIGHT);
       labelsList.get(1).setBounds(10, 30,
               WeldingMethodLabels.LABEL_WIDTH, WeldingMethodLabels.LABEL_HEIGHT);
       textComponentsList.get(0).setBounds(10,60,
               WeldingMethodTextComponents.TEXT_COMPONENT_WIDTH, WeldingMethodTextComponents.TEXT_COMPONENT_HEIGHT);
       labelsList.get(2).setBounds(10, 100,
               WeldingMethodLabels.LABEL_WIDTH, WeldingMethodLabels.LABEL_HEIGHT);
       textComponentsList.get(1).setBounds(10, 130,
               WeldingMethodTextComponents.TEXT_COMPONENT_WIDTH, WeldingMethodTextComponents.TEXT_COMPONENT_HEIGHT);
       labelsList.get(3).setBounds(10, 170,
               WeldingMethodLabels.LABEL_WIDTH, WeldingMethodLabels.LABEL_HEIGHT);
       textComponentsList.get(2).setBounds(10, 200,
               WeldingMethodTextComponents.TEXT_COMPONENT_WIDTH, WeldingMethodTextComponents.TEXT_COMPONENT_HEIGHT);

       weldingMethodLabels.getNameInfo().setText("SMAW");
    }
}
