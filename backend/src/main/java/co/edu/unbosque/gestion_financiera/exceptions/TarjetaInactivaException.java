package co.edu.unbosque.gestion_financiera.exceptions;

public class TarjetaInactivaException extends RuntimeException {
    public TarjetaInactivaException(String mensaje) {
        super(mensaje);
    }
}