package sistema;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
// MUDAR NOME???
public class SistemaVoluntario {
    // Listas
    ArrayList<Voluntario> voluntarios;
    ArrayList<Acao> acoes;
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
    //todo: VOLUNTÁRIOS
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
    }
        return retorno;
        }

        /*String[] listarVoluntarios() {
        Collections.sort(voluntarios, Collections.reverseOrder());
        }*/

    // todo: AÇÕES
    int cadastrarPlantio(String titulo, String descricao, LocalDateTime data, int maxParticipantes, int qtdMudas) throws IllegalArgumentException {
        if (titulo.trim().isEmpty() || descricao.trim().isEmpty() || data == null || maxParticipantes <= 0 || qtdMudas <= 0) {
            throw new IllegalArgumentException("Valor inserido inválido.");
        }

        Plantio plantioNovo = new Plantio(titulo, descricao, data, maxParticipantes, qtdMudas);
        acoes.add(plantioNovo);
        plantios.add(plantioNovo);

        return plantioNovo.getIdAcao();
    }

    int cadastrarMutirao(String titulo, String descricao, LocalDateTime data, int maxParticipantes, int duracaoHoras) throws IllegalArgumentException {
        if (titulo.trim().isEmpty() || descricao.trim().isEmpty() || data == null || maxParticipantes <= 0 || duracaoHoras <= 0) {
            throw new IllegalArgumentException("Valor inserido inválido.");
        }

        Mutirao mutiraoNovo = new Mutirao(titulo, descricao, data, maxParticipantes, duracaoHoras);
        acoes.add(mutiraoNovo);
        mutiroes.add(mutiraoNovo);

        return mutiraoNovo.getIdAcao();
    }

    int cadastrarOficina(String titulo, String descricao, LocalDateTime data, int maxParticipantes, int duracaoHoras, boolean kitMaterial) throws IllegalArgumentException {
        if (titulo.trim().isEmpty() || descricao.trim().isEmpty() || data == null || maxParticipantes <= 0 || duracaoHoras <= 0) {
            throw new IllegalArgumentException("Valor inserido inválido.");
        }

        Oficina oficinaNova = new Oficina(titulo, descricao, data, maxParticipantes, duracaoHoras, kitMaterial);
        acoes.add(oficinaNova);
        oficinas.add(oficinaNova);

        return oficinaNova.getIdAcao();
    }
// ERROS ESTÃO CERTOS? THROWS? todo CHECAR!
    boolean inscreverVoluntario(String emailVoluntario, int idAcao) throws IllegalArgumentException {
        if (emailVoluntario.trim().isEmpty() || idAcao <= 0) {
            throw new IllegalArgumentException("Valor inserido inválido.");
        }
        Acao acao = new Acao("temporario", "temporario", LocalDateTime.now(), 0);
        Voluntario voluntario = new Voluntario("temporario", "temporario", "temporario", 0, 0);

        // CERTO?? 2 acao  X acoes
        for (int i = 0; i < plantios.size(); i++) { if (plantios.get(i).getIdAcao() == idAcao) { acao = plantios.get(i); } }
        for (int i = 0; i < mutiroes.size(); i++) { if (mutiroes.get(i).getIdAcao() == idAcao) { acao = mutiroes.get(i); } }
        for (int i = 0; i < oficinas.size(); i++) { if (oficinas.get(i).getIdAcao() == idAcao) { acao = oficinas.get(i); } }

        for (int i = 0; i < voluntarios.size(); i++) { if (voluntarios.get(i).getEmail().equals(emailVoluntario)) { voluntario = voluntarios.get(i); } }

        acao.getParticipantes().add(voluntario);

        return true;
    }

    //!!!!!!!!!!!!!!!!
    String exibirDetalhesAcao(int idAcao) {
        String retorno = "";
        if (idAcao <= 0) {
            throw new IllegalArgumentException("Erro: ID inserido inválido.");
        }
        for (int i = 0; i < plantios.size(); i++) {
            Plantio plantio = plantios.get(i);

            if (plantio.getIdAcao() == idAcao) {
                int pontuacaoCalculada = 5 + plantio.getQtdMudas() * 2;
                retorno = String.format("Título: %s\nDescrição: %s\nData: %s\nPontuação calculada: %d\nLista de inscritos: %s\nQuantidade de mudas: %d.", plantio.getTitulo(), plantio.getDescricao(), plantio.getData(), pontuacaoCalculada, plantio.getParticipantes(), plantio.getQtdMudas());
            }
        }
            for (int i = 0; i < mutiroes.size(); i++) {
                Mutirao mutirao = mutiroes.get(i);

                if (mutirao.getIdAcao() == idAcao) {
                    int pontuacaoCalculada = mutirao.getDuracaoHoras() * 4;
                    retorno = String.format("Título: %s\nDescrição: %s\nData: %s\nPontuação calculada: %d\nLista de inscritos: %s\nDuração (horas): %d", mutirao.getTitulo(), mutirao.getDescricao(), mutirao.getData(), pontuacaoCalculada, mutirao.getParticipantes(), mutirao.getDuracaoHoras());
                }
            }
                for (int i = 0; i < oficinas.size(); i++) {
                    Oficina oficina = oficinas.get(i);

                    if (oficina.getIdAcao() == idAcao) {
                        int pontuacaoCalculada = oficina.getDuracaoHoras() * 3; // CÁLCULO
                        if (oficina.getKitMaterial()) { pontuacaoCalculada = pontuacaoCalculada + 10; }
                        retorno = String.format("Título: %s\nDescrição: %s\nData: %s\nPontuação calculada: %d\nLista de inscritos: %s\nDuração (horas): %d", oficina.getTitulo(), oficina.getDescricao(), oficina.getData(), pontuacaoCalculada, oficina.getParticipantes(), oficina.getDuracaoHoras());
                    }
                }//HORAS MESMO? // TRIM MESMO? TÁ CERTO? -> (THROW NEW) ERRO?
        if (retorno.trim().isEmpty()) { retorno = "ID não encontrado."; }

        return retorno;
    }

}