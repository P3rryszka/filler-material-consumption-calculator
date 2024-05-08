package com.p3rry.components;

import com.p3rry.components.componentsmanagement.IListAdder;
import com.p3rry.components.componentsmanagement.IListeningAdder;
import com.p3rry.components.componentsmanagement.IComponentsAdder;
import com.p3rry.components.componentsmanagement.ISelfComponentSetter;
import com.p3rry.components.joint.JointSelectionPanel;
import com.p3rry.components.joint.AbstractJointPanel;
import com.p3rry.components.joint.JointPanelFactory;
import com.p3rry.components.weldingmethod.AbstractWeldingMethodPanel;
import com.p3rry.components.weldingmethod.WeldingMethodFactory;
import com.p3rry.components.weldingmethod.WeldingMethodSelectionPanel;
import com.p3rry.consts.WeldingMethodType;
import lombok.NonNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Frame extends JFrame implements IComponentsAdder, ISelfComponentSetter, IListAdder, IListeningAdder, ActionListener {
    public static final int FRAME_WIDTH = 495;
    public static final int FRAME_HEIGHT = 660;

    private JointSelectionPanel jointSelectionPanel;
    private WeldingMethodSelectionPanel weldingMethodSelectionPanel;

    private List<AbstractJointPanel> jointPanelList;
    private List<AbstractWeldingMethodPanel> weldingMethodPanels;
    private AbstractJointPanel kBevelJointPanel;
    private AbstractJointPanel noBevelJointPanel;
    private AbstractJointPanel uBevelJointPanel;
    private AbstractJointPanel vBevelJointPanel;
    private AbstractJointPanel xBevelJointPanel;
    private AbstractJointPanel yBevelJointPanel;
    private AbstractJointPanel tSingleSidedJointPanel;

    private AbstractWeldingMethodPanel smawPanel;
    private AbstractWeldingMethodPanel gmawPanel;

    private ControlPanel controlPanel;
    private ResultPanel resultPanel;


    public Frame() {
        this.jointSelectionPanel = new JointSelectionPanel();
        this.weldingMethodSelectionPanel = new WeldingMethodSelectionPanel();

        this.jointPanelList = new ArrayList<>();
        this.kBevelJointPanel = JointPanelFactory.createJointPanel("K");
        this.noBevelJointPanel = JointPanelFactory.createJointPanel("N");
        this.uBevelJointPanel = JointPanelFactory.createJointPanel("U");
        this.vBevelJointPanel = JointPanelFactory.createJointPanel("V");
        this.xBevelJointPanel = JointPanelFactory.createJointPanel("X");
        this.yBevelJointPanel = JointPanelFactory.createJointPanel("Y");
        this.tSingleSidedJointPanel = JointPanelFactory.createJointPanel("T");

        this.weldingMethodPanels = new ArrayList<>();
        this.smawPanel = WeldingMethodFactory.createWeldingMethod("S");
        this.gmawPanel = WeldingMethodFactory.createWeldingMethod("G");

        this.controlPanel = new ControlPanel();
        this.resultPanel = new ResultPanel();

        addToList();
        setSelfComponent();
        addComponents();
        addListening();
    }

    @Override
    public void addComponents() {
        this.add(jointSelectionPanel.getPanel());
        this.add(weldingMethodSelectionPanel.getPanel());
        this.add(controlPanel.getPanel());
        this.add(resultPanel.getPanel());
    }

    @Override
    public void addListening() {
        jointSelectionPanel.getJointComboBox().addActionListener(this);
        weldingMethodSelectionPanel.getWeldingMethodComboBox().addActionListener(this);
        controlPanel.getCleanButton().addActionListener(this);
        controlPanel.getSecondCleanButton().addActionListener(this);
        controlPanel.getAddButton().addActionListener(this);
    }

    @Override
    public void addToList() {
        jointPanelList.add(noBevelJointPanel);
        jointPanelList.add(uBevelJointPanel);
        jointPanelList.add(kBevelJointPanel);
        jointPanelList.add(vBevelJointPanel);
        jointPanelList.add(xBevelJointPanel);
        jointPanelList.add(yBevelJointPanel);
        jointPanelList.add(tSingleSidedJointPanel);

        weldingMethodPanels.add(smawPanel);
        weldingMethodPanels.add(gmawPanel);
    }

    @Override
    public void setSelfComponent() {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle("Additional Material Consumption Calculator");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(Color.LIGHT_GRAY);
        this.setResizable(true);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double count;
        Object source = e.getSource();

        if(source == jointSelectionPanel.getJointComboBox())
            selectJointPanel();
        else if (source == weldingMethodSelectionPanel.getWeldingMethodComboBox())
            selectWeldingMethod();
    }

    private void selectJointPanel() {
        String selectedIcon = ((ImageIcon) Objects.requireNonNull(jointSelectionPanel.getJointComboBox().getSelectedItem())).getDescription();

        switch (selectedIcon) {
            case "noBevelJoint" -> {
                removeAddToFrame(noBevelJointPanel);
            }

            case "kBevelJoint" -> {
                removeAddToFrame(kBevelJointPanel);
            }

            case "uBevelJoint" -> {
                removeAddToFrame(uBevelJointPanel);
            }
        }
    }

    private void selectWeldingMethod() {
        WeldingMethodType selectedWeldingMethodType = (WeldingMethodType) weldingMethodSelectionPanel.getWeldingMethodComboBox().getSelectedItem();

        switch (selectedWeldingMethodType) {
            case SMAW -> {
                removeAddToFrame(smawPanel);
            }

            case GMAW -> {
                removeAddToFrame(gmawPanel);
            }
        }
    }

    private void removeAddToFrame(@NonNull AbstractJointPanel abstractJointPanel) {
        for (var jointPanel : this.jointPanelList) {
            this.remove(jointPanel.getPanel());
        }

        this.add(abstractJointPanel.getPanel());
        this.revalidate();
        this.repaint();
    }

    private void removeAddToFrame(@NonNull AbstractWeldingMethodPanel abstractWeldingMethodPanel) {
        for (var weldingMethodPanel : this.weldingMethodPanels) {
            this.remove(weldingMethodPanel.getPanel());
        }

        this.add(abstractWeldingMethodPanel.getPanel());
        this.revalidate();
        this.repaint();
    }
}
