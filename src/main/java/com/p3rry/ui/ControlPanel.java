package com.p3rry.ui;

import com.p3rry.ui.componentmanager.IComponentsAdder;
import com.p3rry.ui.componentmanager.IComponentsSetter;
import com.p3rry.ui.componentmanager.ISelfComponentSetter;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


@Getter
public class ControlPanel implements ISelfComponentSetter, IComponentsAdder, IComponentsSetter {
    private static final int PANEL_X_POSITION = 310;
    private static final int PANEL_Y_POSITION = 580;
    private static final int PANEL_WIDTH = 160;
    private static final int PANEL_HEIGHT = 70;

    private static final int ROWS = 2;
    private static final int COLS = 3;
    private static final int HGAP = 5;
    private static final int VGAP = 5;

    private static final int FONT_SIZE = 10;

    private JPanel panel;
    private JButton cleanJointPanelButton, cleanWeldingMethodPanelButton,
            cleanDensityPanelButton, addButton, resetButton;
    private List<JButton> buttons;

    public ControlPanel() {
        this.buttons = new ArrayList<>();
        this.panel = new JPanel();
        this.addButton = new JButton("+");
        this.cleanJointPanelButton = new JButton("JC");
        this.cleanWeldingMethodPanelButton = new JButton("MC");
        this.resetButton = new JButton("R");
        this.cleanDensityPanelButton = new JButton("D");

        setSelfComponent();
        setComponents();
        addComponents();
    }

    @Override
    public void setSelfComponent() {
        panel.setLayout(new GridLayout(ROWS, COLS, HGAP, VGAP));
        panel.setBounds(PANEL_X_POSITION, PANEL_Y_POSITION,
                PANEL_WIDTH, PANEL_HEIGHT);
    }

    @Override
    public void addComponents() {
        panel.add(cleanJointPanelButton);
        panel.add(cleanWeldingMethodPanelButton);
        panel.add(cleanDensityPanelButton);
        panel.add(addButton);
        panel.add(resetButton);
    }

    @Override
    public void setComponents() {
        buttons.add(cleanDensityPanelButton);
        buttons.add(cleanWeldingMethodPanelButton);
        buttons.add(cleanJointPanelButton);
        buttons.add(addButton);
        buttons.add(resetButton);

        buttons.forEach(button ->
                button.setFont(
                        new Font("Arial", Font.BOLD, FONT_SIZE)
                )
        );
    }
}
