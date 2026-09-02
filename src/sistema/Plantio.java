package sistema;

import java.time.LocalDateTime;

public final class Plantio extends Acao {
    // Variável
    private int qtdMudas;

    // Construtor
    public Plantio(String titulo, String descricao, LocalDateTime data, int maxParticipantes, int qtdMudas) throws IllegalArgumentException {
        if (titulo.trim().isEmpty() || descricao.trim().isEmpty() || data == null || maxParticipantes <= 0 || qtdMudas <= 0) {
            throw new IllegalArgumentException("Valor inserido inválido.");
        }
        super(titulo, descricao, data, maxParticipantes);
        this.qtdMudas = qtdMudas;
    }

}