package com.p3rry.components.joint;

import com.p3rry.utlis.InputBlocker;
import lombok.Getter;

import javax.swing.*;

@Getter
public class JointTextComponent {
    public static final int TEXT_COMPONENT_WIDTH = 160;
    public static final int TEXT_COMPONENT_HEIGHT = 30;

    private JTextField thicknessTextField, gapTextField,
            beadTextField, bevelAngleTextField,
            roundingTextField, lengthTextField,
            qualityLevelTextField, legSizeTextField;

    public JointTextComponent() {
        this.thicknessTextField = InputBlocker.numberLimitInput();
        this.gapTextField = InputBlocker.numberLimitInput();
        this.beadTextField = InputBlocker.numberLimitInput();
        this.bevelAngleTextField = InputBlocker.numberLimitInput();
        this.roundingTextField = InputBlocker.numberLimitInput();
        this.lengthTextField = InputBlocker.numberLimitInput();
        this.qualityLevelTextField = InputBlocker.qualityLevelLimitInput();
        this.legSizeTextField = InputBlocker.numberLimitInput();
    }
}
