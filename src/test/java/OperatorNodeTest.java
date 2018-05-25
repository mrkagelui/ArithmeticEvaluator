import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperatorNodeTest {

    @Test
    void evaluate() {
        OperatorNode plus = new OperatorNode(Operator.PLUS,
                new OperandNode(1), new OperandNode(2));
        OperatorNode minus = new OperatorNode(Operator.MINUS,
                new OperandNode(20), plus);
        OperatorNode multiply = new OperatorNode(Operator.MULTIPLY,
                minus, new OperandNode(2));
        assertEquals(3, plus.evaluate());
        assertEquals(17, minus.evaluate());
        assertEquals(34, multiply.evaluate());
        OperatorNode divide = new OperatorNode(Operator.DIVIDE,
                new OperandNode(1), new OperandNode(0));
        assertThrows(ArithmeticException.class, divide::evaluate);
    }

    @Test
    void compareTo() {
        OperatorNode plus = new OperatorNode(Operator.PLUS,
                new OperandNode(1), new OperandNode(2));
        OperatorNode minus = new OperatorNode(Operator.MINUS,
                new OperandNode(1), new OperandNode(2));
        OperatorNode divide = new OperatorNode(Operator.DIVIDE,
                new OperandNode(1), new OperandNode(2));
        OperatorNode multiply = new OperatorNode(Operator.MULTIPLY,
                new OperandNode(1), new OperandNode(2));
        assertTrue(plus.compareTo(multiply) < 0);
        assertTrue(minus.compareTo(multiply) < 0);
        assertTrue(divide.compareTo(plus) > 0);
        assertTrue(divide.compareTo(minus) > 0);
        assertEquals(0, plus.compareTo(minus));
        assertEquals(0, divide.compareTo(multiply));
    }
}