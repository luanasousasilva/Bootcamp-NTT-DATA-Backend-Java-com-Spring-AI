package designpatterns;

public class NotificacaoStatusPedido implements ObservadorPedido {
    private final Notificador notificador;

    public NotificacaoStatusPedido(Notificador notificador) {
        this.notificador = notificador;
    }

    @Override
    public void atualizar(Pedido pedido, StatusPedido statusAnterior) {
        String mensagem = "Pedido #" + pedido.getNumero() + " atualizado de "
                + statusAnterior + " para " + pedido.getStatus() + ".";
        notificador.enviar(pedido.getContato(), mensagem);
    }
}
