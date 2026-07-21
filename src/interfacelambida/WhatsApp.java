package interfacelambida;

public class WhatsApp implements ServicoMensagem {
    @Override
    public void receberMensagem(String mensagem) {
        System.out.println("WhatsApp enviado: " + mensagem);
    }
}
