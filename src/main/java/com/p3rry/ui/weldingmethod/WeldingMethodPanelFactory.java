package com.p3rry.ui.weldingmethod;

import com.p3rry.consts.WeldingMethodType;
import lombok.NonNull;

import javax.swing.*;
import java.util.Arrays;

public class WeldingMethodPanelFactory {
    public static WeldingMethodPanel createWeldingMethod(@NonNull WeldingMethodType weldingMethodType) {
        WeldingMethodLabels weldingMethodLabels = new WeldingMethodLabels();
        WeldingMethodTextComponents weldingMethodTextComponents = new WeldingMethodTextComponents();

        switch (weldingMethodType) {
            case GMAW -> {
               return WeldingMethodPanel.builder()
                       .name(new JLabel("GMAW"))
                       .labelsList(
                               Arrays.asList(
                                       weldingMethodLabels.getWireSpoolMassLabel()
                               )
                       )
                       .textComponentList(
                               Arrays.asList(
                                       weldingMethodTextComponents.getWireSpoolMassTextField()
                               )
                       )
                       .build();
            }
            case SMAW -> {
                return WeldingMethodPanel.builder()
                        .name(new JLabel("SMAW"))
                        .labelsList(
                                Arrays.asList(
                                        weldingMethodLabels.getElectrodeLengthLabel(),
                                        weldingMethodLabels.getElectrodeDiameterLabel(),
                                        weldingMethodLabels.getElectrodeYieldLabel(),
                                        weldingMethodLabels.getDensityLabel()
                                )
                        )
                        .textComponentList(
                                Arrays.asList(
                                        weldingMethodTextComponents.getElectrodeLengthTextField(),
                                        weldingMethodTextComponents.getElectrodeDiameterTextField(),
                                        weldingMethodTextComponents.getElectordeYieldTextField(),
                                        weldingMethodTextComponents.getDensityTextField()
                                )
                        )
                        .build();
            }
            case GTAW -> {
                return WeldingMethodPanel.builder()
                        .name(new JLabel("GTAW"))
                        .labelsList(
                                Arrays.asList(
                                        weldingMethodLabels.getRodLengthLabel(),
                                        weldingMethodLabels.getRodDiameterLabel(),
                                        weldingMethodLabels.getDensityLabel()
                                )
                        )
                        .textComponentList(
                                Arrays.asList(
                                        weldingMethodTextComponents.getRodLengthTextField(),
                                        weldingMethodTextComponents.getRodDiameterTextField(),
                                        weldingMethodTextComponents.getDensityTextField()
                                )
                        )
                        .build();
            }
            default ->
                    throw new IllegalArgumentException("Invalid welding method type!");
        }
    }
}
