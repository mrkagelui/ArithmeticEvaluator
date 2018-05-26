package org.mrkagelui.ArithmeticEvaluator;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class SimpleParser extends Parser {
    Map<Integer, Integer> bracketPairMap;

    public SimpleParser(String s) throws MalformedEquationException {
        super(s);
        buildBracketMap();
    }

    private void buildBracketMap() throws MalformedEquationException {
        Stack<Integer> leftBracketLocations = new Stack<>();
        bracketPairMap = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            if ('(' == input.charAt(i)) {
                leftBracketLocations.push(i);
            } else if (')' == input.charAt(i)) {
                if (leftBracketLocations.empty()) {
                    throw new MalformedEquationException("No matching bracket: more closing than opening");
                }
                bracketPairMap.put(leftBracketLocations.pop(), i);
            }
        }
        if (!leftBracketLocations.empty())
            throw new MalformedEquationException("No matching bracket: more opening than closing");
    }

    @Override
    Node parse() {
        return parseRecursively(0, input.length());
    }

    private Node parseRecursively(int beginsAt, int endsBefore) {
        Node root = null;
        OperatorNode newRoot = null;

        for (int i = beginsAt; i < endsBefore;) {
            char c = input.charAt(i);
            if ("+-*/".indexOf(c) >= 0) {
                newRoot = new OperatorNode(getOperatorFromChar(c));
                newRoot.pushOperand(root);
                i++;
            } else if ('(' == c) {
                int nextI = getPairedBracketIndex(i);
                Node temp = parseRecursively(i + 1, nextI);
                i = nextI+1;
                if (null == root) {
                    root = temp;
                } else if (null != newRoot) {
                    newRoot.pushOperand(temp);
                    root = newRoot;
                    newRoot = null;
                }
            } else {
                OperandNode tempValue = new OperandNode(0);
                int nextI = nextNumericalValue(i, tempValue);

                if (null == root && null == newRoot) {
                    root = tempValue;
                    i = nextI;
                } else {
                    if (nextI == endsBefore) {
                        newRoot.pushOperand(tempValue);
                        i = nextI;
                    } else {
                        OperatorNode nextOperator = new OperatorNode(getOperatorFromChar(input.charAt(nextI)));
                        if (nextOperator.compareTo(newRoot) > 0) {
                            Node next = parseRecursively(i, endsBefore);
                            newRoot.pushOperand(next);
                            i = endsBefore;
                        } else {
                            newRoot.pushOperand(tempValue);
                            i = nextI;
                        }
                    }
                    root = newRoot;
                    newRoot = null;
                }
            }
        }
        return root;
    }

    private int nextNumericalValue(int startIndex, OperandNode result) {
        StringBuilder number = new StringBuilder();
        char c;
        while ((Character.isDigit(c = input.charAt(startIndex)) || '.' == c)) {
            number.append(c);
            startIndex++;
            if (startIndex == input.length()) {
                break;
            }
        }
        double value = Double.parseDouble(number.toString());
        number.setLength(0);
        result.setValue(value);
        return startIndex;
    }

    private int getPairedBracketIndex(int leftIndex) {
        return bracketPairMap.get(leftIndex);
    }

    public static Operator getOperatorFromChar(char ch) {
        Operator op = null;
        switch (ch) {
            case '+':
                op = Operator.PLUS;
                break;
            case '-':
                op = Operator.MINUS;
                break;
            case '*':
                op = Operator.MULTIPLY;
                break;
            case '/':
                op = Operator.DIVIDE;
                break;
        }
        return op;
    }
}
