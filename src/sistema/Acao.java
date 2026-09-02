package sistema;

import java.time.LocalDateTime;

public class Acao {
    // Objetos
    private String titulo;
    private String descricao;
    private LocalDateTime data;
    private int maxParticipantes;
    private int idAcao; // PRECISA SER SÓ 3, NO TOTAL?

    // Construtor // ERRO DO ID TÁ CERTO?
    public Acao(String titulo, String descricao, LocalDateTime data, int maxParticipantes, int idAcao) throws IllegalArgumentException {
        if (titulo.trim().isEmpty() || descricao.trim().isEmpty() || data == null || maxParticipantes <= 0 || idAcao < 0 || idAcao > 3) {
            throw new IllegalArgumentException("Valor inserido inválido.");
        }
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.maxParticipantes = maxParticipantes;
        this.idAcao = idAcao;
    }

    // Getters
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public LocalDateTime getData() { return data; }
    public int getMaxParticipantes() { return maxParticipantes; }
    public int getIdAcao() { return idAcao; }

    // Setter
    public void setIdAcao(int idAcao) { this.idAcao = idAcao; }

}