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
import com.p3rry.utlis.InputMessages;
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
    private AbstractWeldingMethodPanel gtawPanel;
    private AbstractWeldingMethodPanel currentWeldingMethod;

    private ControlPanel controlPanel;
    private ResultPanel resultPanel;

    public Frame() {
        this.jointSelectionPanel = new JointSelectionPanel();
        this.weldingMethodSelectionPanel = new WeldingMethodSelectionPanel();

        this.jointPanelList = new ArrayList<>();
        this.kBevelJointPanel = JointPanelFactory.createJointPanel(Description.K_BEVEL_JOINT);
        this.noBevelJointPanel = JointPanelFactory.createJointPanel(Description.NO_BEVEL_JOINT);
        this.uBevelJointPanel = JointPanelFactory.createJointPanel(Description.U_BEVEL_JOINT);
        this.vBevelJointPanel = JointPanelFactory.createJointPanel(Description.V_BEVEL_JOINT);
        this.xBevelJointPanel = JointPanelFactory.createJointPanel(Description.X_BEVEL_JOINT);
        this.yBevelJointPanel = JointPanelFactory.createJointPanel(Description.Y_BEVEL_JOINT);
        this.tSingleSidedJointPanel = JointPanelFactory.createJointPanel(Description.T_SINGLE_SIDED_JOINT);

        this.weldingMethodPanels = new ArrayList<>();
        this.smawPanel = WeldingMethodPanelFactory.createWeldingMethod(WeldingMethodType.SMAW);
        this.gmawPanel = WeldingMethodPanelFactory.createWeldingMethod(WeldingMethodType.GMAW);
        this.gtawPanel = WeldingMethodPanelFactory.createWeldingMethod(WeldingMethodType.GTAW);

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
        weldingMethodPanels.add(gtawPanel);
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
            case GTAW -> setCurrentWeldingMethod(gtawPanel);
        }
    }

    private void handleCleanButton() {
        if(currentPanel == null) {
            InputMessages.displaySelectPanel("JP");
            System.err.println("Select joint panel!");
        } else {
            currentPanel.cleanComponents(currentPanel.getTextComponentsList());
        }
    }

    private void handleSecondCleanButton() {
        if(currentWeldingMethod == null) {
            InputMessages.displaySelectPanel("WP");
            System.err.println("Select welding method!");
        } else {
            currentWeldingMethod.cleanComponents(currentWeldingMethod.getTextComponentsList());
        }
    }

    private void handleAddButton(double mass, double additionalMaterial) {
        if((currentPanel == null && currentWeldingMethod == null) ||
                (currentPanel == null || currentWeldingMethod == null)) {
            System.err.println("Select panels!");
            InputMessages.displaySelectPanel("BP");
        } else {
            if (currentPanel.checkIfComponentsAreEmpty(currentPanel.getTextComponentsList()) ||
                    currentWeldingMethod.checkIfComponentsAreEmpty(currentWeldingMethod.getTextComponentsList())) {
                System.err.println("Make sure a parameter is provided within selected joint panel or welding method panel!");
                InputMessages.displayEmptyParam();
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
                else
                    throw new IllegalArgumentException("Current joint panel is invalid - null!");

                if (currentWeldingMethod == gmawPanel)
                    additionalMaterial = AdditionalMaterialCalculator.calculateGmawAdditionalMaterial(gmawPanel, mass);
                else if (currentWeldingMethod == smawPanel)
                    additionalMaterial = AdditionalMaterialCalculator.calculateSmawAdditionalMaterial(smawPanel, mass, Properties.STEEL_DENSITY_KG_MM3);
                else if (currentWeldingMethod == gtawPanel)
                    additionalMaterial = AdditionalMaterialCalculator.calculateGtawAdditionalMaterial(gtawPanel, mass, Properties.STEEL_DENSITY_KG_MM3);
                else
                    throw new IllegalArgumentException("Current welding method panel is invalid - null!");

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
