package edu.fpdual.controller;

import edu.fpdual.api.dto.GameSiete;
import edu.fpdual.service.GameSieteService;
import jakarta.ws.rs.core.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameSieteControllerTest {

    @Mock
    private GameSieteService gameSieteServiceMock;

    @Mock
    private GameSiete gameSieteMock;

    @Mock
    private List<GameSiete> gameSieteListMock;

    @InjectMocks
    private GameSieteController gameSieteControllerInject;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsertGame_ok() throws SQLException, ClassNotFoundException {

        when(gameSieteServiceMock.insertGame(gameSieteMock)).thenReturn(1);

        Response response = gameSieteControllerInject.insertGame(gameSieteMock);

        MatcherAssert.assertThat(response.getStatus(), Matchers.is(201));

    }

    @Test
    public void testInsertGame_ko() throws SQLException, ClassNotFoundException {

        when(gameSieteServiceMock.insertGame(gameSieteMock)).thenReturn(0);

        Response response = gameSieteControllerInject.insertGame(gameSieteMock);

        MatcherAssert.assertThat(response.getStatus(), Matchers.is(500));

    }

    @Test
    public void testFindByName_ok() throws SQLException, ClassNotFoundException {

        when(gameSieteServiceMock.findGameByName(anyString())).thenReturn(gameSieteListMock);

        Response response = gameSieteControllerInject.findByName(anyString());

        MatcherAssert.assertThat(response.getEntity(), Matchers.is(gameSieteListMock));

    }

    @Test
    public void testFindByName_ko() throws SQLException, ClassNotFoundException {

        when(gameSieteServiceMock.findGameByName(anyString())).thenReturn(null);

        Response response = gameSieteControllerInject.findByName(anyString());

        MatcherAssert.assertThat(response.getEntity(), Matchers.nullValue());

    }

}
