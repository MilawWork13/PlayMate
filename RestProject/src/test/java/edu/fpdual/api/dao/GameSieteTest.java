package edu.fpdual.api.dao;

import edu.fpdual.api.dto.GameSiete;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameSieteTest {

    @Mock
    private ResultSet resultSetMock;

    @Mock
    private java.sql.Date dateMock;


    @Test
    void testGameSieteConstruction_ok() throws SQLException {

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

        GameSiete actualGameSiete = new GameSiete(resultSetMock);

        MatcherAssert.assertThat(actualGameSiete, Matchers.is(expectedGameSiete));

    }

    @Test
    void testGameSieteConstruction_ko() throws SQLException {

        when(resultSetMock.getString("player1")).thenThrow(new SQLException("Mock SQLException"));

        assertThrows(RuntimeException.class, () -> new GameSiete(resultSetMock));

    }

}
