package edu.fpdual.web.service.client;

import edu.fpdual.web.service.dto.Player;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.Setter;

/**
 * @author: Álvaro Terrasa y Artem Korzhan
 * @version: 1.0
 * Clase de cliente para acciónes con Player. Gestiona la conexión con
 * la Api y se encarga de dirigir al controlador de la misma
 * donde se va a escoger una función específica según el Path indicado en esta clase.
 */
public class PlayerClient {

    /**
     * Atributo de WebTarget.
     */
    private final WebTarget webTarget;

    /**
     * Se inicializa el webtarget.
     * Se especifica la URL base para posterior conexión con la Api.
     */
    public PlayerClient(Client client) {
        this.webTarget = client.target("http://localhost:8081/RestProject/api/player");
    }

    /**
     * Elimina el jugador de la BBDD.
     *
     * @param - player - El objeto player que se va a eliminar.
     * @return La respuesta HTTP recibida del servidor.
     */
    public Response deletePlayer(Player player) {
        return webTarget.path("/deletePlayer")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(player, MediaType.APPLICATION_JSON));
    }

    /**
     * Registra un nuevo jugador a la BBDD.
     *
     * @param - gameRaya - El objeto player que se va a insertar en BBDD.
     * @return La respuesta HTTP recibida del servidor.
     */
    public Response insertPlayer(Player player) {

        return webTarget.path("/insertPlayer")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(player, MediaType.APPLICATION_JSON));
    }

    /**
     * Busca un jugador según el nombre o el correo BBDD.
     *
     * @param - nickname - El nombre que se va a buscar en BBDD.
     * @param - email - El correo electrónico que se va a buscar en BBDD.
     * @return El objeto Player que corresponde al nombre de usuario y correo electrónico especificados.
     */
    public Player findPlayer(String nickname, String email) {
        return webTarget.path("/findPlayer")
                .queryParam("nickname", nickname)
                .queryParam("email", email)
                .request(MediaType.APPLICATION_JSON)
                .get(Player.class);
    }

    /**
     * Busca un jugador según el nombre BBDD.
     *
     * @param - nickname - El nombre que se va a buscar en BBDD.
     * @return El objeto Player que corresponde al nombre de usuario especificado.
     */
    public Player findPlayerByName(String nickname) {
        return webTarget.path("/findPlayerByName")
                .queryParam("nickname", nickname)
                .request(MediaType.APPLICATION_JSON)
                .get(Player.class);
    }

    /**
     * Cambia la contraseña de un usuario de la BBDD.
     *
     * @param - player - El usuario que se va a buscar y la nueva contraseña.
     * @return La respuesta HTTP recibida del servidor.
     */
    public Response updatePassword(Player player) {

        return webTarget.path("/updatePassword")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(player, MediaType.APPLICATION_JSON));

    }
}