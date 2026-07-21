package interfacelambida;

public class ProdutoSaudeBemEstar implements Produto {
    private final double valor;

    public ProdutoSaudeBemEstar(double valor) {
        this.valor = valor;
    }

    @Override
    public double calcularImposto() {
        return valor * 0.015;
    }
}
