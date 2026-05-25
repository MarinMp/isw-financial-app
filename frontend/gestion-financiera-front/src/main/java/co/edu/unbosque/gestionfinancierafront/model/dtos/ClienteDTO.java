package co.edu.unbosque.gestionfinancierafront.model.dtos;

import java.io.Serializable;

public class ClienteDTO implements Serializable {

    private Integer idCliente;
    private String numeroIdentificacion;
    private String correoElectronico;
    private String nombreCompleto;

    public ClienteDTO() {}

    public ClienteDTO(Integer idCliente, String numeroIdentificacion, String correoElectronico, String nombreCompleto) {
        this.idCliente = idCliente;
        this.numeroIdentificacion = numeroIdentificacion;
        this.correoElectronico = correoElectronico;
        this.nombreCompleto = nombreCompleto;
    }

    public Integer getIdCliente() { return idCliente; }
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }

    public String getNumeroIdentificacion() { return numeroIdentificacion; }
    public void setNumeroIdentificacion(String n) { this.numeroIdentificacion = n; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String c) { this.correoElectronico = c; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String n) { this.nombreCompleto = n; }
}