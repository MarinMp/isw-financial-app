package co.edu.unbosque.gestion_financiera.exceptions;

public class ClienteNoExisteException extends RuntimeException {
    public ClienteNoExisteException(String mensaje) {
        super(mensaje);
    }
}