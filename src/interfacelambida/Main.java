package interfacelambida;

public class Main {
    public static void main(String[] args) {
        enviarMensagemDeMarketing();
        calcularImpostos();
        calcularAreas();
    }

    private static void enviarMensagemDeMarketing() {
        System.out.println("--- Marketing ---");
        String mensagem = "Aproveite nossa promoção de hoje!";

        ServicoMensagem[] servicos = {
                new SMS(),
                new Email(),
                new RedesSociais(),
                new WhatsApp()
        };

        for (ServicoMensagem servico : servicos) {
            servico.receberMensagem(mensagem);
        }
    }

    private static void calcularImpostos() {
        System.out.println("\n--- Impostos ---");
        Produto[] produtos = {
                new ProdutoAlimentacao(100.0),
                new ProdutoSaudeBemEstar(100.0),
                new ProdutoVestuario(100.0),
                new ProdutoCultura(100.0)
        };

        for (Produto produto : produtos) {
            System.out.printf("%s: R$ %.2f%n",
                    produto.getClass().getSimpleName(), produto.calcularImposto());
        }
    }

    private static void calcularAreas() {
        System.out.println("\n--- Áreas ---");
        FiguraGeometrica[] figuras = {
                new Quadrado(5.0),
                new Retangulo(4.0, 6.0),
                new Circulo(3.0)
        };

        for (FiguraGeometrica figura : figuras) {
            System.out.printf("%s: %.2f%n",
                    figura.getClass().getSimpleName(), figura.calcularArea());
        }
    }
}
