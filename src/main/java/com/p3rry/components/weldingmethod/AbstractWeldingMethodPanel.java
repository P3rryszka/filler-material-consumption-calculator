package com.p3rry.components.weldingmethod;

import com.p3rry.components.componentsmanagement.IComponentsAdder;
import com.p3rry.components.componentsmanagement.IComponentsSetter;
import com.p3rry.components.componentsmanagement.ISelfComponentSetter;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractWeldingMethodPanel implements ISelfComponentSetter, IComponentsAdder, IComponentsSetter {
    public static final int PANEL_X_POSITION = 300;
    public static final int PANEL_Y_POSITION = 140;
    public static final int PANEL_WIDTH = 180;
    public static final int PANEL_HEIGHT = 400;

    @Getter
    protected JPanel panel;
    protected WeldingMethodLabels weldingMethodLabels;
    protected WeldingMethodTextComponents weldingMethodTextComponents;

    public AbstractWeldingMethodPanel() {
        this.panel = new JPanel();
        this.weldingMethodLabels = new WeldingMethodLabels();
        this.weldingMethodTextComponents = new WeldingMethodTextComponents();

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
}
