package com.p3rry.utlis;

import com.p3rry.components.joint.AbstractJointPanel;
import com.p3rry.components.weldingmethod.AbstractWeldingMethodPanel;
import com.p3rry.consts.QualityLevel;
import lombok.NonNull;

public class InputParser {
    public static double parseDoubleBevelJointParams(@NonNull AbstractJointPanel jointPanelType, int index) {
            return Double.parseDouble(jointPanelType.getTextComponentsList().get(index).getText());
    }

    public static QualityLevel parseEnumBevelJointParams(@NonNull AbstractJointPanel jointPanelType, int index) {
        return QualityLevel.valueOf(jointPanelType.getTextComponentsList().get(index).getText());
    }

    public static double parseWireSpoolMassParam(@NonNull AbstractWeldingMethodPanel weldingMethodPanelType) {
        return Double.parseDouble(weldingMethodPanelType.getWeldingMethodTextComponents().getWireSpoolMassTextField().getText());
    }

    public static double parseElectrodeLengthParam(@NonNull AbstractWeldingMethodPanel weldingMethodPanelType) {
        return Double.parseDouble(weldingMethodPanelType.getWeldingMethodTextComponents().getElectrodeLengthTextField().getText());
    }

    public static double parseElectrodeDiameterParam(@NonNull AbstractWeldingMethodPanel weldingMethodPanelType) {
        return Double.parseDouble(weldingMethodPanelType.getWeldingMethodTextComponents().getElectrodeDiameterTextField().getText());
    }
}
