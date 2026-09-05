package Testes;

import ClassesAcoes.SistemaAcoes;
import ClassesVoluntarios.SistemaVoluntarios;
import Excecoes.AcaoLotadaException;
import Excecoes.AcaoNaoEncontradaException;
import Excecoes.DuplaInscricaoException;
import Excecoes.VoluntarioNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SistemaAcoesTeste {
    private SistemaVoluntarios sistemaVoluntarios;
    private SistemaAcoes sistemaAcoes;

    @BeforeEach
    public void setup() {
        this.sistemaVoluntarios = new SistemaVoluntarios();
        this.sistemaAcoes = new SistemaAcoes(this.sistemaVoluntarios); // passamos sistemaVoluntarios como parametro para conseguir inscrever alguem em uma ação
    }

    @Test
    @DisplayName("Deve calcular a pontuacao da acao e quantidade de acoes")
    public void deveCalcularPontuacaoEInscricaoAcoes() {
        int idPlantio = sistemaAcoes.cadastrarPlantio("Plantio", "Mudas", "2026-10-10T10:00:00", 10, 10);
        int idMutirao = sistemaAcoes.cadastrarMutirao("Mutirão", "Reciclagem", "2026-10-11T10:00:00", 10, 3);
        int idOficina = sistemaAcoes.cadastrarOficina("Oficina", "Papel", "2026-10-12T10:00:00", 10, 2, true);

        sistemaVoluntarios.cadastrarVoluntario("Rebeca", "rebeca@email.com", "12312");

        assertTrue(sistemaAcoes.inscreverVoluntario("rebeca@email.com", idPlantio));
        assertTrue(sistemaAcoes.inscreverVoluntario("rebeca@email.com", idMutirao));
        assertTrue(sistemaAcoes.inscreverVoluntario("rebeca@email.com", idOficina));

        String dadosRebeca = sistemaVoluntarios.exibirVoluntario("rebeca@email.com");
        assertTrue(dadosRebeca.contains("quantidadeAcoes=3"));
    }

    @Test
    @DisplayName("Deve testar se o voluntario nao foi encontrado")
    public void deveLancarExcecaoVoluntarioNaoEncontrado() {
        int idPlantio = sistemaAcoes.cadastrarPlantio("Plantio", "Mudas", "2026-10-10T10:00:00", 10, 10);

        assertThrows(VoluntarioNaoEncontradoException.class, () -> {
            sistemaAcoes.inscreverVoluntario("inexistente@email.com", idPlantio);
        });
    }

    @Test
    @DisplayName("Deve testar se a acao nao foi encontrado")
    public void deveLancarExcecaoAcaoNaoEncontrada() {
        sistemaVoluntarios.cadastrarVoluntario("Renan", "renan@email.com", "98798");

        assertThrows(AcaoNaoEncontradaException.class, () -> {
            sistemaAcoes.inscreverVoluntario("renan@email.com", 9999);
        });
    }

    @Test
    @DisplayName("Deve testar a dupla inscricao")
    public void deveLancarExcecaoInscricaoDupla() {
        int id = sistemaAcoes.cadastrarMutirao("Mutirão", "Praia", "2026-10-10T10:00:00", 5, 2);
        sistemaVoluntarios.cadastrarVoluntario("Laura", "laura@email.com", "123");

        sistemaAcoes.inscreverVoluntario("laura@email.com", id);

        assertThrows(DuplaInscricaoException.class, () -> {
            sistemaAcoes.inscreverVoluntario("laura@email.com", id);
        });
    }

    @Test
    @DisplayName("Deve testar a acao lotada")
    public void deveLancarExcecaoAcaoLotada() {
        int id = sistemaAcoes.cadastrarMutirao("Mutirão Express", "Praia", "2026-10-10T10:00:00", 1, 2);

        sistemaVoluntarios.cadastrarVoluntario("Daniel", "daniel@email.com", "67676");
        sistemaVoluntarios.cadastrarVoluntario("Yane", "yane@email.com", "24680");

        sistemaAcoes.inscreverVoluntario("daniel@email.com", id);

        assertThrows(AcaoLotadaException.class, () -> {
            sistemaAcoes.inscreverVoluntario("yane@email.com", id);
        });
    }

    @Test
    @DisplayName("Deve testar os detatlhes da acao")
    public void deveLancarExibirDetalhesAcao() {
        int id = sistemaAcoes.cadastrarPlantio("Plantio Urbano", "Mudas nativas", "2026-10-10T10:00:00", 5, 10);
        sistemaVoluntarios.cadastrarVoluntario("Evelyn", "evelyn@email.com", "36900");

        sistemaAcoes.inscreverVoluntario("evelyn@email.com", id);

        String detalhes = sistemaAcoes.exibirDetalhesAcao(id);

        assertTrue(detalhes.contains("Plantio Urbano"));
        assertTrue(detalhes.contains("Evelyn"));
    }
}
