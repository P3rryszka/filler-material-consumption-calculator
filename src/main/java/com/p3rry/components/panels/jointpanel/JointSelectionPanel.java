package com.p3rry.components.panels.jointpanel;

import com.p3rry.components.Frame;
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

    private static final int PANEL_X_POSITION = 0;
    private static final int PANEL_Y_POSITION = 0;
    private static final int PANEL_WIDTH = Frame.FRAME_WIDTH;
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
        this.panel.setLayout(null);
        this.panel.setBounds(PANEL_X_POSITION, PANEL_Y_POSITION,
                PANEL_WIDTH, PANEL_HEIGHT);
        this.panel.setBackground(Color.RED);
    }

    @Override
    public void addComponents() {
        this.panel.add(this.jointComboBox);
    }

    @Override
    public void setComponents() {
        this.jointComboBox.setModel(loadIcons());
        this.jointComboBox.setMaximumRowCount(2);
        this.jointComboBox.setBounds(JOINT_COMBOBOX_X_POSITION, JOINT_COMBOBOX_Y_POSITION,
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
