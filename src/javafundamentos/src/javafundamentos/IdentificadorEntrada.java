package javafundamentos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/** Identifica os tipos do exercício e normaliza telefones. */
public final class IdentificadorEntrada {
    private static final Pattern INTEIRO = Pattern.compile("[+-]?\\d+");
    private static final Pattern DECIMAL = Pattern.compile("[+-]?\\d+[.,]\\d+");
    private static final DateTimeFormatter DATA_BR = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    private static final DateTimeFormatter DATA_HORA_BR = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm[:ss]");

    private IdentificadorEntrada() {
    }

    public static String identificar(String entrada) {
        String valor = entrada == null ? "" : entrada.trim();
        if (valor.isEmpty()) {
            return "Entrada inválida: informe algum valor.";
        }
        if (valor.startsWith("[") && valor.endsWith("]")) {
            return identificarArray(valor);
        }
        if (ehDataHora(valor)) {
            return "Tipo identificado: data e hora.";
        }
        if (ehData(valor)) {
            return "Tipo identificado: data.";
        }

        String digitos = valor.replaceAll("\\D", "");
        if (Telefone.possuiTamanhoValido(digitos)) {
            return Telefone.formatar(valor);
        }
        if ("true".equalsIgnoreCase(valor) || "false".equalsIgnoreCase(valor)) {
            return "Tipo identificado: booleano.";
        }
        if (INTEIRO.matcher(valor).matches()) {
            return "Tipo identificado: número inteiro.";
        }
        if (DECIMAL.matcher(valor).matches()) {
            return "Tipo identificado: número com ponto flutuante.";
        }
        if (!digitos.isEmpty()) {
            return "Entrada inválida: não se trata de um número de telefone válido.";
        }
        return "Tipo identificado: texto.";
    }

    private static boolean ehData(String valor) {
        return podeConverterData(valor, DateTimeFormatter.ISO_LOCAL_DATE)
                || podeConverterData(valor, DATA_BR);
    }

    private static boolean ehDataHora(String valor) {
        return podeConverterDataHora(valor, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                || podeConverterDataHora(valor, DATA_HORA_BR);
    }

    private static boolean podeConverterData(String valor, DateTimeFormatter formato) {
        try {
            LocalDate.parse(valor, formato);
            return true;
        } catch (DateTimeParseException excecao) {
            return false;
        }
    }

    private static boolean podeConverterDataHora(String valor, DateTimeFormatter formato) {
        try {
            LocalDateTime.parse(valor, formato);
            return true;
        } catch (DateTimeParseException excecao) {
            return false;
        }
    }

    private static String identificarArray(String valor) {
        String conteudo = valor.substring(1, valor.length() - 1).trim();
        if (conteudo.isEmpty()) {
            return "Tipo identificado: array vazio.";
        }

        List<String> tipos = new ArrayList<>();
        for (String elemento : conteudo.split(",")) {
            tipos.add(identificar(elemento.trim()));
        }
        return "Tipo identificado: array. Elementos: " + tipos;
    }
}
