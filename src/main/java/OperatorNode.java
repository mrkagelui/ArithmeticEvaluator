enum Operator {
    PLUS,
    MINUS,
    MULTIPLY,
    DIVIDE
}

public class OperatorNode extends Node implements Comparable<Node>{
    private Operator operator;
    private Node left;
    private Node right;

    public OperatorNode(Operator o, Node n) {
        this.operator = o;
        this.left = n;
    }

    public OperatorNode(Operator operator, Node left, Node right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public Operator getOperator() {
        return this.operator;
    }

    @Override
    double evaluate() {
        double result;
        switch (operator) {
            case PLUS:
                result = left.evaluate() + right.evaluate();
                break;
            case MINUS:
                result = (null != right) ?
                        left.evaluate() - right.evaluate()
                        : 0 - left.evaluate();
                break;
            case DIVIDE:
                if (0.0 == right.evaluate()) throw new ArithmeticException("Divide by zero");
                result = left.evaluate() / right.evaluate();
                break;
            default:
                result = left.evaluate() * right.evaluate();
                break;
        }
        return result;
    }

    @Override
    public int compareTo(Node o) {
        if (! (o instanceof OperatorNode) ){
            return 0;
        }
        int result = 0;
        OperatorNode oN = (OperatorNode)o;
        switch (this.operator) {
            case PLUS:
            case MINUS:
                switch (oN.getOperator()) {
                    case PLUS:
                    case MINUS:
                        result = 0;
                        break;
                    case DIVIDE:
                    case MULTIPLY:
                        result = -1;
                        break;
                }
                break;
            case MULTIPLY:
            case DIVIDE:
                switch (oN.getOperator()) {
                    case PLUS:
                    case MINUS:
                        result = 1;
                        break;
                    case DIVIDE:
                    case MULTIPLY:
                        result = 0;
                        break;
                }
                break;
        }
        return result;
    }
}
