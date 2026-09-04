package testes;

import sistema.Sistema;
import sistema.Voluntario;
import sistema.Acao;
import sistema.Plantio;
import sistema.Mutirao;
import sistema.Oficina;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class VoluntarioTestes {

    private Sistema sistema;

    @BeforeEach
    public void setUp() {
        sistema = new Sistema();
    }

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
        Voluntario voluntarioNovo1 = new Voluntario("Mateus", "mateusmoraes@unifacisa.com", "524694");
        Voluntario voluntarioNovo2 = new Voluntario("maria", "mariaserafina@unifacisa.com", "592294");

        assertEquals("Nome: Mateus\nQuantidade de ações participante: 0\nPontuação acumulada: 0", sistema.exibirVoluntario("mateusmoraes@unifacisa.com"));
        assertEquals("Email não encontrado.", sistema.exibirVoluntario("errado"));
    }

    // LISTAR VOLUNTÁRIOS

    // todo: AÇÕES
    // Cadastrar plantio
    @Test
    @DisplayName("Deve cadastrar plantio")
    public void deveCadastrarPlantio() {
        sistema.cadastrarPlantio("Plantio 1", "Plantação", LocalDateTime.now(), 1000, 10);

        assertFalse(sistema.getPlantios().isEmpty());
    }

    // Cadastrar mutirão
    @Test
    @DisplayName("Deve cadastrar mutirão")
    public void deveCadastrarMutirao() {
        sistema.cadastrarMutirao("Mutirão 1", "Mutirão", LocalDateTime.now(), 2000, 7);

        assertFalse(sistema.getMutiroes().isEmpty());
    }

    // Cadastrar oficina
    @Test
    @DisplayName("Deve cadastrar oficina")
    public void deveCadastrarOficina() {
        sistema.cadastrarOficina("Oficina 1", "Oficina", LocalDateTime.now(), 1300, 6, true);

        assertFalse(sistema.getOficinas().isEmpty());
    }

    @Test
    @DisplayName("Deve inscrever voluntário em uma ação")
    public void deveInscreverVoluntario() {
        Voluntario voluntarioNovo = new Voluntario("Maria", "mariaserafina@unifacisa.com", "233674");
        Plantio plantioNovo = new Plantio("Plantio Novo", "Plantação", LocalDateTime.now(), 1050, 12);

        sistema.inscreverVoluntario("mariaserafina@unifacisa.com", 1);

        // PODE JUNTAR?
        //assertEquals(1, plantioNovo.getParticipantes().size());
        //assertEquals(1, voluntarioNovo.getQuantidadeAcoes());
        assertEquals(1, plantioNovo.getParticipantes().size(), voluntarioNovo.getQuantidadeAcoes());
    }

    @Test
    @DisplayName("Deve exibir detalhes de uma ação")
    public void deveExibirDetalhesAcao() {
        Mutirao mutiraoNovo = new Mutirao("Mutirão Novo", "Mutirão", LocalDateTime.now(), 1520, 6);

        assertEquals("Título: Mutirão Novo\nDescrição: Mutirão\nData: %s\nPontuação calculada: %d\nLista de inscritos: %s\nDuração (horas): %d", sistema.exibirDetalhesAcao(1));
    }

}