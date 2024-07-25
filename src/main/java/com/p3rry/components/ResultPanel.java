package com.p3rry.components;

import com.p3rry.components.componentsmanagers.IComponentsAdder;
import com.p3rry.components.componentsmanagers.IComponentsSetter;
import com.p3rry.components.componentsmanagers.ISelfComponentSetter;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class ResultPanel implements ISelfComponentSetter, IComponentsAdder, IComponentsSetter {
    private static final int PANEL_X_POSITION = 300;
    private static final int PANEL_Y_POSITION = 530;
    private static final int PANEL_WIDTH = 180;
    private static final int PANEL_HEIGHT = 50;

    private static final String INITIAL_VALUE = "0";

    private JPanel panel;
    private JTextArea resultTextField;

    public ResultPanel() {
        this.panel = new JPanel();
        this.resultTextField = new JTextArea();

        setSelfComponent();
        addComponents();
        setComponents();
    }

    @Override
    public void setSelfComponent() {
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBounds(PANEL_X_POSITION, PANEL_Y_POSITION,
                PANEL_WIDTH, PANEL_HEIGHT);
    }

    @Override
    public void addComponents() {
        panel.add(resultTextField);
    }

    @Override
    public void setComponents() {
        resultTextField.setPreferredSize(new Dimension(160,30));
        resultTextField.setEditable(false);
        resultTextField.setText(INITIAL_VALUE);
        resultTextField.setFocusable(false);
    }
}
