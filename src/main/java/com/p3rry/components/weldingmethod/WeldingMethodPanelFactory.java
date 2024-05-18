package com.p3rry.components.weldingmethod;

import com.p3rry.consts.WeldingMethodType;
import lombok.NonNull;

public class WeldingMethodPanelFactory {
    public static AbstractWeldingMethodPanel createWeldingMethod(@NonNull WeldingMethodType weldingMethodType) {
        switch (weldingMethodType) {
            case GMAW ->  {
                return new GmawPanel();
            }
            case SMAW -> {
                return new SmawPanel();
            }
            case GTAW -> {
                return new GtawPanel();
            }
            default ->  throw new IllegalArgumentException("Invalid welding method type!");
        }
    }
}
