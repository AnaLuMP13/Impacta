package ClassesAcoes;

import java.time.LocalDateTime;

public class OficinaEcologica extends Acoes {
    private int duracaoHoras;
    private boolean kitMaterial;

    public OficinaEcologica(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes, int duracaoHoras, boolean kitMaterial) {
        super(id, titulo, descricao, data, maxParticipantes);
        this.duracaoHoras = duracaoHoras;
        this.kitMaterial = kitMaterial;
    }

    public int getDuracaoHoras() {
        return duracaoHoras;
    }

    public boolean isKitMaterial() {
        return kitMaterial;
    }

    @Override
    public int calcularPontuacao() {
        return (3 * duracaoHoras);
    }

    @Override
    public String exibirDetalhes() {
        return super.exibirDetalhes() + "Duração: " + duracaoHoras + "h | Kit Material: " + (kitMaterial ? "Sim" : "Não");
    }
}
