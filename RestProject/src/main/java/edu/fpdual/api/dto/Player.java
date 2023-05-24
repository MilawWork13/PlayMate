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
 * Objeto que representa a un jugador de la BBDD.
 */
public class Player {
    private String nickname;
    private String password;
    private String email;

    /**
     * Constructor que permite generar un objeto Player
     * a partir del resultado de una consulta SQL.
     * @param resultSet - ResultSet - Fila de la BBDD.
     */
    public Player(ResultSet resultSet) {
        try {
            this.nickname = resultSet.getString("nickname");
            this.password = resultSet.getString("password");
            this.email = resultSet.getString("email");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
