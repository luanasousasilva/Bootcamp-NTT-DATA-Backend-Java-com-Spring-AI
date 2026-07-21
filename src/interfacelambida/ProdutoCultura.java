package interfacelambida;

public class ProdutoCultura implements Produto {
    private final double valor;

    public ProdutoCultura(double valor) {
        this.valor = valor;
    }

    @Override
    public double calcularImposto() {
        return valor * 0.04;
    }
}
