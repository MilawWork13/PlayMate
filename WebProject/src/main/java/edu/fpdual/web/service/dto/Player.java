package edu.fpdual.web.service.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlRootElement
/**
 * @author: Álvaro Terrasa y Artem Korzhan
 * @version: 1.0
 * Objeto que representa un jugador en la BBDD.
 */
public class Player {

    private String nickname;
    private String password;
    private String email;
}
