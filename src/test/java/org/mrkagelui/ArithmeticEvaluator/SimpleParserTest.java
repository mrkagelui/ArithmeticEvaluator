package org.mrkagelui.ArithmeticEvaluator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleParserTest {

    @Test
    void parse() {
        try {
            Parser p;
            p = new SimpleParser("-376.5");
            assertEquals(-376.5, p.parse().evaluate());
            p = new SimpleParser("-3+76.5");
            assertEquals(73.5, p.parse().evaluate());
            p = new SimpleParser("1.5+4/8");
            assertEquals(2, p.parse().evaluate());
            p = new SimpleParser("(1.5+4)/8");
            assertEquals(0.6875, p.parse().evaluate());
            p = new SimpleParser("(1.5+2)*(-3)/4");
            assertEquals(-2.625, p.parse().evaluate());
            assertThrows(MalformedEquationException.class, () -> new SimpleParser("(1.5+2)*(-3/4"));
        } catch (MalformedEquationException e) {
            e.printStackTrace();
        }
    }
}