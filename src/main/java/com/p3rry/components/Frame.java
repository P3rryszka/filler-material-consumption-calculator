package com.p3rry.components;

import com.p3rry.components.componentsmanagement.IListeningAdder;
import com.p3rry.components.componentsmanagement.IComponentsAdder;
import com.p3rry.components.componentsmanagement.ISelfComponentSetter;
import com.p3rry.components.panels.jointpanel.JointSelectionPanel;
import com.p3rry.components.panels.jointpanel.AbstractJointPanel;
import com.p3rry.components.panels.jointpanel.JointPanelFactory;
import com.p3rry.components.panels.weldingmethodpanel.WeldingMethodSelectionPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Frame extends JFrame implements IComponentsAdder, ISelfComponentSetter, IListeningAdder, ActionListener {

    public static final int FRAME_WIDTH = 300;
    public static final int FRAME_HEIGHT = 1000;

    private JointSelectionPanel jointSelectionPanel;
    private WeldingMethodSelectionPanel weldingMethodSelectionPanel;
    private AbstractJointPanel kBevelJointPanel;
    private AbstractJointPanel noBevelJointPanel;

    public Frame() {
        this.jointSelectionPanel = new JointSelectionPanel();
        this.weldingMethodSelectionPanel = new WeldingMethodSelectionPanel();

        this.kBevelJointPanel = JointPanelFactory.createJointPanel("K");
        this.noBevelJointPanel = JointPanelFactory.createJointPanel("N");

        setSelfComponent();
        addComponents();
        addListening();
    }

    @Override
    public void addComponents() {
        this.add(this.jointSelectionPanel.getPanel());
        this.add(this.weldingMethodSelectionPanel.getPanel());
    }

    @Override
    public void addListening() {
        this.jointSelectionPanel.getJointComboBox().addActionListener(this);
        this.weldingMethodSelectionPanel.getWeldingMethodComboBox().addActionListener(this);
    }

    @Override
    public void setSelfComponent() {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle("Additional Material Consumption Calculator");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(Color.LIGHT_GRAY);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double count;
        String selectedIcon = ((ImageIcon) Objects.requireNonNull(jointSelectionPanel.getJointComboBox().getSelectedItem())).getDescription();

        switch (selectedIcon) {
            case "noBevelJoint" -> {
                this.remove(kBevelJointPanel.getPanel());
                this.add(noBevelJointPanel.getPanel());
                this.revalidate();
                this.repaint();
            }

            case "vBevelJoint" -> {
                this.remove(noBevelJointPanel.getPanel());
                this.add(kBevelJointPanel.getPanel());
                this.revalidate();
                this.repaint();
            }

            case "xBevelJoint" -> {

            }
        }
    }
}
