package designpatterns;

public interface ObservadorPedido {
    void atualizar(Pedido pedido, StatusPedido statusAnterior);
}
