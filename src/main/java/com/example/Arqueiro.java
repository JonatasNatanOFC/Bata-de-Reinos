package com.example;

public class Arqueiro extends Peca {
    public Arqueiro(int x, int y, Jogador dono) {
        super("Arqueiro", "A", x, y, dono, new MovimentoDiagonal());
    }
}