package sistema;

import java.util.ArrayList;

public class Voluntario {
    // Objetos
    private String nome;
    private String email;
    private String matricula;

    // Getters
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getMatricula() { return matricula; }

    // Construtor
    public Voluntario(String nome, String email, String matricula) throws IllegalArgumentException {
        if (nome.trim().isEmpty() || email.trim().isEmpty() || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("Valor inserido inválido.");
        }
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
    }

}