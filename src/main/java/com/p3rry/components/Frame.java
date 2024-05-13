package com.p3rry.components;

import com.p3rry.calculations.AdditionalMaterialCalculator;
import com.p3rry.calculations.MassCalculator;
import com.p3rry.components.componentsmanagement.IListAdder;
import com.p3rry.components.componentsmanagement.IListeningAdder;
import com.p3rry.components.componentsmanagement.IComponentsAdder;
import com.p3rry.components.componentsmanagement.ISelfComponentSetter;
import com.p3rry.components.joint.JointSelectionPanel;
import com.p3rry.components.joint.AbstractJointPanel;
import com.p3rry.components.joint.JointPanelFactory;
import com.p3rry.components.utlis.InputMessages;
import com.p3rry.components.weldingmethod.AbstractWeldingMethodPanel;
import com.p3rry.components.weldingmethod.WeldingMethodPanelFactory;
import com.p3rry.components.weldingmethod.WeldingMethodSelectionPanel;
import com.p3rry.consts.Description;
import com.p3rry.consts.Properties;
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
    public static final int FRAME_HEIGHT = 690;
    private double count;

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
    private AbstractJointPanel currentPanel;

    private AbstractWeldingMethodPanel smawPanel;
    private AbstractWeldingMethodPanel gmawPanel;
    private AbstractWeldingMethodPanel currentWeldingMethod;

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
        this.smawPanel = WeldingMethodPanelFactory.createWeldingMethod("S");
        this.gmawPanel = WeldingMethodPanelFactory.createWeldingMethod("G");

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
        controlPanel.getResetButton().addActionListener(this);
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
        double mass = 0;
        double additionalMaterial = 0;
        
        if(e.getSource() == jointSelectionPanel.getJointComboBox())
            selectJointPanel();
        else if(e.getSource() == weldingMethodSelectionPanel.getWeldingMethodComboBox())
            selectWeldingMethod();
        else if (e.getSource() == controlPanel.getResetButton())
            resetResult();
        else if (e.getSource() == controlPanel.getSecondCleanButton())
            handleSecondCleanButton();
        else if (e.getSource() == controlPanel.getCleanButton())
           handleCleanButton();
        else if(e.getSource() == controlPanel.getAddButton())
            handleAddButton(mass, additionalMaterial);
    }

    private void selectJointPanel() {
        String selectedIcon = ((ImageIcon) Objects.requireNonNull(jointSelectionPanel.getJointComboBox().getSelectedItem())).getDescription();

        switch (Description.valueOf(selectedIcon)) {
            case NO_BEVEL_JOINT-> {
                setCurrentJointPanel(noBevelJointPanel);
                currentPanel = noBevelJointPanel;
            }
            case V_BEVEL_JOINT -> {
                setCurrentJointPanel(vBevelJointPanel);
                currentPanel = vBevelJointPanel;
            }
            case Y_BEVEL_JOINT -> {
                setCurrentJointPanel(yBevelJointPanel);
                currentPanel = yBevelJointPanel;
            }
            case K_BEVEL_JOINT -> {
                setCurrentJointPanel(kBevelJointPanel);
                currentPanel = kBevelJointPanel;
            }
            case X_BEVEL_JOINT -> {
                setCurrentJointPanel(xBevelJointPanel);
                currentPanel = xBevelJointPanel;
            }
            case U_BEVEL_JOINT -> {
                setCurrentJointPanel(uBevelJointPanel);
                currentPanel = uBevelJointPanel;
            }
            case T_SINGLE_SIDED_JOINT -> {
                setCurrentJointPanel(tSingleSidedJointPanel);
                currentPanel = tSingleSidedJointPanel;
            }
        }
    }

    private void selectWeldingMethod() {
        WeldingMethodType selectedWeldingMethodType = (WeldingMethodType) weldingMethodSelectionPanel.getWeldingMethodComboBox().getSelectedItem();

        switch (selectedWeldingMethodType) {
            case SMAW -> setCurrentWeldingMethod(smawPanel);
            case GMAW -> setCurrentWeldingMethod(gmawPanel);
        }
    }

    private void handleCleanButton() {
        if(currentPanel == null) {
            InputMessages.displaySelectPanel("J");
            System.err.println("Select joint panel!");
        } else {
            currentPanel.cleanComponents();
        }
    }

    private void handleSecondCleanButton() {
        if(currentWeldingMethod == null) {
            InputMessages.displaySelectPanel("W");
            System.err.println("Select welding method!");
        } else if(currentWeldingMethod == smawPanel) {
            smawPanel.cleanComponents("L");
            smawPanel.cleanComponents("D");
        } else if (currentWeldingMethod == gmawPanel) {
            gmawPanel.cleanComponents("W");
        }
    }

    private void handleAddButton(double mass, double additionalMaterial) {
        if((currentPanel == null && currentWeldingMethod == null) ||
                (currentPanel == null || currentWeldingMethod == null)) {
            InputMessages.displaySelectPanel("P");
            System.err.println("Select panels!");
        } else {
            if(currentPanel.checkIfComponentsAreEmpty()) {
                InputMessages.displayEmptyParam();
                System.err.println("Make sure a parameter is provided within selected joint panel!");
            } else {
                if (currentPanel == noBevelJointPanel)
                    mass = MassCalculator.calculateNoBevelJointMass(noBevelJointPanel, Properties.STEEL_DENSITY_KG_MM3);
                else if (currentPanel == vBevelJointPanel)
                    mass = MassCalculator.calculateVBevelJointMass(vBevelJointPanel, Properties.STEEL_DENSITY_KG_MM3);
                else if (currentPanel == yBevelJointPanel)
                    mass = MassCalculator.calculateYBevelJoint(yBevelJointPanel, Properties.STEEL_DENSITY_KG_MM3);
                else if (currentPanel == xBevelJointPanel)
                    mass = MassCalculator.calculateXBevelJoint(xBevelJointPanel, Properties.STEEL_DENSITY_KG_MM3);
                else if (currentPanel == kBevelJointPanel)
                    mass = MassCalculator.calculateKBevelJoint(kBevelJointPanel, Properties.STEEL_DENSITY_KG_MM3);
                else if (currentPanel == uBevelJointPanel)
                    mass = MassCalculator.calculateUBevelJoint(uBevelJointPanel, Properties.STEEL_DENSITY_KG_MM3);
                else if (currentPanel == tSingleSidedJointPanel)
                    mass = MassCalculator.calculateTSingleSidedJoint(tSingleSidedJointPanel, Properties.STEEL_DENSITY_KG_MM3);

                if (currentWeldingMethod == gmawPanel) {
                    if (gmawPanel.checkIfComponentsAreEmpty("W")) {
                        InputMessages.displayEmptyParam();
                        System.err.println("Make sure a parameter is provided within selected welding method panel!");
                    } else {
                        additionalMaterial = AdditionalMaterialCalculator.calculateGmawAdditionalMaterial(currentWeldingMethod, mass);
                    }
                } else if (currentWeldingMethod == smawPanel) {
                    if (smawPanel.checkIfComponentsAreEmpty("L") || smawPanel.checkIfComponentsAreEmpty("D")) {
                        InputMessages.displayEmptyParam();
                        System.err.println("Make sure a parameter is provided within selected welding method panel!");
                    } else {
                        additionalMaterial = AdditionalMaterialCalculator.calculateSmawAdditionalMaterial(currentWeldingMethod, mass);
                    }
                }

                count += additionalMaterial;
                resultPanel.getResultTextField().setText(String.valueOf((int) count + 1));
            }
        }
    }
    
    private void setCurrentWeldingMethod(@NonNull AbstractWeldingMethodPanel weldingMethodPanel) {
        setCurrentWeldingMethodPanel(weldingMethodPanel);
        currentWeldingMethod = weldingMethodPanel;
        resetResult();
    }

    private void resetResult() {
        count = 0;
        resultPanel.getResultTextField().setText(String.valueOf((int) count));
    }

    private void setCurrentJointPanel(@NonNull AbstractJointPanel jointPanel) {
        for (var panel : this.jointPanelList) {
            this.remove(panel.getPanel());
        }

        this.add(jointPanel.getPanel());
        this.revalidate();
        this.repaint();
    }

    private void setCurrentWeldingMethodPanel(@NonNull AbstractWeldingMethodPanel weldingMethodPanel) {
        for (var panel : this.weldingMethodPanels) {
            this.remove(panel.getPanel());
        }

        this.add(weldingMethodPanel.getPanel());
        this.revalidate();
        this.repaint();
    }
}
