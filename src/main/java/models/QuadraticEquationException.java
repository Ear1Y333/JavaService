package models;

public class QuadraticEquationException extends RuntimeException{
    private final String formula;
    private final double d;

    public QuadraticEquationException(String message, String formula, double d) {
        super(message);
        this.formula = formula;
        this.d = d;
    }

    public String getFormula() {
        return formula;
    }

    public double getD() {
        return d;
    }
}