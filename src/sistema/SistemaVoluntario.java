package sistema;

import java.util.ArrayList;
// MUDAR NOME???
public class SistemaVoluntario {
    // Listas
    ArrayList<Voluntario> voluntarios;
    ArrayList<Integer> quantidadeAcoes;
    ArrayList<Integer> pontuacoesAcumuladas;

    // Construtor
    public SistemaVoluntario() {
        this.voluntarios = new ArrayList<>();
    }

    // Getters
    public ArrayList<Voluntario> getVoluntarios() {
        return voluntarios;
    }

    // Métodos
    boolean cadastrarVoluntario(String nome, String email, String matricula) throws IllegalArgumentException {
        Voluntario voluntarioNovo = new Voluntario(nome, email, matricula);

        for (Voluntario voluntario : voluntarios) {
            if (voluntario.getEmail().equals(voluntarioNovo.getEmail())) {
                throw new IllegalArgumentException("Erro: Email já está sendo utilizado.");
            }
        }

        this.voluntarios.add(voluntarioNovo);
        return true;
    }

    String exibirVoluntario(String email) throws IllegalArgumentException {
        String retorno = "";

        // CONTINUAR!!!!!!!!!!!!!
        for (Voluntario voluntario : voluntarios) {
            if (voluntario.getEmail().equals(email)) {
                //int posicao = voluntarios.indexOf(voluntario);
                // VALORES TEMPORÁRIOS
                retorno = String.format("Nome: %s\nQuantidade de ações participante: %d\nPontuação acumulada: %e", voluntario.getNome(), 12, 12);
            }
    }
        return retorno;
        }

}