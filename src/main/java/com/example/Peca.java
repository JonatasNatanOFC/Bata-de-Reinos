package com.example;

public class Peca {
    private final String nome;
    private final String simbolo;
    private int posicaoX;
    private int posicaoY;
    private final Jogador jogadorDono;
    private final MovimentoStrategy movimentoStrategy;

    public Peca(String nome, String simbolo, int x, int y, Jogador dono, MovimentoStrategy strategy) {
        this.nome = nome;
        this.simbolo = simbolo;
        this.posicaoX = x;
        this.posicaoY = y;
        this.jogadorDono = dono;
        this.movimentoStrategy = strategy;
    }

    public boolean podeMoverPara(int novoX, int novoY, Peca[][] tabuleiro) {
        return this.movimentoStrategy.podeMover(this, novoX, novoY, tabuleiro);
    }

    public void moverPara(int novoX, int novoY) {
        this.posicaoX = novoX;
        this.posicaoY = novoY;
    }

    public int getPosicaoX() {
        return posicaoX;
    }

    public int getPosicaoY() {
        return posicaoY;
    }

    public Jogador getJogadorDono() {
        return jogadorDono;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) em [%d, %d]", nome, simbolo, posicaoX, posicaoY);
    }
}