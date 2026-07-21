package interfacelambida;

public class Quadrado implements FiguraGeometrica {
    private final double lado;

    public Quadrado(double lado) {
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        return lado * lado;
    }
}
