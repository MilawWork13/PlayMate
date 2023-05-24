package edu.fpdual.web.service.client;

import edu.fpdual.web.service.dto.Player;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerClientTest {

    @Mock
    private Client client;
    @Mock
    private WebTarget webTarget;

    @Mock
    private Invocation.Builder builder;

    @Mock
    private Response response;

    private PlayerClient playerClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        playerClient = new PlayerClient();
    }

    @Test
    void testInsertPlayer_ok() {

        Player player = new Player();

        when(client.target("http://localhost:8081/RestProject/api/player")).thenReturn(webTarget);
        when(webTarget.path("/insertPlayer")).thenReturn(webTarget);
        when(webTarget.request(MediaType.TEXT_PLAIN)).thenReturn(builder);
        when(builder.post(Entity.entity(player, MediaType.APPLICATION_JSON))).thenReturn(response);

        // Invoke the method under test
        Response actualResponse = playerClient.insertPlayer(player);

        // Verify the interactions
        verify(client).target("http://localhost:8081/RestProject/api/player");
        verify(webTarget).path("/insertPlayer");
        verify(webTarget).request(MediaType.TEXT_PLAIN);
        verify(builder).post(Entity.entity(player, MediaType.APPLICATION_JSON));

        // Verify the returned response
        assertEquals(response, actualResponse);
    }
}



