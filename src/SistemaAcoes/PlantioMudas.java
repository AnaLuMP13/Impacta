package SistemaAcoes;

import java.time.LocalDateTime;

public class PlantioMudas extends Acao {

    private int qtdMudas;

    public PlantioMudas(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes, int qtdMudas) {
        super(id, titulo, descricao, data, maxParticipantes);
        this.qtdMudas = qtdMudas;
    }

    @Override
    public int calcularPontuacao() {
        return 5 + 2 * qtdMudas;
    }

    @Override
    protected String detalhesEspecificos() {
        return "Tipo: Plantio de Mudas\nQuantidade de Mudas: " + qtdMudas;
    }
}
