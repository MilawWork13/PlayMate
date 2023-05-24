package edu.fpdual.web.service;

import edu.fpdual.web.service.client.GameSieteClient;
import edu.fpdual.web.service.dto.GameSiete;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.*;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyString;

@ExtendWith(MockitoExtension.class)
public class GameSieteServiceTest {

    @Mock
    private List<GameSiete> listGameSieteMock;

    @Mock
    private GameSieteClient gameSieteClientMock;

    @Mock
    private GameSieteService gameSieteServiceMock;

    @Mock
    private Date dateMock;

    @InjectMocks
    private GameSieteService gameSieteService;

    @BeforeEach
    public void setUp() {
        this.gameSieteService = new GameSieteService();
    }

    @Test
    public void testRanking_ok() {

        List<GameSiete> dataRetrieved = Arrays.asList(GameSiete.builder()
                .player1("Alvaro")
                .player2("Artem")
                .player3("Gisela")
                .dealer("Juan")
                .player1score(5)
                .player2score(4)
                .player3score(8)
                .dealerScore(4)
                .player1bet(2)
                .player2bet(1.33f)
                .player3bet(1)
                .timestamp(dateMock)
                .build());

        when(gameSieteClientMock.findByName("Alvaro")).thenReturn(dataRetrieved);

        long winCount = gameSieteService.infoGana("Alvaro", dataRetrieved);
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("gameData", dataRetrieved);
        responseMap.put("winCount", winCount);

        Map<String, Object> actualResponseMap = gameSieteService.ranking("Alvaro");

        MatcherAssert.assertThat(responseMap.get("gameData"), Matchers.is(dataRetrieved));
        MatcherAssert.assertThat(responseMap.get("winCount"), Matchers.is(winCount));
        MatcherAssert.assertThat(responseMap, Matchers.is(actualResponseMap));

    }

}
