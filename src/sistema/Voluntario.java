package sistema;

public class Voluntario {
    // Variáveis
    private String nome;
    private String email;
    private String matricula;
    private int quantidadeAcoes;
    private int pontuacaoAcumulada;

    // Getters
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getMatricula() { return matricula; }
    public int getQuantidadeAcoes() { return quantidadeAcoes; }
    public int getPontuacaoAcumulada() { return pontuacaoAcumulada; }

    // Setters
    public void setQuantidadeAcoes(int quantidadeAcoes) { this.quantidadeAcoes = quantidadeAcoes; }
    public void setPontuacaoAcumulada(int pontuacaoAcumulada) { this.pontuacaoAcumulada = pontuacaoAcumulada; }

    // Construtor
    public Voluntario(String nome, String email, String matricula, int quantidadeAcoes, int pontuacaoAcumulada) throws IllegalArgumentException {
        if (nome.trim().isEmpty() || email.trim().isEmpty() || matricula.trim().isEmpty() || quantidadeAcoes < 0 || pontuacaoAcumulada < 0) {
            throw new IllegalArgumentException("Valor inserido inválido.");
        }
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.quantidadeAcoes = quantidadeAcoes;
        this.pontuacaoAcumulada = pontuacaoAcumulada;
    }

}