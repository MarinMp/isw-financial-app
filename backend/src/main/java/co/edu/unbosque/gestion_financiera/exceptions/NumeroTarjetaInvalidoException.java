package co.edu.unbosque.gestion_financiera.exceptions;

public class NumeroTarjetaInvalidoException extends RuntimeException {
    public NumeroTarjetaInvalidoException(String mensaje) {
        super(mensaje);
    }
}