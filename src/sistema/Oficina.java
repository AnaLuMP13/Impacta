package sistema;

import java.time.LocalDateTime;

public final class Oficina extends Acao {
    // Variáveis // PRECISA SER AÇÃO??
    private int duracaoHoras;
    private boolean kitMaterial;

    // Construtor
    public Oficina(String titulo, String descricao, LocalDateTime data, int maxParticipantes, int duracaoHoras, boolean kitMaterial) throws IllegalArgumentException {
        if (titulo.trim().isEmpty() || descricao.trim().isEmpty() || data == null || maxParticipantes <= 0 || duracaoHoras <= 0) {
            throw new IllegalArgumentException("Valor inserido inválido.");
        }
        super(titulo, descricao, data, maxParticipantes);
        this.duracaoHoras = duracaoHoras;
        this.kitMaterial = kitMaterial;
    }

}