package services;

import static org.junit.Assert.*;

import Exceptions.QuadraticEquationException;
import models.QuadraticEquationResponse;
import org.junit.Test;

public class QuadraticEquationServiceTest {

    private final QuadraticEquationService service = new QuadraticEquationServiceImpl();

    @Test
    public void testSolveEquation() {
        QuadraticEquationResponse result = service.solveEquation(1, -3, 2);
        assertEquals(2, result.getX1(), 0.01);
        assertEquals(1, result.getX2(), 0.01);
    }
    @Test(expected = QuadraticEquationException.class)
    public void testNegativeDiscriminant() {
        service.solveEquation(1, -3, 5);
    }


    @Test(expected = QuadraticEquationException.class)
    public void testNoRealRoots() {
        service.solveEquation(1, 2, 5);
    }
}