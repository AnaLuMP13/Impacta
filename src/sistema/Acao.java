package sistema;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
// TODO ABSTRATO??
public class Acao {
    // todo checar
    // Contador
    private static int contadorId = 0;

    // Variáveis //ARRAYLIST OU LIST?
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

    // Getters // DEIXA SÓ OS USADOS?
    public static int getContadorId() { return contadorId; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public LocalDateTime getData() { return data; }
    public int getMaxParticipantes() { return maxParticipantes; }
    public int getIdAcao() { return idAcao; }
    public List<Voluntario> getParticipantes() { return participantes; }

    // Setter
    public void setIdAcao(int idAcao) { this.idAcao = idAcao; }

}