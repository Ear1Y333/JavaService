package services;

import Exceptions.QuadraticEquationException;
import models.QuadraticEquationResponse;

public class QuadraticEquationServiceImpl implements QuadraticEquationService {
    private static final String FORMULA = "%fx^2 %fx + %f = 0";

    @Override
    public QuadraticEquationResponse solveEquation(double a, double b, double c) {
        double discriminant = b * b - 4 * a * c;
        String formatedFormula = String.format(FORMULA, a, b, c);

        if (discriminant < 0) {
            throw new QuadraticEquationException("No real roots", formatedFormula, discriminant);
        }
        double x1 = (-b + Math.sqrt(discriminant)) / (2 * a);
        double x2 = (discriminant == 0) ? x1 : (-b - Math.sqrt(discriminant)) / (2 * a);
        return new QuadraticEquationResponse(formatedFormula, discriminant, x1, (discriminant == 0) ? null : x2);
    }
}