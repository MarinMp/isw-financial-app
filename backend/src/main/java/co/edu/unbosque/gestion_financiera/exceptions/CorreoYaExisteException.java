package co.edu.unbosque.gestion_financiera.exceptions;

public class CorreoYaExisteException extends RuntimeException {

    public CorreoYaExisteException(String mensaje) {
        super(mensaje);
    }
}