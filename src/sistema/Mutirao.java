package sistema;

import java.time.LocalDateTime;

public class Mutirao extends Acao {
    // Objetos
    private int duracaoHoras;

    // Construtor
    public Mutirao(String titulo, String descricao, LocalDateTime data, int maxParticipantes, int idAcao, int duracaoHoras) throws IllegalArgumentException {
        if (titulo.trim().isEmpty() || descricao.trim().isEmpty() || data == null || maxParticipantes <= 0 || idAcao < 0 || idAcao > 3 || duracaoHoras <= 0) {
            throw new IllegalArgumentException("Valor inserido inválido.");
        }
        super(titulo, descricao, data, maxParticipantes, idAcao);
        setIdAcao(2);
        this.duracaoHoras = duracaoHoras;
    }

}