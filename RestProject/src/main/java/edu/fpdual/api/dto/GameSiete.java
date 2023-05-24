package edu.fpdual.api.dto;

import lombok.*;

import java.sql.Date;
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
 * Objeto que representa a una partida de siete y medio en la BBDD.
 */
public class GameSiete {

    private String player1;
    private String player2;
    private String player3;
    private String dealer;
    private float player1score;
    private float player2score;
    private float player3score;
    private float dealerScore;
    private float player1bet;
    private float player2bet;
    private float player3bet;
    private Date timestamp;

    /**
     * Constructor que genera un GameSiete a partir del resultado de
     * una consulta SQL.
     * @param resultSet - ResultSet - Fila de la BBDD.
     */
    public GameSiete(ResultSet resultSet) {
        try {
            this.player1 = resultSet.getString("player1");
            this.player2 = resultSet.getString("player2");
            this.player3 = resultSet.getString("player3");
            this.dealer = resultSet.getString("dealer");
            this.player1score = resultSet.getFloat("player1score");
            this.player2score = resultSet.getFloat("player2score");
            this.player3score = resultSet.getFloat("player3score");
            this.dealerScore = resultSet.getFloat("dealerScore");
            this.player1bet = resultSet.getFloat("player1bet");
            this.player2bet = resultSet.getFloat("player2bet");
            this.player3bet = resultSet.getFloat("player3bet");
            this.timestamp = resultSet.getDate("timestamp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
