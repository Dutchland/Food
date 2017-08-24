package com.voeding;

import com.voeding.domain.MacroType;
import com.voeding.domain.valueobjects.MassPercentage;

import java.util.Map;

public class ProductValidator {
    public void validate(String productName, Map<MacroType, MassPercentage> macros){

    //    com.voeding.utils.Conditions.notBiggerThan(getMacro(com.voeding.domain.MacroType.SATURATED_FAT).value(), getMacro(com.voeding.domain.MacroType.FAT).value(), "Saturated fat cannot be bigger than total fat");
    //    com.voeding.utils.Conditions.notBiggerThan(getMacro(com.voeding.domain.MacroType.SUGAR).value(), getMacro(com.voeding.domain.MacroType.CARBOHYDRATE).value(), "Sugar cannot be bigger than carbohydrates");
    }
}
