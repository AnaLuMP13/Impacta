package sistema;

import java.time.LocalDateTime;

public class Oficina extends Acao {
    // Objetos // PRECISA SER AÇÃO??
    private int duracaoHoras;
    private boolean kitMaterial;

    // Construtor
    public Oficina(String titulo, String descricao, LocalDateTime data, int maxParticipantes, int idAcao, int duracaoHoras, boolean kitMaterial) throws IllegalArgumentException {
        if (titulo.trim().isEmpty() || descricao.trim().isEmpty() || data == null || maxParticipantes <= 0 || idAcao < 0 || idAcao > 3 || duracaoHoras <= 0) {
            throw new IllegalArgumentException("Valor inserido inválido.");
        }
        super(titulo, descricao, data, maxParticipantes, idAcao);
        setIdAcao(3);
        this.duracaoHoras = duracaoHoras;
        this.kitMaterial = kitMaterial;
    }

}