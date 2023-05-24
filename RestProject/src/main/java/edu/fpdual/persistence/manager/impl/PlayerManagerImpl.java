package edu.fpdual.persistence.manager.impl;

import edu.fpdual.api.dto.Player;
import edu.fpdual.persistence.manager.PlayerManager;

import java.sql.*;

/**
 * @author : Álvaro Terrasa y Artem Korzhan
 * @version : 1.0
 * Clase que contiene todos los métodos con la lógica SQL
 * para realizar operaciones en la BBDD para el objeto Player.
 */
public class PlayerManagerImpl implements PlayerManager {

    /**
     * Realiza una operación insert que registra un jugador en la BBDD.
     *
     * @param con    - Connection - Conexión con la BBDD..
     * @param player - Player - Jugador a registrar
     * @return int - Resultado de la operación (n.º filas introducidas)
     */
    @Override
    public int insert(Connection con, Player player) {
        String sql = "INSERT INTO player(nickname, password, email) VALUES(?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, player.getNickname());
            stmt.setString(2, player.getPassword());
            stmt.setString(3, player.getEmail());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Realiza una operación select que devuelve un jugador de la BBDD cuyo
     * nombre o email coincide con el de los proporcionados en los parámetros
     *
     * @param con      - Connection - Conexión con la BBDD
     * @param nickname - String - Nombre a buscar
     * @param email    - String - Email a buscar
     * @return <u>
     * <li>Player - Jugador encontrado</li>
     * <li>null - Operación fallida</li>
     * </u>
     */
    public Player findPlayer(Connection con, String nickname, String email) {

        String sql = "SELECT * FROM player WHERE nickname = ? OR email = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nickname);
            stmt.setString(2, email);
            ResultSet result = stmt.executeQuery();
            Player playerFound = null;
            while (result.next()) {
                playerFound = new Player(result);
            }
            return playerFound;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Realiza una operación select que devuelve un jugador de la BBDD cuyo
     * nombre o email coincide con el de los proporcionados en los parámetros
     *
     * @param con      - Connection - Conexión con la BBDD
     * @param nickname - String - Nombre a buscar
     * @return <u>
     * <li>Player - Jugador encontrado</li>
     * <li>null - Operación fallida</li>
     * </u>
     */
    public Player findPlayerByName(Connection con, String nickname) {

        String sql = "SELECT * FROM player WHERE nickname = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, nickname);
            ResultSet result = stmt.executeQuery();
            Player playerFound = null;
            while (result.next()) {
                playerFound = new Player(result);
            }
            return playerFound;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Realiza una operación update que modifica la contraseña del jugador.
     * Para ello, en el objeto Player que se recibe, se busca dicho jugador
     * en la BBDD usando su email, y se actualiza la contraseña usando la
     * que tiene también dicho jugador recibido (que es la actualizada).
     *
     * @param con    - Connection - Conexión con la BBDD
     * @param player - Jugador - Jugador a actualizar
     * @return int - Resultado de la operación (n.º filas afectadas)
     */
    public int updatePassword(Connection con, Player player) {

        String sql = "UPDATE player SET password = ? WHERE email LIKE ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, player.getPassword());
            stmt.setString(2, player.getEmail());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
    }

    /**
     * Realiza una operación delete que modifica la contraseña del jugador.
     * Para ello, en el objeto Player que se recibe, se busca dicho jugador
     * en la BBDD usando su nickname, y se valida la operación con su contraseña.
     *
     * @param con    - Connection - Conexión con la BBDD
     * @param player - Jugador - Jugador a actualizar
     * @return int - Resultado de la operación (n.º filas afectadas)
     */
    public int delete(Connection con, Player player) {
        String sql = "DELETE FROM player WHERE nickname LIKE ? AND password LIKE ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, player.getNickname());
            stmt.setString(2, player.getPassword());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
