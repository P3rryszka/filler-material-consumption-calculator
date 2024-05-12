package com.p3rry.components.utlis;

import com.p3rry.consts.QualityLevel;

import javax.swing.*;

public class InputMessages {
    public static void displaySelectJointPanel(String selectionType) {
        if (selectionType.equalsIgnoreCase("J"))
            JOptionPane.showMessageDialog(null, "Select joint panel!", "", JOptionPane.INFORMATION_MESSAGE);
        else if (selectionType.equalsIgnoreCase("W"))
            JOptionPane.showMessageDialog(null, "Select welding method!", "", JOptionPane.INFORMATION_MESSAGE);
        else if (selectionType.equalsIgnoreCase("P"))
            JOptionPane.showMessageDialog(null, "Select panels!", "", JOptionPane.INFORMATION_MESSAGE);
        else
            throw new IllegalArgumentException("Invalid input!");
    }

    public static void displayThisParameterCannotBe(double value, String  inequalityType) {
        if(inequalityType.equalsIgnoreCase("L"))
            JOptionPane.showMessageDialog(null, "This parameter cannot be < " + value, "", JOptionPane.WARNING_MESSAGE);
        else if (inequalityType.equalsIgnoreCase("LE"))
            JOptionPane.showMessageDialog(null, "This parameter cannot be <= " + value, "", JOptionPane.WARNING_MESSAGE);
        else if (inequalityType.equalsIgnoreCase("E"))
            JOptionPane.showMessageDialog(null, "This parameter cannot be = " + value, "", JOptionPane.WARNING_MESSAGE);
        else if (inequalityType.equalsIgnoreCase("G"))
            JOptionPane.showMessageDialog(null, "This parameter cannot be > " + value, "", JOptionPane.WARNING_MESSAGE);
        else if (inequalityType.equalsIgnoreCase("GE"))
            JOptionPane.showMessageDialog(null, "This parameter cannot be >= " + value, "", JOptionPane.WARNING_MESSAGE);
    }

    public static void displayThisParameterCannotBe(double bottomValue, double upperValue) {
        JOptionPane.showMessageDialog(null, "This parameter cannot be out of " + bottomValue + " - " + upperValue);
    }

    public static void displayThisParameterCannotBe(QualityLevel firstValue, QualityLevel secondValue, QualityLevel thirdValue) {
        JOptionPane.showMessageDialog(null, "This parameter cannot be different than: " + firstValue + " " +
                " " + secondValue + " and " + thirdValue);
    }
}
