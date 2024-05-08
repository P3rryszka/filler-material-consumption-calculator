package com.p3rry.components.weldingmethod;

import lombok.Getter;

import javax.swing.*;

@Getter
public class WeldingMethodLabels {
    public static final int LABEL_WIDTH = 160;
    public static final int LABEL_HEIGHT = 20;

    private JLabel nameInfo;
    private JLabel wireSpoolMassLabel;
    private JLabel electrodeLengthLabel,
            electrodeDiameterLabel;

    public WeldingMethodLabels() {
        this.nameInfo = new JLabel();
        this.wireSpoolMassLabel = new JLabel("Wire spool mass [kg]:");
        this.electrodeLengthLabel = new JLabel("Electrode length [mm]:");
        this.electrodeDiameterLabel = new JLabel("Electrode diameter [mm]:");
    }
}
