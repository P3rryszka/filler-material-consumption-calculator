package com.p3rry.calculations;

import com.p3rry.components.BaseMaterialDensityPanel;
import lombok.NonNull;

import java.util.Optional;

public class DensityParser {
    public static double parseBaseMaterialDensity(@NonNull BaseMaterialDensityPanel baseMaterialDensityPanel) {
        try {
            return Optional.ofNullable(baseMaterialDensityPanel.getBaseMaterialDensityTextField().getText())
                    .map(Double::parseDouble)
                    .orElseThrow(() ->
                        new IllegalArgumentException("Parsing value within base material density panel is null!")
                    );
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid input within base material density panel!");
        }
    }
}
