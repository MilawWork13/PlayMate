package edu.fpdual.persistence.manager.impl;

import edu.fpdual.api.dto.GameRaya;
import edu.fpdual.persistence.manager.GameRayaManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Álvaro Terrasa y Artem Korzhan
 * @version : 1.0
 * Clase que contiene todos los métodos con la lógica SQL
 * para realizar operaciones en la BBDD para el objeto GameRaya.
 */
public class GameRayaManagerImpl implements GameRayaManager {

    /**
     * Realiza una operación insert que introduce una partida en la BBDD.
     *
     * @param con      - Connection - Conexión con la BBDD
     * @param gameRaya - GameRaya - Partida a introducir
     * @return int - Resultado de la operación
     */
    public int insert(Connection con, GameRaya gameRaya) {

        String sql = "INSERT INTO gameraya(player1, player2, winner, date) values (?, ?, ?, ?)";

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, gameRaya.getPlayer1());
            statement.setString(2, gameRaya.getPlayer2());
            statement.setString(3, gameRaya.getWinner());
            statement.setString(4, gameRaya.getDate());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Realiza una operación select que devuelve una lista de partidas de
     * cuatro en raya en las que haya participado el jugador con el nombre proporcionado.
     *
     * @param con  - Connection - Conexión con la BBDD.
     * @param name - String - Nombre del jugador
     * @return <u>
     * <li>List<GameRaya> - Lista de partidas encontradas</li>
     * <li>null - Operación fallida</li>
     * </u>
     */
    public List<GameRaya> findByName(Connection con, String name) {
        String sql = "SELECT * FROM gameraya WHERE " +
                "player1 LIKE ? OR " +
                "player2 LIKE ?";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + name + "%");

            ResultSet result = statement.executeQuery();
            List<GameRaya> gameRayas = new ArrayList<>();
            while (result.next()) {
                gameRayas.add(new GameRaya(result));
            }
            return gameRayas;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
