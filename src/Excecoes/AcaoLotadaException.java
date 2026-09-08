package Excecoes;

public class AcaoLotadaException extends RuntimeException {
    public AcaoLotadaException(int idAcao) {
       super("Acao " + idAcao + " atingiu capacidade maxima de participantes.");
    }
}
