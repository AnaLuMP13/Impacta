package sistema;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Acao {
    // Contador
    private static int contadorId = 1;

    // Variáveis
    private String titulo;
    private String descricao;
    private LocalDateTime data;
    private int maxParticipantes;
    private List<Voluntario> participantes;
    private int idAcao;

    // Construtor
    public Acao(String titulo, String descricao, LocalDateTime data, int maxParticipantes) throws IllegalArgumentException {
        if (titulo.trim().isEmpty() || descricao.trim().isEmpty() || data == null || maxParticipantes <= 0) {
            throw new IllegalArgumentException("Valor inserido inválido.");
        }
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.maxParticipantes = maxParticipantes;
        this.idAcao = contadorId++;
        this.participantes = new ArrayList<>();


    }

    // Getters
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public LocalDateTime getData() { return data; }
    public int getIdAcao() { return idAcao; }
    public List<Voluntario> getParticipantes() { return participantes; }

}