package models;

public class QuadraticEquationResponse {
    private final String formula;
    private final double d;
    private final double x1;
    private final Double x2;

    public QuadraticEquationResponse(String formula, double d, double x1, Double x2) {
        this.formula = formula;
        this.d = d;
        this.x1 = x1;
        this.x2 = x2;
    }

    public String getFormula() {
        return formula;
    }

    public double getX1() {
        return x1;
    }

    public Double getX2() {
        return x2;
    }

    public double getD() {
        return d;
    }
}