package edu.fpdual.web.service;


import edu.fpdual.web.email.VerificationCodeGenerator;
import edu.fpdual.web.service.client.PlayerClient;
import edu.fpdual.web.service.dto.Player;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.client.internal.HttpUrlConnector;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @InjectMocks
    private PlayerService playerServiceMock;

    private PlayerClient playerClient;

    @Mock
    private Invocation.Builder builder;

    private WebTarget webTarget;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testInsertPlayer_ok() {

        playerClient = new PlayerClient();
        Player player = new Player("Pablo", "123", "email@gmail.com");

        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8081/RestProject/api/player");

        when(webTarget.path("/insertPlayer")).thenReturn(webTarget);
        when(webTarget.request(MediaType.TEXT_PLAIN)).thenReturn(builder);
        when(builder.post(Entity.entity(player, MediaType.APPLICATION_JSON))).thenReturn(Response.status(Response.Status.CREATED).build());

        when(playerClient.findPlayer(anyString(), anyString())).thenReturn(null);
        //when(playerClientMock.insertPlayer(player)).thenReturn(Response.status(Response.Status.CREATED).build());
//        when(webTarget.path("/insertPlayer")
//                .request(MediaType.TEXT_PLAIN)
//                .post(Entity.entity(player, MediaType.APPLICATION_JSON))).thenReturn(Response.status(Response.Status.CREATED).build());

        String result = playerServiceMock.insertPlayer(player);

        assertEquals("0", result);

    }

}
