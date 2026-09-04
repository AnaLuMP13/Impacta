package SistemaAcoes;

import java.time.LocalDateTime;

public class OficinaEcologica extends Acao {
    private int duracaoHoras;
    private boolean kitMaterial;

    // Construtor
    public OficinaEcologica(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes, int duracaoHoras, boolean kitMaterial) {
        super(id, titulo, descricao, data, maxParticipantes);
        this.duracaoHoras = duracaoHoras;
        this.kitMaterial = kitMaterial;
    }

    @Override
    public int calcularPontuacao() {
        int pontos = 3 * duracaoHoras;
        if (kitMaterial == true) {
            pontos += 10;
            return pontos;
        }
        return pontos;
    }
}
