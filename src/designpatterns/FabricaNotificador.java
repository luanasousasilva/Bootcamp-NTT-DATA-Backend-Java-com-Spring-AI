package designpatterns;

public final class FabricaNotificador {
    private FabricaNotificador() {
    }

    public static Notificador criar(String canal) {
        return switch (canal.toLowerCase()) {
            case "email" -> new NotificadorEmail();
            case "sms" -> new NotificadorSms();
            default -> throw new IllegalArgumentException("Canal de notificação inválido: " + canal);
        };
    }
}
