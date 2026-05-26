package co.edu.unbosque.gestion_financiera.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tarjeta_credito")
public class TarjetaCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTarjeta;

    @Column(name = "numero_tarjeta", nullable = false, unique = true)
    private String numeroTarjeta;

    @Column(name = "fecha_vencimiento", nullable = false)
    private String fechaVencimiento;

    @Column(name = "franquicia", nullable = false)
    private String franquicia;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "cupo_total", nullable = false)
    private Double cupoTotal;

    @Column(name = "cupo_disponible", nullable = false)
    private Double cupoDisponible;

    @Column(name = "cupo_utilizado", nullable = false)
    private Double cupoUtilizado;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    public Integer getIdTarjeta() { return idTarjeta; }
    public void setIdTarjeta(Integer idTarjeta) { this.idTarjeta = idTarjeta; }

    public String getNumeroTarjeta() { return numeroTarjeta; }
    public void setNumeroTarjeta(String numeroTarjeta) { this.numeroTarjeta = numeroTarjeta; }

    public String getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(String fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public String getFranquicia() { return franquicia; }
    public void setFranquicia(String franquicia) { this.franquicia = franquicia; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Double getCupoTotal() { return cupoTotal; }
    public void setCupoTotal(Double cupoTotal) { this.cupoTotal = cupoTotal; }

    public Double getCupoDisponible() { return cupoDisponible; }
    public void setCupoDisponible(Double cupoDisponible) { this.cupoDisponible = cupoDisponible; }

    public Double getCupoUtilizado() { return cupoUtilizado; }
    public void setCupoUtilizado(Double cupoUtilizado) { this.cupoUtilizado = cupoUtilizado; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}