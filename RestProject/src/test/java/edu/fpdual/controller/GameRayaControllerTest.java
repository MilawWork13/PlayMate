package edu.fpdual.controller;

import edu.fpdual.api.dto.GameRaya;
import edu.fpdual.service.GameRayaService;
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
public class GameRayaControllerTest {

    @Mock
    private GameRayaService gameRayaServiceMock;

    @Mock
    private GameRaya gameRayaMock;

    @Mock
    private List<GameRaya> listGameRayaMock;

    @InjectMocks
    private GameRayaController gameRayaControllerInject;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsertGame_ok() throws SQLException, ClassNotFoundException {

        when(gameRayaServiceMock.insertGame(gameRayaMock)).thenReturn(1);

        Response response = gameRayaControllerInject.insertGame(gameRayaMock);

        MatcherAssert.assertThat(response.getStatus(), Matchers.is(201));

    }

    @Test
    public void testInsertGame_ko() throws SQLException, ClassNotFoundException {

        when(gameRayaServiceMock.insertGame(gameRayaMock)).thenReturn(0);

        Response response = gameRayaControllerInject.insertGame(gameRayaMock);

        MatcherAssert.assertThat(response.getStatus(), Matchers.is(500));

    }

    @Test
    public void testFindByName_ok() throws SQLException, ClassNotFoundException {

        when(gameRayaServiceMock.findGameByName(anyString())).thenReturn(listGameRayaMock);

        Response response = gameRayaControllerInject.findGameByName(anyString());

        MatcherAssert.assertThat(response.getEntity(), Matchers.is(listGameRayaMock));

    }

    @Test
    public void testFindByName_ko() throws SQLException, ClassNotFoundException {

        when(gameRayaServiceMock.findGameByName(anyString())).thenReturn(null);

        Response response = gameRayaControllerInject.findGameByName(anyString());

        MatcherAssert.assertThat(response.getEntity(), Matchers.nullValue());

    }

}
