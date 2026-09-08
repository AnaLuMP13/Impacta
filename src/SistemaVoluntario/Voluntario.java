package SistemaVoluntario;

import SistemaAcoes.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// a classe implementa Comparable para que o Java saiba ordenar o ranking de voluntarios
public class Voluntario implements Comparable<Voluntario> {

    private String nome;
    private String email;
    private String matricula;
    // lista com as acoes que o voluntario especifico esta participando
    private List<SistemaAcoes.Acao> acoesInscritas;

    public Voluntario(String nome, String email, String matricula) {
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        // lista de acoes vazia ao criar o voluntario
        this.acoesInscritas = new ArrayList<>();
    }

    // adiciona uma acao na lista do historico do voluntario
    public void adicionarAcao(SistemaAcoes.Acao acao) {
        acoesInscritas.add(acao);
    }

    public int calcularPontuacao() {
        int total = 0;
        for (SistemaAcoes.Acao acao : acoesInscritas) {
            total += acao.calcularPontuacao();
        }
        return total;
    }

    // devolve a quantidade total de acoes em que o voluntario esta inscrito
    public int getQuantidadeAcoes() {
        return acoesInscritas.size();
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getMatricula() { return matricula; }

    // metodo obrigatorio da interface Comparable para definir a regra de ordenacao
    @Override
    public int compareTo(Voluntario outro) {
        // comparamos o outro com this para inverter a ordem normal e fazer do maior pro menor (decrescente)
        int cmp = Integer.compare(outro.calcularPontuacao(), this.calcularPontuacao());
        // se as pontuacoes forem diferentes, ja temos quem fica na frente no ranking
        if (cmp != 0) {
            return cmp;
        }
        // se as pontuacoes forem iguais (empate), desempata por ordem alfabetica do nome
        return this.nome.compareToIgnoreCase(outro.nome);
    }

    // o equals define quando dois voluntarios sao considerados iguais
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Voluntario outro = (Voluntario) obj;
        // dois voluntarios sao iguais se tiverem o mesmo e-mail (chave unica)
        return Objects.equals(email, outro.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    // exibe os detalhes do voluntario com nome, e-mail, matricula, total de acoes e pontuacao acumulada
    @Override
    public String toString() {
        return nome + " (E-mail: " + email + ", Matricula: " + matricula
                + ", Acoes: " + getQuantidadeAcoes()
                + ", Pontuacao: " + calcularPontuacao() + ")";
    }
}
