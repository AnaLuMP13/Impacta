package SistemaVoluntario;

public class Voluntario {
    private String nome;
    private String email;
    private String matricula;
    private int qtdAcoes;
    private int pontuacaoAcumulada;

    // construtor
    public Voluntario(String nome, String email, String matricula) {
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.qtdAcoes = 0;
        pontuacaoAcumulada = 0;
    }

    // String para exibirVoluntario()
    @Override
    public String toString() {
        return nome + "\n- Ações cadastradas: " + qtdAcoes + "\n- Pontuação acumulada: " + pontuacaoAcumulada;
    }

    // getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getQtdAcoes() {
        return qtdAcoes;
    }

    public void setQtdAcoes(int qtdAcoes) {
        this.qtdAcoes = qtdAcoes;
    }

    public int getPontuacaoAcumulada() {
        return pontuacaoAcumulada;
    }

    public void setPontuacaoAcumulada(int pontuacaoAcumulada) {
        this.pontuacaoAcumulada = pontuacaoAcumulada;
    }
}
