package com.p3rry.components;

import com.p3rry.components.componentsmanagement.IComponentsAdder;
import com.p3rry.components.componentsmanagement.ISelfComponentSetter;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class ControlPanel implements ISelfComponentSetter, IComponentsAdder {
    public static final int PANEL_X_POSITION = 300;
    public static final int PANEL_Y_POSITION = 580;
    public static final int PANEL_WIDTH = 180;
    public static final int PANEL_HEIGHT = 70;

    private JPanel panel;
    private JButton cleanButton, secondCleanButton,
            addButton, resetButton;

    public ControlPanel() {
        this.panel = new JPanel();
        this.addButton = new JButton("+");
        this.cleanButton = new JButton("JC");
        this.secondCleanButton = new JButton("WMC");
        this.resetButton = new JButton("R");

        setSelfComponent();
        addComponents();
    }

    @Override
    public void setSelfComponent() {
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBounds(PANEL_X_POSITION, PANEL_Y_POSITION,
                PANEL_WIDTH, PANEL_HEIGHT);
    }

    @Override
    public void addComponents() {
        panel.add(cleanButton);
        panel.add(secondCleanButton);
        panel.add(addButton);
        panel.add(resetButton);
    }
}
