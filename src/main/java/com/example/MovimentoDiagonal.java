package com.example;

public class MovimentoDiagonal implements MovimentoStrategy {
    @Override
    public boolean podeMover(Peca peca, int novoX, int novoY, Peca[][] tabuleiro) {
        int deltaX = novoX - peca.getPosicaoX();
        int deltaY = novoY - peca.getPosicaoY();
        if (Math.abs(deltaX) != Math.abs(deltaY))
            return false;
        int distancia = Math.abs(deltaX);
        if (distancia == 0 || distancia > 2)
            return false;
        if (distancia == 2) {
            int passoX = Integer.signum(deltaX);
            int passoY = Integer.signum(deltaY);
            if (tabuleiro[peca.getPosicaoX() + passoX][peca.getPosicaoY() + passoY] != null)
                return false;
        }
        return true;
    }
}