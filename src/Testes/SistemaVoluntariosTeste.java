package Testes;

import ClassesAcoes.SistemaAcoes;
import ClassesVoluntarios.SistemaVoluntarios;
import Excecoes.EmailDuplicadoException;
import Excecoes.EmailVazioException;
import Excecoes.MatriculaVaziaException;
import Excecoes.NomeVazioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SistemaVoluntariosTeste {
    private SistemaVoluntarios sistemaVoluntarios;
    private SistemaAcoes sistemaAcoes;

    @BeforeEach
    public void setup() {
        this.sistemaVoluntarios = new SistemaVoluntarios();
        this.sistemaAcoes = new SistemaAcoes(this.sistemaVoluntarios); // passamos sistemaVoluntarios como parametro para conseguir inscrever alguem em uma ação
    }

    @Test
    @DisplayName("Deve testar o cadastro de voluntario com sucesso")
    public void deveCadastrarVoluntarioComSucesso() {
        boolean cadastrado = sistemaVoluntarios.cadastrarVoluntario("Maria", "maria@email.com", "12345");
        assertTrue(cadastrado);

        String dados = sistemaVoluntarios.exibirVoluntario("maria@email.com");
        assertTrue(dados.contains("Maria"));
    }

    @Test
    @DisplayName("Deve testar o nome vazio")
    public void deveLancarExcecaoNomeVazio() {
        assertThrows(NomeVazioException.class, () -> {
            sistemaVoluntarios.cadastrarVoluntario("", "gustavo@email.com", "67890");
        });

        assertThrows(NomeVazioException.class, () -> {
            sistemaVoluntarios.cadastrarVoluntario(null, "gustavo@email.com", "67890");
        });
    }

    @Test
    @DisplayName("Deve testar o email vazio")
    public void deveLancarExcecaoEmailVazio() {
        assertThrows(EmailVazioException.class, () -> {
            sistemaVoluntarios.cadastrarVoluntario("Carlos", "", "12121");
        });

        assertThrows(EmailVazioException.class, () -> {
            sistemaVoluntarios.cadastrarVoluntario("Carlos", null, "12121");
        });
    }

    @Test
    @DisplayName("Deve testar a matricula vazia")
    public void deveLancarExcecaoMatriculaVazia() {
        assertThrows(MatriculaVaziaException.class, () -> {
            sistemaVoluntarios.cadastrarVoluntario("Fernanda", "fernanda@email.com", "   ");
        });

        assertThrows(MatriculaVaziaException.class, () -> {
            sistemaVoluntarios.cadastrarVoluntario("Fernanda", "fernanda@email.com", null);
        });
    }

    @Test
    @DisplayName("Deve testar o email duplicado")
    public void deveLancarExcecaoEmailDuplicado() {
        sistemaVoluntarios.cadastrarVoluntario("João", "joao@email.com", "11111");

        assertThrows(EmailDuplicadoException.class, () -> {
            sistemaVoluntarios.cadastrarVoluntario("João Silva", "joao@email.com", "22222");
        });
    }

    @Test
    @DisplayName("Deve testar a ordem de voluntarios")
    public void deveOrdenarRankingVoluntarios() {
        sistemaVoluntarios.cadastrarVoluntario("Bruno", "bruno@email.com", "13");
        sistemaVoluntarios.cadastrarVoluntario("Alice", "alice@email.com", "22");
        sistemaVoluntarios.cadastrarVoluntario("Jose", "jose@email.com", "31");

        int idPlantio = sistemaAcoes.cadastrarPlantio("Plantio", "Mudas", "2026-10-10T10:00:00", 10, 10);

        sistemaAcoes.inscreverVoluntario("jose@email.com", idPlantio);
        sistemaAcoes.inscreverVoluntario("alice@email.com", idPlantio);

        String[] ranking = sistemaVoluntarios.listarVoluntarios();

        assertTrue(ranking[0].contains("Alice"));
        assertTrue(ranking[1].contains("Jose"));
        assertTrue(ranking[2].contains("Bruno"));
    }
}
