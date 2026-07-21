package interfacelambida;

public class SMS implements ServicoMensagem {
    @Override
    public void receberMensagem(String mensagem) {
        System.out.println("SMS enviado: " + mensagem);
    }
}
