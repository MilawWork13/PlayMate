package edu.fpdual.service;

import edu.fpdual.api.dto.GameSiete;
import edu.fpdual.persistence.connector.MySQLConnector;
import edu.fpdual.persistence.manager.impl.GameSieteManagerImpl;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameSieteServiceTest {

    @Mock
    private MySQLConnector connectorMock;

    @Mock
    private GameSieteManagerImpl gameSieteMangerImplMock;

    @Mock
    private GameSiete gameSieteMock;

    @Mock
    private List<GameSiete> listGameSieteMock;

    @Mock
    private Connection connectionMock;

    @InjectMocks
    private GameSieteService gameSieteServiceInject;

    @Test
    public void testGameSieteServiceConstruction_ok() {

        GameSieteService serviceMock = new GameSieteService(connectorMock, gameSieteMangerImplMock);
        MatcherAssert.assertThat(connectorMock, Matchers.is(serviceMock.getConnector()));
        MatcherAssert.assertThat(gameSieteMangerImplMock, Matchers.is(serviceMock.getManager()));

    }

    @Test
    public void testInsertGame_ok() throws SQLException, ClassNotFoundException {

        when(connectorMock.getMySQLConnection()).thenReturn(connectionMock);
        when(gameSieteMangerImplMock.insert(connectionMock, gameSieteMock)).thenReturn(1);

        int result = gameSieteServiceInject.insertGame(gameSieteMock);

        verify(connectionMock).close();
        MatcherAssert.assertThat(result, Matchers.is(1));

    }

    @Test
    public void testFindByName_ok() throws SQLException, ClassNotFoundException{

        when(connectorMock.getMySQLConnection()).thenReturn(connectionMock);
        when(gameSieteMangerImplMock.findByName(connectionMock, "")).thenReturn(listGameSieteMock);

        List<GameSiete> gameSietes = gameSieteServiceInject.findGameByName("");

        verify(connectionMock).close();
        MatcherAssert.assertThat(gameSietes, Matchers.is(listGameSieteMock));

    }

}
