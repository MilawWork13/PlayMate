package edu.fpdual.api.dao;

import edu.fpdual.api.dto.GameRaya;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GameRayaTest {

    @Mock
    private ResultSet resultSetMock;

    @Test
    void testGameRayaConstruction_ok() throws SQLException {

        GameRaya expectedGameRaya = GameRaya.builder()
                .player1("Alvaro")
                .player2("Artem")
                .winner("Artem")
                .date("24-05-2023")
                .build();

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

        GameRaya actualGameRaya = new GameRaya(resultSetMock);

        MatcherAssert.assertThat(actualGameRaya, Matchers.is(expectedGameRaya));
    }

    @Test
    public void testGameRayaConstruction_ko() throws SQLException {

        when(resultSetMock.getString("player1")).thenThrow(new SQLException("Mock SQLException"));

        assertThrows(RuntimeException.class, () -> new GameRaya(resultSetMock));

    }

}
