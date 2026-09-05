package testes;

import org.junit.jupiter.api.TestTemplate;
import sistema.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class Testes {

    private Sistema sistema;

    @BeforeEach
    public void setUp() { sistema = new Sistema(); }

    // todo: Voluntários
    // Cadastrar voluntário
    @Test
    @DisplayName("Deve cadastrar voluntário")
    public void deveCadastrarVoluntario() {
        sistema.cadastrarVoluntario("João", "joãoalmeida@unifacisa.com",  "123345");

        assertFalse(sistema.getVoluntarios().isEmpty());
    }

    // Exibir voluntário
    @Test
    @DisplayName("Deve exibir voluntário")
    public void deveExibirVoluntario() {
        sistema.cadastrarVoluntario("Mateus", "mateussilveira@unifacisa.com", "524694");

        assertEquals("Nome: Mateus\nQuantidade de ações participante: 0\nPontuação acumulada: 0", sistema.exibirVoluntario("mateussilveira@unifacisa.com"));
        assertEquals("Email não encontrado.", sistema.exibirVoluntario("errado"));
    }

    // LISTAR VOLUNTÁRIOS

    // todo: AÇÕES
    // Cadastrar plantio
    @Test
    @DisplayName("Deve cadastrar plantio")
    public void deveCadastrarPlantio() {
        LocalDateTime HorarioEvento = LocalDateTime.of(2026, 11, 12, 10, 0);

        sistema.cadastrarPlantio("Plantio 1", "Plantação", HorarioEvento, 1000, 10);

        assertFalse(sistema.getPlantios().isEmpty());
    }

    // Cadastrar mutirão
    @Test
    @DisplayName("Deve cadastrar mutirão")
    public void deveCadastrarMutirao() {
        LocalDateTime HorarioEvento = LocalDateTime.of(2026, 12, 25, 10, 30);

        sistema.cadastrarMutirao("Mutirão 1", "Mutirão", HorarioEvento, 2000, 7);

        assertFalse(sistema.getMutiroes().isEmpty());
    }

    // Cadastrar oficina
    @Test
    @DisplayName("Deve cadastrar oficina")
    public void deveCadastrarOficina() {
        LocalDateTime HorarioEvento = LocalDateTime.of(2026, 12, 25, 12, 10);

        sistema.cadastrarOficina("Oficina 1", "Oficina", HorarioEvento, 1300, 6, true);

        assertFalse(sistema.getOficinas().isEmpty());
    }

    // Inscrever voluntário em ação existente
    @Test
    @DisplayName("Deve inscrever voluntário em uma ação")
    public void deveInscreverVoluntario() {
        LocalDateTime HorarioEvento = LocalDateTime.of(2026, 9, 12, 10, 0);

        sistema.cadastrarVoluntario("Maria", "mariaserafina@unifacisa.com", "233674");
        sistema.cadastrarPlantio("Plantio Novo", "Plantação", HorarioEvento, 1050, 12);

        Voluntario voluntarioNovo = sistema.getVoluntarios().getFirst();
        Plantio plantioNovo = sistema.getPlantios().getFirst();

        assertTrue(sistema.inscreverVoluntario(voluntarioNovo.getEmail(), plantioNovo.getIdAcao()));
        assertFalse(sistema.inscreverVoluntario(voluntarioNovo.getEmail(), plantioNovo.getIdAcao()));
        assertFalse(sistema.inscreverVoluntario("Email errado", plantioNovo.getIdAcao()));

        assertEquals(1, plantioNovo.getParticipantes().size());
        assertEquals(1, voluntarioNovo.getQuantidadeAcoes());
    }

    // Exibir detalhes de uma certa ação
    @Test
    @DisplayName("Deve exibir detalhes de uma ação")
    public void deveExibirDetalhesAcao() {
        LocalDateTime HorarioEvento = LocalDateTime.of(2026, 7, 31, 13, 15);

        sistema.cadastrarMutirao("Mutirão 1", "Mutirão", HorarioEvento, 2000, 7);

        Mutirao mutiraoNovo = sistema.getMutiroes().getFirst();

        assertEquals("Título: Mutirão 1\nDescrição: Mutirão\nData: 2026-07-31T13:15\nPontuação calculada: 28\nLista de inscritos: []\nDuração (horas): 7", sistema.exibirDetalhesAcao(mutiraoNovo.getIdAcao()));
        // TÁ CERTO ESSE CASO?
        assertEquals("ID não encontrado.", sistema.exibirDetalhesAcao(100));
    }

    // todo: ERROS
    // todo: Voluntários
    // Cadastrar voluntário
    @Test
    @DisplayName("Deve lançar erro quando for utilizar um email já cadastrado.")
    public void deveLancarExcecaoEmailInvalido() {
        sistema.cadastrarVoluntario("Mário", "mariosilveira@unifacisa.com", "984623");

        assertThrows(IllegalArgumentException.class, () -> sistema.cadastrarVoluntario("Mário", "mariosilveira@unifacisa.com", "924836"));
    }

    // LISTAR VOLUNTÁRIOS

    // todo: AÇÕES
    // ESSES JÁ BASTAM?
    @Test
    @DisplayName("Deve lançar erro quando algum valor inválido for inserido (em banco ou menor ou igual a 0)")
    public void deveLancarExcecaoValorInvalido() {
        LocalDateTime HorarioEvento = LocalDateTime.of(2026, 3, 11, 14, 20);

        // Cadastro de diferentes ações
        assertThrows(IllegalArgumentException.class, () -> sistema.cadastrarPlantio("", "Plantio", HorarioEvento, 1000, 10));
        assertThrows(IllegalArgumentException.class, () -> sistema.cadastrarPlantio("Plantio Novo", "", HorarioEvento, 1000, 10));
        assertThrows(IllegalArgumentException.class, () -> sistema.cadastrarMutirao("Mutirão 1", "Mutirão", null, 1000, 6));
        assertThrows(IllegalArgumentException.class, () -> sistema.cadastrarOficina("Oficina 1", "Oficina", HorarioEvento, 0, 6, false));
        assertThrows(IllegalArgumentException.class, () -> sistema.cadastrarOficina("Oficina 2", "Oficina", HorarioEvento, 1000, 0, false));

        // Inscrever voluntário em uma ação existente
        sistema.cadastrarMutirao("Mutirão 2", "Mutirão", HorarioEvento, 1200, 7);
        Mutirao mutiraoNovo = sistema.getMutiroes().getFirst();

        assertThrows(IllegalArgumentException.class, () -> sistema.inscreverVoluntario("", mutiraoNovo.getIdAcao()));
    }

    // SEPAREI CORRETAMENTE?
    // Exibir detalhes de uma certa ação
    @Test
    @DisplayName("Deve lançar erro quando o ID inserido for inválido")
    public void deveLancarExcecaoIdInvalido() {
        // PRECISA CADASTRAR UMA AÇÃO?
        LocalDateTime HorarioEvento = LocalDateTime.of(2026, 3, 7, 10, 10);

        sistema.cadastrarOficina("Oficina Nova", "Oficina", HorarioEvento, 900, 6, true);
        Oficina oficinaNova = sistema.getOficinas().getFirst();

        assertThrows(IllegalArgumentException.class, () -> sistema.exibirDetalhesAcao(0));
        assertThrows(IllegalArgumentException.class, () -> sistema.exibirDetalhesAcao(-1));
    }

}