package com.p3rry.components.joint;

import com.p3rry.components.componentsmanagers.IComponentsAdder;
import com.p3rry.components.componentsmanagers.IComponentsSetter;
import com.p3rry.components.componentsmanagers.ISelfComponentSetter;
import com.p3rry.consts.JointType;
import lombok.Getter;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.IOException;

@Getter
public class JointSelectionPanel implements ISelfComponentSetter, IComponentsSetter, IComponentsAdder {
    private static final int PANEL_X_POSITION = 0;
    private static final int PANEL_Y_POSITION = 0;
    private static final int PANEL_WIDTH = 300;
    private static final int PANEL_HEIGHT = 140;

    private static final int JOINT_COMBOBOX_X_POSITION = 40;
    private static final int JOINT_COMBOBOX_Y_POSITION = 10;
    private static final int JOINT_COMBOBOX_WIDTH = 210;
    private static final int JOINT_COMBOBOX_HEIGHT = 120;

    private JPanel panel;
    private JComboBox<Icon> jointComboBox;

    public JointSelectionPanel() {
        this.panel = new JPanel();
        this.jointComboBox = new JComboBox<>();
        
        setSelfComponent();
        addComponents();
        setComponents();
    }

    @Override
    public void setSelfComponent() {
        panel.setLayout(null);
        panel.setBounds(PANEL_X_POSITION, PANEL_Y_POSITION,
                PANEL_WIDTH, PANEL_HEIGHT);
    }

    @Override
    public void addComponents() {
        panel.add(jointComboBox);
    }

    @Override
    public void setComponents() {
        jointComboBox.setModel(loadIcons());
        jointComboBox.setMaximumRowCount(2);
        jointComboBox.setFocusable(false);
        jointComboBox.setBounds(JOINT_COMBOBOX_X_POSITION, JOINT_COMBOBOX_Y_POSITION,
                JOINT_COMBOBOX_WIDTH, JOINT_COMBOBOX_HEIGHT);
    }

    private DefaultComboBoxModel<Icon> loadIcons() {
        DefaultComboBoxModel<Icon> dm = new DefaultComboBoxModel<>();

        try {
            ImageIcon noBevelJointIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/NoBevelJointIcon.png")));
            noBevelJointIcon.setDescription(JointType.NO_BEVEL_JOINT.toString());
            dm.addElement(noBevelJointIcon);

            ImageIcon vBevelJointIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/VBevelJointIcon.png")));
            vBevelJointIcon.setDescription(JointType.V_BEVEL_JOINT.toString());
            dm.addElement(vBevelJointIcon);

            ImageIcon yBevelJointIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/YBevelJointIcon.png")));
            yBevelJointIcon.setDescription(JointType.Y_BEVEL_JOINT.toString());
            dm.addElement(yBevelJointIcon);

            ImageIcon kBevelJointIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/KBevelJointIcon.png")));
            kBevelJointIcon.setDescription(JointType.K_BEVEL_JOINT.toString());
            dm.addElement(kBevelJointIcon);

            ImageIcon xBevelJointIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/XBevelJointIcon.png")));
            xBevelJointIcon.setDescription(JointType.X_BEVEL_JOINT.toString());
            dm.addElement(xBevelJointIcon);

            ImageIcon uBevelJointIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/uBevelJointIcon.png")));
            uBevelJointIcon.setDescription(JointType.U_BEVEL_JOINT.toString());
            dm.addElement(uBevelJointIcon);

            ImageIcon tSingleSidedJoint = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/TSingleSidedJointIcon.png")));
            tSingleSidedJoint.setDescription(JointType.T_SINGLE_SIDED_JOINT.toString());
            dm.addElement(tSingleSidedJoint);

            return dm;
        } catch (IOException e) {
            throw new RuntimeException("Issues with streaming image! " + e.getMessage());
        }
    }
}
