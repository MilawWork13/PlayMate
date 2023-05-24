package edu.fpdual.web.service.client;

import edu.fpdual.web.service.dto.GameRaya;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

public class GameRayaClient {

    private final WebTarget webTarget;

    public GameRayaClient() {
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8081/RestProject/api/gameRaya");
    }

    public Response registerGame(GameRaya gameRaya) {

        return webTarget.path("/insertGame")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(gameRaya, MediaType.APPLICATION_JSON));
    }
    public List<GameRaya> findByName(String nickname) {
        return webTarget.path("/findByName")
                .queryParam("nickname", nickname)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<GameRaya>>() {
                });
    }

}
