package com.example;

import java.util.Scanner;

public class ControladorInput {
    private final Scanner scanner;

    public ControladorInput() {
        this.scanner = new Scanner(System.in);
    }

    public int[] pedirCoordenadas(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem + " ");
                String linha = scanner.nextLine();
                String[] partes = linha.trim().split("\\s+");

                if (partes.length != 2) {
                    System.out.println(
                            "Erro: Formato inválido. Por favor, insira dois números separados por espaço (ex: '4 5').");
                    continue;
                }

                int x = Integer.parseInt(partes[0]);
                int y = Integer.parseInt(partes[1]);

                return new int[] { x, y };

            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira apenas números.");
            }
        }
    }

    public void esperarEnter() {
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }

    public void fecharScanner() {
        scanner.close();
    }
}