package com.example;

public class RenderizadorTabuleiro {
    public void exibir(Peca[][] tabuleiro, Jogador jogadorAtual) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("--- Batalha dos Reinos ---");
        System.out.println("Vez de: " + jogadorAtual.getNome());
        System.out.println("\n     0   1   2   3   4   5   6   7  (X)");
        System.out.println("   +---+---+---+---+---+---+---+---+");
        for (int y = 7; y >= 0; y--) {
            System.out.print(" " + y + " |");
            for (int x = 0; x < 8; x++) {
                Peca peca = tabuleiro[x][y];
                if (peca == null) {
                    System.out.print(" . |");
                } else {
                    String simbolo = peca.getSimbolo();
                    if (peca.getJogadorDono().getNome().contains("Jogador 1")) {
                        System.out.print(" " + simbolo.toUpperCase() + " |");
                    } else {
                        System.out.print(" " + simbolo.toLowerCase() + " |");
                    }
                }
            }
            System.out.println(" " + y);
            System.out.println("   +---+---+---+---+---+---+---+---+");
        }
    }
}