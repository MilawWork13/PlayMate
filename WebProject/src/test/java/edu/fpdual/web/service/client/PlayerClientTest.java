package edu.fpdual.web.service.client;

import edu.fpdual.web.service.dto.GameSiete;
import edu.fpdual.web.service.dto.Player;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerClientTest {

    private PlayerClient playerClient;

    @Mock
    private WebTarget webTargetMock;

    @Mock
    private Client clientMock;

    @Mock
    private Invocation.Builder builderMock;

    @Mock
    private Response responseMock;

    @Mock
    private Date dateMock;

    @BeforeEach
    public void setUp() {
        playerClient = new PlayerClient(clientMock);
        when(clientMock.target(anyString())).thenReturn(webTargetMock);
        when(webTargetMock.path(Mockito.anyString())).thenReturn(webTargetMock);
    }

    @Test
    public void testInsertPlayer_ok() {

        playerClient = new PlayerClient(clientMock);
        when(webTargetMock.request(Mockito.anyString())).thenReturn(builderMock);
        when(builderMock.post(Mockito.any())).thenReturn(responseMock);
        when(responseMock.getStatus()).thenReturn(201);

        Player expectedPlayer = Player.builder()
                .nickname("Alvaro")
                .password("123")
                .email("alvaro@gmail.com")
                .build();

        Response actualResponse = playerClient.insertPlayer(expectedPlayer);

        MatcherAssert.assertThat(actualResponse, Matchers.is(responseMock));
        MatcherAssert.assertThat(responseMock.getStatus(), Matchers.is(201));

    }

    @Test
    public void testDeletePlayer_ok() {

        playerClient = new PlayerClient(clientMock);
        when(webTargetMock.request(Mockito.anyString())).thenReturn(builderMock);
        when(builderMock.post(Mockito.any())).thenReturn(responseMock);
        when(responseMock.getStatus()).thenReturn(201);

        Player expectedPlayer = Player.builder()
                .nickname("Alvaro")
                .password("123")
                .email("alvaro@gmail.com")
                .build();

        Response actualResponse = playerClient.deletePlayer(expectedPlayer);

        MatcherAssert.assertThat(actualResponse, Matchers.is(responseMock));
        MatcherAssert.assertThat(responseMock.getStatus(), Matchers.is(201));

    }

    @Test
    public void testUpdatePassword_ok() {

        playerClient = new PlayerClient(clientMock);
        when(webTargetMock.request(Mockito.anyString())).thenReturn(builderMock);
        when(builderMock.post(Mockito.any())).thenReturn(responseMock);
        when(responseMock.getStatus()).thenReturn(201);

        Player expectedPlayer = Player.builder()
                .nickname("Alvaro")
                .password("123")
                .email("alvaro@gmail.com")
                .build();

        Response actualResponse = playerClient.updatePassword(expectedPlayer);

        MatcherAssert.assertThat(actualResponse, Matchers.is(responseMock));
        MatcherAssert.assertThat(responseMock.getStatus(), Matchers.is(201));

    }
}