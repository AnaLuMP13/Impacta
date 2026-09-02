package sistema;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
// MUDAR NOME???
public class SistemaVoluntario {
    // Listas
    ArrayList<Voluntario> voluntarios;
    ArrayList<Plantio> plantios;
    ArrayList<Mutirao> mutiroes;
    ArrayList<Oficina> oficinas;

    // Construtor
    public SistemaVoluntario() {
        this.voluntarios = new ArrayList<>();
    }

    // Getter
    public ArrayList<Voluntario> getVoluntarios() {
        return voluntarios;
    }

    // Métodos
    //todo: VOLUNTÁRIO
    boolean cadastrarVoluntario(String nome, String email, String matricula) throws IllegalArgumentException {
        Voluntario voluntarioNovo = new Voluntario(nome, email, matricula,0, 0);

        for (Voluntario voluntario : voluntarios) {
            if (voluntario.getEmail().equals(voluntarioNovo.getEmail())) {
                throw new IllegalArgumentException("Erro: email já está sendo utilizado.");
            }
        }

        /*PRECISA DE "THIS"??*/voluntarios.add(voluntarioNovo);
        return true;
    }
    // PRECISA DO "THROWS"?
    String exibirVoluntario(String email) throws IllegalArgumentException {
        String retorno = "";

        for (Voluntario voluntario : voluntarios) {
            if (voluntario.getEmail().equals(email)) {
                retorno = String.format("Nome: %s\nQuantidade de ações participante: %d\nPontuação acumulada: %d", voluntario.getNome(), voluntario.getQuantidadeAcoes(), voluntario.getPontuacaoAcumulada());
            } else { throw new IllegalArgumentException("Erro: email não cadastrado."); }
            //TÁ BEM ORGANIZADO? (em cima)
    }
        return retorno;
        }

        //todo: listarVoluntarios()
    String[] listarVoluntarios() {

    }

    // todo: AÇÕES
    //BOOLEAN? OU ID?
    int cadastrarPlantio(String titulo, String descricao, LocalDateTime data, int maxParticipantes, int qtdMudas) throws IllegalArgumentException {
        if (titulo.trim().isEmpty() || descricao.trim().isEmpty() || data == null || maxParticipantes <= 0 || qtdMudas <= 0) {
            throw new IllegalArgumentException("Valor inserido inválido.");
        }

        Plantio plantioNovo = new Plantio(titulo, descricao, data, maxParticipantes, qtdMudas);
        plantios.add(plantioNovo);

        return plantioNovo.getIdAcao();
    }

    int cadastrarMutirao(String titulo, String descricao, LocalDateTime data, int maxParticipantes, int duracaoHoras) throws IllegalArgumentException {
        if (titulo.trim().isEmpty() || descricao.trim().isEmpty() || data == null || maxParticipantes <= 0 || duracaoHoras <= 0) {
            throw new IllegalArgumentException("Valor inserido inválido.");
        }

        Mutirao mutiraoNovo = new Mutirao(titulo, descricao, data, maxParticipantes, duracaoHoras);
        mutiroes.add(mutiraoNovo);

        return mutiraoNovo.getIdAcao();
    }

    int cadastrarOficina(String titulo, String descricao, LocalDateTime data, int maxParticipantes, int duracaoHoras, boolean kitMaterial) throws IllegalArgumentException {
        if (titulo.trim().isEmpty() || descricao.trim().isEmpty() || data == null || maxParticipantes <= 0 || duracaoHoras <= 0) {
            throw new IllegalArgumentException("Valor inserido inválido.");
        }

        Oficina oficinaNova = new Oficina(titulo, descricao, data, maxParticipantes, duracaoHoras, kitMaterial);
        oficinas.add(oficinaNova);

        return oficinaNova.getIdAcao();
    }

    // todo boolean inccrever voluntario

    // todo exibir detalhe acao


}