package sistema;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// MUDAR NOME???
public class Sistema {
    // Listas
    private ArrayList<Voluntario> voluntarios;
    //private ArrayList<Acao> acoes = new ArrayList<>();
    private ArrayList<Plantio> plantios;
    private ArrayList<Mutirao> mutiroes;
    private ArrayList<Oficina> oficinas;

    // Construtor
    public Sistema() {
        this.voluntarios = new ArrayList<>();
        this.plantios = new ArrayList<>();
        this.mutiroes = new ArrayList<>();
        this.oficinas = new ArrayList<>();
    }

    // Getter
    public ArrayList<Voluntario> getVoluntarios() {
        return voluntarios;
    }
    //public ArrayList<Acao> getAcoes() { return acoes; }
    public ArrayList<Plantio> getPlantios() { return plantios; }
    public ArrayList<Mutirao> getMutiroes() { return mutiroes; }
    public ArrayList<Oficina> getOficinas() { return oficinas; }

    // Métodos
    //todo: VOLUNTÁRIOS
    public boolean cadastrarVoluntario(String nome, String email, String matricula) throws IllegalArgumentException {
        Voluntario voluntarioNovo = new Voluntario(nome, email, matricula);

        for (Voluntario voluntario : voluntarios) {
            if (voluntario.getEmail().equals(voluntarioNovo.getEmail())) {
                throw new IllegalArgumentException("Erro: email já está sendo utilizado.");
            }
        }
        voluntarios.add(voluntarioNovo);
        return true;
    }

    public String exibirVoluntario(String email) {
        String retorno = "";

        for (Voluntario voluntario : voluntarios) {
            if (voluntario.getEmail().equals(email)) {
                retorno = String.format("Nome: %s\nQuantidade de ações participante: %d\nPontuação acumulada: %d", voluntario.getNome(), voluntario.getQuantidadeAcoes(), voluntario.getPontuacaoAcumulada());
                break;
            } else { retorno = ("Email não encontrado."); }
    }
        return retorno;
        }
    //!!!!!!!!!!!!!!!!
        /*public String[] listarVoluntarios() {
        List.sort(voluntarios),new Comparator<Voluntario>() {
            public int compare(Voluntario v1, Voluntario v2) {
                if (v1.getPontuacaoAcumulada() < v2.getPontuacaoAcumulada()) {
                    return 1;
                }
                if (v1.getPontuacaoAcumulada() > v2.getPontuacaoAcumulada()) {
                    return -1;
                }
                if (v1.getPontuacaoAcumulada() == v2.getPontuacaoAcumulada()) {
                    return v1.getNome().compareToIgnoreCase(v2.getNome());
                }
                return v1.getNome().compareToIgnoreCase(v2.getNome());
            }
        };

        String[] listaOrganizada = voluntarios.sort();

        if  (listaOrganizada == null) {
            listaOrganizada[1] = "Não há voluntários inscritos.";
        }

        return listaOrganizada;
    }*/

    // todo: AÇÕES
    public int cadastrarPlantio(String titulo, String descricao, LocalDateTime data, int maxParticipantes, int qtdMudas) throws IllegalArgumentException {
        if (titulo.trim().isEmpty() || descricao.trim().isEmpty() || data == null || maxParticipantes <= 0 || qtdMudas <= 0) {
            throw new IllegalArgumentException("Valor inserido inválido.");
        }
        Plantio plantioNovo = new Plantio(titulo, descricao, data, maxParticipantes, qtdMudas);

        plantios.add(plantioNovo);

        return plantioNovo.getIdAcao();
    }

    public int cadastrarMutirao(String titulo, String descricao, LocalDateTime data, int maxParticipantes, int duracaoHoras) throws IllegalArgumentException {
        if (titulo.trim().isEmpty() || descricao.trim().isEmpty() || data == null || maxParticipantes <= 0 || duracaoHoras <= 0) {
            throw new IllegalArgumentException("Valor inserido inválido.");
        }
        Mutirao mutiraoNovo = new Mutirao(titulo, descricao, data, maxParticipantes, duracaoHoras);

        mutiroes.add(mutiraoNovo);

        return mutiraoNovo.getIdAcao();
    }

    public int cadastrarOficina(String titulo, String descricao, LocalDateTime data, int maxParticipantes, int duracaoHoras, boolean kitMaterial) throws IllegalArgumentException {
        if (titulo.trim().isEmpty() || descricao.trim().isEmpty() || data == null || maxParticipantes <= 0 || duracaoHoras <= 0) {
            throw new IllegalArgumentException("Valor inserido inválido.");
        }
        Oficina oficinaNova = new Oficina(titulo, descricao, data, maxParticipantes, duracaoHoras, kitMaterial);

        oficinas.add(oficinaNova);

        return oficinaNova.getIdAcao();
    }
// ERROS ESTÃO CERTOS? THROWS? todo CHECAR!
    public boolean inscreverVoluntario(String emailVoluntario, int idAcao) throws IllegalArgumentException {
        if (emailVoluntario.trim().isEmpty() || idAcao <= 0) {
            throw new IllegalArgumentException("Erro: Valor inserido inválido.");
        }

        Voluntario voluntario = new Voluntario("Temporário", "Temporário", "Temporário");
        for (int i = 0; i < voluntarios.size(); i++) {
            if (voluntarios.get(i).getEmail().equals(emailVoluntario)) {
                voluntario = voluntarios.get(i);
                break;
            }
        }
        if (!voluntario.getNome().equals("Temporário")) {
        int pontuacaoAcumulada = voluntario.getPontuacaoAcumulada();

        for (int i = 0; i < plantios.size(); i++) {
            if (plantios.get(i).getIdAcao() == idAcao) {
                Plantio plantio = plantios.get(i);

                if (!plantio.getParticipantes().contains(voluntario)) {
                    pontuacaoAcumulada += 5 + plantio.getQtdMudas() * 2;
                    plantio.getParticipantes().add(voluntario);
                } else { return false; }
                break;
            }
        }
        for (int i = 0; i < mutiroes.size(); i++) {
            if (mutiroes.get(i).getIdAcao() == idAcao) {
                Mutirao mutirao = mutiroes.get(i);

                if (!mutirao.getParticipantes().contains(voluntario)) {
                pontuacaoAcumulada += mutirao.getDuracaoHoras() * 4;
                mutirao.getParticipantes().add(voluntario);
                } else { return false; }
                break;
            }
        }
        for (int i = 0; i < oficinas.size(); i++) {
            if (oficinas.get(i).getIdAcao() == idAcao) {
                Oficina oficina = oficinas.get(i);

                if (!oficina.getParticipantes().contains(voluntario)) {
                pontuacaoAcumulada += oficina.getDuracaoHoras() * 3;
                if (oficina.getKitMaterial()) { pontuacaoAcumulada += 10; }
                oficina.getParticipantes().add(voluntario);
                } else { return false; }
                break;
            }
        }

        voluntario.setQuantidadeAcoes(voluntario.getQuantidadeAcoes() + 1);
        voluntario.setPontuacaoAcumulada(pontuacaoAcumulada);

        return true; } else {
            return false;
        }
    }

    //!!!!!!!!!!!!!!!!
    public String exibirDetalhesAcao(int idAcao) {
        String retorno = "";

        if (idAcao <= 0) {
            throw new IllegalArgumentException("Erro: ID inserido inválido.");
        }
        for (int i = 0; i < plantios.size(); i++) {
            if (plantios.get(i).getIdAcao() == idAcao) {
                Plantio plantio = plantios.get(i);

                int pontuacaoCalculada = 5 + plantio.getQtdMudas() * 2;
                retorno = String.format("Título: %s\nDescrição: %s\nData: %s\nPontuação calculada: %d\nLista de inscritos: %s\nQuantidade de mudas: %d.", plantio.getTitulo(), plantio.getDescricao(), plantio.getData(), pontuacaoCalculada, plantio.getParticipantes(), plantio.getQtdMudas());
                break;
            }
        }
            for (int i = 0; i < mutiroes.size(); i++) {
                if (mutiroes.get(i).getIdAcao() == idAcao) {
                    Mutirao mutirao = mutiroes.get(i);

                    int pontuacaoCalculada = mutirao.getDuracaoHoras() * 4;
                    retorno = String.format("Título: %s\nDescrição: %s\nData: %s\nPontuação calculada: %d\nLista de inscritos: %s\nDuração (horas): %d", mutirao.getTitulo(), mutirao.getDescricao(), mutirao.getData(), pontuacaoCalculada, mutirao.getParticipantes(), mutirao.getDuracaoHoras());
                    break;
                }
            }
                for (int i = 0; i < oficinas.size(); i++) {
                    if (oficinas.get(i).getIdAcao() == idAcao) {
                        Oficina oficina = oficinas.get(i);

                        int pontuacaoCalculada = oficina.getDuracaoHoras() * 3;
                        if (oficina.getKitMaterial()) { pontuacaoCalculada += 10; }
                        retorno = String.format("Título: %s\nDescrição: %s\nData: %s\nPontuação calculada: %d\nLista de inscritos: %s\nDuração (horas): %d", oficina.getTitulo(), oficina.getDescricao(), oficina.getData(), pontuacaoCalculada, oficina.getParticipantes(), oficina.getDuracaoHoras());
                        break;
                    }
                }

        if (retorno.trim().isEmpty()) { retorno = "ID não encontrado."; }

        return retorno;
    }

}