package co.edu.unbosque.gestion_financiera.model.dtos;

public class TarjetaCreditoDTO {

    private String numeroTarjeta;
    private String fechaVencimiento;
    private Double cupoTotal;
    private Double cupoDisponible;
    private Integer idCliente;

    public String getNumeroTarjeta() { return numeroTarjeta; }
    public void setNumeroTarjeta(String numeroTarjeta) { this.numeroTarjeta = numeroTarjeta; }

    public String getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(String fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }

    public Double getCupoTotal() { return cupoTotal; }
    public void setCupoTotal(Double cupoTotal) { this.cupoTotal = cupoTotal; }

    public Double getCupoDisponible() { return cupoDisponible; }
    public void setCupoDisponible(Double cupoDisponible) { this.cupoDisponible = cupoDisponible; }

    public Integer getIdCliente() { return idCliente; }
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }
}