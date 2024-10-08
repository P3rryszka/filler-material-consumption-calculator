package com.p3rry.ui;

import com.p3rry.calculation.additionalmaterial.FillerMaterialCalculator;
import com.p3rry.calculation.MassCalculator;
import com.p3rry.ui.componentmanager.IComponentsAdder;
import com.p3rry.ui.componentmanager.ISelfComponentSetter;
import com.p3rry.ui.joint.*;
import com.p3rry.utlis.InputMessages;
import com.p3rry.ui.weldingmethod.WeldingMethodPanel;
import com.p3rry.ui.weldingmethod.WeldingMethodPanelFactory;
import com.p3rry.ui.weldingmethod.WeldingMethodSelectionPanel;
import com.p3rry.consts.JointType;
import com.p3rry.consts.CommonProperties;
import com.p3rry.consts.WeldingMethodType;
import lombok.NonNull;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Frame extends JFrame implements IComponentsAdder, ISelfComponentSetter, ActionListener {
    private static final int FRAME_WIDTH = 495;
    private static final int FRAME_HEIGHT = 690;
    private static final String INFORMATION_BOUT_CLEANING = "Every parameter is empty! Nothing to clear!";

    private JointSelectionPanel jointSelectionPanel;
    private WeldingMethodSelectionPanel weldingMethodSelectionPanel;

    private JointPanel kBevelJointPanel, noBevelJointPanel,
            uBevelJointPanel, vBevelJointPanel,
            xBevelJointPanel, yBevelJointPanel,
            tSingleSidedJointPanel;

    private WeldingMethodPanel smawPanel, gmawPanel,
            gtawPanel;

    private ControlPanel controlPanel;
    private ResultPanel resultPanel;
    private BaseMaterialDensityPanel baseMaterialDensityPanel;

    private double count;
    private JointPanel currentPanel;
    private WeldingMethodPanel currentWeldingMethod;

    public Frame() {
        this.noBevelJointPanel = JointPanelFactory.createJointPanel(JointType.NO_BEVEL_JOINT);
        this.vBevelJointPanel = JointPanelFactory.createJointPanel(JointType.V_BEVEL_JOINT);
        this.uBevelJointPanel = JointPanelFactory.createJointPanel(JointType.U_BEVEL_JOINT);
        this.tSingleSidedJointPanel = JointPanelFactory.createJointPanel(JointType.T_SINGLE_SIDED_JOINT);
        this.yBevelJointPanel = JointPanelFactory.createJointPanel(JointType.Y_BEVEL_JOINT);
        this.kBevelJointPanel = JointPanelFactory.createJointPanel(JointType.K_BEVEL_JOINT);
        this.xBevelJointPanel = JointPanelFactory.createJointPanel(JointType.X_BEVEL_JOINT);

        this.smawPanel = WeldingMethodPanelFactory.createWeldingMethod(WeldingMethodType.SMAW);
        this.gmawPanel = WeldingMethodPanelFactory.createWeldingMethod(WeldingMethodType.GMAW);
        this.gtawPanel = WeldingMethodPanelFactory.createWeldingMethod(WeldingMethodType.GTAW);

        this.jointSelectionPanel = new JointSelectionPanel();
        this.weldingMethodSelectionPanel = new WeldingMethodSelectionPanel();
        this.controlPanel = new ControlPanel();
        this.resultPanel = new ResultPanel();
        this.baseMaterialDensityPanel = new BaseMaterialDensityPanel();

        setSelfComponent();
        addComponents();
        addListeners();
    }

    private void addListeners() {
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
    public void setSelfComponent() {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle("Filler Material Consumption Calculator");
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
            handleCleanWeldingButton();
        else if (e.getSource() == controlPanel.getCleanJointPanelButton())
            handleCleanJointButton();
        else if (e.getSource() == controlPanel.getCleanDensityPanelButton())
            handleCleanDensityButton();
        else if(e.getSource() == controlPanel.getAddButton())
            handleAddButton();
    }

    private void selectJointPanel() {
        String selectedIcon = ((ImageIcon) Objects.requireNonNull(jointSelectionPanel.getJointComboBox().getSelectedItem())).getDescription();

        switch (JointType.valueOf(selectedIcon)) {
            case NO_BEVEL_JOINT -> setCurrentJoint(noBevelJointPanel);
            case V_BEVEL_JOINT -> setCurrentJoint(vBevelJointPanel);
            case Y_BEVEL_JOINT -> setCurrentJoint(yBevelJointPanel);
            case K_BEVEL_JOINT -> setCurrentJoint(kBevelJointPanel);
            case X_BEVEL_JOINT -> setCurrentJoint(xBevelJointPanel);
            case U_BEVEL_JOINT -> setCurrentJoint(uBevelJointPanel);
            case T_SINGLE_SIDED_JOINT -> setCurrentJoint(tSingleSidedJointPanel);
            default ->
                    throw new IllegalArgumentException("Issues with joint panels! Invalid value of enumeration type!");
        }
    }

    private void selectWeldingMethod() {
        WeldingMethodType selectedWeldingMethodType = (WeldingMethodType) Objects.requireNonNull(weldingMethodSelectionPanel.getWeldingMethodComboBox().getSelectedItem());

        switch (selectedWeldingMethodType) {
            case SMAW -> setCurrentWeldingMethod(smawPanel);
            case GMAW -> setCurrentWeldingMethod(gmawPanel);
            case GTAW -> setCurrentWeldingMethod(gtawPanel);
            default ->
                    throw new IllegalArgumentException("Issues with welding method panels! Invalid value of enumeration type!");
        }
    }

    private void handleCleanWeldingButton() {
        if(currentWeldingMethod == null) {
            InputMessages.displayNoSelectedPanel("WeldingMethod");
            System.err.println("Select welding method panel!");
        } else {
            if(checkIfAllComponentsAreEmpty(currentWeldingMethod.getTextComponentsList())) {
                InputMessages.displayAllParamsAreEmpty();
                System.err.println(INFORMATION_BOUT_CLEANING);
            } else
                cleanComponents(currentWeldingMethod.getTextComponentsList());
        }
    }

    private void handleCleanJointButton() {
        if(currentPanel == null) {
            InputMessages.displayNoSelectedPanel("JointPanel");
            System.err.println("Select joint panel!");
        } else {
            if(checkIfAllComponentsAreEmpty(currentPanel.getTextComponentsList())) {
                InputMessages.displayAllParamsAreEmpty();
                System.err.println(INFORMATION_BOUT_CLEANING);
            } else
                cleanComponents(currentPanel.getTextComponentsList());
        }
    }

    private void handleCleanDensityButton() {
        if(baseMaterialDensityPanel.getBaseMaterialDensityTextField().getText().isEmpty()) {
            InputMessages.displayAllParamsAreEmpty();
            System.err.println(INFORMATION_BOUT_CLEANING);
        } else
            baseMaterialDensityPanel.getBaseMaterialDensityTextField().setText("");
    }

    private void handleAddButton() {
        if(currentPanel == null ||
                currentWeldingMethod == null) {
            System.err.println("Select panels!");
            InputMessages.displayNoSelectedPanel("BothPanels");
        } else {
            if (checkIfAnyComponentIsEmpty(currentPanel.getTextComponentsList()) ||
                    checkIfAnyComponentIsEmpty(currentWeldingMethod.getTextComponentsList()) ||
                    baseMaterialDensityPanel.getBaseMaterialDensityTextField().getText().isEmpty()) {
                System.err.println("Make sure a parameter is provided within joint panel, welding method panel or density panel!");
                InputMessages.displayEmptyParam();
            } else {
                double baseMaterialDensity = parseBaseMaterialDensityPanelText();
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

                double fillerMaterial;
                if (currentWeldingMethod == gmawPanel)
                    fillerMaterial = FillerMaterialCalculator.calculateGmawAdditionalMaterial(gmawPanel, mass);
                else if (currentWeldingMethod == smawPanel)
                    fillerMaterial = FillerMaterialCalculator.calculateSmawAdditionalMaterial(smawPanel, mass);
                else if (currentWeldingMethod == gtawPanel)
                    fillerMaterial = FillerMaterialCalculator.calculateGtawAdditionalMaterial(gtawPanel, mass);
                else
                    throw new IllegalArgumentException("Current welding method panel is invalid!");

                count += fillerMaterial;
                resultPanel.getResultTextField().setText(String.valueOf((int) count + 1));

                //Mass checkpoint
                System.out.println("Mass checkpoint: " + mass);

                //Additional material checkpoint
                System.out.println("Additional material checkpoint: " + fillerMaterial);

                //Sum of additional material -> count checkpoint
                System.out.println("Sum of additional material checkpoint: " + count);

                System.out.println("*************************************************");
            }
        }
    }

    private void resetResult() {
        count = 0;
        resultPanel.getResultTextField().setText(String.valueOf(0));
    }

    private void setCurrentWeldingMethod(@NonNull WeldingMethodPanel weldingMethodPanel) {
        if(currentWeldingMethod == null)
            this.add(weldingMethodPanel.getPanel());
        else {
            this.remove(currentWeldingMethod.getPanel());
            this.add(weldingMethodPanel.getPanel());
        }
        this.revalidate();
        this.repaint();
        currentWeldingMethod = weldingMethodPanel;
        resetResult();
    }

    private void setCurrentJoint(@NonNull JointPanel jointPanel) {
        if(currentPanel == null)
            this.add(jointPanel.getPanel());
        else {
            this.remove(currentPanel.getPanel());
            this.add(jointPanel.getPanel());
        }
        this.revalidate();
        this.repaint();
        currentPanel = jointPanel;
    }

    private void cleanComponents(@NonNull List<JTextComponent> list) {
        list.forEach(textComponent ->
                textComponent.setText(""));
    }

    private boolean checkIfAnyComponentIsEmpty(@NonNull List<JTextComponent> list) {
        return list.stream()
                .anyMatch(textComponent ->
                        textComponent.getText().isEmpty());
    }

    private boolean checkIfAllComponentsAreEmpty(@NonNull List<JTextComponent> list) {
        return list.stream()
                .allMatch(textComponent ->
                        textComponent.getText().isEmpty());
    }

    private double parseBaseMaterialDensityPanelText() {
            return Optional.ofNullable(baseMaterialDensityPanel.getBaseMaterialDensityTextField().getText())
                    .map(Double::parseDouble)
                    .orElseThrow(() ->
                            new IllegalArgumentException("Parsing value within base material density panel is null!")
                    );
    }
}
