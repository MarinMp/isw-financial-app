package co.edu.unbosque.gestionfinancierafront.model.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TarjetaCreditoDTO implements Serializable {

    private Integer idTarjeta;
    private String numeroTarjeta;
    private String fechaVencimiento;
    private String franquicia;
    private String estado;
    private Double cupoTotal;
    private Double cupoDisponible;
    private Double cupoUtilizado;
    private ClienteDTO cliente;
    private Integer idCliente;

    public TarjetaCreditoDTO() {}

    public Integer getIdTarjeta() { return idTarjeta; }
    public void setIdTarjeta(Integer idTarjeta) { this.idTarjeta = idTarjeta; }

    public String getNumeroTarjeta() { return numeroTarjeta; }
    public void setNumeroTarjeta(String n) { this.numeroTarjeta = n; }

    public String getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(String f) { this.fechaVencimiento = f; }

    public String getFranquicia() { return franquicia; }
    public void setFranquicia(String f) { this.franquicia = f; }

    public String getEstado() { return estado; }
    public void setEstado(String e) { this.estado = e; }

    public Double getCupoTotal() { return cupoTotal; }
    public void setCupoTotal(Double c) { this.cupoTotal = c; }

    public Double getCupoDisponible() { return cupoDisponible; }
    public void setCupoDisponible(Double c) { this.cupoDisponible = c; }

    public Double getCupoUtilizado() { return cupoUtilizado; }
    public void setCupoUtilizado(Double c) { this.cupoUtilizado = c; }

    public ClienteDTO getCliente() { return cliente; }
    public void setCliente(ClienteDTO cliente) { this.cliente = cliente; }

    public Integer getIdCliente() { return idCliente; }
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }
}