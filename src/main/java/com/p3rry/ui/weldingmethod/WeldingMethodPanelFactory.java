package com.p3rry.ui.weldingmethod;

import com.p3rry.consts.WeldingMethodType;
import lombok.NonNull;

import javax.swing.*;
import java.util.Arrays;

public class WeldingMethodPanelFactory {
    public static WeldingMethodPanel createWeldingMethod(@NonNull WeldingMethodType weldingMethodType) {
        WeldingMethodLabel weldingMethodLabel = new WeldingMethodLabel();
        WeldingMethodTextComponents weldingMethodTextComponents = new WeldingMethodTextComponents();

        switch (weldingMethodType) {
            case GMAW -> {
               return WeldingMethodPanel.builder()
                       .name(new JLabel("GMAW"))
                       .labelsList(
                               Arrays.asList(
                                       weldingMethodLabel.getWireSpoolMassLabel()
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
                                        weldingMethodLabel.getElectrodeLengthLabel(),
                                        weldingMethodLabel.getElectrodeDiameterLabel(),
                                        weldingMethodLabel.getElectrodeYieldLabel(),
                                        weldingMethodLabel.getDensityLabel()
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
                                        weldingMethodLabel.getRodLengthLabel(),
                                        weldingMethodLabel.getRodDiameterLabel(),
                                        weldingMethodLabel.getDensityLabel()
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
