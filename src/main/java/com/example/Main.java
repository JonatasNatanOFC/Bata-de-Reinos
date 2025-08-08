package com.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bem-vindo ao Batalha dos Reinos!");
        System.out.println("==================================");

        RenderizadorTabuleiro renderizador = new RenderizadorTabuleiro();
        ControladorInput controlador = new ControladorInput();

        Jogo jogo = Jogo.getInstancia(renderizador, controlador);

        jogo.iniciar();
    }
}