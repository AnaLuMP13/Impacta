package testes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sistema.Voluntario;

public class VoluntarioTestes {
    //@BeforeEach

    // Cadastrar voluntário
    @Test
    @DisplayName("Deve cadastrar voluntário")
public void deveCadastrarVoluntario() {
        Voluntario vol = new Voluntario("João", "joãoalmeida@unifacisa.com", "123345");
    }

}