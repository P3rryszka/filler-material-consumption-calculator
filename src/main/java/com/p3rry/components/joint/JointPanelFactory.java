package com.p3rry.components.joint;

import com.p3rry.components.joint.jointbuttweldpanel.*;
import com.p3rry.components.joint.jointfilletweldpanel.TSingleSidedJointPanel;
import lombok.NonNull;

public class JointPanelFactory {
    public static AbstractJointPanel createJointPanel(@NonNull String jointType) {
            if (jointType.equalsIgnoreCase("K"))
                return new KBevelJointPanel();
            else if (jointType.equalsIgnoreCase("N"))
                return new NoBevelJointPanel();
            else if (jointType.equalsIgnoreCase("U"))
                return new UBevelJointPanel();
            else if (jointType.equalsIgnoreCase("V"))
                return new VBevelJointPanel();
            else if (jointType.equalsIgnoreCase("X"))
                return new XBevelJointPanel();
            else if (jointType.equalsIgnoreCase("Y"))
                return new YBevelJointPanel();
            else if (jointType.equalsIgnoreCase("T"))
                return new TSingleSidedJointPanel();
            else
                throw new IllegalArgumentException("Invalid joint type!");
    }
}
