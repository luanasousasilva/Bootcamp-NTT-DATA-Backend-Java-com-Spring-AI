package designpatterns;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private final int numero;
    private final String cliente;
    private final String contato;
    private final List<ItemPedido> itens;
    private final double frete;
    private final List<ObservadorPedido> observadores = new ArrayList<>();
    private StatusPedido status = StatusPedido.CRIADO;

    private Pedido(Builder builder) {
        numero = builder.numero;
        cliente = builder.cliente;
        contato = builder.contato;
        itens = List.copyOf(builder.itens);
        frete = builder.frete;
    }

    public void adicionarObservador(ObservadorPedido observador) {
        observadores.add(observador);
    }

    public void atualizarStatus(StatusPedido novoStatus) {
        StatusPedido statusAnterior = status;
        status = novoStatus;
        observadores.forEach(observador -> observador.atualizar(this, statusAnterior));
    }

    public void processarPagamento(EstrategiaPagamento estrategiaPagamento) {
        estrategiaPagamento.pagar(getTotal());
        atualizarStatus(StatusPedido.PAGAMENTO_APROVADO);
    }

    public int getNumero() { return numero; }
    public String getCliente() { return cliente; }
    public String getContato() { return contato; }
    public StatusPedido getStatus() { return status; }
    public double getTotal() { return itens.stream().mapToDouble(ItemPedido::getSubtotal).sum() + frete; }

    public static class Builder {
        private final int numero;
        private final String cliente;
        private final String contato;
        private final List<ItemPedido> itens = new ArrayList<>();
        private double frete;

        public Builder(int numero, String cliente, String contato) {
            this.numero = numero;
            this.cliente = cliente;
            this.contato = contato;
        }

        public Builder adicionarItem(ItemPedido item) {
            itens.add(item);
            return this;
        }

        public Builder comFrete(double frete) {
            this.frete = frete;
            return this;
        }

        public Pedido construir() {
            if (itens.isEmpty()) {
                throw new IllegalStateException("O pedido deve ter ao menos um item.");
            }
            return new Pedido(this);
        }
    }
}
