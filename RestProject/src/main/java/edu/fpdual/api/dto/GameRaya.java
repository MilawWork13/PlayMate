package edu.fpdual.api.dto;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Data
/**
 * @author: Álvaro Terrasa y Artem Korzhan
 * @version: 1.0
 * Objeto que representa a una partida de cuatro en raya en la BBDD.
 */
public class GameRaya {
    private String player1;
    private String player2;
    private String winner;
    private String date;

    /**
     * Constructor que genera un GameSiete a partir del resultado de
     * una consulta SQL.
     * @param resultSet - ResultSet - Fila de la BBDD.
     */
    public GameRaya(ResultSet resultSet) {
        try {
            this.player1 = resultSet.getString("player1");
            this.player2 = resultSet.getString("player2");
            this.winner = resultSet.getString("winner");
            this.date = resultSet.getString("date");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
