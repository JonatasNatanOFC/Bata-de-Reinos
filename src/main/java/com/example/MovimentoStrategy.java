package com.example;

public interface MovimentoStrategy {
    boolean podeMover(Peca peca, int novoX, int novoY, Peca[][] tabuleiro);
}