package org.mrkagelui.ArithmeticEvaluator;
public class OperandNode extends Node {
    private double value;

    public OperandNode(double v) {
        value = v;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public double evaluate() {
        return value;
    }
}
