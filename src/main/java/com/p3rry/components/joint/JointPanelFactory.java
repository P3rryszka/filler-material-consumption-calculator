package com.p3rry.components.joint;

import com.p3rry.components.joint.jointbuttweldpanel.*;
import com.p3rry.components.joint.jointfilletweldpanel.TSingleSidedJointPanel;
import com.p3rry.consts.JointType;
import lombok.NonNull;

public class JointPanelFactory {
    public static AbstractJointPanel createJointPanel(@NonNull JointType jointType) {
        switch (jointType) {
            case NO_BEVEL_JOINT -> {
                return new NoBevelJointPanel();
            }
            case V_BEVEL_JOINT -> {
                return new VBevelJointPanel();
            }
            case Y_BEVEL_JOINT -> {
                return new YBevelJointPanel();
            }
            case K_BEVEL_JOINT -> {
                return new KBevelJointPanel();
            }
            case X_BEVEL_JOINT -> {
                return new XBevelJointPanel();
            }
            case U_BEVEL_JOINT -> {
                return new UBevelJointPanel();
            }
            case T_SINGLE_SIDED_JOINT -> {
                return new TSingleSidedJointPanel();
            }
            default ->
                    throw new IllegalArgumentException("Invalid input!");
        }
    }
}
