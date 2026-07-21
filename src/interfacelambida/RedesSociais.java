package interfacelambida;

public class RedesSociais implements ServicoMensagem {
    @Override
    public void receberMensagem(String mensagem) {
        System.out.println("Mensagem publicada nas redes sociais: " + mensagem);
    }
}
