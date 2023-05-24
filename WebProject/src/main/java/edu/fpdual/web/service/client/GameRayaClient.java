package edu.fpdual.web.service.client;

import edu.fpdual.web.service.dto.GameRaya;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

/**
 * @author: Álvaro Terrasa y Artem Korzhan
 * @version: 1.0
 * Clase de cliente para el cuatro en raya. Gestiona la conexión con
 * la Api y se encarga de dirigir al controlador de la misma
 * donde se va a escoger una función específica según el Path indicado en esta clase.
 */
public class GameRayaClient {

    /**
     * Atributo de WebTarget.
     */
    private final WebTarget webTarget;

    /**
     * Se inicializa el webtarget.
     * Se especifica la URL base para posterior conexión con la Api.
     */
    public GameRayaClient() {
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8081/RestProject/api/gameRaya");
    }

    /**
     * Registra un nuevo juego de Cuatro en raya en el servidor.
     *
     * @param - gameRaya - El objeto GameRaya que se va a registrar.
     * @return La respuesta HTTP recibida del servidor.
     */
    public Response registerGame(GameRaya gameRaya) {
        return webTarget.path("/insertGame")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(gameRaya, MediaType.APPLICATION_JSON));
    }

    /**
     * Busca un jugador según su nombre(nickname) en la BBDD.
     *
     * @param - nickname - El nombre de usuario por el cual se realizará la búsqueda.
     * @return Una lista de objetos GameRaya que coinciden con el nombre de usuario especificado.
     */
    public List<GameRaya> findByName(String nickname) {
        return webTarget.path("/findByName")
                .queryParam("nickname", nickname)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<GameRaya>>() {
                });
    }

}
