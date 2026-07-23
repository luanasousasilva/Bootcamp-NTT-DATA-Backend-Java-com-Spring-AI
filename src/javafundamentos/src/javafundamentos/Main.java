package javafundamentos;

import java.util.Scanner;

/**
 * Aplicação de console para identificar tipos e normalizar telefones brasileiros.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Identificador de tipos e formatador de telefones");
        System.out.println("Digite um valor ou 'sair' para encerrar.");

        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) {
                break;
            }

            String entrada = scanner.nextLine();
            if ("sair".equalsIgnoreCase(entrada.trim())) {
                break;
            }

            System.out.println(IdentificadorEntrada.identificar(entrada));
        }

        scanner.close();
    }
}
