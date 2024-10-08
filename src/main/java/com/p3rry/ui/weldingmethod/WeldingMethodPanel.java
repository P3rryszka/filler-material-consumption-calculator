package com.p3rry.ui.weldingmethod;

import com.p3rry.ui.componentmanager.IComponentsAdder;
import com.p3rry.ui.componentmanager.IComponentsSetter;
import com.p3rry.ui.componentmanager.ISelfComponentSetter;
import lombok.Builder;
import lombok.Getter;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.util.List;

public class WeldingMethodPanel implements ISelfComponentSetter, IComponentsAdder, IComponentsSetter {
    protected static final int PANEL_X_POSITION = 300;
    protected static final int PANEL_Y_POSITION = 140;
    protected static final int PANEL_WIDTH = 180;
    protected static final int PANEL_HEIGHT = 310;


    @Getter
    protected JPanel panel;
    @Getter
    protected List<JTextComponent> textComponentsList;
    protected JLabel name;
    protected List<JLabel> labelsList;

    @Builder
    public WeldingMethodPanel(List<JLabel> labelsList, List<JTextComponent> textComponentList,
                              JLabel name) {

        this.labelsList = labelsList;
        this.textComponentsList = textComponentList;
        this.name = name;
        this.panel = new JPanel();

        setSelfComponent();
        addComponents();
        setComponents();
    }

    @Override
    public void addComponents() {
       labelsList.forEach(panel::add);
       textComponentsList.forEach(panel::add);
       panel.add(this.name);
    }

    @Override
    public void setSelfComponent() {
        panel.setLayout(null);
        panel.setBounds(PANEL_X_POSITION, PANEL_Y_POSITION,
                PANEL_WIDTH, PANEL_HEIGHT);
    }

    @Override
    public void setComponents() {

        this.name.setBounds(10, 0,
                WeldingMethodLabel.LABEL_WIDTH, WeldingMethodLabel.LABEL_HEIGHT);
        positionComponentsWithOffset(labelsList, 10, 30,
                WeldingMethodLabel.LABEL_WIDTH, WeldingMethodLabel.LABEL_HEIGHT, 70);
        positionComponentsWithOffset(textComponentsList, 10, 60,
                WeldingMethodTextComponents.TEXT_COMPONENT_WIDTH, WeldingMethodTextComponents.TEXT_COMPONENT_HEIGHT, 70);
    }
}
