package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Jogador {
    private final String nome;
    private final List<Peca> pecas;

    public Jogador(String nome) {
        this.nome = nome;
        this.pecas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void adicionarPeca(Peca peca) {
        this.pecas.add(peca);
    }

    public void removerPeca(Peca peca) {
        this.pecas.remove(peca);
    }

    public boolean aindaTemPecas() {
        return !pecas.isEmpty();
    }

    public void limparPecas() {
        this.pecas.clear();
    }

    public List<Peca> getPecas() {

        return Collections.unmodifiableList(pecas);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Jogador jogador = (Jogador) o;
        return Objects.equals(nome, jogador.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}