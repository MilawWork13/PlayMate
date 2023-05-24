package edu.fpdual.controller;

import edu.fpdual.api.dto.Player;
import edu.fpdual.persistence.connector.MySQLConnector;
import edu.fpdual.persistence.manager.impl.PlayerManagerImpl;
import edu.fpdual.service.PlayerService;
import jakarta.ws.rs.core.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerControllerTest {

    @Mock
    private PlayerService playerServiceMock;

    @Mock
    private Player playerMock;

    @InjectMocks
    private PlayerController playerControllerInject;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsertPlayer_ok() throws SQLException, ClassNotFoundException {

        when(playerServiceMock.insertPlayer(playerMock)).thenReturn(1);

        Response response = playerControllerInject.insertPlayer(playerMock);

        MatcherAssert.assertThat(response.getStatus(), Matchers.is(201));

    }

    @Test
    public void testInsertPlayer_ko() throws SQLException, ClassNotFoundException {

        when(playerServiceMock.insertPlayer(playerMock)).thenReturn(0);

        Response response = playerControllerInject.insertPlayer(playerMock);

        MatcherAssert.assertThat(response.getStatus(), Matchers.is(500));

    }

    @Test
    public void testFindPlayer_ok() throws SQLException, ClassNotFoundException {

        when(playerServiceMock.findPlayer(anyString(), anyString())).thenReturn(playerMock);

        Response response = playerControllerInject.findPlayer(anyString(), anyString());

        MatcherAssert.assertThat(response.getEntity(), Matchers.is(playerMock));

    }

    @Test
    public void testFindPlayer_ko() throws SQLException, ClassNotFoundException {

        when(playerServiceMock.findPlayer(anyString(), anyString())).thenReturn(null);

        Response response = playerControllerInject.findPlayer(anyString(), anyString());

        MatcherAssert.assertThat(response.getEntity(), Matchers.nullValue());

    }

    @Test
    public void testFindPlayerByName_ok() throws SQLException, ClassNotFoundException {

        when(playerServiceMock.findPlayerByName(anyString())).thenReturn(playerMock);

        Response response = playerControllerInject.findPlayerByName(anyString());

        MatcherAssert.assertThat(response.getEntity(), Matchers.is(playerMock));

    }

    @Test
    public void testFindPlayerByName_ko() throws SQLException, ClassNotFoundException {

        when(playerServiceMock.findPlayerByName(anyString())).thenReturn(null);

        Response response = playerControllerInject.findPlayerByName(anyString());

        MatcherAssert.assertThat(response.getEntity(), Matchers.nullValue());

    }

    @Test
    public void testUpdatePassword_ok() throws SQLException, ClassNotFoundException {

        when(playerServiceMock.updatePassword(playerMock)).thenReturn(1);

        Response response = playerControllerInject.updatePassword(playerMock);

        MatcherAssert.assertThat(response.getStatus(), Matchers.is(201));

    }

    @Test
    public void testUpdatePassword_ko() throws SQLException, ClassNotFoundException {

        when(playerServiceMock.updatePassword(playerMock)).thenReturn(0);

        Response response = playerControllerInject.updatePassword(playerMock);

        MatcherAssert.assertThat(response.getStatus(), Matchers.is(500));

    }


    @Test
    public void testDeletePlayer_ok() throws SQLException, ClassNotFoundException{

        when(playerServiceMock.deletePlayer(playerMock)).thenReturn(1);

        Response response = playerControllerInject.deletePlayer(playerMock);

        MatcherAssert.assertThat(response.getStatus(), Matchers.is(201));

    }

    @Test
    public void testDeletePlayer_ko() throws SQLException, ClassNotFoundException {

        when(playerServiceMock.deletePlayer(playerMock)).thenReturn(0);

        Response response = playerControllerInject.deletePlayer(playerMock);

        MatcherAssert.assertThat(response.getStatus(), Matchers.is(500));

    }
}

