package com.p3rry;

import com.p3rry.components.Frame;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Frame::new);
    }
}