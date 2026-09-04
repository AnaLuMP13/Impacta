package SistemaVoluntario;
import SistemaAcoes.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

public class Impacta {
    private List<Voluntario> voluntarios = new ArrayList<>();
    private List<Acao> acoes = new ArrayList<>();
    private int proximoId = 1;
    private static final DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public boolean cadastrarVoluntario(String nome, String email, String matricula) {
        for (Voluntario voluntarioExistente : voluntarios) {
            if (voluntarioExistente.getEmail().equalsIgnoreCase(email)) {
                return false; // Email ja foi cadastrado
            }
        }
        voluntarios.add(new Voluntario(nome, email, matricula));
        return true;
    }

    public String exibirVoluntario(String email) {
        for (Voluntario voluntarioExistente : voluntarios) {
            if (voluntarioExistente.getEmail().equalsIgnoreCase(email)) {
                return voluntarioExistente.toString(); // ToString da classe Voluntario
            }
        }
        return null;
    }

    /*
    fazer listarVoluntario
     */

    // Cadastro das ações
    public int cadastrarPlantio(String titulo, String descricao, String data,
                                int maxParticipantes, int qtdMudas) {
        LocalDateTime dataConvertida = LocalDateTime.parse(data, dataFormatada);
        Plantio p = new Plantio(proximoId, titulo, descricao, dataConvertida, maxParticipantes, qtdMudas);
        acoes.add(p);
        return proximoId++;
    }

    public int cadastrarMutirao(String titulo, String descricao, String data,
                                int maxParticipantes, int duracaoHoras) {
        LocalDateTime dataConvertida = LocalDateTime.parse(data, dataFormatada);
        MutiraoReciclagem m = new MutiraoReciclagem(proximoId, titulo, descricao, dataConvertida, maxParticipantes, duracaoHoras);
        acoes.add(m);
        return proximoId++;
    }

    public int cadastrarOficina(String titulo, String descricao, String data,
                                int maxParticipantes, int duracaoHoras, boolean kitMaterial) {
        LocalDateTime dataConvertida = LocalDateTime.parse(data, dataFormatada);
        OficinaEcologica o = new OficinaEcologica(proximoId, titulo, descricao, dataConvertida, maxParticipantes, duracaoHoras, kitMaterial);
        acoes.add(o);
        return proximoId++;
    }


}
