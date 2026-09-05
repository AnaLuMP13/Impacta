package ClassesVoluntarios;

import java.util.Objects;

public class Voluntarios implements Comparable<Voluntarios> { // ordena uma lista por determinado atributo
    private String nome;
    private String email;
    private String matricula;
    private int quantidadeAcoes;
    private int pontuacaoImpacto;

    public Voluntarios(String nome, String email, String matricula) {
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.quantidadeAcoes = 0;
        this.pontuacaoImpacto = 0;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getQuantidadeAcoes() {
        return quantidadeAcoes;
    }

    public int getPontuacaoImpacto() {
        return pontuacaoImpacto;
    }

    public void adicionarPontuacao(int pontos){
        pontuacaoImpacto += pontos;
        quantidadeAcoes++;
    }

    @Override
    public int compareTo(Voluntarios o) { // "o" representa o outro voluntario que esta sendo comparado
        if (this.pontuacaoImpacto != o.pontuacaoImpacto) { // "this" representa o voluntario atual
            return Integer.compare(o.pontuacaoImpacto, this.pontuacaoImpacto); // a pontuacao maior aparece primeiro
        }
        return this.nome.compareToIgnoreCase(o.nome); // se os resultados forem iguais, desempata por nome
    }

    public boolean equals(Object o) {
        if (this == o) return true; // verifica se sao o mesmo objeto
        if (!(o instanceof Voluntarios)) return false; // se "o" nao for um objeto do tipo Voluntarios, eles nao sao iguais
        Voluntarios voluntarios = (Voluntarios) o;
        return Objects.equals(email, voluntarios.email);
    }

    public int hashCode() { // dois objetos sao iguais se tiverem o mesmo email
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return   "nome='" + nome + '\'' +
                ", quantidadeAcoes=" + quantidadeAcoes +
                ", pontuacaoImpacto=" + pontuacaoImpacto +
                '}';
    }
}
