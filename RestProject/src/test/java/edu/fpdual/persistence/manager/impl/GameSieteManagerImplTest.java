package edu.fpdual.persistence.manager.impl;

import edu.fpdual.api.dto.GameSiete;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.sql.*;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GameSieteManagerImplTest {

    @Mock
    private Connection connectionMock;

    @Mock
    private PreparedStatement preparedStatementMock;

    @Mock
    private ResultSet resultSetMock;

    @Mock
    private java.sql.Date dateMock;

    @Mock
    private GameSiete gameSieteMock;

    @InjectMocks
    private GameSieteManagerImpl gameSieteManagerImplInject;



    @Test
    void testInsert_ok() throws SQLException {

        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);

        int result = gameSieteManagerImplInject.insert(connectionMock, gameSieteMock);

        verify(preparedStatementMock).executeUpdate();
        MatcherAssert.assertThat(result, Matchers.is(1));
    }

    @Test
    void testInsert_ko() throws SQLException {

        when(connectionMock.prepareStatement(anyString())).thenThrow(new SQLException(""));

        int result = gameSieteManagerImplInject.insert(connectionMock, gameSieteMock);

        MatcherAssert.assertThat(result, Matchers.is(0));

    }

    @Test
    void testFindByName_ok() throws SQLException {

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

        when(resultSetMock.getFloat(any())).thenAnswer(new Answer<Float>() {

            @Override
            public Float answer(InvocationOnMock invocationOnMock) throws Throwable {

                if (invocationOnMock.getArgument(0).equals("player1score")) {
                    return expectedGameSiete.getPlayer1score();
                } else if (invocationOnMock.getArgument(0).equals("player2score")) {
                    return expectedGameSiete.getPlayer2score();
                } else if (invocationOnMock.getArgument(0).equals("player3score")) {
                    return expectedGameSiete.getPlayer3score();
                } else if (invocationOnMock.getArgument(0).equals("dealerScore")) {
                    return expectedGameSiete.getDealerScore();
                } else if (invocationOnMock.getArgument(0).equals("player1bet")) {
                    return expectedGameSiete.getPlayer1bet();
                } else if (invocationOnMock.getArgument(0).equals("player2bet")) {
                    return expectedGameSiete.getPlayer2bet();
                } else if (invocationOnMock.getArgument(0).equals("player3bet")) {
                    return expectedGameSiete.getPlayer3bet();
                } else {
                    return null;
                }
            }
        });

        when(resultSetMock.getDate("timestamp")).thenReturn(dateMock);

        when(resultSetMock.getString(any())).thenAnswer(new Answer<String>() {

            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {

                if (invocationOnMock.getArgument(0).equals("player1")) {
                    return expectedGameSiete.getPlayer1();
                } else if (invocationOnMock.getArgument(0).equals("player2")) {
                    return expectedGameSiete.getPlayer2();
                } else if (invocationOnMock.getArgument(0).equals("player3")) {
                    return expectedGameSiete.getPlayer3();
                } else if (invocationOnMock.getArgument(0).equals("dealer")) {
                    return expectedGameSiete.getDealer();
                } else {
                    return null;
                }
            }
        });

        List<GameSiete> gameSietes = gameSieteManagerImplInject.findByName(connectionMock, anyString());

        MatcherAssert.assertThat(gameSietes, Matchers.hasSize(1));
        MatcherAssert.assertThat(gameSietes.iterator().next(), Matchers.is(expectedGameSiete));
    }

    @Test
    public void testFindByName_ko() throws SQLException {

        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeQuery()).thenThrow(new SQLException("Mock SQLException"));

        List<GameSiete> gameSieteError = gameSieteManagerImplInject.findByName(connectionMock, anyString());

        MatcherAssert.assertThat(gameSieteError, Matchers.nullValue());

    }

}
