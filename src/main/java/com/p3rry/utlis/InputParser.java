package com.p3rry.utlis;

import com.p3rry.components.joint.AbstractJointPanel;
import com.p3rry.components.weldingmethod.AbstractWeldingMethodPanel;
import com.p3rry.consts.QualityLevel;
import lombok.NonNull;

public class InputParser {
    public static double parseDoubleJointPanelParams(@NonNull AbstractJointPanel jointPanelType, int index) {
            return Double.parseDouble(jointPanelType.getTextComponentsList().get(index).getText());
    }

    public static QualityLevel parseEnumJointPanelParams(@NonNull AbstractJointPanel jointPanelType, int index) {
        return QualityLevel.valueOf(jointPanelType.getTextComponentsList().get(index).getText());
    }

    public static double parseDoubleWeldingMethodPanel(@NonNull AbstractWeldingMethodPanel weldingMethodPanelType, int index) {
        return Double.parseDouble(weldingMethodPanelType.getTextComponentsList().get(index).getText());
    }
}
