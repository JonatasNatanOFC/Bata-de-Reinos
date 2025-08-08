package com.example;

public class Jogo {
    public static final int TAMANHO_TABULEIRO = 8;
    private static Jogo instancia;

    private final Peca[][] tabuleiro;
    private final Jogador jogador1;
    private final Jogador jogador2;
    private Jogador jogadorAtual;

    private final RenderizadorTabuleiro renderizador;
    private final ControladorInput controlador;

    private Jogo(RenderizadorTabuleiro renderizador, ControladorInput controlador) {
        this.tabuleiro = new Peca[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
        this.jogador1 = new Jogador("Jogador 1 (Brancas - MAIÚSCULAS)");
        this.jogador2 = new Jogador("Jogador 2 (Pretas - minúsculas)");
        this.jogadorAtual = jogador1;
        this.renderizador = renderizador;
        this.controlador = controlador;
        configurarTabuleiroInicial();
    }

    public static Jogo getInstancia(RenderizadorTabuleiro renderizador, ControladorInput controlador) {
        if (instancia == null) {
            instancia = new Jogo(renderizador, controlador);
        }
        return instancia;
    }

    public void iniciar() {
        while (true) {
            renderizador.exibir(tabuleiro, jogadorAtual);

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
        controlador.fecharScanner();
    }

    private boolean realizarTurno() {
        int[] posOrigem = controlador.pedirCoordenadas("Digite as coordenadas da peça a mover (X Y):");
        int xOrigem = posOrigem[0];
        int yOrigem = posOrigem[1];

        Peca pecaEscolhida = tabuleiro[xOrigem][yOrigem];

        if (pecaEscolhida == null) {
            System.out.println("Erro: Não há nenhuma peça nesta posição.");
            return false;
        }
        if (!pecaEscolhida.getJogadorDono().equals(jogadorAtual)) {
            System.out.println("Erro: Esta peça não pertence a você.");
            return false;
        }

        System.out.printf("Peça escolhida: %s em [%d, %d].\n", pecaEscolhida.getNome(), xOrigem, yOrigem);
        int[] posDestino = controlador.pedirCoordenadas("Para onde deseja mover? (X Y):");
        int novoX = posDestino[0];
        int novoY = posDestino[1];

        if (validarMovimento(pecaEscolhida, novoX, novoY)) {
            moverPeca(pecaEscolhida, novoX, novoY);
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

    private void moverPeca(Peca peca, int novoX, int novoY) {
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

    private boolean verificarFimDeJogo() {
        Jogador adversario = (jogadorAtual == jogador1) ? jogador2 : jogador1;
        return !adversario.aindaTemPecas();
    }

    private void trocarJogador() {
        jogadorAtual = (jogadorAtual == jogador1) ? jogador2 : jogador1;
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