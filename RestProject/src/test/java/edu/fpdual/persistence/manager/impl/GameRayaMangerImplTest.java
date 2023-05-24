package edu.fpdual.persistence.manager.impl;

import edu.fpdual.api.dto.GameRaya;
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameRayaMangerImplTest {

    @Mock
    private Connection connectionMock;

    @Mock
    private PreparedStatement preparedStatementMock;

    @Mock
    private ResultSet resultSetMock;

    @Mock
    private GameRaya gameRayaMock;

    @InjectMocks
    private GameRayaManagerImpl gameRayaManagerImplInject;

    @Test
    void testInsert_ok() throws SQLException {

        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeUpdate()).thenReturn(1);

        int result = gameRayaManagerImplInject.insert(connectionMock, gameRayaMock);

        verify(preparedStatementMock).executeUpdate();
        MatcherAssert.assertThat(result, Matchers.is(1));
    }

    @Test
    void testInsert_ko() throws SQLException {

        when(connectionMock.prepareStatement(anyString())).thenThrow(new SQLException(""));

        int result = gameRayaManagerImplInject.insert(connectionMock, gameRayaMock);

        MatcherAssert.assertThat(result, Matchers.is(0));

    }

    @Test
    void testFindByName_ok() throws SQLException{

        GameRaya expectedGameRaya = GameRaya.builder()
                .player1("Alvaro")
                .player2("Artem")
                .winner("Artem")
                .date("24-05-2023")
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

        when(resultSetMock.getString(any())).thenAnswer(new Answer<String>() {

            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {

                if (invocationOnMock.getArgument(0).equals("player1")) {
                    return expectedGameRaya.getPlayer1();
                } else if (invocationOnMock.getArgument(0).equals("player2")) {
                    return expectedGameRaya.getPlayer2();
                } else if (invocationOnMock.getArgument(0).equals("winner")) {
                    return expectedGameRaya.getWinner();
                } else if (invocationOnMock.getArgument(0).equals("date")) {
                    return expectedGameRaya.getDate();
                } else {
                    return null;
                }
            }
        });

        List<GameRaya> gameRayas = gameRayaManagerImplInject.findByName(connectionMock, anyString());

        MatcherAssert.assertThat(gameRayas, Matchers.hasSize(1));
        MatcherAssert.assertThat(gameRayas.iterator().next(), Matchers.is(expectedGameRaya));
    }

    @Test
    public void testFindByName_ko() throws SQLException {

        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);
        when(preparedStatementMock.executeQuery()).thenThrow(new SQLException("Mock SQLException"));

        List<GameRaya> gameRayaError = gameRayaManagerImplInject.findByName(connectionMock, anyString());

        MatcherAssert.assertThat(gameRayaError, Matchers.nullValue());

    }

}
