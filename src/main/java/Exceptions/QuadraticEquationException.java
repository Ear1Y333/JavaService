package Exceptions;

public class QuadraticEquationException extends RuntimeException{
    private final String formula;
    private final double discriminant;

    public QuadraticEquationException(String message, String formula, double discriminant) {
        super(message);
        this.formula = formula;
        this.discriminant = discriminant;
    }

    public String getFormula() {
        return formula;
    }

    public double getDiscriminant() {
        return discriminant;
    }
}