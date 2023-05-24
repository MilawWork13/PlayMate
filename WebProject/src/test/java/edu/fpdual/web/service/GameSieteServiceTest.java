package edu.fpdual.web.service;

import edu.fpdual.web.service.client.GameSieteClient;
import edu.fpdual.web.service.dto.GameSiete;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @InjectMocks
    private GameSieteService gameSieteServiceInject;

    @Spy
    private Map<String, Object> responseMapMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRanking_ok() {

        String requestBody = "exampleRequest";
        List<GameSiete> dataRetrieved = new ArrayList<>();
        when(gameSieteClientMock.findByName(any())).thenReturn(dataRetrieved);

        responseMapMock.put("gameData", dataRetrieved);
        responseMapMock.put("winCount", anyLong());

        Map<String, Object> actualMap = gameSieteServiceInject.ranking(anyString());

    }

}
