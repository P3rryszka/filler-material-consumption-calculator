package com.p3rry.components.panels.weldingmethodpanel;

import com.p3rry.components.Frame;
import com.p3rry.components.componentsmanagement.IComponentsAdder;
import com.p3rry.components.componentsmanagement.IComponentsSetter;
import com.p3rry.components.componentsmanagement.ISelfComponentSetter;
import com.p3rry.consts.WeldingMethod;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class WeldingMethodSelectionPanel implements ISelfComponentSetter, IComponentsSetter, IComponentsAdder {
    private static final int PANEL_X_POSITION = 0;
    private static final int PANEL_Y_POSITION = 630;
    private static final int PANEL_WIDTH = Frame.FRAME_WIDTH; //300
    private static final int PANEL_HEIGHT = 60;

    private static final int WELDING_METHOD_COMBOBOX_X_POSITION = 100;
    private static final int WELDING_METHOD_COMBOBOX_Y_POSITION = 10;
    private static final int WELDING_METHOD_COMBOBOX_WIDTH = 100;
    private static final int WELDING_METHOD_COMBOBOX_HEIGHT = 40;

    private JPanel panel;
    private JComboBox<WeldingMethod> weldingMethodComboBox;

    public WeldingMethodSelectionPanel() {
        this.panel = new JPanel();
        this.weldingMethodComboBox = new JComboBox<>(WeldingMethod.values());

        setSelfComponent();
        addComponents();
        setComponents();
    }

    @Override
    public void setSelfComponent() {
        this.panel.setLayout(null);
        this.panel.setBounds(PANEL_X_POSITION, PANEL_Y_POSITION,
                PANEL_WIDTH, PANEL_HEIGHT);
        this.panel.setBackground(Color.GREEN);
    }

    @Override
    public void addComponents() {
        this.panel.add(this.weldingMethodComboBox);
    }

    @Override
    public void setComponents() {
        this.weldingMethodComboBox.setMaximumRowCount(3);
        this.weldingMethodComboBox.setBounds(WELDING_METHOD_COMBOBOX_X_POSITION, WELDING_METHOD_COMBOBOX_Y_POSITION,
                WELDING_METHOD_COMBOBOX_WIDTH, WELDING_METHOD_COMBOBOX_HEIGHT);
    }
}
