package interfacelambida;

public class Email implements ServicoMensagem {
    @Override
    public void receberMensagem(String mensagem) {
        System.out.println("E-mail enviado: " + mensagem);
    }
}
