package ClassesVoluntarios;

import ClassesAcoes.Acoes;
import Excecoes.*;

import java.util.*;

public class SistemaVoluntarios {
    private Map<String, Voluntarios> participantes; // para guardar os voluntários usando o e-mail como chave
    private Map<Integer, Acoes> acoes; // para guardar os acoes usando o id como chave
    private int proximoIdAcao;

    public SistemaVoluntarios() {
        this.participantes = new HashMap<>();
        this.acoes = new HashMap<>();
        this.proximoIdAcao = 1;
    }

    public boolean cadastrarVoluntario(String nome, String email, String matricula) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new NomeVazioException("Nome não pode ser vazio.");
        }

        if (email == null || email.trim().isEmpty()) {
            throw new EmailVazioException("E-mail não pode ser vazio.");
        }

        if (matricula == null || matricula.trim().isEmpty()) {
            throw new MatriculaVaziaException("Matrícula não pode ser vazia.");
        }

        if (participantes.containsKey(email)) {
            throw new EmailDuplicadoException("E-mail já cadastrado no sistema.");
        }

        participantes.put(email, new Voluntarios(nome, email, matricula));
        return true;
    }

    public String exibirVoluntario(String email) {
        Voluntarios voluntarios = participantes.get(email);
        if (voluntarios == null) {
            throw new VoluntarioNaoEncontradoException("Voluntário não encontrado.");
        }
        return voluntarios.toString();
    }

    public String[] listarVoluntarios() {
        List<Voluntarios> lista = new ArrayList<>(participantes.values());
        Collections.sort(lista); // ordena a lista usando de acordo com o compareTo

        String[] resultado = new String[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            resultado[i] = lista.get(i).toString();
        }
        return resultado;
    }

    public Voluntarios buscarVoluntario(String email) {
        Voluntarios participante = participantes.get(email);
        if (participante == null) {
            throw new VoluntarioNaoEncontradoException("Voluntário não encontrado.");
        }
        return participante;
    }
}
