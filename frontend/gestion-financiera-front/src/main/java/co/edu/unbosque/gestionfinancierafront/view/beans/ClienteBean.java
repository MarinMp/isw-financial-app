package co.edu.unbosque.gestionfinancierafront.view.beans;
import co.edu.unbosque.gestionfinancierafront.model.dtos.ClienteDTO;
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

@Named("clienteBean")
@ViewScoped
public class ClienteBean implements Serializable {

    private static final String BASE_URL = "http://localhost:8081/api/clientes";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<ClienteDTO> clientes;
    private ClienteDTO nuevoCliente = new ClienteDTO();
    private boolean registroExitoso = false;

    public boolean isRegistroExitoso() { return registroExitoso; }

    @PostConstruct
    public void init() {
        cargarClientes();
    }

    public void cargarClientes() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            clientes = Arrays.asList(
                    objectMapper.readValue(response.body(), ClienteDTO[].class));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "No se pudo cargar la lista de clientes"));
        }
    }

    public void registrarCliente() {
        registroExitoso = false;
        try {
            String json = objectMapper.writeValueAsString(nuevoCliente);
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
                                "Éxito", "Cliente registrado correctamente"));
                nuevoCliente = new ClienteDTO();
                cargarClientes();
            } else if (response.statusCode() == 400) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Advertencia", response.body()));
            } else if (response.statusCode() == 409) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Duplicado", response.body()));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Error", response.body()));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "No se pudo registrar el cliente"));
        }
    }

    public List<ClienteDTO> getClientes() { return clientes; }
    public void setClientes(List<ClienteDTO> clientes) { this.clientes = clientes; }

    public ClienteDTO getNuevoCliente() { return nuevoCliente; }
    public void setNuevoCliente(ClienteDTO nuevoCliente) { this.nuevoCliente = nuevoCliente; }
}