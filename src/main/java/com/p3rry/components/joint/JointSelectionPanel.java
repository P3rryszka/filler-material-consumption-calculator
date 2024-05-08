package com.p3rry.components.joint;

import com.p3rry.components.componentsmanagement.IComponentsAdder;
import com.p3rry.components.componentsmanagement.IComponentsSetter;
import com.p3rry.components.componentsmanagement.ISelfComponentSetter;
import lombok.Getter;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

@Getter
public class JointSelectionPanel implements ISelfComponentSetter, IComponentsSetter, IComponentsAdder {
    public static final int PANEL_X_POSITION = 0;
    public static final int PANEL_Y_POSITION = 0;
    public static final int PANEL_WIDTH = 300;
    public static final int PANEL_HEIGHT = 140;

    public static final int JOINT_COMBOBOX_X_POSITION = 40;
    public static final int JOINT_COMBOBOX_Y_POSITION = 10;
    public static final int JOINT_COMBOBOX_WIDTH = 210;
    public static final int JOINT_COMBOBOX_HEIGHT = 120;

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
            noBevelJointIcon.setDescription("noBevelJoint");
            dm.addElement(noBevelJointIcon);

            ImageIcon vBevelJointIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/VBevelJointIcon.png")));
            vBevelJointIcon.setDescription("vBevelJoint");
            dm.addElement(vBevelJointIcon);

            ImageIcon yBevelJointIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/YBevelJointIcon.png")));
            yBevelJointIcon.setDescription("yBevelJoint");
            dm.addElement(yBevelJointIcon);

            ImageIcon kBevelJointIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/KBevelJointIcon.png")));
            kBevelJointIcon.setDescription("kBevelJoint");
            dm.addElement(kBevelJointIcon);

            ImageIcon xBevelJointIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/XBevelJointIcon.png")));
            xBevelJointIcon.setDescription("xBevelJoint");
            dm.addElement(xBevelJointIcon);

            ImageIcon uBevelJointIcon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/uBevelJointIcon.png")));
            uBevelJointIcon.setDescription("uBevelJoint");
            dm.addElement(uBevelJointIcon);

            ImageIcon tSingleSidedJoint = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/images/TSingleSidedJointIcon.png")));
            tSingleSidedJoint.setDescription("tSingleSidedJoint");
            dm.addElement(tSingleSidedJoint);

            return dm;
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            return dm;
        }
    }
}
