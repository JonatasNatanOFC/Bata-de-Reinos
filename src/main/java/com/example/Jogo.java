package com.example;

public class Jogo {
    public static final int TAMANHO_TABULEIRO = 8;

    private final Peca[][] tabuleiro;
    private final Jogador jogador1;
    private final Jogador jogador2;
    private Jogador jogadorAtual;

    private final RenderizadorTabuleiro renderizador;
    private final ControladorInput controlador;

    public Jogo(RenderizadorTabuleiro renderizador, ControladorInput controlador) {
        this.tabuleiro = new Peca[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
        this.jogador1 = new Jogador("Jogador 1 (Brancas - MAIÚSCULAS)");
        this.jogador2 = new Jogador("Jogador 2 (Pretas - minúsculas)");
        this.jogadorAtual = jogador1;
        this.renderizador = renderizador;
        this.controlador = controlador;
        configurarTabuleiroInicial();
    }

    public void iniciar() {
        while (true) {
            renderizador.exibir(tabuleiro, jogadorAtual);
            exibirPecasDoJogadorAtual();

            if (!realizarTurno()) {
                System.out.println("Jogada inválida. Tente novamente.");
                controlador.esperarEnter();
                continue;
            }

            if (verificarFimDeJogo()) {
                renderizador.exibir(tabuleiro, jogadorAtual);
                System.out.println("🎉 FIM DE JOGO! O vencedor é " + jogadorAtual.getNome() + " 🎉");
                break;
            }

            trocarJogador();
        }
    }

    private boolean realizarTurno() {
        java.util.List<Peca> pecasDoJogador = jogadorAtual.getPecas();
        if (pecasDoJogador.isEmpty()) {
            return false;
        }
        
        int indiceEscolhido = controlador.pedirIndice("Escolha o número da peça a mover:");
        if (indiceEscolhido < 1 || indiceEscolhido > pecasDoJogador.size()) {
            System.out.println("Erro: Número de peça inválido.");
            return false;
        }

        Peca pecaEscolhida = pecasDoJogador.get(indiceEscolhido - 1);

        System.out.printf("Peça escolhida: %s.\n", pecaEscolhida.toString());
        int[] posDestino = controlador.pedirCoordenadas("Para onde deseja mover? (X Y):");
        int novoX = posDestino[0];
        int novoY = posDestino[1];

        if (validarMovimento(pecaEscolhida, novoX, novoY)) {
            Command move = new MoverCommand(pecaEscolhida, novoX, novoY, this.tabuleiro);
            move.execute();
            return true;
        }
        return false;
    }

    private boolean validarMovimento(Peca peca, int novoX, int novoY) {
        if (!(novoX >= 0 && novoX < TAMANHO_TABULEIRO && novoY >= 0 && novoY < TAMANHO_TABULEIRO)) {
            System.out.println("Movimento inválido: Fora do tabuleiro.");
            return false;
        }

        if (!peca.podeMoverPara(novoX, novoY, this.tabuleiro)) {
            System.out.println("Movimento inválido: A peça não se move desta forma ou o caminho está obstruído.");
            return false;
        }

        Peca pecaNoDestino = tabuleiro[novoX][novoY];
        if (pecaNoDestino != null && pecaNoDestino.getJogadorDono().equals(jogadorAtual)) {
            System.out.println("Movimento inválido: Não pode mover para uma casa ocupada por uma peça sua.");
            return false;
        }
        return true;
    }

    private boolean verificarFimDeJogo() {
        Jogador adversario = (jogadorAtual == jogador1) ? jogador2 : jogador1;
        return !adversario.aindaTemPecas();
    }

    private void trocarJogador() {
        jogadorAtual = (jogadorAtual == jogador1) ? jogador2 : jogador1;
    }

    private void exibirPecasDoJogadorAtual() {
        System.out.println("\nPeças disponíveis para " + jogadorAtual.getNome() + ":");
        java.util.List<Peca> pecas = jogadorAtual.getPecas();
        for (int i = 0; i < pecas.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, pecas.get(i).toString());
        }
        System.out.println();
    }

    private void configurarTabuleiroInicial() {
        
        adicionarPeca(PecaFactory.criarPeca(TipoPeca.GUERREIRO, jogador1, 2, 0));
        adicionarPeca(PecaFactory.criarPeca(TipoPeca.ARQUEIRO, jogador1, 3, 0));
        adicionarPeca(PecaFactory.criarPeca(TipoPeca.CAVALEIRO, jogador1, 4, 0));

        
        adicionarPeca(PecaFactory.criarPeca(TipoPeca.GUERREIRO, jogador2, 2, 7));
        adicionarPeca(PecaFactory.criarPeca(TipoPeca.ARQUEIRO, jogador2, 3, 7));
        adicionarPeca(PecaFactory.criarPeca(TipoPeca.CAVALEIRO, jogador2, 4, 7));
    }

    private void adicionarPeca(Peca peca) {
        peca.getJogadorDono().adicionarPeca(peca);
        tabuleiro[peca.getPosicaoX()][peca.getPosicaoY()] = peca;
    }
}