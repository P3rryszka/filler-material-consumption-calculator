package com.p3rry.ui.weldingmethod;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class WeldingMethodLabel {
    public static final int LABEL_WIDTH = 160;
    public static final int LABEL_HEIGHT = 20;

    private JLabel wireSpoolMassLabel;
    private JLabel electrodeLengthLabel,
            electrodeDiameterLabel, electrodeYieldLabel,
            rodLengthLabel, rodDiameterLabel,
            densityLabel;

    public WeldingMethodLabel() {
        this.wireSpoolMassLabel = new JLabel("Wire spool mass [kg]:");
        this.electrodeLengthLabel = new JLabel("Electrode length [mm]:");
        this.electrodeDiameterLabel = new JLabel("Electrode diameter [mm]:");
        this.electrodeYieldLabel = new JLabel("Electrode yield [%]");
        this.rodLengthLabel = new JLabel("Rod length [mm]: ");
        this.rodDiameterLabel = new JLabel("Rod diameter [mm]: ");
        this.densityLabel = new JLabel("Filler density [g/cm3]: ");
    }
}
