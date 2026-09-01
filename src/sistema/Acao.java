package sistema;

import java.time.LocalDateTime;

public class Acao {
    // Objetos
    private String titulo;
    private String descricao;
    private LocalDateTime data;
    private int maxParticipantes;

    // Construtor
    public Acao(String titulo, String descricao, LocalDateTime data, int maxParticipantes) throws IllegalArgumentException {
        if (titulo.trim().isEmpty() || descricao.trim().isEmpty() || data == null || maxParticipantes <= 0) {
            throw new IllegalArgumentException("Valor inserido inválido.");
        }
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.maxParticipantes = maxParticipantes;
    }

    // Getters
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public LocalDateTime getData() { return data; }
    public int getMaxParticipantes() { return maxParticipantes; }

}