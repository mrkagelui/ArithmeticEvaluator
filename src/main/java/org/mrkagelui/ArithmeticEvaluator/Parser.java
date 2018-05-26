package org.mrkagelui.ArithmeticEvaluator;

public abstract class Parser {
    protected String input;
    public Parser(String s) {
        input = s;
    }
    abstract Node parse() throws MalformedEquationException;
}
