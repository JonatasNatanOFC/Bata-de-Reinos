package com.example;

public class MovimentoEmL implements MovimentoStrategy {
    @Override
    public boolean podeMover(Peca peca, int novoX, int novoY, Peca[][] tabuleiro) {
        int deltaX = Math.abs(novoX - peca.getPosicaoX());
        int deltaY = Math.abs(novoY - peca.getPosicaoY());
        return (deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1);
    }
}