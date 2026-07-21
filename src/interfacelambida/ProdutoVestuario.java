package interfacelambida;

public class ProdutoVestuario implements Produto {
    private final double valor;

    public ProdutoVestuario(double valor) {
        this.valor = valor;
    }

    @Override
    public double calcularImposto() {
        return valor * 0.025;
    }
}
