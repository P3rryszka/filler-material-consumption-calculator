package com.p3rry.ui;

import com.p3rry.ui.componentmanager.IComponentsAdder;
import com.p3rry.ui.componentmanager.IComponentsSetter;
import com.p3rry.ui.componentmanager.ISelfComponentSetter;
import com.p3rry.utlis.InputBlocker;
import lombok.Getter;

import javax.swing.*;

public class BaseMaterialDensityPanel implements ISelfComponentSetter, IComponentsAdder, IComponentsSetter {
    private static final int PANEL_X_POSITION = 300;
    private static final int PANEL_Y_POSITION = 450;
    private static final int PANEL_WIDTH = 180;
    private static final int PANEL_HEIGHT = 170;

    private static final int LABEL_WIDTH = 160;
    private static final int LABEL_HEIGHT = 20;

    private static final int TEXT_COMPONENT_WIDTH = 160;
    private static final int TEXT_COMPONENT_HEIGHT = 30;

    @Getter
    private JTextField baseMaterialDensityTextField;
    @Getter
    private JPanel panel;
    private JLabel baseMaterialDensityLabel;

    public BaseMaterialDensityPanel() {
        this.panel = new JPanel();
        this.baseMaterialDensityLabel = new JLabel("Base material density [g/cm^3]:");
        this.baseMaterialDensityTextField = InputBlocker.numberLimitInput();

        setSelfComponent();
        setComponents();
        addComponents();
    }

    @Override
    public void addComponents() {
        panel.add(baseMaterialDensityLabel);
        panel.add(baseMaterialDensityTextField);
    }

    @Override
    public void setComponents() {
        baseMaterialDensityLabel.setBounds(10, 10,
                LABEL_WIDTH, LABEL_HEIGHT);
        baseMaterialDensityTextField.setBounds(10, 40,
                TEXT_COMPONENT_WIDTH, TEXT_COMPONENT_HEIGHT);
    }

    @Override
    public void setSelfComponent() {
        panel.setLayout(null);
        panel.setBounds(PANEL_X_POSITION, PANEL_Y_POSITION,
                PANEL_WIDTH, PANEL_HEIGHT);
    }
}
