package com.p3rry.components;

import com.p3rry.calculations.DensityParser;
import com.p3rry.calculations.additionalmaterial.AdditionalMaterialCalculator;
import com.p3rry.calculations.MassCalculator;
import com.p3rry.components.componentsmanagers.IListAdder;
import com.p3rry.components.componentsmanagers.IComponentsAdder;
import com.p3rry.components.componentsmanagers.ISelfComponentSetter;
import com.p3rry.components.joint.JointSelectionPanel;
import com.p3rry.components.joint.AbstractJointPanel;
import com.p3rry.components.joint.JointPanelFactory;
import com.p3rry.utlis.InputMessages;
import com.p3rry.components.weldingmethod.AbstractWeldingMethodPanel;
import com.p3rry.components.weldingmethod.WeldingMethodPanelFactory;
import com.p3rry.components.weldingmethod.WeldingMethodSelectionPanel;
import com.p3rry.consts.JointType;
import com.p3rry.consts.Properties;
import com.p3rry.consts.WeldingMethodType;
import lombok.NonNull;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Frame extends JFrame implements IComponentsAdder, ISelfComponentSetter, IListAdder, ActionListener {
    private static final int FRAME_WIDTH = 495;
    private static final int FRAME_HEIGHT = 690;
    private static final String CLEAN_INFORMATION = "Every parameter is empty! Nothing to clear!";
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
    private BaseMaterialDensityPanel baseMaterialDensityPanel;

    public Frame() {
        this.jointPanelList = new ArrayList<>();
        this.kBevelJointPanel = JointPanelFactory.createJointPanel(JointType.K_BEVEL_JOINT);
        this.noBevelJointPanel = JointPanelFactory.createJointPanel(JointType.NO_BEVEL_JOINT);
        this.uBevelJointPanel = JointPanelFactory.createJointPanel(JointType.U_BEVEL_JOINT);
        this.vBevelJointPanel = JointPanelFactory.createJointPanel(JointType.V_BEVEL_JOINT);
        this.xBevelJointPanel = JointPanelFactory.createJointPanel(JointType.X_BEVEL_JOINT);
        this.yBevelJointPanel = JointPanelFactory.createJointPanel(JointType.Y_BEVEL_JOINT);
        this.tSingleSidedJointPanel = JointPanelFactory.createJointPanel(JointType.T_SINGLE_SIDED_JOINT);

        this.weldingMethodPanels = new ArrayList<>();
        this.smawPanel = WeldingMethodPanelFactory.createWeldingMethod(WeldingMethodType.SMAW);
        this.gmawPanel = WeldingMethodPanelFactory.createWeldingMethod(WeldingMethodType.GMAW);
        this.gtawPanel = WeldingMethodPanelFactory.createWeldingMethod(WeldingMethodType.GTAW);

        this.jointSelectionPanel = new JointSelectionPanel();
        this.weldingMethodSelectionPanel = new WeldingMethodSelectionPanel();
        this.controlPanel = new ControlPanel();
        this.resultPanel = new ResultPanel();
        this.baseMaterialDensityPanel = new BaseMaterialDensityPanel();

        addToList();
        setSelfComponent();
        addComponents();
        addListeners();
    }

    public void addListeners() {
        jointSelectionPanel.getJointComboBox().addActionListener(this);
        weldingMethodSelectionPanel.getWeldingMethodComboBox().addActionListener(this);

        controlPanel.getCleanJointPanelButton().addActionListener(this);
        controlPanel.getCleanWeldingMethodPanelButton().addActionListener(this);
        controlPanel.getAddButton().addActionListener(this);
        controlPanel.getResetButton().addActionListener(this);
        controlPanel.getCleanDensityPanelButton().addActionListener(this);
    }

    @Override
    public void addComponents() {
        this.add(jointSelectionPanel.getPanel());
        this.add(weldingMethodSelectionPanel.getPanel());
        this.add(controlPanel.getPanel());
        this.add(resultPanel.getPanel());
        this.add(baseMaterialDensityPanel.getPanel());
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
        if(e.getSource() == jointSelectionPanel.getJointComboBox())
            selectJointPanel();
        else if(e.getSource() == weldingMethodSelectionPanel.getWeldingMethodComboBox())
            selectWeldingMethod();
        else if (e.getSource() == controlPanel.getResetButton())
            resetResult();
        else if (e.getSource() == controlPanel.getCleanWeldingMethodPanelButton())
            handleCleanWeldingMethodPanelButton();
        else if (e.getSource() == controlPanel.getCleanJointPanelButton())
            handleCleanJointPanelButton();
        else if (e.getSource() == controlPanel.getCleanDensityPanelButton())
            handleCleanDensityPanelButton();
        else if(e.getSource() == controlPanel.getAddButton())
            handleAddButton();
    }

    private void selectJointPanel() {
        String selectedIcon = ((ImageIcon) Objects.requireNonNull(jointSelectionPanel.getJointComboBox().getSelectedItem())).getDescription();

        switch (JointType.valueOf(selectedIcon)) {
            case NO_BEVEL_JOINT-> setCurrentJoint(noBevelJointPanel);
            case V_BEVEL_JOINT -> setCurrentJoint(vBevelJointPanel);
            case Y_BEVEL_JOINT -> setCurrentJoint(yBevelJointPanel);
            case K_BEVEL_JOINT -> setCurrentJoint(kBevelJointPanel);
            case X_BEVEL_JOINT -> setCurrentJoint(xBevelJointPanel);
            case U_BEVEL_JOINT -> setCurrentJoint(uBevelJointPanel);
            case T_SINGLE_SIDED_JOINT -> setCurrentJoint(tSingleSidedJointPanel);
            default ->
                    throw new IllegalArgumentException("Issues bevel joint panels! Invalid value of enumeration type!");
        }
    }

    private void selectWeldingMethod() {
        WeldingMethodType selectedWeldingMethodType = (WeldingMethodType) Objects.requireNonNull(weldingMethodSelectionPanel.getWeldingMethodComboBox().getSelectedItem());

        switch (selectedWeldingMethodType) {
            case SMAW -> setCurrentWeldingMethod(smawPanel);
            case GMAW -> setCurrentWeldingMethod(gmawPanel);
            case GTAW -> setCurrentWeldingMethod(gtawPanel);
            default ->
                    throw new IllegalArgumentException();
        }
    }

    private void handleCleanWeldingMethodPanelButton() {
        if(currentWeldingMethod == null) {
            InputMessages.displaySelectPanel("WP");
            System.err.println("Select welding method panel!");
        } else {
            if(checkIfAllComponentsAreEmpty(currentWeldingMethod.getTextComponentsList())) {
                InputMessages.displayAllParamsAreEmpty();
                System.err.println(CLEAN_INFORMATION);
            } else
                cleanComponents(currentWeldingMethod.getTextComponentsList());
        }
    }

    private void handleCleanJointPanelButton() {
        if(currentPanel == null) {
            InputMessages.displaySelectPanel("JP");
            System.err.println("Select joint panel!");
        } else {
            if(checkIfAllComponentsAreEmpty(currentPanel.getTextComponentsList())) {
                InputMessages.displayAllParamsAreEmpty();
                System.err.println(CLEAN_INFORMATION);
            } else
                cleanComponents(currentPanel.getTextComponentsList());
        }
    }

    private void handleCleanDensityPanelButton() {
        if(baseMaterialDensityPanel.getBaseMaterialDensityTextField().getText().isEmpty()) {
            InputMessages.displayAllParamsAreEmpty();
            System.err.println(CLEAN_INFORMATION);
        } else
            baseMaterialDensityPanel.getBaseMaterialDensityTextField().setText("");
    }

    private void handleAddButton() {
        if(currentPanel == null ||
                currentWeldingMethod == null) {
            System.err.println("Select panels!");
            InputMessages.displaySelectPanel("BP");
        } else {
            if (checkIfAnyComponentIsEmpty(currentPanel.getTextComponentsList()) ||
                    checkIfAnyComponentIsEmpty(currentWeldingMethod.getTextComponentsList()) ||
                    baseMaterialDensityPanel.getBaseMaterialDensityTextField().getText().isEmpty()) {
                System.err.println("Make sure a parameter is provided within joint panel, welding method panel or density panel!");
                InputMessages.displayEmptyParam();
            } else {
                double baseMaterialDensity = (DensityParser.parseBaseMaterialDensity(baseMaterialDensityPanel)
                        * Properties.TO_KG_MM3_FACTOR);
                double mass;

                if (currentPanel == noBevelJointPanel)
                    mass = MassCalculator.calculateNoBevelJointMass(noBevelJointPanel, baseMaterialDensity);
                else if (currentPanel == vBevelJointPanel)
                    mass = MassCalculator.calculateVBevelJointMass(vBevelJointPanel, baseMaterialDensity);
                else if (currentPanel == yBevelJointPanel)
                    mass = MassCalculator.calculateYBevelJoint(yBevelJointPanel, baseMaterialDensity);
                else if (currentPanel == xBevelJointPanel)
                    mass = MassCalculator.calculateXBevelJoint(xBevelJointPanel, baseMaterialDensity);
                else if (currentPanel == kBevelJointPanel)
                    mass = MassCalculator.calculateKBevelJoint(kBevelJointPanel, baseMaterialDensity);
                else if (currentPanel == uBevelJointPanel)
                    mass = MassCalculator.calculateUBevelJoint(uBevelJointPanel, baseMaterialDensity);
                else if (currentPanel == tSingleSidedJointPanel)
                    mass = MassCalculator.calculateTSingleSidedJoint(tSingleSidedJointPanel, baseMaterialDensity);
                else
                    throw new IllegalArgumentException("Current joint panel is invalid!");

                double additionalMaterial;
                if (currentWeldingMethod == gmawPanel)
                    additionalMaterial = AdditionalMaterialCalculator.calculateGmawAdditionalMaterial(gmawPanel, mass);
                else if (currentWeldingMethod == smawPanel)
                    additionalMaterial = AdditionalMaterialCalculator.calculateSmawAdditionalMaterial(smawPanel, mass);
                else if (currentWeldingMethod == gtawPanel)
                    additionalMaterial = AdditionalMaterialCalculator.calculateGtawAdditionalMaterial(gtawPanel, mass);
                else
                    throw new IllegalArgumentException("Current welding method panel is invalid!");

                count += additionalMaterial;
                resultPanel.getResultTextField().setText(String.valueOf((int) count + 1));

                //Mass checkpoint
                System.out.println("Mass checkpoint: " + mass);

                //Additional material checkpoint
                System.out.println("Additional material checkpoint: " + additionalMaterial);

                //Sum of additional material -> count checkpoint
                System.out.println("Sum of additional material checkpoint: " + count);

                System.out.println("*************************************************");
            }
        }
    }
    
    private void setCurrentWeldingMethod(@NonNull AbstractWeldingMethodPanel weldingMethodPanel) {
        setCurrentWeldingMethodPanel(weldingMethodPanel);
        currentWeldingMethod = weldingMethodPanel;
        resetResult();
    }

    private void setCurrentJoint(@NonNull AbstractJointPanel jointPanel) {
        setCurrentJointPanel(jointPanel);
        currentPanel = jointPanel;
    }

    private void resetResult() {
        count = 0;
        resultPanel.getResultTextField().setText(String.valueOf(0));
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

    private void cleanComponents(List<JTextComponent> textComponentList) {
        textComponentList.forEach(textComponent ->
                textComponent.setText(""));
    }

    private boolean checkIfAnyComponentIsEmpty(List<JTextComponent> textComponentList) {
        return textComponentList.stream()
                .anyMatch(textComponent ->
                        textComponent.getText().isEmpty());
    }

    private boolean checkIfAllComponentsAreEmpty(List<JTextComponent> textComponentList) {
        return textComponentList.stream()
                .allMatch(textComponent ->
                        textComponent.getText().isEmpty());
    }
}
