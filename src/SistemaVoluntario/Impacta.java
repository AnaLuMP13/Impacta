package SistemaVoluntario;

import Excecoes.*;
import SistemaAcoes.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Impacta {

    private List<Voluntario> voluntarios;
    private List<Acao> acoes;
    private int proximoIdAcao;

    public Impacta() {
        this.voluntarios = new ArrayList<>();
        this.acoes = new ArrayList<>();
        this.proximoIdAcao = 1;
    }

    // metodo auxiliar privado que percorre a lista procurando o voluntario pelo e-mail
    // se encontrar alguem com o mesmo e-mail, devolve o voluntario; se terminar o for sem achar, devolve null
    private Voluntario buscarVoluntario(String email) {
        for (Voluntario v : voluntarios) {
            if (v.getEmail().equals(email)) {
                return v;
            }
        }
        return null;
    }

    // percorre a lista de acoes procurando pelo ID numerico
    private Acao buscarAcao(int id) {
        for (Acao a : acoes) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public boolean cadastrarVoluntario(String nome, String email, String matricula) {
        if (buscarVoluntario(email) != null) {
            throw new EmailDuplicadoException(email);
        }
        voluntarios.add(new Voluntario(nome, email, matricula));
        return true;
    }

    public String exibirVoluntario(String email) {
        Voluntario v = buscarVoluntario(email);
        // se achou o voluntario, chama o toString dele; se nao achou, retorna null como pede o enunciado
        return v != null ? v.toString() : null;
    }

    // gera o ranking com todos os voluntarios ordenados por pontos decrescentes e nome
    public String[] listarVoluntarios() {
        List<Voluntario> ordenados = new ArrayList<>(voluntarios);
        Collections.sort(ordenados);

        // converte a lista ordenada num array de String[] com a mesma quantidade de posicoes
        String[] resultado = new String[ordenados.size()];
        for (int i = 0; i < ordenados.size(); i++) {
            resultado[i] = ordenados.get(i).toString();
        }
        return resultado;
    }

    public int cadastrarPlantio(String titulo, String descricao, String data,
                                int maxParticipantes, int qtdMudas) {
        int id = proximoIdAcao++;
        LocalDateTime dataHora = LocalDateTime.parse(data);
        acoes.add(new PlantioMudas(id, titulo, descricao, dataHora, maxParticipantes, qtdMudas));
        return id;
    }

    public int cadastrarMutirao(String titulo, String descricao, String data,
                                int maxParticipantes, int duracaoHoras) {
        int id = proximoIdAcao++;
        LocalDateTime dataHora = LocalDateTime.parse(data);
        acoes.add(new MutiraoReciclagem(id, titulo, descricao, dataHora, maxParticipantes, duracaoHoras));
        return id;
    }

    public int cadastrarOficina(String titulo, String descricao, String data,
                                int maxParticipantes, int duracaoHoras, boolean kitMaterial) {
        int id = proximoIdAcao++;
        LocalDateTime dataHora = LocalDateTime.parse(data);
        acoes.add(new OficinaEcologica(id, titulo, descricao, dataHora, maxParticipantes, duracaoHoras, kitMaterial));
        return id;
    }

    public boolean inscreverVoluntario(String emailVoluntario, int idAcao) {
        // busca o voluntario e a acao pelas listas
        Voluntario v = buscarVoluntario(emailVoluntario);
        Acao a = buscarAcao(idAcao);

        if (v == null || a == null) {
            return false;
        }

        a.inscrever(v);
        v.adicionarAcao(a);
        return true;
    }

    public String exibirDetalhesAcao(int idAcao) {
        Acao a = buscarAcao(idAcao);
        return a != null ? a.exibirDetalhes() : null;
    }
}
