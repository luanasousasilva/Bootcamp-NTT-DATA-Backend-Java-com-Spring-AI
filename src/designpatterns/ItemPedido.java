package designpatterns;

public record ItemPedido(String descricao, int quantidade, double precoUnitario) {

    public ItemPedido {
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("A descrição do item é obrigatória.");
        }
        if (quantidade <= 0 || precoUnitario <= 0) {
            throw new IllegalArgumentException("Quantidade e preço devem ser positivos.");
        }
    }

    public double getSubtotal() {
        return quantidade * precoUnitario;
    }
}
