package com.p3rry.components.panels.jointpanel;

import com.p3rry.components.Frame;
import com.p3rry.components.componentsmanagement.IComponentsAdder;
import com.p3rry.components.componentsmanagement.IComponentsSetter;
import com.p3rry.components.componentsmanagement.IListAdder;
import com.p3rry.components.componentsmanagement.ISelfComponentSetter;
import lombok.Getter;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractJointPanel implements IListAdder, IComponentsSetter, ISelfComponentSetter, IComponentsAdder {
    private static final int PANEL_X_POSITION = 0;
    private static final int PANEL_Y_POSITION = 140;
    private static final int PANEL_WIDTH = Frame.FRAME_WIDTH;
    private static final int PANEL_HEIGHT = 490;

    @Getter
    protected JPanel panel;
    protected JointLabels jointLabels;
    protected JointTextComponents jointTextComponents;
    protected List<JLabel> labelsList;
    protected List<JTextComponent> textComponentsList;

    protected AbstractJointPanel() {
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
        this.labelsList.
                forEach(label -> this.panel.add(label));

        this.textComponentsList.
                forEach(textComponent -> this.panel.add(textComponent));
    }

    @Override
    public void setSelfComponent() {
        this.panel.setLayout(null);
        this.panel.setBounds(PANEL_X_POSITION, PANEL_Y_POSITION,
                PANEL_WIDTH, PANEL_HEIGHT);
    }

    @Override
    public void setComponents() {
        setListOfComponents(this.labelsList,70 , 0,
                JointLabels.LABEL_WIDTH, JointLabels.LABEL_HEIGHT);

        setListOfComponents(this.textComponentsList, 70, 30,
                JointTextComponents.TEXT_COMPONENT_WIDTH, JointTextComponents.TEXT_COMPONENT_HEIGHT);

        this.labelsList.
                forEach(label -> {
            label.setVerticalAlignment(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
        });
    }
}
