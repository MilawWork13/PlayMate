package edu.fpdual.web.service.client;

import edu.fpdual.web.service.dto.GameSiete;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

public class GameSieteClient {

    private final WebTarget webTarget;

    public GameSieteClient(Client client) {
        this.webTarget = client.target("http://localhost:8081/RestProject/api/gameSiete");
    }

    public Response insertGame(GameSiete gameSiete) {

        return webTarget.path("/insertGame")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(gameSiete, MediaType.APPLICATION_JSON));

    }

    public List<GameSiete> findByName(String nickname) {
        return webTarget.path("/findByName")
                .queryParam("nickname", nickname)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<GameSiete>>(){});
    }

}
