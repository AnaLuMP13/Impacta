package SistemaAcoes;

import java.time.LocalDateTime;

public class Plantio extends Acao {
    private int qtdMudas;

    // Construtor
    public Plantio(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes, int qtdMudas) {
        super(id, titulo, descricao, data, maxParticipantes);
        this.qtdMudas = qtdMudas;
    }

    @Override
    public int calcularPontuacao() {
        int pontuacaoFinal = 5 + (2 + qtdMudas);
        return pontuacaoFinal;
    }

    // get e set
    public int getQtdMudas() {
        return qtdMudas;
    }

    public void setQtdMudas(int qtdMudas) {
        this.qtdMudas = qtdMudas;
    }
}
