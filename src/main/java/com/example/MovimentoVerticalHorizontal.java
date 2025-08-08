// --- MovimentoVerticalHorizontal.java ---
package com.example;

public class MovimentoVerticalHorizontal implements MovimentoStrategy {
    @Override
    public boolean podeMover(Peca peca, int novoX, int novoY, Peca[][] tabuleiro) {
        int deltaX = novoX - peca.getPosicaoX();
        int deltaY = novoY - peca.getPosicaoY();
        boolean movimentoEmLinhaReta = (deltaX == 0 && deltaY != 0) || (deltaX != 0 && deltaY == 0);
        if (!movimentoEmLinhaReta)
            return false;
        int distancia = Math.abs(deltaX) + Math.abs(deltaY);
        if (distancia > 3)
            return false;
        if (deltaX == 0) {
            int passo = Integer.signum(deltaY);
            for (int i = peca.getPosicaoY() + passo; i != novoY; i += passo) {
                if (tabuleiro[peca.getPosicaoX()][i] != null)
                    return false;
            }
        } else {
            int passo = Integer.signum(deltaX);
            for (int i = peca.getPosicaoX() + passo; i != novoX; i += passo) {
                if (tabuleiro[i][peca.getPosicaoY()] != null)
                    return false;
            }
        }
        return true;
    }
}