package com.p3rry.components.joint;

import com.p3rry.components.componentsmanagement.IComponentsAdder;
import com.p3rry.components.componentsmanagement.IComponentsSetter;
import com.p3rry.components.componentsmanagement.IListAdder;
import com.p3rry.components.componentsmanagement.ISelfComponentSetter;
import lombok.Getter;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractJointPanel implements IListAdder, IComponentsSetter, ISelfComponentSetter, IComponentsAdder {
    public static final int PANEL_X_POSITION = 0;
    public static final int PANEL_Y_POSITION = 140;
    public static final int PANEL_WIDTH = 300;
    public static final int PANEL_HEIGHT = 500;

    @Getter
    protected JPanel panel;
    protected JointLabels jointLabels;
    protected JointTextComponents jointTextComponents;
    protected List<JLabel> labelsList;
    protected List<JTextComponent> textComponentsList;

    public AbstractJointPanel() {
        this.panel = new JPanel();
        this.jointLabels = new JointLabels();
        this.jointTextComponents = new JointTextComponents();
        this.labelsList = new ArrayList<>();
        this.textComponentsList = new ArrayList<>();

        addToList();
        setSelfComponent();
        addComponents();
        setComponents();
    }

    @Override
    public void addComponents() {
        labelsList.
                forEach(label -> panel.add(label));

        textComponentsList.
                forEach(textComponent -> panel.add(textComponent));
    }

    @Override
    public void setSelfComponent() {
        panel.setLayout(null);
        panel.setBounds(PANEL_X_POSITION, PANEL_Y_POSITION,
                PANEL_WIDTH, PANEL_HEIGHT);
    }

    @Override
    public void setComponents() {
        setListOfComponents(labelsList,70 , 0,
                JointLabels.LABEL_WIDTH, JointLabels.LABEL_HEIGHT);

        setListOfComponents(textComponentsList, 70, 30,
                JointTextComponents.TEXT_COMPONENT_WIDTH, JointTextComponents.TEXT_COMPONENT_HEIGHT);

        labelsList.
                forEach(label -> {
            label.setVerticalAlignment(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
        });
    }
}
