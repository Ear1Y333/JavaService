package models;

public class EquationParameters {
    private final double a;
    private final double b;
    private final double c;

    public EquationParameters(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }
}