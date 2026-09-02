package sistema;

import java.time.LocalDateTime;

public class Plantio extends Acao {
    // Objetos
    private int qtdMudas;

    // Construtor
    public Plantio(String titulo, String descricao, LocalDateTime data, int maxParticipantes, int idAcao, int qtdMudas) throws IllegalArgumentException {
        if (titulo.trim().isEmpty() || descricao.trim().isEmpty() || data == null || maxParticipantes <= 0 || idAcao < 0 || idAcao > 3 || qtdMudas <= 0) {
            throw new IllegalArgumentException("Valor inserido inválido.");
        }
        super(titulo, descricao, data, maxParticipantes, idAcao);
        setIdAcao(1);
        this.qtdMudas = qtdMudas;
    }

}