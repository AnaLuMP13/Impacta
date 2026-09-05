package ClassesAcoes;

import ClassesVoluntarios.SistemaVoluntarios;
import ClassesVoluntarios.Voluntarios;
import Excecoes.AcaoNaoEncontradaException;
import Excecoes.VoluntarioNaoEncontradoException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class SistemaAcoes {
    private Map<Integer, Acoes> acoes; // para guardar as acoes usando oid como chave
    private SistemaVoluntarios sistemaVoluntarios; // para guardar o sistema que tem os voluntarios
    private int proximoId;

    public SistemaAcoes(SistemaVoluntarios sistemaVoluntarios) { // passamos como parametro porque SistemaAcoes precisa procurar voluntarios
        this.sistemaVoluntarios = sistemaVoluntarios;
        this.acoes = new HashMap<>();
        this.proximoId = 1;
    }

    public int cadastrarPlantio(String titulo, String descricao, String data, int maxParticipantes, int qtdMudas) {
        LocalDateTime dt = LocalDateTime.parse(data); // transforma a data no tipo LocalDateTime
        int id = proximoId++;
        Acoes acao = new PlantioDeMudas(id, titulo, descricao, dt, maxParticipantes, qtdMudas);
        acoes.put(id, acao);
        return id;
    }

    public int cadastrarMutirao(String titulo, String descricao, String data, int maxParticipantes, int duracaoHoras) {
        LocalDateTime dt = LocalDateTime.parse(data);
        int id = proximoId++;
        Acoes acao = new MutiraoDeReciclagem(id, titulo, descricao, dt, maxParticipantes, duracaoHoras);
        acoes.put(id, acao);
        return id;
    }

    public int cadastrarOficina(String titulo, String descricao, String data, int maxParticipantes, int duracaoHoras, boolean kitMaterial) {
        LocalDateTime dt = LocalDateTime.parse(data);
        int id = proximoId++;
        Acoes acao = new OficinaEcologica(id, titulo, descricao, dt, maxParticipantes, duracaoHoras, kitMaterial);
        acoes.put(id, acao);
        return id;
    }

    public boolean inscreverVoluntario(String emailVoluntario, int idAcao) {
        Voluntarios voluntarios = sistemaVoluntarios.buscarVoluntario(emailVoluntario);
        if (voluntarios == null) {
            throw new VoluntarioNaoEncontradoException("Voluntário não encontrado.");
        }

        Acoes acao = acoes.get(idAcao);
        if (acao == null) {
            throw new AcaoNaoEncontradaException("Ação não encontrada.");
        }

        return acao.adicionarVoluntario(voluntarios);
    }

    public String exibirDetalhesAcao(int idAcao) {
        Acoes acao = acoes.get(idAcao);
        if (acao == null) {
            throw new IllegalArgumentException("Ação não encontrada.");
        }
        return acao.exibirDetalhes();
    }
}
