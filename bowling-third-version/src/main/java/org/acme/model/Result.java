package org.acme.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Result {

    private int baseValue;
    private boolean isCalculated;
    private int finalValue;

    private int accumulatedValue;

    public int getBaseValue() {
        return baseValue;
    }

    public void addBaseValue(int baseValue) {
        this.baseValue += baseValue;
    }

    public void addFinalValue(int finalValue) {
        this.finalValue += finalValue;
    }

    public boolean isCalculated() {
        return isCalculated;
    }

    public void setCalculated(boolean calculated) {
        isCalculated = calculated;
    }

    public int getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(int finalValue) {
        this.finalValue = finalValue;
    }

    public int getAccumulatedValue() {
        return accumulatedValue;
    }

    public void setAccumulatedValue(int accumulatedValue) {
        this.accumulatedValue = accumulatedValue;
    }
}
