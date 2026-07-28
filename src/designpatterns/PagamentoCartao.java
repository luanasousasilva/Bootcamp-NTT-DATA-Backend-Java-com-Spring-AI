package designpatterns;

public class PagamentoCartao implements EstrategiaPagamento {
    @Override
    public void pagar(double valor) {
        System.out.printf("Pagamento de R$ %.2f aprovado no cartão de crédito.%n", valor);
    }
}
