package com.p3rry.components.weldingmethod;

import lombok.NonNull;

public class WeldingMethodPanelFactory {
    public static AbstractWeldingMethodPanel createWeldingMethod(@NonNull String weldingMethodType) {
        if(weldingMethodType.equalsIgnoreCase("S"))
            return new SmawPanel();
        else if (weldingMethodType.equalsIgnoreCase("G"))
            return new GmawPanel();
        else
            throw new IllegalArgumentException("Invalid welding method type!");
    }
}
