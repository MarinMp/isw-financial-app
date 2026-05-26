package co.edu.unbosque.gestionfinancierafront.view.beans;

import co.edu.unbosque.gestionfinancierafront.model.dtos.TarjetaCreditoDTO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

@Named("tarjetaBean")
@ViewScoped
public class TarjetaCreditoBean implements Serializable {

    private static final String BASE_URL = "http://localhost:8081/api/tarjetas";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<TarjetaCreditoDTO> tarjetas;
    private TarjetaCreditoDTO nuevaTarjeta = new TarjetaCreditoDTO();
    private TarjetaCreditoDTO tarjetaSeleccionada;
    private boolean registroExitoso = false;

    public boolean isRegistroExitoso() { return registroExitoso; }

    @PostConstruct
    public void init() {
        cargarTarjetas();
    }

    public void cargarTarjetas() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            tarjetas = Arrays.asList(
                    objectMapper.readValue(response.body(), TarjetaCreditoDTO[].class));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "No se pudo cargar la lista de tarjetas"));
        }
    }

    public void registrarTarjeta() {
        registroExitoso = false;
        try {
            String json = objectMapper.writeValueAsString(nuevaTarjeta);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 201) {
                registroExitoso = true;
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Éxito", "Tarjeta registrada correctamente"));
                nuevaTarjeta = new TarjetaCreditoDTO();
                cargarTarjetas();
            } else if (response.statusCode() == 400) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Advertencia", response.body()));
            } else if (response.statusCode() == 409) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Duplicado", response.body()));
            } else if (response.statusCode() == 404) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "No encontrado", response.body()));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Error", response.body()));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "No se pudo registrar la tarjeta"));
        }
    }

    public void eliminarTarjeta(Integer idTarjeta) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/" + idTarjeta))
                    .DELETE()
                    .build();
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Éxito", "Tarjeta eliminada correctamente"));
                cargarTarjetas();
            } else if (response.statusCode() == 400) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Advertencia", response.body()));
            } else if (response.statusCode() == 404) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "No encontrado", response.body()));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Error", response.body()));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "No se pudo eliminar la tarjeta"));
        }
    }

    public void seleccionarTarjeta(TarjetaCreditoDTO tarjeta) {
        this.tarjetaSeleccionada = tarjeta;
    }

    public void modificarCupoTotal() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/" + tarjetaSeleccionada.getIdTarjeta()
                            + "/cupo?nuevoCupoTotal=" + tarjetaSeleccionada.getCupoTotal()))
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "Éxito", "Cupo total modificado correctamente"));
                cargarTarjetas();
            } else if (response.statusCode() == 400) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Advertencia", response.body()));
            } else if (response.statusCode() == 404) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "No encontrado", response.body()));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Error", response.body()));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "No se pudo modificar el cupo total"));
        }
    }

    public List<TarjetaCreditoDTO> getTarjetas() { return tarjetas; }
    public void setTarjetas(List<TarjetaCreditoDTO> tarjetas) { this.tarjetas = tarjetas; }

    public TarjetaCreditoDTO getNuevaTarjeta() { return nuevaTarjeta; }
    public void setNuevaTarjeta(TarjetaCreditoDTO nuevaTarjeta) { this.nuevaTarjeta = nuevaTarjeta; }

    public TarjetaCreditoDTO getTarjetaSeleccionada() { return tarjetaSeleccionada; }
    public void setTarjetaSeleccionada(TarjetaCreditoDTO t) { this.tarjetaSeleccionada = t; }
}