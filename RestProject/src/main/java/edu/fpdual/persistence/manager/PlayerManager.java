package edu.fpdual.persistence.manager;

import edu.fpdual.api.dto.Player;

import java.sql.Connection;

/**
 * Extensión del Manager, especifando el genérico para Player
 */
public interface PlayerManager extends Manager<Player>{

    int insert(Connection con, Player player);

}
