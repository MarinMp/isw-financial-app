package co.edu.unbosque.gestion_financiera.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente_tarjeta")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @Column(name = "numero_identificacion", nullable = false, unique = true)
    private String numeroIdentificacion;

    @Column(name = "correo_electronico", nullable = false)
    private String correoElectronico;

    @Column(name = "nombre_completo", nullable = false)
    private String nombreCompleto;

    public Integer getIdCliente() { return idCliente; }
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }

    public String getNumeroIdentificacion() { return numeroIdentificacion; }
    public void setNumeroIdentificacion(String numeroIdentificacion) { this.numeroIdentificacion = numeroIdentificacion; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
}