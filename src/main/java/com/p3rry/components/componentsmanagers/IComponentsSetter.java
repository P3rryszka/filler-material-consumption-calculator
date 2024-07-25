package com.p3rry.components.componentsmanagers;

import lombok.NonNull;

import javax.swing.*;
import java.util.List;

@FunctionalInterface
public interface IComponentsSetter {
    void setComponents();

    default <T extends JComponent> void positionComponentsWithOffset(@NonNull List<T> list, int xPosition,
                                            int yPosition, int width, int height,
                                            int index, int summand) {
        for(int i = index; i < list.size(); i++) {
            list.get(i).setBounds(xPosition, yPosition, width, height);
            yPosition += summand;
        }
    }
}
