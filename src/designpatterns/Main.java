package designpatterns;

public class Main {
    public static void main(String[] args) {
        ConfiguracaoLoja configuracao = ConfiguracaoLoja.getInstancia();
        System.out.println("=== " + configuracao.getNomeLoja() + " ===");

        Pedido pedido = new Pedido.Builder(1001, "Luana", "luana@email.com")
                .adicionarItem(new ItemPedido("Teclado mecânico", 1, 249.90))
                .adicionarItem(new ItemPedido("Mouse sem fio", 1, 129.90))
                .comFrete(configuracao.getFretePadrao())
                .construir();

        pedido.adicionarObservador(new NotificacaoStatusPedido(FabricaNotificador.criar("email")));
        pedido.adicionarObservador(new NotificacaoStatusPedido(FabricaNotificador.criar("sms")));

        System.out.printf("Pedido #%d de %s - Total: R$ %.2f%n", pedido.getNumero(),
                pedido.getCliente(), pedido.getTotal());
        pedido.processarPagamento(new PagamentoPix());
        pedido.atualizarStatus(StatusPedido.EM_PREPARACAO);
        pedido.atualizarStatus(StatusPedido.ENVIADO);
        pedido.atualizarStatus(StatusPedido.ENTREGUE);
    }
}
