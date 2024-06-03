package com.p3rry.components.componentsmanagement;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.util.List;


@FunctionalInterface
public interface IListAdder {
    void addToList();

    default void cleanComponents(List<JTextComponent> textComponentList) {
        textComponentList.forEach(textComponent -> textComponent.setText(""));
    }

    default boolean checkIfAnyComponentIsEmpty(List<JTextComponent> textComponentList) {
        return textComponentList.stream()
                .anyMatch(textComponent ->
                        textComponent.getText().isEmpty());
    }

    default boolean checkIfAllComponentsAreEmpty(List<JTextComponent> textComponentList) {
        return textComponentList.stream()
                .allMatch(textComponent ->
                        textComponent.getText().isEmpty());
    }

    default void manageComponents(List<JTextComponent> textComponentsList, List<JLabel> labelsList,
                                  JPanel panel) {
        labelsList.forEach(panel::add);
        textComponentsList.forEach(panel::add);
    }
}
