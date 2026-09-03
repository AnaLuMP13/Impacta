package sistema;

import java.time.LocalDateTime;

public final class Mutirao extends Acao {
    // Variáveis
    private int duracaoHoras;

    // Construtor
    public Mutirao(String titulo, String descricao, LocalDateTime data, int maxParticipantes, int duracaoHoras) throws IllegalArgumentException {
        if (titulo.trim().isEmpty() || descricao.trim().isEmpty() || data == null || maxParticipantes <= 0 || duracaoHoras <= 0) {
            throw new IllegalArgumentException("Valor inserido inválido.");
        }
        super(titulo, descricao, data, maxParticipantes);
        this.duracaoHoras = duracaoHoras;
    }

    // Getter //PRECISA DE "THIS?"
    public int getDuracaoHoras() { return duracaoHoras; }

}