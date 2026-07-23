package javafundamentos;

final class Telefone {
    private Telefone() {
    }

    static boolean possuiTamanhoValido(String digitos) {
        int quantidade = digitos.length();
        return quantidade == 8 || quantidade == 9 || quantidade == 10 || quantidade == 11;
    }

    static String formatar(String entrada) {
        String digitos = entrada.replaceAll("\\D", "");
        String formato;
        String tipo;

        switch (digitos.length()) {
            case 8:
                formato = digitos.substring(0, 4) + "-" + digitos.substring(4);
                tipo = "telefone fixo sem DDD";
                break;
            case 9:
                formato = digitos.substring(0, 5) + "-" + digitos.substring(5);
                tipo = "celular sem DDD";
                break;
            case 10:
                formato = "(" + digitos.substring(0, 2) + ")" + digitos.substring(2, 6)
                        + "-" + digitos.substring(6);
                tipo = "telefone fixo com DDD";
                break;
            case 11:
                formato = "(" + digitos.substring(0, 2) + ")" + digitos.substring(2, 7)
                        + "-" + digitos.substring(7);
                tipo = "celular com DDD";
                break;
            default:
                return "Entrada inválida: não se trata de um número de telefone válido.";
        }

        return "Tipo de dispositivo: " + tipo + ". Telefone formatado: " + formato + ".";
    }
}
