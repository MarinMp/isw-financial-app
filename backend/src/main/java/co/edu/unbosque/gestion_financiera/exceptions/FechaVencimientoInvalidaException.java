package co.edu.unbosque.gestion_financiera.exceptions;

public class FechaVencimientoInvalidaException extends RuntimeException {
    public FechaVencimientoInvalidaException(String mensaje) {
        super(mensaje);
    }
}