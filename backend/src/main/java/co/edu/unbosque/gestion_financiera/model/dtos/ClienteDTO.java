package co.edu.unbosque.gestion_financiera.model.dtos;

public class ClienteDTO {

    private String numeroIdentificacion;
    private String correoElectronico;
    private String nombreCompleto;

    public String getNumeroIdentificacion() { return numeroIdentificacion; }
    public void setNumeroIdentificacion(String numeroIdentificacion) { this.numeroIdentificacion = numeroIdentificacion; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
}