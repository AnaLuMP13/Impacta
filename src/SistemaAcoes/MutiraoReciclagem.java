package SistemaAcoes;

import java.time.LocalDateTime;

public class MutiraoReciclagem extends Acao {
    private int duracaoHoras;

    // Construtor
    public MutiraoReciclagem(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes, int duracaoHoras) {
        super(id, titulo, descricao, data, maxParticipantes);
        this.duracaoHoras = duracaoHoras;
    }

    @Override
    public int calcularPontuacao() {
        int resultado = 4 * duracaoHoras;
        return resultado;
    }

    // get e set
    public int getDuracaoHoras() {
        return duracaoHoras;
    }

    public void setDuracaoHoras(int duracaoHoras) {
        this.duracaoHoras = duracaoHoras;
    }
}
