package edu.fpdual.web.service.client;

import edu.fpdual.web.service.dto.GameSiete;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameSieteClientTest {


    private GameSieteClient gameSieteClient;

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

    @Before
    public void setUp() {
        gameSieteClient = new GameSieteClient(clientMock);
        when(clientMock.target(anyString())).thenReturn(webTargetMock);
    }

    @Test
    public void testInsertGame_ok() {

        when(webTargetMock.request()).thenReturn(builderMock);
        when(builderMock.post(Mockito.any())).thenReturn(responseMock);
        when(responseMock.getStatus()).thenReturn(201);

        GameSiete expectedGameSiete = GameSiete.builder()
                .player1("Alvaro")
                .player2("Artem")
                .player3("Gisela")
                .dealer("Juan")
                .player1score(5)
                .player2score(4)
                .player3score(8)
                .dealerScore(6.5f)
                .player1bet(2)
                .player2bet(1.33f)
                .player3bet(1)
                .timestamp(dateMock)
                .build();

        Response actualResponse = gameSieteClient.insertGame(expectedGameSiete);

        MatcherAssert.assertThat(actualResponse, Matchers.is(responseMock));
        MatcherAssert.assertThat(responseMock.getStatus(), Matchers.is(201));

        //        when(builderMock.accept(ArgumentMatchers.any(MediaType.class))).thenReturn(builderMock);
//        when(builderMock.buildPost(ArgumentMatchers.any(Entity.class))).thenReturn(invocationMock);
        //when(invocationMock.invoke(Response.class)).thenReturn(responseMock);

    }

}
