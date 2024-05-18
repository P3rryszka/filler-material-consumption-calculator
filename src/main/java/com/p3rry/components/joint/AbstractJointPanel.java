package com.p3rry.components.joint;

import com.p3rry.components.componentsmanagement.*;
import lombok.Getter;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractJointPanel implements IListAdder, IComponentsSetter, ISelfComponentSetter, IComponentsAdder {
    public static final int PANEL_X_POSITION = 0;
    public static final int PANEL_Y_POSITION = 140;
    public static final int PANEL_WIDTH = 300;
    public static final int PANEL_HEIGHT = 500;

    protected JointLabel jointLabel;
    protected JointTextComponent jointTextComponent;
    protected List<JLabel> labelsList;

    @Getter
    protected JPanel panel;
    @Getter
    protected List<JTextComponent> textComponentsList;

    public AbstractJointPanel() {
        this.panel = new JPanel();
        this.jointLabel = new JointLabel();
        this.jointTextComponent = new JointTextComponent();
        this.labelsList = new ArrayList<>();
        this.textComponentsList = new ArrayList<>();

        addToList();
        setSelfComponent();
        addComponents();
        setComponents();
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

    @Override
    public void setComponents() {
        setListOfComponents(labelsList,70 , 0,
                JointLabel.LABEL_WIDTH, JointLabel.LABEL_HEIGHT);

        setListOfComponents(textComponentsList, 70, 30,
                JointTextComponent.TEXT_COMPONENT_WIDTH, JointTextComponent.TEXT_COMPONENT_HEIGHT);

        labelsList.
                forEach(label -> {
            label.setVerticalAlignment(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
        });
    }
}
