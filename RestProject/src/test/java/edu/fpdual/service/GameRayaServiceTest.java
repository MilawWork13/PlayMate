package edu.fpdual.service;

import edu.fpdual.api.dto.GameRaya;
import edu.fpdual.api.dto.GameSiete;
import edu.fpdual.persistence.connector.MySQLConnector;
import edu.fpdual.persistence.manager.impl.GameRayaManagerImpl;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameRayaServiceTest {

    @Mock
    private MySQLConnector connectorMock;

    @Mock
    private Connection connectionMock;

    @Mock
    private GameRaya gameRayaMock;

    @Mock
    private List<GameRaya> listGameRayaMock;

    @Mock
    private GameRayaManagerImpl gameRayaManagerImplMock;

    @InjectMocks
    private GameRayaService gameRayaServiceInject;

    @Test
    public void testGameRayaServiceConstruction_ok() {

        GameRayaService serviceMock = new GameRayaService(connectorMock, gameRayaManagerImplMock);
        MatcherAssert.assertThat(connectorMock, Matchers.is(serviceMock.getConnector()));
        MatcherAssert.assertThat(gameRayaManagerImplMock, Matchers.is(serviceMock.getManager()));

    }

    @Test
    public void testInsertGame_ok() throws SQLException, ClassNotFoundException {

        when(connectorMock.getMySQLConnection()).thenReturn(connectionMock);
        when(gameRayaManagerImplMock.insert(connectionMock, gameRayaMock)).thenReturn(1);

        int result = gameRayaServiceInject.insertGame(gameRayaMock);

        verify(connectionMock).close();
        MatcherAssert.assertThat(result, Matchers.is(1));

    }

    @Test
    public void testFindByName_ok() throws SQLException, ClassNotFoundException{

        when(connectorMock.getMySQLConnection()).thenReturn(connectionMock);
        when(gameRayaManagerImplMock.findByName(connectionMock, "")).thenReturn(listGameRayaMock);

        List<GameRaya> gameRayas = gameRayaServiceInject.findGameByName("");

        verify(connectionMock).close();
        MatcherAssert.assertThat(gameRayas, Matchers.is(listGameRayaMock));

    }

}
