package edu.fpdual.service;

import edu.fpdual.api.dto.GameRaya;
import edu.fpdual.persistence.connector.MySQLConnector;
import edu.fpdual.persistence.manager.impl.GameRayaManagerImpl;
import lombok.Getter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: Álvaro Terrasa y Artem Korzhan
 * @version: 1.0
 * Clase de servicio para el cuatro en raya. Gestiona la conexión con la BBDD
 * y se encarga de ejecutar los métodos SQL de persistencia, haciendo de capa
 * intermedia entre esta y su controlador pertinente.
 */
@Getter
public class GameRayaService {

    /**
     * Clase del conector.
     */
    private MySQLConnector connector;
    /**
     * Clase de implementación de métodos SQL.
     */
    private GameRayaManagerImpl manager;

    /**
     * Constructor que crea el objeto de servicio connector sus atributos
     *
     * @param connector - MySQLConnector - Conector de la BBDD.
     * @param manager   - GameSieteManagerImpl - Clase connector los métodos SQL.
     */
    public GameRayaService(MySQLConnector connector, GameRayaManagerImpl manager) {
        this.connector = connector;
        this.manager = manager;
    }

    /**
     * Realiza una operación insert que introduce una partida en la BBDD.
     *
     * @param gameRaya - GameRaya - Partida a registrar
     * @return int - Resultado de la operación (n.º filas afectadas)
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int insertGame(GameRaya gameRaya) throws SQLException, ClassNotFoundException {

        Connection con = null;

        try {
            con = connector.getMySQLConnection();
            return manager.insert(con, gameRaya);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    /**
     * Realiza una operación select que devuelve una lista de partidas de
     * cuatro en raya en las que haya participado el jugador con el nombre proporcionado.
     *
     * @param nickname - String - Nombre del jugador
     * @return <u>
     * <li>List<GameRaya> Lista de partidas encontradas</li>
     * <li>null - Operación fallida</li>
     * </u>
     */
    public List<GameRaya> findGameByName(String nickname) throws SQLException, ClassNotFoundException {

        Connection con = null;
        try {
            con = connector.getMySQLConnection();
            return manager.findByName(con, nickname);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

}
