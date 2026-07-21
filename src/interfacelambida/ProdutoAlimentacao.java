package interfacelambida;

public class ProdutoAlimentacao implements Produto {
    private final double valor;

    public ProdutoAlimentacao(double valor) {
        this.valor = valor;
    }

    @Override
    public double calcularImposto() {
        return valor * 0.01;
    }
}
