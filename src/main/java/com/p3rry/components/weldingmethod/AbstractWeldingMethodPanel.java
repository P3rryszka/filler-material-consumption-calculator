package com.p3rry.components.weldingmethod;

import com.p3rry.components.componentsmanagement.IComponentsAdder;
import com.p3rry.components.componentsmanagement.IComponentsSetter;
import com.p3rry.components.componentsmanagement.IListAdder;
import com.p3rry.components.componentsmanagement.ISelfComponentSetter;
import lombok.Getter;
import lombok.NonNull;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWeldingMethodPanel implements ISelfComponentSetter, IComponentsAdder, IComponentsSetter, IListAdder {
    public static final int PANEL_X_POSITION = 300;
    public static final int PANEL_Y_POSITION = 140;
    public static final int PANEL_WIDTH = 180;
    public static final int PANEL_HEIGHT = 400;

    protected WeldingMethodLabels weldingMethodLabels;
    protected WeldingMethodTextComponents weldingMethodTextComponents;
    protected List<JLabel> labelsList;

    @Getter
    protected JPanel panel;
    @Getter
    protected List<JTextComponent> textComponentsList;


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
       manageComponents(textComponentsList, labelsList, panel);
    }

    @Override
    public void setSelfComponent() {
        panel.setLayout(null);
        panel.setBounds(PANEL_X_POSITION, PANEL_Y_POSITION,
                PANEL_WIDTH, PANEL_HEIGHT);
    }
}
