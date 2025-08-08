package com.example;


public class PecaFactory {

    public static Peca criarPeca(TipoPeca tipo, Jogador dono, int x, int y) {
        switch (tipo) {
            case GUERREIRO:
                return new Guerreiro(x, y, dono);
            case ARQUEIRO:
                return new Arqueiro(x, y, dono);
            case CAVALEIRO:
                return new Cavaleiro(x, y, dono);
            default:
                throw new IllegalArgumentException("Tipo de peça desconhecido: " + tipo);
        }
    }
}   