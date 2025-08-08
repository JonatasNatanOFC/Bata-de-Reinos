package com.example;

public class Cavaleiro extends Peca {
    public Cavaleiro(int x, int y, Jogador dono) {
        super("Cavaleiro", "C", x, y, dono, new MovimentoEmL());
    }
}