package designpatterns;

public class NotificadorSms implements Notificador {
    @Override
    public void enviar(String destinatario, String mensagem) {
        System.out.printf("SMS para %s: %s%n", destinatario, mensagem);
    }
}
