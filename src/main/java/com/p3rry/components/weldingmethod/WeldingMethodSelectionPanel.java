package com.p3rry.components.weldingmethod;

import com.p3rry.components.componentsmanagers.IComponentsAdder;
import com.p3rry.components.componentsmanagers.IComponentsSetter;
import com.p3rry.components.componentsmanagers.ISelfComponentSetter;
import com.p3rry.consts.WeldingMethodType;
import lombok.Getter;

import javax.swing.*;

@Getter
public class WeldingMethodSelectionPanel implements ISelfComponentSetter, IComponentsSetter, IComponentsAdder {
    private static final int PANEL_X_POSITION = 300;
    private static final int PANEL_Y_POSITION = 0;
    private static final int PANEL_WIDTH = 180;
    private static final int PANEL_HEIGHT = 140;

    private static final int WELDING_METHOD_COMBOBOX_X_POSITION = 40;
    private static final int WELDING_METHOD_COMBOBOX_Y_POSITION = 50;
    private static final int WELDING_METHOD_COMBOBOX_WIDTH = 100;
    private static final int WELDING_METHOD_COMBOBOX_HEIGHT = 40;

    private JPanel panel;
    private JComboBox<WeldingMethodType> weldingMethodComboBox;

    public WeldingMethodSelectionPanel() {
        this.panel = new JPanel();
        this.weldingMethodComboBox = new JComboBox<>(WeldingMethodType.values());

        setSelfComponent();
        addComponents();
        setComponents();
    }

    @Override
    public void setSelfComponent() {
        panel.setLayout(null);
        panel.setBounds(PANEL_X_POSITION, PANEL_Y_POSITION,
                PANEL_WIDTH, PANEL_HEIGHT);
    }

    @Override
    public void addComponents() {
        panel.add(weldingMethodComboBox);
    }

    @Override
    public void setComponents() {
        weldingMethodComboBox.setMaximumRowCount(3);
        weldingMethodComboBox.setFocusable(false);
        weldingMethodComboBox.setBounds(WELDING_METHOD_COMBOBOX_X_POSITION, WELDING_METHOD_COMBOBOX_Y_POSITION,
                WELDING_METHOD_COMBOBOX_WIDTH, WELDING_METHOD_COMBOBOX_HEIGHT);
    }
}
