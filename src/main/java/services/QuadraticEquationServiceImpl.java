package services;

import models.QuadraticEquationException;
import models.QuadraticEquationResponse;

public class QuadraticEquationServiceImpl implements QuadraticEquationService {

    @Override
    public QuadraticEquationResponse solveEquation(double a, double b, double c) {
        double D = b * b - 4 * a * c;
        String formula = a + "x^2 + " + b + "x + " + c + " = 0";
        if (D < 0) {
            throw new QuadraticEquationException("No real roots", formula, D);
        }
        double x1 = (-b + Math.sqrt(D)) / (2 * a);
        double x2 = (D == 0) ? x1 : (-b - Math.sqrt(D)) / (2 * a);
        return new QuadraticEquationResponse(formula, D, x1, (D == 0) ? null : x2);
    }
}