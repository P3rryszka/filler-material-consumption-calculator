package com.p3rry.components.weldingmethod;

import com.p3rry.components.componentsmanagers.IComponentsAdder;
import com.p3rry.components.componentsmanagers.IComponentsSetter;
import com.p3rry.components.componentsmanagers.IListAdder;
import com.p3rry.components.componentsmanagers.ISelfComponentSetter;
import lombok.Getter;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWeldingMethodPanel implements ISelfComponentSetter, IComponentsAdder, IComponentsSetter, IListAdder {
    protected static final int PANEL_X_POSITION = 300;
    protected static final int PANEL_Y_POSITION = 140;
    protected static final int PANEL_WIDTH = 180;
    protected static final int PANEL_HEIGHT = 400;

    protected WeldingMethodLabels weldingMethodLabels;
    protected WeldingMethodTextComponents weldingMethodTextComponents;

    @Getter
    protected JPanel panel;
    @Getter
    protected List<JTextComponent> textComponentsList;
    protected List<JLabel> labelsList;

    public AbstractWeldingMethodPanel() {
        this.panel = new JPanel();
        this.weldingMethodLabels = new WeldingMethodLabels();
        this.weldingMethodTextComponents = new WeldingMethodTextComponents();
        this.labelsList = new ArrayList<>();
        this.textComponentsList = new ArrayList<>();

        addToList();
        setSelfComponent();
        setComponents();
        addComponents();
    }

    @Override
    public void addComponents() {
       labelsList.forEach(panel::add);
       textComponentsList.forEach(panel::add);
    }

    @Override
    public void setSelfComponent() {
        panel.setLayout(null);
        panel.setBounds(PANEL_X_POSITION, PANEL_Y_POSITION,
                PANEL_WIDTH, PANEL_HEIGHT);
    }
}
