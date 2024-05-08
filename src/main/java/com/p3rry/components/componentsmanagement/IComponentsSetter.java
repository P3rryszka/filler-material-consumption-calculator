package com.p3rry.components.componentsmanagement;

import lombok.NonNull;

import javax.swing.*;
import java.util.List;

@FunctionalInterface
public interface IComponentsSetter {
    void setComponents();

    default <T extends JComponent> void setListOfComponents(@NonNull List<T> list, int xPosition,
                                                            int yPosition, int width, int height) {
        for(var component : list) {
            component.setBounds(xPosition, yPosition, width, height);
            yPosition+=70;
        }
    }
}
