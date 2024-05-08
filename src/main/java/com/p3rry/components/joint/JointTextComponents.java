package com.p3rry.components.joint;

import com.p3rry.components.utlis.InputBlocker;
import lombok.Getter;

import javax.swing.*;

public class JointTextComponents {
    public static final int TEXT_COMPONENT_WIDTH = 160;
    public static final int TEXT_COMPONENT_HEIGHT = 30;

    private InputBlocker inputBlock;

    @Getter
    private JTextField thicknessTextField, gapTextField,
            beadTextField, bevelAngleTextField,
            roundingTextField, lengthTextField,
            qualityLevelTextField, legSizeTextField;

    public JointTextComponents() {
        this.inputBlock = new InputBlocker();

        this.thicknessTextField = inputBlock.numberLimitInput();
        this.gapTextField = inputBlock.numberLimitInput();
        this.beadTextField = inputBlock.numberLimitInput();
        this.bevelAngleTextField = inputBlock.numberLimitInput();
        this.roundingTextField = inputBlock.numberLimitInput();
        this.lengthTextField = inputBlock.numberLimitInput();
        this.qualityLevelTextField = inputBlock.qualityLevelLimitInput();
        this.legSizeTextField = inputBlock.numberLimitInput();
    }
}
