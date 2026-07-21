package interfacelambida;

public class Retangulo implements FiguraGeometrica {
    private final double base;
    private final double altura;

    public Retangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return base * altura;
    }
}
