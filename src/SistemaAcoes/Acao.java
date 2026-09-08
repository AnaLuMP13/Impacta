package SistemaAcoes;

import Excecoes.*;
import SistemaVoluntario.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// classe mae abstrata
public abstract class Acao {

    private int id;
    private String titulo;
    private String descricao;
    private LocalDateTime data;
    private int maxParticipantes;
    // lista dos alunos voluntarios que estao inscritos nesta acao
    private List<Voluntario> inscritos;

    // construtor
    protected Acao(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.maxParticipantes = maxParticipantes;
        // inicializa a lista de inscritos vazia
        this.inscritos = new ArrayList<>();
    }

    // polimorfismo
    public abstract int calcularPontuacao();

    // metodo abstrato para devolver seus dados exclusivos: qtd de mudas, horas, kit etc
    protected abstract String detalhesEspecificos();

    public void inscrever(Voluntario voluntario) {
        if (inscritos.contains(voluntario)) {
            throw new DuplaInscricaoException(voluntario.getEmail());
        }
        if (inscritos.size() >= maxParticipantes) {
            throw new AcaoLotadaException(id);
        }
        inscritos.add(voluntario);
    }

    public String exibirDetalhes() {
        StringBuilder sb = new StringBuilder();
        sb.append("Titulo: ").append(titulo).append("\n");
        sb.append("Descricao: ").append(descricao).append("\n");
        sb.append("Data: ").append(data).append("\n");
        sb.append(detalhesEspecificos()).append("\n");
        sb.append("Pontuacao: ").append(calcularPontuacao()).append("\n");
        sb.append("Voluntarios inscritos:");

        // lista todos os alunos inscritos ou informa que nao ha nenhum
        if (inscritos.isEmpty()) {
            sb.append(" Nenhum");
        } else {
            for (Voluntario v : inscritos) {
                sb.append("\n- ").append(v.getNome()).append(" (").append(v.getEmail()).append(")");
            }
        }

        return sb.toString();
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public int getMaxParticipantes() {
        return maxParticipantes;
    }

    // retorna a lista de inscritos para consulta
    public List<Voluntario> getInscritos() {
        return inscritos;
    }
}
