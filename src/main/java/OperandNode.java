public class OperandNode extends Node {
    private double value;

    public OperandNode(double v) {
        value = v;
    }

    @Override
    public double evaluate() {
        return value;
    }
}
