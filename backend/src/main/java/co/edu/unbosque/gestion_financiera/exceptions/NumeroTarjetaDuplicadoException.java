package co.edu.unbosque.gestion_financiera.exceptions;

public class NumeroTarjetaDuplicadoException extends RuntimeException {
    public NumeroTarjetaDuplicadoException(String mensaje) {
        super(mensaje);
    }
}