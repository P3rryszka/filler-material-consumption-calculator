package com.p3rry.ui.weldingmethod;

import lombok.Getter;

import javax.swing.*;

@Getter
public class WeldingMethodLabels {
    public static final int LABEL_WIDTH = 160;
    public static final int LABEL_HEIGHT = 20;

    private JLabel wireSpoolMassLabel;
    private JLabel electrodeLengthLabel,
            electrodeDiameterLabel, electrodeYieldLabel,
            rodLengthLabel, rodDiameterLabel,
            densityLabel;

    public WeldingMethodLabels() {
        this.wireSpoolMassLabel = new JLabel("Wire spool mass [kg]:");
        this.electrodeLengthLabel = new JLabel("Electrode length [mm]:");
        this.electrodeDiameterLabel = new JLabel("Electrode diameter [mm]:");
        this.electrodeYieldLabel = new JLabel("Electrode yield [%]");
        this.rodLengthLabel = new JLabel("Rod length [mm]: ");
        this.rodDiameterLabel = new JLabel("Rod diameter [mm]: ");
        this.densityLabel = new JLabel("Filler material density [g/cm3]: ");
    }
}
