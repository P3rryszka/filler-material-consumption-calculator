package com.p3rry.components.panels.jointpanel;

import lombok.Getter;

import javax.swing.*;

@Getter
public class JointLabels {

    public static final int LABEL_WIDTH = 160;
    public static final int LABEL_HEIGHT = 20;

    private JLabel thicknessLabel, gapLabel,
            beadLabel, bevelAngleLabel,
            roundingLabel, lengthLabel,
            qualityLevelLabel, legSizeLabel;

    public JointLabels() {
        this.thicknessLabel = new JLabel("Plate thickness (s) [mm]: ");
        this.gapLabel = new JLabel("Root gap (b) [mm]: ");
        this.beadLabel = new JLabel("Bead height (c) [mm]: ");
        this.bevelAngleLabel = new JLabel("Bevelling angle (α) [dgrs]: ");
        this.roundingLabel = new JLabel("Radius of rounding (r) [mm]: ");
        this.lengthLabel = new JLabel("Joint length (l) [mm]: ");
        this.qualityLevelLabel = new JLabel("Joint quality level - B, C, D: ");
        this.legSizeLabel = new JLabel("Leg size [mm]: ");
    }
}
