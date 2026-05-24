package co.edu.unbosque.gestion_financiera.exceptions;

public class TarjetaNoExisteException extends RuntimeException {
    public TarjetaNoExisteException(String mensaje) {
        super(mensaje);
    }
}