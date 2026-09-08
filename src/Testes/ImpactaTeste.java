package Testes;

import Excecoes.*;

import SistemaVoluntario.Impacta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ImpactaTeste {

    private Impacta sistema;

    @BeforeEach
    void setUp() {
        sistema = new Impacta();
    }

    @Test
    @DisplayName("Plantio de Mudas: 5 + 2 * qtdMudas")
    void testPontuacaoPlantio() {
        sistema.cadastrarVoluntario("Ada Lovelace", "ada@infosec.org", "1337");
        int id = sistema.cadastrarPlantio("Plantio A", "Descricao", "2025-06-01T09:00:00", 10, 20);

        sistema.inscreverVoluntario("ada@infosec.org", id);
        String detalhes = sistema.exibirDetalhesAcao(id);

        // calculo esperado: 5 pontos base + (2 * 20 mudas) = 45 pontos
        assertTrue(detalhes.contains("Pontuacao: 45"));
    }

    @Test
    @DisplayName("Plantio com 0 mudas retorna apenas a base")
    void testPontuacaoPlantioZeroMudas() {
        sistema.cadastrarVoluntario("Ada Lovelace", "ada@infosec.org", "1337");
        int id = sistema.cadastrarPlantio("Plantio B", "Descricao", "2025-06-01T09:00:00", 10, 0);

        sistema.inscreverVoluntario("ada@infosec.org", id);
        String detalhes = sistema.exibirDetalhesAcao(id);

        // caso limite: com zero mudas, deve receber apenas os 5 pontos base
        assertTrue(detalhes.contains("Pontuacao: 5"));
    }

    @Test
    @DisplayName("Mutirao de Reciclagem: 4 * duracaoHoras")
    void testPontuacaoMutirao() {
        sistema.cadastrarVoluntario("Bruce Schneier", "bruce@infosec.org", "2600");
        int id = sistema.cadastrarMutirao("Mutirao A", "Descricao", "2025-07-10T08:00:00", 15, 6);

        sistema.inscreverVoluntario("bruce@infosec.org", id);
        String detalhes = sistema.exibirDetalhesAcao(id);

        // calculo esperado: 4 pontos por hora * 6 horas = 24 pontos
        assertTrue(detalhes.contains("Pontuacao: 24"));
    }

    @Test
    @DisplayName("Oficina Ecologica com kit: 3 * duracaoHoras + 10")
    void testPontuacaoOficinaComKit() {
        sistema.cadastrarVoluntario("Claude Shannon", "shannon@infosec.org", "1948");
        int id = sistema.cadastrarOficina("Oficina A", "Descricao", "2025-08-20T14:00:00", 20, 4, true);

        sistema.inscreverVoluntario("shannon@infosec.org", id);
        String detalhes = sistema.exibirDetalhesAcao(id);

        // calculo esperado: (3 * 4 horas) + 10 pontos de bonus do kit = 22 pontos
        assertTrue(detalhes.contains("Pontuacao: 22"));
    }

    @Test
    @DisplayName("Oficina Ecologica sem kit: 3 * duracaoHoras")
    void testPontuacaoOficinaSemKit() {
        sistema.cadastrarVoluntario("Kevin Mitnick", "kevin@infosec.org", "1963");
        int id = sistema.cadastrarOficina("Oficina B", "Descricao", "2025-09-15T10:00:00", 20, 4, false);

        sistema.inscreverVoluntario("kevin@infosec.org", id);
        String detalhes = sistema.exibirDetalhesAcao(id);

        // calculo esperado sem kit: apenas 3 * 4 horas = 12 pontos (sem os 10 do kit)
        assertTrue(detalhes.contains("Pontuacao: 12"));
    }

    @Test
    @DisplayName("Ordenacao decrescente por pontuacao")
    void testOrdenacaoPorPontuacao() {
        sistema.cadastrarVoluntario("Ada Lovelace", "ada@infosec.org", "1337");
        sistema.cadastrarVoluntario("Bruce Schneier", "bruce@infosec.org", "2600");

        int idPlantio = sistema.cadastrarPlantio("Plantio", "Descricao", "2025-06-01T09:00:00", 10, 20);
        int idMutirao = sistema.cadastrarMutirao("Mutirao", "Descricao", "2025-07-10T08:00:00", 10, 3);

        sistema.inscreverVoluntario("ada@infosec.org", idPlantio);
        sistema.inscreverVoluntario("bruce@infosec.org", idMutirao);

        String[] ranking = sistema.listarVoluntarios();
        // Ada fez 45 pontos e Bruce fez 12 pontos, entao Ada deve ficar em 1º lugar
        assertEquals(2, ranking.length);
        assertTrue(ranking[0].contains("Ada Lovelace"));
        assertTrue(ranking[1].contains("Bruce Schneier"));
    }

    @Test
    @DisplayName("Desempate alfabetico quando pontuacao e igual")
    void testDesempateAlfabetico() {
        sistema.cadastrarVoluntario("Claude Shannon", "shannon@infosec.org", "1948");
        sistema.cadastrarVoluntario("Ada Lovelace", "ada@infosec.org", "1337");
        sistema.cadastrarVoluntario("Bruce Schneier", "bruce@infosec.org", "2600");

        int idMutirao = sistema.cadastrarMutirao("Mutirao", "Descricao", "2025-07-10T08:00:00", 10, 5);

        // todos se inscrevem no mesmo mutirao e ficam empatados com 20 pontos
        sistema.inscreverVoluntario("shannon@infosec.org", idMutirao);
        sistema.inscreverVoluntario("ada@infosec.org", idMutirao);
        sistema.inscreverVoluntario("bruce@infosec.org", idMutirao);

        String[] ranking = sistema.listarVoluntarios();
        // como empatou nos pontos, o compareTo deve ordenar por ordem alfabetica (Ada -> Bruce -> Claude)
        assertEquals(3, ranking.length);
        assertTrue(ranking[0].contains("Ada Lovelace"));
        assertTrue(ranking[1].contains("Bruce Schneier"));
        assertTrue(ranking[2].contains("Claude Shannon"));
    }

    @Test
    @DisplayName("Voluntario sem acoes aparece no ranking com pontuacao 0")
    void testVoluntarioSemAcoes() {
        sistema.cadastrarVoluntario("Ada Lovelace", "ada@infosec.org", "1337");
        sistema.cadastrarVoluntario("Bruce Schneier", "bruce@infosec.org", "2600");

        int idPlantio = sistema.cadastrarPlantio("Plantio", "Descricao", "2025-06-01T09:00:00", 10, 10);
        sistema.inscreverVoluntario("ada@infosec.org", idPlantio);

        String[] ranking = sistema.listarVoluntarios();
        // Bruce nao se inscreveu em nada, entao deve aparecer no ranking com 0 pontos acumulados
        assertEquals(2, ranking.length);
        assertTrue(ranking[0].contains("Ada Lovelace"));
        assertTrue(ranking[1].contains("Bruce Schneier"));
        assertTrue(ranking[1].contains("Pontuacao: 0"));
    }

    @Test
    @DisplayName("Lista vazia quando nao ha voluntarios")
    void testRankingListaVazia() {
        String[] ranking = sistema.listarVoluntarios();
        // se o sistema acabou de iniciar e nao cadastrou ninguem, o array do ranking vem com tamanho 0
        assertEquals(0, ranking.length);
    }

    @Test
    @DisplayName("Email duplicado lanca EmailDuplicadoException")
    void testEmailDuplicado() {
        sistema.cadastrarVoluntario("Ada Lovelace", "ada@infosec.org", "1337");

        // valida que o sistema barra o segundo cadastro com o mesmo e-mail jogando a excecao
        assertThrows(EmailDuplicadoException.class, () ->
                sistema.cadastrarVoluntario("Clone Ada", "ada@infosec.org", "9999"));
    }

    @Test
    @DisplayName("Acao lotada lanca AcaoLotadaException")
    void testAcaoLotada() {
        sistema.cadastrarVoluntario("Ada Lovelace", "ada@infosec.org", "1337");
        sistema.cadastrarVoluntario("Bruce Schneier", "bruce@infosec.org", "2600");
        sistema.cadastrarVoluntario("Claude Shannon", "shannon@infosec.org", "1948");

        // cria plantio com limite maximo de apenas 2 participantes
        int idPlantio = sistema.cadastrarPlantio("Plantio", "Descricao", "2025-06-01T09:00:00", 2, 10);

        sistema.inscreverVoluntario("ada@infosec.org", idPlantio);
        sistema.inscreverVoluntario("bruce@infosec.org", idPlantio);

        // a acao ja atingiu o limite de 2 vagas, entao o terceiro inscrito deve ser barrado com AcaoLotadaException
        assertThrows(AcaoLotadaException.class, () ->
                sistema.inscreverVoluntario("shannon@infosec.org", idPlantio));
    }

    @Test
    @DisplayName("Dupla inscricao lanca DuplaInscricaoException")
    void testDuplaInscricao() {
        sistema.cadastrarVoluntario("Ada Lovelace", "ada@infosec.org", "1337");
        int idPlantio = sistema.cadastrarPlantio("Plantio", "Descricao", "2025-06-01T09:00:00", 10, 10);

        sistema.inscreverVoluntario("ada@infosec.org", idPlantio);

        // tentar inscrever a mesma pessoa pela segunda vez na mesma acao deve jogar DuplaInscricaoException
        assertThrows(DuplaInscricaoException.class, () ->
                sistema.inscreverVoluntario("ada@infosec.org", idPlantio));
    }

    @Test
    @DisplayName("Busca por email inexistente retorna null")
    void testVoluntarioNaoEncontrado() {
        // buscar por um e-mail nao cadastrado deve retornar null como pede a especificacao
        assertNull(sistema.exibirVoluntario("fantasma@infosec.org"));
    }

    @Test
    @DisplayName("Busca por ID de acao inexistente retorna null")
    void testAcaoNaoEncontrada() {
        // buscar por um ID de acao inexistente deve retornar null
        assertNull(sistema.exibirDetalhesAcao(999));
    }

    @Test
    @DisplayName("Inscricao com voluntario ou acao inexistente retorna false")
    void testInscricaoEntidadeInexistente() {
        sistema.cadastrarVoluntario("Ada Lovelace", "ada@infosec.org", "1337");
        int id = sistema.cadastrarPlantio("Plantio", "Descricao", "2025-06-01T09:00:00", 10, 5);

        // se o voluntario ou a acao nao existirem, a inscricao deve falhar devolvendo false
        assertFalse(sistema.inscreverVoluntario("fantasma@infosec.org", id));
        assertFalse(sistema.inscreverVoluntario("ada@infosec.org", 999));
    }

    @Test
    @DisplayName("Pontuacao acumulada reflete multiplas inscricoes")
    void testPontuacaoAcumulada() {
        sistema.cadastrarVoluntario("Ada Lovelace", "ada@infosec.org", "1337");

        int idPlantio = sistema.cadastrarPlantio("Plantio", "Descricao", "2025-06-01T09:00:00", 10, 10);
        int idMutirao = sistema.cadastrarMutirao("Mutirao", "Descricao", "2025-07-10T08:00:00", 10, 5);

        sistema.inscreverVoluntario("ada@infosec.org", idPlantio);
        sistema.inscreverVoluntario("ada@infosec.org", idMutirao);

        String dados = sistema.exibirVoluntario("ada@infosec.org");
        // soma dos pontos: 25 do plantio (5 + 2*10) + 20 do mutirao (4*5) = 45 pontos no total
        assertTrue(dados.contains("Pontuacao: 45"));
        assertTrue(dados.contains("Acoes: 2"));
    }

    @Test
    @DisplayName("IDs gerados sequencialmente a partir de 1")
    void testIdsSequenciais() {
        // confere se o contador sequencial da fachada gera os IDs 1, 2 e 3 na ordem de cadastro
        int id1 = sistema.cadastrarPlantio("Plantio", "Descricao", "2025-01-01T00:00:00", 5, 1);
        int id2 = sistema.cadastrarMutirao("Mutirao", "Descricao", "2025-01-01T00:00:00", 5, 1);
        int id3 = sistema.cadastrarOficina("Oficina", "Descricao", "2025-01-01T00:00:00", 5, 1, false);

        assertEquals(1, id1);
        assertEquals(2, id2);
        assertEquals(3, id3);
    }

    @Test
    @DisplayName("Detalhes da acao exibem voluntarios inscritos")
    void testDetalhesComInscritos() {
        sistema.cadastrarVoluntario("Ada Lovelace", "ada@infosec.org", "1337");
        sistema.cadastrarVoluntario("Bruce Schneier", "bruce@infosec.org", "2600");

        int id = sistema.cadastrarPlantio("Plantio Verde", "Plantio em parque",
                "2025-06-01T09:00:00", 10, 15);
        sistema.inscreverVoluntario("ada@infosec.org", id);
        sistema.inscreverVoluntario("bruce@infosec.org", id);

        String detalhes = sistema.exibirDetalhesAcao(id);
        // valida que os dados da acao e a lista com o nome dos dois inscritos aparecem no texto formatado
        assertTrue(detalhes.contains("Plantio Verde"));
        assertTrue(detalhes.contains("Plantio em parque"));
        assertTrue(detalhes.contains("Quantidade de Mudas: 15"));
        assertTrue(detalhes.contains("Ada Lovelace"));
        assertTrue(detalhes.contains("Bruce Schneier"));
    }

    @Test
    @DisplayName("Cadastro retorna true para voluntario valido")
    void testCadastroRetornaTrue() {
        // cadastro realizado com sucesso deve retornar true confirmando a operacao
        assertTrue(sistema.cadastrarVoluntario("Ada Lovelace", "ada@infosec.org", "1337"));
    }

    @Test
    @DisplayName("Inscricao retorna true para inscricao valida")
    void testInscricaoRetornaTrue() {
        sistema.cadastrarVoluntario("Ada Lovelace", "ada@infosec.org", "1337");
        int id = sistema.cadastrarPlantio("Plantio", "Descricao", "2025-06-01T09:00:00", 10, 5);
        // inscricao bem-sucedida deve retornar true
        assertTrue(sistema.inscreverVoluntario("ada@infosec.org", id));
    }
}