package co.edu.unbosque.gestion_financiera.exceptions;

public class ClienteYaExisteException extends RuntimeException {

    public ClienteYaExisteException(String mensaje) {
        super(mensaje);
    }
}