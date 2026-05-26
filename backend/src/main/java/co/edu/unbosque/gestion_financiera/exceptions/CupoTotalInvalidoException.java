package co.edu.unbosque.gestion_financiera.exceptions;

public class CupoTotalInvalidoException extends RuntimeException {
    public CupoTotalInvalidoException(String mensaje) {
        super(mensaje);
    }
}