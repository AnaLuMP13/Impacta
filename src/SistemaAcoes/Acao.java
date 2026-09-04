package SistemaAcoes;

import SistemaVoluntario.Voluntario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Acao {
    private int id;
    private String titulo;
    private String descricao;
    private LocalDateTime data;
    private int maxParticipantes;
    private List<Voluntario> inscritos = new ArrayList<>();

    // Construtor
    public Acao(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.maxParticipantes = maxParticipantes;
    }

    // Metodo para todas as classes
    public abstract int calcularPontuacao();

    public boolean vagaSuficiente() {
        return inscritos.size() < maxParticipantes;
    }

    public boolean voluntarioInscrito(String email) {
        for (Voluntario voluntarioCadastrado : inscritos) {
            if (voluntarioCadastrado.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public int getMaxParticipantes() {
        return maxParticipantes;
    }

    public void setMaxParticipantes(int maxParticipantes) {
        this.maxParticipantes = maxParticipantes;
    }
}
