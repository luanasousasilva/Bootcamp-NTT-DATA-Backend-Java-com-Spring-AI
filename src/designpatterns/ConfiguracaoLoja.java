package designpatterns;

public final class ConfiguracaoLoja {
    private static final ConfiguracaoLoja INSTANCIA = new ConfiguracaoLoja();

    private final String nomeLoja = "Tech Store";
    private final double fretePadrao = 12.50;

    private ConfiguracaoLoja() {
    }

    public static ConfiguracaoLoja getInstancia() {
        return INSTANCIA;
    }

    public String getNomeLoja() {
        return nomeLoja;
    }

    public double getFretePadrao() {
        return fretePadrao;
    }
}
