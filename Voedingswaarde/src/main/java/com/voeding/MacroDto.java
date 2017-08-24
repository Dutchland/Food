package com.voeding;

import com.voeding.domain.MacroType;
import com.voeding.domain.MassPercentage;

import java.math.BigDecimal;

public class MacroDto {
    private String macroType;
    private BigDecimal massPercentage;

    public MacroDto(String macroType, BigDecimal massPercentage) {
        this.macroType = macroType;
        this.massPercentage = massPercentage;
    }

    public static MacroDto fromEntities(MacroType type, MassPercentage massPercentage) {
        return new MacroDto(type.toString(), massPercentage.value());
    }

    public String getMacroType() {
        return macroType;
    }

    public void setMacroType(String macroType) {
        this.macroType = macroType;
    }

    public BigDecimal getMassPercentage() {
        return massPercentage;
    }

    public void setMassPercentage(BigDecimal massPercentage) {
        this.massPercentage = massPercentage;
    }
}
