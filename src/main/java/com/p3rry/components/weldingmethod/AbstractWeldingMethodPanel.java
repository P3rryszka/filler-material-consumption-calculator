package com.p3rry.components.weldingmethod;

import com.p3rry.components.componentsmanagement.IComponentsAdder;
import com.p3rry.components.componentsmanagement.IComponentsSetter;
import com.p3rry.components.componentsmanagement.ISelfComponentSetter;
import lombok.Getter;
import lombok.NonNull;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWeldingMethodPanel implements ISelfComponentSetter, IComponentsAdder, IComponentsSetter {
    public static final int PANEL_X_POSITION = 300;
    public static final int PANEL_Y_POSITION = 140;
    public static final int PANEL_WIDTH = 180;
    public static final int PANEL_HEIGHT = 400;
    private static final String MESSAGE = "Invalid welding method parameters!";

    @Getter
    protected JPanel panel;
    protected WeldingMethodLabels weldingMethodLabels;

    @Getter
    protected WeldingMethodTextComponents weldingMethodTextComponents;
    protected List<JLabel> labelsList;
    protected List<JTextComponent> textComponentsList;

    public AbstractWeldingMethodPanel() {
        this.panel = new JPanel();
        this.weldingMethodLabels = new WeldingMethodLabels();
        this.weldingMethodTextComponents = new WeldingMethodTextComponents();
        this.labelsList = new ArrayList<>();
        this.textComponentsList = new ArrayList<>();

        setSelfComponent();
        setComponents();
        addComponents();
    }

    @Override
    public void setSelfComponent() {
        panel.setLayout(null);
        panel.setBounds(PANEL_X_POSITION, PANEL_Y_POSITION,
                PANEL_WIDTH, PANEL_HEIGHT);
    }

    public void cleanComponents(@NonNull String componentName) {
        if(componentName.equalsIgnoreCase("W"))
            weldingMethodTextComponents.getWireSpoolMassTextField().setText("");
        else if (componentName.equalsIgnoreCase("L"))
            weldingMethodTextComponents.getElectrodeLengthTextField().setText("");
        else if (componentName.equalsIgnoreCase("D"))
            weldingMethodTextComponents.getElectrodeDiameterTextField().setText("");
        else if (componentName.equalsIgnoreCase("RL"))
            weldingMethodTextComponents.getRodLengthTextField().setText("");
        else if (componentName.equalsIgnoreCase("RD"))
            weldingMethodTextComponents.getRodLengthTextField().setText("");
        else
            throw new IllegalArgumentException(MESSAGE);
    }

    public boolean checkIfComponentsAreEmpty(@NonNull String componentName) {
        if(componentName.equalsIgnoreCase("W"))
            return weldingMethodTextComponents.getWireSpoolMassTextField().getText().isEmpty();
        else if (componentName.equalsIgnoreCase("L"))
            return weldingMethodTextComponents.getElectrodeLengthTextField().getText().isEmpty();
        else if (componentName.equalsIgnoreCase("D"))
            return weldingMethodTextComponents.getElectrodeDiameterTextField().getText().isEmpty();
        else if (componentName.equalsIgnoreCase("RL"))
            return weldingMethodTextComponents.getRodLengthTextField().getText().isEmpty();
        else if (componentName.equalsIgnoreCase("RD"))
            return weldingMethodTextComponents.getRodDiameterTextField().getText().isEmpty();
        else
            throw new IllegalArgumentException(MESSAGE);
    }
}
