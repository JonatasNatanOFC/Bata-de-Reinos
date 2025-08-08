package com.example;

public class MoverCommand implements Command {
    private final Peca peca;
    private final int novoX;
    private final int novoY;
    private final Peca[][] tabuleiro;

    public MoverCommand(Peca peca, int novoX, int novoY, Peca[][] tabuleiro) {
        this.peca = peca;
        this.novoX = novoX;
        this.novoY = novoY;
        this.tabuleiro = tabuleiro;
    }

    @Override
    public void execute() {
        Peca pecaAtacada = tabuleiro[novoX][novoY];
        if (pecaAtacada != null) {
            Jogador adversario = pecaAtacada.getJogadorDono();
            adversario.removerPeca(pecaAtacada);
            System.out.printf("\n*** ATAQUE! A peça %s do jogador %s foi removida! ***\n",
                    pecaAtacada.getNome(), adversario.getNome());
        }

        tabuleiro[peca.getPosicaoX()][peca.getPosicaoY()] = null;
        peca.moverPara(novoX, novoY);
        tabuleiro[novoX][novoY] = peca;
    }
}