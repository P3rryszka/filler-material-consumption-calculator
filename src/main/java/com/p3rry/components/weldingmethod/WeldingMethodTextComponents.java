package com.p3rry.components.weldingmethod;

import com.p3rry.components.utlis.InputBlocker;
import lombok.Getter;

import javax.swing.*;

public class WeldingMethodTextComponents {
    public static final int TEXT_COMPONENT_WIDTH = 160;
    public static final int TEXT_COMPONENT_HEIGHT = 30;

    private InputBlocker inputBlocker;

    @Getter
    private JTextField wireSpoolMassTextField;

    @Getter
    private JTextField electrodeLengthTextField,
            electrodeDiameterTextField;

    public WeldingMethodTextComponents() {
        this.inputBlocker = new InputBlocker();
        this.wireSpoolMassTextField = this.inputBlocker.numberLimitInput();
        this.electrodeLengthTextField = this.inputBlocker.numberLimitInput();
        this.electrodeDiameterTextField = this.inputBlocker.numberLimitInput();
    }
}
