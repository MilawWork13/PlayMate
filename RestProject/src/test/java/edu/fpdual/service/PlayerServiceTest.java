package edu.fpdual.service;

import edu.fpdual.api.dto.Player;
import edu.fpdual.persistence.connector.MySQLConnector;
import edu.fpdual.persistence.manager.impl.PlayerManagerImpl;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @Mock
    private MySQLConnector connectorMock;

    @Mock
    private PlayerManagerImpl playerManagerImplMock;

    @Mock
    private Player playerMock;

    @Mock
    private Connection connectionMock;

    @InjectMocks
    private PlayerService playerServiceInject;

    @Test
    public void testPlayerServiceConstruction_ok() {

        PlayerService serviceMock = new PlayerService(connectorMock, playerManagerImplMock);
        MatcherAssert.assertThat(connectorMock, Matchers.is(serviceMock.getConnector()));
        MatcherAssert.assertThat(playerManagerImplMock, Matchers.is(serviceMock.getManager()));

    }

    @Test
    public void testInsertPlayer_ok() throws SQLException, ClassNotFoundException {

        when(connectorMock.getMySQLConnection()).thenReturn(connectionMock);
        when(playerManagerImplMock.insert(connectionMock, playerMock)).thenReturn(1);

        int result = playerServiceInject.insertPlayer(playerMock);

        verify(connectionMock).close();
        MatcherAssert.assertThat(result, Matchers.is(1));

    }

    @Test
    public void testFindPlayerByName_ok() throws SQLException, ClassNotFoundException {

        when(connectorMock.getMySQLConnection()).thenReturn(connectionMock);
        when(playerManagerImplMock.findPlayerByName(connectionMock, "")).thenReturn(playerMock);

        Player playerFound = playerServiceInject.findPlayerByName("");

        verify(connectionMock).close();
        MatcherAssert.assertThat(playerFound, Matchers.is(playerMock));

    }

    @Test
    public void testFindPlayer_ok() throws SQLException, ClassNotFoundException {

        when(connectorMock.getMySQLConnection()).thenReturn(connectionMock);
        when(playerManagerImplMock.findPlayer(connectionMock, "", "")).thenReturn(playerMock);

        Player playerFound = playerServiceInject.findPlayer("", "");

        verify(connectionMock).close();
        MatcherAssert.assertThat(playerFound, Matchers.is(playerMock));

    }

    @Test
    public void testUpdatePassword_ok() throws SQLException, ClassNotFoundException {

        when(connectorMock.getMySQLConnection()).thenReturn(connectionMock);
        when(playerManagerImplMock.updatePassword(connectionMock, playerMock)).thenReturn(1);

        int result = playerServiceInject.updatePassword(playerMock);

        verify(connectionMock).close();
        MatcherAssert.assertThat(result, Matchers.is(1));

    }

    @Test
    public void testDeletePlayer_ok() throws SQLException, ClassNotFoundException{
        when(connectorMock.getMySQLConnection()).thenReturn(connectionMock);
        when(playerManagerImplMock.delete(connectionMock, playerMock)).thenReturn(1);

        int result = playerServiceInject.deletePlayer(playerMock);

        verify(connectionMock).close();
        MatcherAssert.assertThat(result, Matchers.is(1));
    }
}
