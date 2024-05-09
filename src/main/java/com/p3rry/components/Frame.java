package com.p3rry.components;

import com.p3rry.calculations.calculationsmanagement.IWeldOperations;
import com.p3rry.calculations.weld.Mass;
import com.p3rry.calculations.weld.buttweld.UBevelJoint;
import com.p3rry.calculations.weld.buttweld.XBevelJoint;
import com.p3rry.calculations.weld.filletweld.TSingleSidedJoint;
import com.p3rry.components.ControlPanel;
import com.p3rry.components.ResultPanel;
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
import com.p3rry.consts.Description;
import com.p3rry.consts.Properties;
import com.p3rry.consts.QualityLevel;
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
    public static final int FRAME_HEIGHT = 670;

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
    ////
    private IWeldOperations abstractWeld;
    private Mass mass;

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

        //TESTING THINGS
        this.abstractWeld = new XBevelJoint(10, QualityLevel.C, 2, 60, 2);
        this.mass = new Mass(200, abstractWeld);
        System.out.println(mass.calculateMass(Properties.STEEL_DENSITY_KG_MM3));
        //**********************

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
        this.setResizable(false);
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

        switch (Description.valueOf(selectedIcon)) {
            case NO_BEVEL_JOINT-> {
                removeAddToFrame(noBevelJointPanel);
            }

            case V_BEVEL_JOINT -> {
                removeAddToFrame(vBevelJointPanel);
            }

            case Y_BEVEL_JOINT -> {
                removeAddToFrame(yBevelJointPanel);
            }

            case K_BEVEL_JOINT -> {
                removeAddToFrame(kBevelJointPanel);
            }

            case X_BEVEL_JOINT -> {
                removeAddToFrame(xBevelJointPanel);
            }

            case U_BEVEL_JOINT -> {
                removeAddToFrame(uBevelJointPanel);
            }

            case T_SINGLE_SIDED_JOINT -> {
                removeAddToFrame(tSingleSidedJointPanel);
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
