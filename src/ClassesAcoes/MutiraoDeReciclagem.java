package ClassesAcoes;

import java.time.LocalDateTime;

public class MutiraoDeReciclagem  extends Acoes {
    private int duracaoHoras;

    public MutiraoDeReciclagem(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes, int duracaoHoras) {
        super(id, titulo, descricao, data, maxParticipantes);
        this.duracaoHoras = duracaoHoras;
    }

    public int getDuracaoHoras() {
        return duracaoHoras;
    }

    @Override
    public int calcularPontuacao() {
        return 4 * duracaoHoras;
    }

    @Override
    public String exibirDetalhes() {
        return super.exibirDetalhes() + "Duração: " + duracaoHoras + "h";
    }
}
