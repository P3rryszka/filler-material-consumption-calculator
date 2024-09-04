package com.p3rry.ui.weldingmethod;

import com.p3rry.utlis.InputBlocker;
import lombok.Getter;

import javax.swing.*;

@Getter
public class WeldingMethodTextComponents {
    public static final int TEXT_COMPONENT_WIDTH = 160;
    public static final int TEXT_COMPONENT_HEIGHT = 30;

    private JTextField wireSpoolMassTextField;
    
    private JTextField electrodeLengthTextField, electrodeDiameterTextField,
            electordeYieldTextField, rodLengthTextField,
            rodDiameterTextField, densityTextField;

    public WeldingMethodTextComponents() {
        this.wireSpoolMassTextField = InputBlocker.numberLimitInput();
        this.electrodeLengthTextField = InputBlocker.numberLimitInput();
        this.electrodeDiameterTextField = InputBlocker.numberLimitInput();
        this.electordeYieldTextField = InputBlocker.numberLimitInput();
        this.rodLengthTextField = InputBlocker.numberLimitInput();
        this.rodDiameterTextField = InputBlocker.numberLimitInput();
        this.densityTextField = InputBlocker.numberLimitInput();
    }
}
