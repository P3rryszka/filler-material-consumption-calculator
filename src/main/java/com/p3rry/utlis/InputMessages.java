package com.p3rry.utlis;

import com.p3rry.consts.QualityLevel;
import lombok.NonNull;

import javax.swing.*;

public class InputMessages {
    public static void displayNoSelectedPanel(@NonNull String selectionType) {
        switch (selectionType) {
            case ("JointPanel") ->
                    JOptionPane.showMessageDialog(
                            null, "Select joint panel!",
                            "", 1
                    );
            case ("WeldingMethod") ->
                    JOptionPane.showMessageDialog(
                            null, "Select welding method panel!",
                            "", 1
                    );
            case ("BothPanels") ->
                    JOptionPane.showMessageDialog(
                            null, "Select panels!",
                            "", 1
                    );
            default ->
                    throw new IllegalArgumentException("Invalid selection type!");
        }
    }

    public static void displayThisParamCannotBe(double value, @NonNull String inequalityType,
                                                @NonNull String parameterName) {
        switch (inequalityType) {
            case ("<") ->
                    JOptionPane.showMessageDialog(
                            null, parameterName + " cannot be < " + value,
                            "", 2
                    );
            case ("<=") ->
                    JOptionPane.showMessageDialog(
                    null, parameterName + " cannot be <= " + value,
                    "", 2
            );
            case ("=") ->
                    JOptionPane.showMessageDialog(
                            null, parameterName + " cannot be = " + value,
                            "", 2
                    );
            case (">") ->
                    JOptionPane.showMessageDialog(
                            null, parameterName + " cannot be > " + value,
                            "", 2
                    );
            case(">=") ->
                    JOptionPane.showMessageDialog(
                            null, parameterName + " cannot be >= " + value,
                            "", 2
                    );
            default ->
                    throw new IllegalArgumentException("Invalid inequality type!");
        }
    }

    public static void displayThisParamMustBeInRange(double bottomValue, double upperValue,
                                                     @NonNull String parameterName) {
        JOptionPane.showMessageDialog(
                null, parameterName + " cannot be out of range: " + bottomValue + " - " + upperValue,
                "", 2
        );
    }

    public static void displayThisParamCannotBeDiffThan(@NonNull QualityLevel firstValue, @NonNull QualityLevel secondValue,
                                                        @NonNull QualityLevel thirdValue) {
        JOptionPane.showMessageDialog(
                null, "This parameter cannot be different than: " + firstValue + " " +
                " " + secondValue + " and " + thirdValue
        );
    }

    public static void displayEmptyParam() {
        JOptionPane.showMessageDialog(
                null, "Make sure every parameter is provided!",
                "", 2
        );
    }

    public static void displayAllParamsAreEmpty() {
        JOptionPane.showMessageDialog(
                null, "Nothing to clear! Every parameter is empty!",
                "", 2
        );
    }
}
