package com.p3rry.utlis;

import com.p3rry.consts.QualityLevel;

import javax.swing.*;

public class InputMessages {
    public static void displaySelectPanel(String selectionType) {
        switch (selectionType) {
            case ("JP") -> JOptionPane.showMessageDialog(null, "Select joint panel!", "", JOptionPane.INFORMATION_MESSAGE);
            case ("WP") -> JOptionPane.showMessageDialog(null, "Select welding method panel!", "", JOptionPane.INFORMATION_MESSAGE);
            case ("BP") -> JOptionPane.showMessageDialog(null, "Select panels!", "", JOptionPane.INFORMATION_MESSAGE);
            default -> throw new IllegalArgumentException("Invalid selection type!");
        }
    }

    public static void displayThisParamCannotBe(double value, String  inequalityType) {
        switch (inequalityType) {
            case ("<") -> JOptionPane.showMessageDialog(null, "This parameter cannot be < " + value, "", JOptionPane.WARNING_MESSAGE);
            case ("<=") -> JOptionPane.showMessageDialog(null, "This parameter cannot be <= " + value, "", JOptionPane.WARNING_MESSAGE);
            case ("=") -> JOptionPane.showMessageDialog(null, "This parameter cannot be = " + value, "", JOptionPane.WARNING_MESSAGE);
            case (">") -> JOptionPane.showMessageDialog(null, "This parameter cannot be > " + value, "", JOptionPane.WARNING_MESSAGE);
            case(">=") -> JOptionPane.showMessageDialog(null, "This parameter cannot be >= " + value, "", JOptionPane.WARNING_MESSAGE);
            default -> throw new IllegalArgumentException("Invalid inequality type!");
        }
    }

    public static void displayThisParamCannotBe(double bottomValue, double upperValue) {
        JOptionPane.showMessageDialog(null, "This parameter cannot be out of " + bottomValue + " - " + upperValue);
    }

    public static void displayThisParamCannotBe(QualityLevel firstValue, QualityLevel secondValue, QualityLevel thirdValue) {
        JOptionPane.showMessageDialog(null, "This parameter cannot be different than: " + firstValue + " " +
                " " + secondValue + " and " + thirdValue);
    }

    public static void displayEmptyParam() {
        JOptionPane.showMessageDialog(null, "Make sure every parameter is provided!", "", JOptionPane.WARNING_MESSAGE);
    }

    public static void displayAllParamsAreEmpty() {
        JOptionPane.showMessageDialog(null, "Nothing to clear! Every parameter is empty!", "", JOptionPane.INFORMATION_MESSAGE);
    }
}
