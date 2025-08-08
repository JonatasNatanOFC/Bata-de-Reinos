package com.example;

public class Guerreiro extends Peca {
    public Guerreiro(int x, int y, Jogador dono) {
        super("Guerreiro", "G", x, y, dono, new MovimentoVerticalHorizontal());
    }
}