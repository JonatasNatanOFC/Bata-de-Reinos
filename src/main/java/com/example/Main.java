package com.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bem-vindo ao Batalha dos Reinos!");
        System.out.println("==================================");

        RenderizadorTabuleiro renderizador = new RenderizadorTabuleiro();
        try (ControladorInput controlador = new ControladorInput()) {
            Jogo jogo = new Jogo(renderizador, controlador);
            jogo.iniciar();
        }
    }
}