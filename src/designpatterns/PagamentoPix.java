package designpatterns;

public class PagamentoPix implements EstrategiaPagamento {
    @Override
    public void pagar(double valor) {
        System.out.printf("Pagamento de R$ %.2f aprovado via Pix.%n", valor);
    }
}
