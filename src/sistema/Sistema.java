package sistema;

import classes.Mutirao;
import classes.Oficina;
import classes.Plantio;
import classes.Voluntario;

import java.time.LocalDateTime;
import java.util.*;

public class Sistema {
    // Listas
    private ArrayList<Voluntario> voluntarios;
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

    // Getters
    public ArrayList<Voluntario> getVoluntarios() {
        return voluntarios;
    }
    public ArrayList<Plantio> getPlantios() { return plantios; }

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
                retorno = voluntario.toString();
                break;
            } else { retorno = ("Email não encontrado."); }
        }
        return retorno;
    }

    public String listarVoluntarios() {
        voluntarios.sort(Comparator.comparingInt(Voluntario::getPontuacaoAcumulada).reversed().thenComparing(Voluntario::getNome));

        String[] listaNomesOrganizada=  new String[voluntarios.size()];

        for(int i = 0; i < voluntarios.size(); i++){
            listaNomesOrganizada[i] = voluntarios.get(i).getNome();
        }

        return Arrays.toString(listaNomesOrganizada);
    }


    // todo: AÇÕES
    public int cadastrarPlantio(String titulo, String descricao, LocalDateTime data, int maxParticipantes, int qtdMudas) throws IllegalArgumentException {
        if (titulo.trim().isEmpty() || descricao.trim().isEmpty() || data == null || maxParticipantes <= 0 || qtdMudas <= 0) {
            throw new IllegalArgumentException("Valor inserido inválido.");
        }
        Plantio plantioNovo = new Plantio(titulo, descricao, data, maxParticipantes, qtdMudas);

        plantios.add(plantioNovo);

        return plantioNovo.getIdAcao();
    }
/*
    public int cadastrarMutirao(String titulo, String descricao, LocalDateTime data, int maxParticipantes, int duracaoHoras) throws IllegalArgumentException {

        return ...;
    }

    public int cadastrarOficina(String titulo, String descricao, LocalDateTime data, int maxParticipantes, int duracaoHoras, boolean kitMaterial) throws IllegalArgumentException {

        return ...;
    }
*/
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
/*
    public String exibirDetalhesAcao(int idAcao) {

        return ...;
    }
*/
}