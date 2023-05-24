package edu.fpdual.persistence.manager.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import edu.fpdual.api.dto.GameSiete;
import lombok.NoArgsConstructor;

/**
 * @author : Álvaro Terrasa y Artem Korzhan
 * @version : 1.0
 * Clase que contiene todos los métodos con la lógica SQL
 * para realizar operaciones en la BBDD para el objeto GameSiete.
 */
@NoArgsConstructor
public class GameSieteManagerImpl {

    /**
     * Realiza una operación insert que introduce una partida en la BBDD.
     *
     * @param con       - Connection - Conexión con la BBDD
     * @param gameSiete - GameSiete - Partida a introducir
     * @return int - Resultado de la operación (n.º filas introducidas)
     */
    public int insert(Connection con, GameSiete gameSiete) {

        String sql = "INSERT INTO gamesiete(player1, player2, player3, dealer, timestamp, player1score, player2score, player3score, dealerScore, " +
                "player1bet, player2bet, player3bet) values (?, ?, ?, ?, SYSDATE(), ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, gameSiete.getPlayer1());
            stmt.setString(2, gameSiete.getPlayer2());
            stmt.setString(3, gameSiete.getPlayer3());
            stmt.setString(4, gameSiete.getDealer());
            stmt.setFloat(5, gameSiete.getPlayer1score());
            stmt.setFloat(6, gameSiete.getPlayer2score());
            stmt.setFloat(7, gameSiete.getPlayer3score());
            stmt.setFloat(8, gameSiete.getDealerScore());
            stmt.setFloat(9, gameSiete.getPlayer1bet());
            stmt.setFloat(10, gameSiete.getPlayer2bet());
            stmt.setFloat(11, gameSiete.getPlayer3bet());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Realiza una operación select que devuelve una lista de partidas de
     * siete y medio en las que haya participado el jugador con el nombre proporcionado.
     *
     * @param con  - Connection - Conexión con la BBDD.
     * @param name - String - Nombre del jugador
     * @return <u>
     * <li>List<GameSiete> Lista de partidas encontradas</li>
     * <li>null - Operación fallida</li>
     * </u>
     */
    public List<GameSiete> findByName(Connection con, String name) {

        String sql = "SELECT player1, player2, player3, dealer, timestamp, player1score, player2score, player3score, dealerScore, " +
                "player1bet, player2bet, player3bet FROM gamesiete WHERE " +
                "player1 LIKE ? OR " +
                "player2 LIKE ? OR " +
                "player3 LIKE ? OR " +
                "dealer LIKE ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, "%" + name + "%");
            stmt.setString(2, "%" + name + "%");
            stmt.setString(3, "%" + name + "%");
            stmt.setString(4, "%" + name + "%");

            ResultSet result = stmt.executeQuery();
            List<GameSiete> gameSietes = new ArrayList<>();
            while (result.next()) {
                gameSietes.add(new GameSiete(result));
            }
            return gameSietes;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
