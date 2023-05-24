package edu.fpdual.persistence.manager.impl;

import edu.fpdual.api.dto.Player;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerManagerImplTest {

    @Mock
    private Connection connectionMock;

    @Mock
    private PreparedStatement preparedStatementMock;

    @Mock
    private ResultSet resultSetMock;

    @Mock
    private Player playerMock;

    @InjectMocks
    private PlayerManagerImpl playerManagerImplInject;

    @Test
    void testInsert_ok() throws SQLException {

        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        int result = playerManagerImplInject.insert(connectionMock, playerMock);

        verify(preparedStatementMock).executeUpdate();

        MatcherAssert.assertThat(result, Matchers.is(1));
    }

    @Test
    void testInsert_ko() throws SQLException {

        when(connectionMock.prepareStatement(anyString())).thenThrow(new SQLException(""));

        int result = playerManagerImplInject.insert(connectionMock,playerMock);

        MatcherAssert.assertThat(result, Matchers.is(0));

    }

    @Test
    void testFindPlayerByName_ok() throws SQLException {

        Player expectedPlayer = Player.builder()
                .nickname("Pepe")
                .password("123")
                .email("paco@gmail.com")
                .build();

        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenAnswer(new Answer<Boolean>() {

            private int counter = 0;

            @Override
            public Boolean answer(InvocationOnMock invocationOnMock) throws Throwable {
                if (counter < 1) {
                    counter++;
                    return true;
                } else {
                    return false;
                }
            }
        });

        when(resultSetMock.getString(anyString())).thenAnswer(new Answer<String>() {

            @Override
            public String answer(InvocationOnMock invocationOnMock) {

                if(invocationOnMock.getArgument(0).equals("nickname")) {
                    return expectedPlayer.getNickname();
                } else if (invocationOnMock.getArgument(0).equals("password")) {
                    return expectedPlayer.getPassword();
                } else if (invocationOnMock.getArgument(0).equals("email")) {
                    return expectedPlayer.getEmail();
                } else {
                    return null;
                }
            }
        });

        Player playerFound = playerManagerImplInject.findPlayerByName(connectionMock, anyString());

        MatcherAssert.assertThat(playerFound, Matchers.is(expectedPlayer));

    }

    @Test
    public void testFindPlayerByName_ko() throws SQLException {

        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeQuery()).thenThrow(new SQLException("Mock SQLException"));
        Player playerError = playerManagerImplInject.findPlayerByName(connectionMock, anyString());
        MatcherAssert.assertThat(playerError, Matchers.nullValue());

    }

    @Test
    void testFindPlayer_ok() throws SQLException {

        Player expectedPlayer = Player.builder()
                .nickname("Pepe")
                .password("123")
                .email("paco@gmail.com")
                .build();

        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
        when(resultSetMock.next()).thenAnswer(new Answer<Boolean>() {

            private int counter = 0;

            @Override
            public Boolean answer(InvocationOnMock invocationOnMock) throws Throwable {
                if (counter < 1) {
                    counter++;
                    return true;
                } else {
                    return false;
                }
            }
        });

        when(resultSetMock.getString(anyString())).thenAnswer(new Answer<String>() {

            @Override
            public String answer(InvocationOnMock invocationOnMock) {

                if(invocationOnMock.getArgument(0).equals("nickname")) {
                    return expectedPlayer.getNickname();
                } else if (invocationOnMock.getArgument(0).equals("password")) {
                    return expectedPlayer.getPassword();
                } else if (invocationOnMock.getArgument(0).equals("email")) {
                    return expectedPlayer.getEmail();
                } else {
                    return null;
                }
            }
        });

        Player playerFound = playerManagerImplInject.findPlayer(connectionMock, "anyString()", "anyString()");

        MatcherAssert.assertThat(playerFound, Matchers.is(expectedPlayer));

    }

    @Test
    public void testFindPlayer_ko() throws SQLException {

        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeQuery()).thenThrow(new SQLException("Mock SQLException"));
        Player playerError = playerManagerImplInject.findPlayer(connectionMock, "anyString()", "anyString()");
        MatcherAssert.assertThat(playerError, Matchers.nullValue());

    }

    @Test
    void testUpdatePassword_ok() throws SQLException {

        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        int result = playerManagerImplInject.updatePassword(connectionMock, playerMock);

        verify(preparedStatementMock).executeUpdate();

        MatcherAssert.assertThat(result, Matchers.is(1));
    }

    @Test
    void testUpdatePassword_ko() throws SQLException {

        when(connectionMock.prepareStatement(anyString())).thenThrow(new SQLException(""));

        int result = playerManagerImplInject.updatePassword(connectionMock,playerMock);

        MatcherAssert.assertThat(result, Matchers.is(0));
    }

    @Test
    void testDeletePlayer_ok() throws SQLException {

        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);
        int result = playerManagerImplInject.delete(connectionMock, playerMock);

        verify(preparedStatementMock).executeUpdate();

        MatcherAssert.assertThat(result, Matchers.is(1));
    }

    @Test
    void testDeletePlayer_ko() throws SQLException {

        when(connectionMock.prepareStatement(anyString())).thenThrow(new SQLException(""));

        int result = playerManagerImplInject.delete(connectionMock,playerMock);

        MatcherAssert.assertThat(result, Matchers.is(0));
    }
}
