package designpatterns;

public class NotificadorEmail implements Notificador {
    @Override
    public void enviar(String destinatario, String mensagem) {
        System.out.printf("E-mail para %s: %s%n", destinatario, mensagem);
    }
}
