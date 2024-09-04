package com.p3rry.ui.joint;

import com.p3rry.ui.componentmanager.IComponentsAdder;
import com.p3rry.ui.componentmanager.IComponentsSetter;
import com.p3rry.ui.componentmanager.ISelfComponentSetter;
import lombok.Builder;
import lombok.Getter;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.util.List;

public class JointPanel implements IComponentsSetter, ISelfComponentSetter, IComponentsAdder {
    protected static final int PANEL_X_POSITION = 0;
    protected static final int PANEL_Y_POSITION = 140;
    protected static final int PANEL_WIDTH = 300;
    protected static final int PANEL_HEIGHT = 500;

    protected List<JLabel> labelsList;

    @Getter
    protected List<JTextComponent> textComponentsList;

    @Getter
    protected JPanel panel;

    @Builder
    public JointPanel(List<JLabel> labelsList, List<JTextComponent> textComponentsList) {
        this.labelsList = labelsList;
        this.textComponentsList = textComponentsList;
        this.panel = new JPanel();

        addComponents();
        setSelfComponent();
        setComponents();
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

    @Override
    public void setComponents() {

        positionComponentsWithOffset(labelsList, 70, 0,
                JointLabel.LABEL_WIDTH, JointLabel.LABEL_HEIGHT, 70);

        positionComponentsWithOffset(textComponentsList, 70, 30,
                JointTextComponent.TEXT_COMPONENT_WIDTH, JointTextComponent.TEXT_COMPONENT_HEIGHT, 70);

        labelsList.forEach(label -> {
            label.setVerticalAlignment(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
        });
    }
}
