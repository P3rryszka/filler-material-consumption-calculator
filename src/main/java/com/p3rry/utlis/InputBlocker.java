package com.p3rry.utlis;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputBlocker {
    public static JTextField numberLimitInput() {
        JTextField limitTextField = new JTextField();
        limitTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e)
            {
                char input = e.getKeyChar();
                if(!(input >= '0' && input <= '9' ||
                        input == '.' && limitTextField.getText().indexOf('.') == -1 )) {
                    e.consume();
                }
            }
        });
        return limitTextField;
    }

    public static JTextField qualityLevelLimitInput() {
        JTextField limitTextField = new JTextField();
        limitTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char input = e.getKeyChar();

                if (!(input == 'B' || input == 'C' || input == 'D')) {
                    e.consume();
                } else {
                    limitTextField.setText(String.valueOf(input));
                    e.consume();
                }
            }
        });
        return limitTextField;
    }
}
