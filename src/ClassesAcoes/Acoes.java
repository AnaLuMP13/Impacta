package ClassesAcoes;

import ClassesVoluntarios.Voluntarios;
import Excecoes.AcaoLotadaException;
import Excecoes.DuplaInscricaoException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Acoes {
    private int id;
    private String titulo;
    private String descricao;
    private LocalDateTime data;
    private int maxParticipantes;
    private List<Voluntarios> voluntarios;

    public Acoes(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.maxParticipantes = maxParticipantes;
        this.voluntarios = new ArrayList<>();
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

    public List<Voluntarios> getVoluntarios() {
        return voluntarios;
    }

    public abstract int calcularPontuacao();

    public boolean adicionarVoluntario(Voluntarios voluntario){
        if(voluntarios.size() >= maxParticipantes) {
            throw new AcaoLotadaException("Capacidade maxima atingida");
        }

        if(voluntarios.contains(voluntario)){
            throw new DuplaInscricaoException("Voluntario ja cadastrado na ação");
        }

        voluntarios.add(voluntario);
        voluntario.adicionarPontuacao(calcularPontuacao());
        return true;
    }

    public String exibirDetalhes() {
        StringBuilder sb = new StringBuilder(); // cria e modifica strings sem gerar novos objetos
        sb.append(String.format("ID: %d\nTítulo: %s\nDescrição: %s\nData: %s\nPontuação da Ação: %d\nInscritos:\n",
                id, titulo, descricao, data.toString(), calcularPontuacao()));

        for (Voluntarios voluntario : voluntarios) {
            sb.append(voluntario.getNome()).append("\n");
        }
        return sb.toString();
    }
}
