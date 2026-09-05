package ClassesAcoes;

import java.time.LocalDateTime;

public class PlantioDeMudas extends Acoes {
    private int qtdMudas;

    public PlantioDeMudas(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes, int qtdMudas) {
        super(id, titulo, descricao, data, maxParticipantes);
        this.qtdMudas = qtdMudas;
    }

    public int getQtdMudas() {
        return qtdMudas;
    }

    @Override
    public int calcularPontuacao() {
        return 5 + (2 * qtdMudas);
    }

    @Override
    public String exibirDetalhes() {
        return super.exibirDetalhes() + "Mudas Previstas: " + qtdMudas;
    }
}
